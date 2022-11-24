package com.example.project.controller;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.model.Account;
import com.example.project.model.User;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/transact")
public class DepositTransferController {

	@Autowired
	AccountRepository accountRepository;

	User user;
    double currentBalance;
    double newBalance;
	
	@GetMapping("/deposit")
	@ResponseBody
	public Long deposit(@RequestParam("accountNumber") Long accountNumber 
										) {
		Long deposit_amount = (long) 5;
		if(deposit_amount == 0) {
			System.out.println("Error: Deposit Amount cannot be 0");
			return null;
		}
		               
        BigDecimal balance;

		Optional<Account> account = accountRepository.findById(accountNumber);
        balance = account.get().getBalance();
        
        BigDecimal newBalance = balance.add(new BigDecimal(deposit_amount));
        System.out.println(newBalance);

        account.ifPresent(x -> x.setBalance(newBalance));
        accountRepository.save(account.get());	
        
              
        return null;

	}
}