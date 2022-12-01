package com.example.project.service;

import com.example.project.model.Account;
import com.example.project.model.Customer;
import com.example.project.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface CustomerService {
    Customer createCustomer(Customer c);

    Account createAccount(Account a);

    List<Customer> findAllCustomers();

    Page<Customer> findPaginatedCustomers(int pageNo, int pageSize);

    Optional<Customer> findCustomerById(Long id);

}
