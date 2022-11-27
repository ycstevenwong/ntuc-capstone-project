package com.example.project.serviceImpl;

import com.example.project.model.AccountType;
import com.example.project.repository.AccountTypeRepo;
import com.example.project.service.AccountTypeService;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public class AccountTypeServiceImpl implements AccountTypeService {

    @Resource
    AccountTypeRepo aRepo;

    @Override
    public Optional<AccountType> findByName(String name) {
        return aRepo.findByName(name);
    }
}
