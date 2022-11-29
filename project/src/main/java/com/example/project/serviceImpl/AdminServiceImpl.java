package com.example.project.serviceImpl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.project.model.AccountType;
import com.example.project.repository.AdminRepo;
import com.example.project.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	
	@Resource
	AdminRepo adminRepo;

	@Override
	public AccountType createAcccountType(AccountType ac) {		
		return adminRepo.saveAndFlush(ac);
	}

}
