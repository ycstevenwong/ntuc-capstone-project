package com.example.project.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.model.Account;
import com.example.project.model.Customer;
import com.example.project.repository.AccountRepo;
import com.example.project.repository.CustomerRepo;

@Controller
public class AccountController {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AccountRepo accountRepo;

    @GetMapping("/accounts")
    public String searchAccounts(
        Model model,
        @RequestParam("name") Optional<String> nameParam,
        @RequestParam("nric") Optional<String> nricParam,
        @RequestParam("birthDate") @DateTimeFormat(pattern="rrrr-mm-dd") Optional<String> birthDateParam
    ) {
        // TODO: Replace with DateFormatter
        String name = nameParam.orElse(null);
        String nric = nricParam.orElse(null);
        LocalDate birthDate;
        try {
            birthDate = birthDateParam.map(param -> LocalDate.parse(param)).orElse(null);
        } catch (Exception e) {
            birthDate = null;
        }
        Optional<Customer> customer = customerRepo.findCustomer(
            name,
            nric,
            birthDate
        );

        Optional<String> redirect = customer.map(c -> "redirect:/accounts/" + c.getId() + "?page=1");
        if (redirect.isPresent()) {
            return redirect.get();
        } else {
            model.addAttribute("name", name);
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
            model.addAttribute("customer", c);
            model.addAttribute("name", c.getName());
            model.addAttribute("secretNric", c.getNric().substring(5, 9)); // Last 4 characters
            model.addAttribute("birthDate", c.getBirthDate());
        });

        accounts.ifPresent(as -> model.addAttribute("accounts", as));

        model.addAttribute("page", page);
        model.addAttribute("pageLimit", pageLimit);
        model.addAttribute("lastPage", accounts.map(as -> as.getTotalPages()).orElse(null));

        return "accounts";
    }
}