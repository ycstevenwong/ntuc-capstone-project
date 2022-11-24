package com.example.project.serviceImpl;

import com.example.project.model.AccountOpenRequest;
import com.example.project.model.AccountOpenStatus;
import com.example.project.model.User;
import com.example.project.repository.AccountRequestRepo;
import com.example.project.service.AccountRequestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Service
@Transactional
public class AccountRequestServiceImpl implements AccountRequestService {

    @Resource
    private AccountRequestRepo requestRepo;


    @Override
    public AccountOpenRequest createRequest(AccountOpenRequest request, User user, AccountOpenStatus status) {
        request.setUser(user);
        request.setStatus(AccountOpenStatus.PENDING);
        return requestRepo.saveAndFlush(request);
    }
}
