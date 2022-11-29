package com.example.project.service;


/**
 * @Author Fusheng Tan
 * @Version 1.0
 */

import java.util.List;

import com.example.project.model.Account;

public interface AccountService {
    List<Account> findByAccountType(Long id);
}
