package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/deposit")
public class DepositTransferController {

//	
//	@Autowired
//	AccountRepository account;
	 
	@GetMapping("/display")
	@ResponseBody
	public void print() {
        System.out.println("HI");
    }
	
//	public Long deposit(@PathVariable("accountNumber") Long accountNumber, int deposit_amount) {
//		System.out.println(account.findAll());
//		System.out.println("hi");
//
//		return accountNumber;
//
//	}	
}