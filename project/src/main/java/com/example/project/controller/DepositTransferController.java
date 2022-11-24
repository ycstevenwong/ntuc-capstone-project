package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.model.Account;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/deposit")
public class DepositTransferController {

	@Autowired
	AccountRepository account;

	@GetMapping("/display")
	@ResponseBody
	public Long deposit(@Param("accountNumber") Long accountNumber) {
		System.out.println(account.findAll());
		System.out.println("hi");
		
		return accountNumber;
	}
}