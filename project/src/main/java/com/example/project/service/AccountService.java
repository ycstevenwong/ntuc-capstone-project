package com.example.project.service;

import java.util.List;

import com.example.project.model.Account;

public interface AccountService {
    List<Account> findByAccountType(Long id);
}
