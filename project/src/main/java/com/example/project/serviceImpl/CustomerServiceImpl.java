package com.example.project.serviceImpl;

import com.example.project.model.Account;
import com.example.project.model.Customer;
import com.example.project.repository.AccountRepo;
import com.example.project.repository.CustomerRepo;
import com.example.project.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Customer> findAllCustomers() {
        return cRepo.findAll();
    }

    @Override
    public Page<Customer> findPaginatedCustomers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return cRepo.findAll(pageable);
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) {
       return cRepo.findById(id);
    }


}
