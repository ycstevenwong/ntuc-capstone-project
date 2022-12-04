package com.example.project.controller;

import com.example.project.dto.AccountDto;
import com.example.project.dto.CustomerDto;
import com.example.project.model.Account;
import com.example.project.model.AccountType;
import com.example.project.model.Customer;
import com.example.project.repository.AccountTypeRepo;
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
            Account newAccount = Account.builder().accountType(aType.get()).balance(balance).status("OPEN").customer(newCustomer).build();
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

    @GetMapping("/view/page/{pageNo}")
    public String viewAllPaginatedCustomers(@PathVariable(value = "pageNo") int pageNo, Model model,Object message){
        // default page size
        int pageSize = 5;
        isFirstLoad = false;
        Page<Customer> paginatedCustomers = cService.findPaginatedCustomers(pageNo, pageSize);
        List<Customer> allCustomers = paginatedCustomers.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", paginatedCustomers.getTotalPages());
        model.addAttribute("totalItems", paginatedCustomers.getTotalElements());
        model.addAttribute("allCustomers", allCustomers);
        model.addAttribute("customer", new CustomerDto());
        model.addAttribute("message", message);
        model.addAttribute("isFirstLoad",isFirstLoad);
        return "view_all_customers";
    }

    @GetMapping("/view")
    public String viewHomePage(Model model, HttpServletRequest request) {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        Object message = null; if(inputFlashMap!=null) message = inputFlashMap.get("message");
        return viewAllPaginatedCustomers(1, model,message);
    }
}
