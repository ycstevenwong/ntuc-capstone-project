package com.example.project.serviceImpl;

import com.example.project.model.Account;
import com.example.project.model.Customer;
import com.example.project.repository.AccountRepo;
import com.example.project.repository.CustomerRepo;
import com.example.project.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Resource
    CustomerRepo cRepo;

    @Resource
    AccountRepo aRepo;

    @Override
    public Customer createCustomer(Customer c) {
        return cRepo.saveAndFlush(c);
    }

    @Override
    public Account createAccount(Account a) {
        return aRepo.saveAndFlush(a);
    }


}
