package com.example.project.serviceImpl;

import com.example.project.model.User;
import com.example.project.repository.UserRepo;
import com.example.project.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepo userRepo;

    @Override
    public User createUser(User user) {
        return userRepo.saveAndFlush(user);
    }
}
