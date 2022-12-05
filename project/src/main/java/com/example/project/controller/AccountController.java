package com.example.project.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.project.dto.AccountDto;
import com.example.project.model.AccountType;
import com.example.project.model.Transaction;
import com.example.project.repository.AccountTypeRepo;
import com.example.project.repository.TransactionRepo;
import com.example.project.service.AccountService;
import com.example.project.service.CustomerService;
import com.example.project.service.DormantAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.project.model.Account;
import com.example.project.model.Customer;
import com.example.project.repository.AccountRepo;
import com.example.project.repository.CustomerRepo;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

@Controller
public class AccountController {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Resource
    private CustomerService cService;

    @Resource
    private AccountTypeRepo aRepo;

    @Resource
    private AccountService accountService;

    @Autowired
    private DormantAccountService dormantAccountService;

    @Autowired
    TransactionRepo transactionRepo;




    @GetMapping("/accounts")
    public String searchAccounts(
        Model model,
        @RequestParam("name") Optional<String> nameParam,
        @RequestParam("nric") Optional<String> nricParam,
        @RequestParam("birthDate") @DateTimeFormat(pattern="rrrr-mm-dd") Optional<String> birthDateParam
    ) {
        // TODO: Replace with DateFormatter
        String nric = nricParam.orElse(null);
        LocalDate birthDate;
        try {
            birthDate = birthDateParam.map(param -> LocalDate.parse(param)).orElse(null);
        } catch (Exception e) {
            birthDate = null;
        }
        Optional<Customer> customer = customerRepo.findCustomer(
            nric,
            birthDate
        );

        Optional<String> redirect = customer.map(c -> "redirect:/accounts/" + c.getId() + "?page=1");
        if (redirect.isPresent()) {
            return redirect.get();
        } else {
            model.addAttribute("secretNric", nric);
            model.addAttribute("birthDate", birthDate);
            return "accounts";
        }
    }

    @GetMapping("/accounts/{id}")
    public String showCustomerAccounts(
        Model model,
        @PathVariable("id") Long customerId,
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "pageLimit", defaultValue = "10") Integer pageLimit
    ) {
        Pageable paging = PageRequest.of(page - 1, pageLimit, Sort.by("accountNumber"));

        Optional<Customer> customer = customerRepo.findById(customerId);
        Optional<Page<Account>> accounts = customer.map(c -> accountRepo.findAllCustomerAccountsWithPagination(c, paging));

        customer.ifPresent(c -> {
            String nric = c.getNric();
            model.addAttribute("customer", c);
            model.addAttribute("name", c.getName());
            model.addAttribute("secretNric", nric.substring(nric.length() - 4)); // Last 4 characters
            model.addAttribute("birthDate", c.getBirthDate());
        });

        accounts.ifPresent(as -> model.addAttribute("accounts", as));

        // Pagination for table
		model.addAttribute("page", page);
		model.addAttribute("pageLimit", pageLimit);
		model.addAttribute("lastPage", accounts.map(as -> as.getTotalPages()).orElse(1));
		model.addAttribute("BASE_URL", "accounts");

		// For newly added accounts
        List<AccountType> allAccountTypes = aRepo.findAll();
        model.addAttribute("newAccount",new AccountDto());
        model.addAttribute("accountTypes",allAccountTypes);
        return "accounts";
    }

    @PostMapping("accounts/add/{id}")
    public ModelAndView addAccount(@PathVariable("id") Long customerId, AccountDto account){
        Optional<Customer> customer= customerRepo.findById(customerId);
        AccountType accountType = aRepo.findByName(account.getType()).orElse(null);
        if(accountType!=null) {
           customer.ifPresent(c -> {
               Account newAccount = Account.builder().accountType(accountType).balance(new BigDecimal(account.getInitialBalance()))
                       .status("OPEN").registerTime(Timestamp.valueOf(LocalDateTime.now())).customer(c).build();
               Timestamp expiryTime = accountService.calculateExpiryTime(newAccount.getRegisterTime(), newAccount);
               newAccount.setExpiryTime(expiryTime);
               
               cService.createAccount(newAccount);
           });
       }
        return new ModelAndView("redirect:/accounts/"+ customerId);
    }

    @RequestMapping("/accounts/getInterest/{accountNumber}")
    @ResponseBody
    public Map<String,Object> listDetailByNumber(@PathVariable("accountNumber") Long accoNumber){
        Account account = accountService.selectAccountByAccountNumber(accoNumber);
        BigDecimal interest = accountService.calculateInterest(account);
        Map<String,Object> map = new HashMap<>();
        map.put("interest", interest);

        return map;
    }

    @PostMapping("/accounts/confirmClose/{customerId}")
    public String confirmClose(@PathVariable("customerId") Long customerId, String accountNumber, String interest, RedirectAttributes redirectAttributes){
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
        return "redirect:/accounts/"+ customerId;
    }

    @RequestMapping("/accounts/renewAccount/{accountNumber}")
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

    @PostMapping("accounts/confirmRenew/{customerId}")
    public String confirmRenew(@PathVariable("customerId") Long customerId, String accountNumber, RedirectAttributes redirectAttributes){
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

        return "redirect:/accounts/"+ customerId;
    }




}
