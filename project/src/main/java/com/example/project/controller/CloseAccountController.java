package com.example.project.controller;

import com.example.project.model.Account;
import com.example.project.model.Transaction;
import com.example.project.repository.TransactionRepo;
import com.example.project.service.AccountService;
import com.example.project.service.DormantAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/closeAccount")
public class CloseAccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private DormantAccountService dormantAccountService;
    @Autowired
    TransactionRepo transactionRepo;

    @RequestMapping("/all")
    public String listAllAccountsWithoutCloseStatus(Model model){
        List<Account> accounts = accountService.listAllAccountsWithoutCloseStatus();
        model.addAttribute("accounts", accounts);
        return "close-account";
    }

    @RequestMapping("/getInterest/{accountNumber}")
    @ResponseBody
    public Map<String,Object> listDetailByNumber(@PathVariable("accountNumber") Long accoNumber){
        Account account = accountService.selectAccountByAccountNumber(accoNumber);
        BigDecimal interest = accountService.calculateInterest(account);
        Map<String,Object> map = new HashMap<>();
        map.put("interest", interest);

        return map;
    }

    @PostMapping("/confirmClose")
    public String confirmClose(String accountNumber, String interest, RedirectAttributes redirectAttributes){
        Long accountNo = Long.valueOf(accountNumber);
        Account account = accountService.selectAccountByAccountNumber(accountNo);
        account.setStatus("CLOSED");

        //deposit the interest into transaction history
        BigDecimal bigDecimalInterest = new BigDecimal(interest);
        Transaction transactionRecord1 = new Transaction();
        transactionRecord1.setAmount(bigDecimalInterest);
        transactionRecord1.setAccount(account);
        transactionRecord1.setDescription("DEPOSIT");
        transactionRepo.save(transactionRecord1);

        BigDecimal totalWithdraw = account.getBalance().add(bigDecimalInterest);
        String successMsg = "Close account successfully! We will withdraw " + totalWithdraw + "SGD";
        redirectAttributes.addFlashAttribute("successMsg", successMsg);

        // withdraw total amount with interest
        Transaction transactionRecord2 = new Transaction();
        transactionRecord2.setAmount(totalWithdraw.negate());
        transactionRecord2.setAccount(account);
        transactionRecord2.setDescription("WITHDRAW");
        transactionRepo.save(transactionRecord2);

        account.setBalance(new BigDecimal("0"));
        accountService.saveAccount(account);
        dormantAccountService.moveClosedAccountInfoIntoDormant(account);
        return "redirect:/closeAccount/all";
    }

    @RequestMapping("/renewAccount/{accountNumber}")
    @ResponseBody
    public Map<String, Object> renewDetails(@PathVariable("accountNumber") Long accountNumber){
        Account account = accountService.selectAccountByAccountNumber(accountNumber);
        Timestamp renewTime = accountService.calculateRenewTime(account);
        LocalDate renewDate = renewTime.toLocalDateTime().toLocalDate();
        BigDecimal interest = accountService.calculateInterest(account);
        BigDecimal afterRenewBalance = account.getBalance().add(interest);

        Map<String, Object> map = new HashMap<>();
        map.put("renewTime", renewDate);
        map.put("afterRenewBalance", afterRenewBalance);
        return map;
    }

    @PostMapping("/confirmRenew")
    public String confirmRenew(String accountNumber, RedirectAttributes redirectAttributes){
        Long accountNo = Long.valueOf(accountNumber);
        Account account = accountService.selectAccountByAccountNumber(accountNo);
        BigDecimal interest = accountService.calculateInterest(account);
        //deposit the interest
        Transaction transactionRecord = new Transaction();
        transactionRecord.setAmount(interest);
        transactionRecord.setAccount(account);
        transactionRecord.setDescription("DEPOSIT");
        transactionRepo.save(transactionRecord);
        BigDecimal renewBalance = account.getBalance().add(interest);
        account.setBalance(renewBalance);
        account.setExpiryTime(accountService.calculateRenewTime(account));
        accountService.saveAccount(account);
        String successMsg = "Renew account successfully! Your initial balance will be " + renewBalance + "SGD";
        redirectAttributes.addFlashAttribute("successMsg", successMsg);

        return "redirect:/closeAccount/all";
    }

}
