package com.example.project.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.project.model.Account;
import com.example.project.model.Transaction;
import com.example.project.repository.AccountRepo;
import com.example.project.repository.TransactionRepo;

@Controller
@RequestMapping("/account/transaction")
public class TransactionController {

	@Autowired
	AccountRepo accountRepository;
	@Autowired
	TransactionRepo transactionRepo;

	@GetMapping("/deposit")
	public String deposit(@RequestParam("accountNumber") Long accountNumber,
			@RequestParam("amount") BigDecimal amount) {

		// Deposit Amount must be MORE THAN ZERO
		if (amount.equals(new BigDecimal("0"))) {
			return "ErrorPage";
		}

		Optional<Account> account = accountRepository.findById(accountNumber);
		Transaction transactionRecord = new Transaction();
		// Set transaction field based on desired value
		transactionRecord.setAmount(amount);
		transactionRecord.setAccount(account.get());
		transactionRecord.setDescription("DEPOSIT");

		// Update account field based on transacted amount
		BigDecimal newBalance = account.get().getBalance().add(amount);
		account.get().setBalance(newBalance);
		// Commit the record to transaction & account table
		transactionRepo.save(transactionRecord);
		accountRepository.save(account.get());

		return "SuccessPage";
	}

	@GetMapping("/withdraw")
	public String withdraw(@RequestParam("accountNumber") Long accountNumber,
			@RequestParam("amount") BigDecimal amount) {

		Optional<Account> account = accountRepository.findById(accountNumber);

		// Balance amount must be MORE THAN withdrawn amount
		if (amount.compareTo(account.get().getBalance()) == 1) {
			return "ErrorPage";
		}

		Transaction transactionRecord = new Transaction();
		// Set transaction field based on desired value
		transactionRecord.setAmount(amount.negate());
		transactionRecord.setAccount(account.get());
		transactionRecord.setDescription("WITHDRAW");

		BigDecimal newBalance = account.get().getBalance().subtract(amount);
		account.get().setBalance(newBalance);
		// Commit the record to transaction & account table
		transactionRepo.save(transactionRecord);
		accountRepository.save(account.get());

		return "SuccessPage";
	}

	@GetMapping("/display")
	public String display(@Param("accountNum") Long accountNum, Model model) {
		List<Transaction> transactionList = (List<Transaction>) transactionRepo.findAll();
		List<Transaction> filteredList = new ArrayList<>();
		for (Transaction transactionRecord : transactionList) {
			if (transactionRecord.getAccount().getAccountNumber().equals(accountNum))
				filteredList.add(transactionRecord);
		}
		// fetch all accounts available
		List<Account> accountList = accountRepository.findAll();


		// fetch current account balance
		Optional<Account> account = accountRepository.findById(accountNum);
		BigDecimal currentBalance = account.get().getBalance();

		model.addAttribute("currentBalance", currentBalance);
		model.addAttribute("transactionList", filteredList);
		model.addAttribute("accountNumber", accountNum);
		model.addAttribute("accountList", accountList);


		return "TransactionHistory";
	}
}
