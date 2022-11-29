package com.example.project.service;

import com.example.project.model.Account;
import com.example.project.model.DormantAccount;

public interface DormantAccountService {

    public DormantAccount moveClosedAccountInfoIntoDormant(Account account);
}
