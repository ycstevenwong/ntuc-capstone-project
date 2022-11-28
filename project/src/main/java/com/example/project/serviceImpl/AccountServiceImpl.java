package com.example.project.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.model.Account;
import com.example.project.repository.AccountRepo;
import com.example.project.service.AccountService;
@Service
@Transactional
public class AccountServiceImpl implements AccountService{

    @Resource
    AccountRepo accountRepo;

    @Override
    public List<Account> findByAccountType(Long id) {
        
        return accountRepo.findByAccountType(id);
    }
    
}
