package com.example.project.controller;

import com.example.project.dto.AccountDto;
import com.example.project.dto.CustomerDto;
import com.example.project.model.Account;
import com.example.project.model.AccountType;
import com.example.project.model.Customer;
import com.example.project.repository.AccountTypeRepo;
import com.example.project.service.AccountService;
import com.example.project.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    private CustomerService cService;

    @Resource
    private AccountService accountService;

    @Resource
    private AccountTypeRepo aRepo;

    private Boolean isFirstLoad = true;

    @GetMapping("/create")
    public ModelAndView prepareCustomerCreation() {
        ModelAndView mav = new ModelAndView("register");
        List<AccountType> allAccountTypes = aRepo.findAll();
        mav.addObject("accountTypes",allAccountTypes);
        mav.addObject("customer", new CustomerDto());
        mav.addObject("account", new AccountDto());
        return mav;
    }

    @PostMapping("/create")
    public String createNewCustomer(@ModelAttribute("customer") @Valid CustomerDto customer, BindingResult result, @ModelAttribute("account") AccountDto account, RedirectAttributes redirectAttrs) {
        // validation
        if (result.hasErrors()) return "register";
        // Register Customer
        Customer c = Customer.builder().nric(customer.getNric()).name(customer.getName()).age(customer.getAge())
                .gender(customer.getGender()).emailAddress(customer.getEmailAddress()).birthDate(customer.getBirthDate())
                .phone(customer.getPhone()).address(customer.getAddress()).nomineeNric(customer.getNomineeNric())
                .nomineeName(customer.getNomineeName()).build();
        Customer newCustomer = cService.createCustomer(c);
        // If the customer would like to open the account now
        Optional<AccountType> aType = aRepo.findByName(account.getType());
        if (aType.isPresent()) {
            // set defualt balance if the user does not input the deposit
            BigDecimal balance = account.getInitialBalance().isBlank() ? new BigDecimal("0.00") : new BigDecimal(account.getInitialBalance());
            Account newAccount = Account.builder().accountType(aType.get()).balance(balance).status("OPEN").customer(newCustomer).registerTime(Timestamp.valueOf(LocalDateTime.now())).build();

            Timestamp expiryTime = accountService.calculateExpiryTime(newAccount.getRegisterTime(), newAccount);
            newAccount.setExpiryTime(expiryTime);
            cService.createAccount(newAccount);

        }
        redirectAttrs.addFlashAttribute("message", "Account created!");
        return ("redirect:/customer/view");
    }

    @PostMapping("/edit")
    public ModelAndView editCustomer(@ModelAttribute("customer")CustomerDto customer) {
        Customer c = Customer.builder().nric(customer.getNric()).name(customer.getName()).age(customer.getAge())
                .gender(customer.getGender()).emailAddress(customer.getEmailAddress()).birthDate(customer.getBirthDate())
                .phone(customer.getPhone()).address(customer.getAddress()).nomineeNric(customer.getNomineeNric())
                .nomineeName(customer.getNomineeName()).id(customer.getId()).build();
        Customer newCustomer = cService.createCustomer(c);
        return new ModelAndView("redirect:/customer/view");
    }

    @GetMapping("/view")
    public String viewAllPaginatedCustomers(
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "pageLimit", defaultValue = "10") Integer pageLimit,
        Model model,
        Object message
    ) {
        isFirstLoad = false;

        Page<Customer> paginatedCustomers = cService.findPaginatedCustomers(page, pageLimit);
        List<Customer> allCustomers = paginatedCustomers.getContent();

        model.addAttribute("message", message);
        model.addAttribute("isFirstLoad",isFirstLoad);
        model.addAttribute("allCustomers", allCustomers);
        model.addAttribute("customer", new CustomerDto());

        // Pagination for table
        int numPages = paginatedCustomers.getTotalPages();
		model.addAttribute("page", page);
		model.addAttribute("pageLimit", pageLimit);
		model.addAttribute("lastPage", numPages > 0 ? numPages : 1);
		model.addAttribute("BASE_URL", "customer/view");

        return "view_all_customers";
    }
}
