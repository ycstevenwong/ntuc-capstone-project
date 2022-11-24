package com.example.project.service;

import com.example.project.model.AccountOpenRequest;
import com.example.project.model.AccountOpenStatus;
import com.example.project.model.User;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface AccountRequestService {
    AccountOpenRequest createRequest(AccountOpenRequest request, User user, AccountOpenStatus status);
}
