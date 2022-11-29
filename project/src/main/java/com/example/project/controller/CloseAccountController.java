package com.example.project.controller;

import com.example.project.model.Account;
import com.example.project.service.AccountService;
import com.example.project.service.DormantAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/all")
    public String listAllAccountsWithoutCloseStatus(Model model){
        List<Account> accounts = accountService.listAllAccountsWithoutCloseStatus();
        model.addAttribute("accounts", accounts);
        return "close-account";
    }

    @RequestMapping("/getInterest/{accountNumber}")
    @ResponseBody
    public Map<String,Object> listDetailByNumber(@PathVariable("accountNumber") Long accoNumber){
        System.out.println(accoNumber);
        Account account = accountService.selectAccountByAccountNumber(accoNumber);
        double interestRate = account.getAccountType().getInterestRate();
        BigDecimal interest = accountService.calculateInterest(account);
        Map<String,Object> map = new HashMap<>();
        map.put("interest", interest);
        map.put("interestRate", interestRate);

        return map;
    }

    @PostMapping("/confirmClose")
    public String confirmClose(String accountNumber, String closeConfirm, String interest){
        Long accountNo = Long.valueOf(accountNumber);
        Account account = accountService.selectAccountByAccountNumber(accountNo);
        if(closeConfirm.equalsIgnoreCase("closed")){
            account.setStatus("CLOSED");
            accountService.saveAccount(account);
            BigDecimal bigDecimalInterest = new BigDecimal(interest);
            BigDecimal totalWithdraw = account.getBalance().add(bigDecimalInterest);

            // withdraw totalWithdraw method

            dormantAccountService.moveClosedAccountInfoIntoDormant(account);
        }
        return "redirect:/closeAccount/all";
    }

    @RequestMapping("/renewAccount/{accountNumber}")
    @ResponseBody
    public Map<String, Object> renewDetails(@PathVariable("accountNumber") Long accountNumber){
        Account account = accountService.selectAccountByAccountNumber(accountNumber);
        LocalDate registerDate = account.getRegisterTime().toLocalDateTime().toLocalDate();
        LocalDate expiryTime = accountService.calculateExpiryTime(registerDate, account);
        LocalDate renewTime = accountService.calculateRenewTime(expiryTime, account);
        Map<String, Object> map = new HashMap<>();
        map.put("registerDate", registerDate);
        map.put("expiryTime", expiryTime);
        map.put("renewTime", renewTime);
        LocalDate now = LocalDate.now();
        if(now.isAfter(expiryTime)){
            map.put("renewStatus", false);
        }else{
            map.put("renewStatus", true);
        }
        return map;
    }

    @PostMapping("/confirmRenew")
    public String confirmRenew(String accountNumber, String renewConfirm){
        Long accountNo = Long.valueOf(accountNumber);
        Account account = accountService.selectAccountByAccountNumber(accountNo);
        if(renewConfirm.equalsIgnoreCase("agree")){
            account.setNumberOfRenew(account.getNumberOfRenew() + 1);
            accountService.saveAccount(account);
        }
        return "redirect:/closeAccount/all";
    }

}
