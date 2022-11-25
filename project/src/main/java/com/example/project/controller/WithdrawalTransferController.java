package com.example.project.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.model.Account;
import com.example.project.model.Customer;
import com.example.project.model.Transaction;
import com.example.project.repository.AccountRepo;
import com.example.project.repository.TransactionRepo;

@Controller
@RequestMapping("/transact")
public class WithdrawalTransferController {

	@Autowired
	AccountRepo accountRepository;

	@Autowired
	TransactionRepo withdrawalTransactionRepo;

	Customer customer;
	double currentBalance;
	double newBalance;

	@GetMapping("/withdrawal")
	public String deposit(@RequestParam("accountNumber") Long accountNumber, @RequestParam("amount") BigDecimal amount) {

		Optional<Account> account = accountRepository.findById(accountNumber);

		Transaction withdrawal = new Transaction();
		withdrawal.setAmount(amount);
		withdrawal.setAccount(account.get());
		withdrawal.setDescription("WITHDRAW");
		withdrawalTransactionRepo.save(withdrawal);

		BigDecimal balance;
		balance = account.get().getBalance();

		if (amount.compareTo(balance) == 1) {
			System.out.println("Error: You have insufficient value to perform this withdrawal");
			return "ErrorPage";
		}

		else {
			BigDecimal newBalance = balance.subtract(amount);
			account.ifPresent(x -> x.setBalance(newBalance));
			accountRepository.save(account.get());

			return "SuccessPage";
		}

	}

}
