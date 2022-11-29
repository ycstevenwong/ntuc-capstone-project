package com.example.project.service;

import com.example.project.model.AccountType;

import java.util.Optional;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface AccountTypeService {
    Optional<AccountType> findByName(String name);
}
