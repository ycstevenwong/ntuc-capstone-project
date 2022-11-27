package com.example.project.service;

import com.example.project.model.Account;
import com.example.project.model.Customer;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface CustomerService {
    Customer createCustomer(Customer c);

    Account createAccount(Account a);
}
