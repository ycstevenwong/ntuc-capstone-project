package com.example.project.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.model.Account;
import com.example.project.model.AccountType;
import com.example.project.model.Customer;
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

	Boolean transactionCompletion = false;
	Boolean isFirstLoad = true; 

	// End point for deposit logic that reroute back to main page
	@GetMapping("/Deposit")
	public String deposit(@RequestParam("accountNumber") Long accountNumber,
			@RequestParam("amount") BigDecimal amount) {

		isFirstLoad = false;
		// Deposit Amount must be MORE THAN ZERO
		if (amount.equals(new BigDecimal("0"))) {
			transactionCompletion = false;
			return "redirect:/account/transaction/display?accountNum=" + accountNumber;
		}

		transactionCompletion = true;

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

		return "redirect:/account/transaction/display?accountNum=" + accountNumber;
	}

	// End point for withdraw logic that reroute back to main page
	@GetMapping("/Withdraw")
	public String withdraw(@RequestParam("accountNumber") Long accountNumber,
			@RequestParam("amount") BigDecimal amount) {

		Optional<Account> account = accountRepository.findById(accountNumber);

		isFirstLoad = false;
		// Balance amount must be MORE THAN withdrawn amount
		if (amount.compareTo(account.get().getBalance()) == 1) {
			transactionCompletion = false;
			return "redirect:/account/transaction/display?accountNum=" + accountNumber;
		}

		transactionCompletion = true;

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

		return "redirect:/account/transaction/display?accountNum=" + accountNumber;
	}

	// Main Display Page for transaction history
	@GetMapping("/display")
	public String display(@Param("accountNum") Long accountNum, Model model) {
		List<Transaction> transactionList = (List<Transaction>) transactionRepo.findAll();
		List<Transaction> filteredList = new ArrayList<>();
		for (Transaction transactionRecord : transactionList) {
			if (transactionRecord.getAccount().getAccountNumber().equals(accountNum))
				filteredList.add(transactionRecord);
		}
		// sort based on time
		Collections.sort(filteredList, new Comparator<Transaction>() {
			public int compare(Transaction t1, Transaction t2) {
				return t2.getTime().compareTo(t1.getTime());
			}
		});

		// fetch all accounts available
		List<Account> accountList = accountRepository.findAll();

		// fetch current account details
		Optional<Account> account = accountRepository.findById(accountNum);
		BigDecimal currentBalance = account.get().getBalance();
		Customer accountDetails = account.get().getCustomer();
		String accountType = account.get().getAccountType().getName();
		String accountNRIC = account.get().getCustomer().getNric().substring(5);

		model.addAttribute("currentBalance", currentBalance);
		model.addAttribute("transactionList", filteredList);
		model.addAttribute("accountNumber", accountNum);
		model.addAttribute("accountNRIC", accountNRIC);

		model.addAttribute("accountList", accountList);
		model.addAttribute("accountDetails", accountDetails);
		model.addAttribute("accountType", accountType);
		
		// Boolean Condition for alert box
		model.addAttribute("transactionCompletion", transactionCompletion);
		model.addAttribute("isFirstLoad", isFirstLoad);

		return "TransactionHistory";
	}
}
