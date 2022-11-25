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
public class DepositTransferController {

	@Autowired
	AccountRepo accountRepository;

	@Autowired
	TransactionRepo TransactionRepo;

	Customer customer;
	double currentBalance;
	double newBalance;

	@GetMapping("/deposit")
	public String deposit(@RequestParam("accountNumber") Long accountNumber,
			@RequestParam("depositAmount") BigDecimal deposit_amount) {
		if (deposit_amount.equals(0)) {
			System.out.println("Error: Deposit Amount cannot be 0");
			return "ErrorPage";
		}
		Optional<Account> account = accountRepository.findById(accountNumber);
		Transaction deposit = new Transaction();
		deposit.setAmount(deposit_amount);
		deposit.setAccount(account.get());
		deposit.setDescription("DEPOSIT");
		TransactionRepo.save(deposit);
		BigDecimal balance;
		balance = account.get().getBalance();

		BigDecimal newBalance = balance.add(deposit_amount);
		System.out.println(newBalance);

		account.ifPresent(x -> x.setBalance(newBalance));
		accountRepository.save(account.get());

		return "SuccessPage";
	}
}