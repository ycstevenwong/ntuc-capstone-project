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

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
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

    @GetMapping("/create")
    public ModelAndView prepareCustomerCreation() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("customer", new CustomerDto());
        mav.addObject("account", new AccountDto());
        return mav;
    }

    @PostMapping("/create")
    public ModelAndView createNewCustomer(@ModelAttribute("customer") @Valid CustomerDto customer, BindingResult result, @ModelAttribute("account") AccountDto account) {
        // validation
        if (result.hasErrors()) return new ModelAndView("register");
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
            Account newAccount = Account.builder().accountType(aType.get()).balance(balance).status("Available").customer(newCustomer).build();
            cService.createAccount(newAccount);
        }
        return new ModelAndView("customer-register-success");
    }

//    @GetMapping("/edit/{id}")
//    public ModelAndView editCustomer(@PathVariable Long id) {
////        ModelAndView mav = new ModelAndView("staff_application_edit");
////        Application application = appService.findApplication(id);
////        mav.addObject("application",application);
////        return mav;
//    }


    @GetMapping("/view/page/{pageNo}")
    public String viewAllPaginatedCustomers(@PathVariable(value = "pageNo") int pageNo, Model model){
        int pageSize = 5;
        Page<Customer> paginatedCustomers = cService.findPaginatedCustomers(pageNo, pageSize);
        List<Customer> allCustomers = paginatedCustomers.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", paginatedCustomers.getTotalPages());
        model.addAttribute("totalItems", paginatedCustomers.getTotalElements());
        model.addAttribute("allCustomers", allCustomers);
        return "view_all_customers";
    }

    @GetMapping("/view")
    public String viewHomePage(Model model) {
        return viewAllPaginatedCustomers(1, model);
    }
}
