package com.example.project.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/customer/accounts")
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
			return "redirect:/customer/accounts/" + accountNumber;
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

		return "redirect:/customer/accounts/" + accountNumber;
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
			return "redirect:/customer/accounts/" + accountNumber;
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

		return "redirect:/customer/accounts/" + accountNumber;
	}

	// Main Display Page for transaction history
	@GetMapping("/{accountNum}")
	public String display(@PathVariable("accountNum") Long accountNum,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageLimit", defaultValue = "10") Integer pageLimit,
			Model model) {
				
		// fetch current account details
		Optional<Account> account = accountRepository.findById(accountNum);
		account.ifPresent(x -> model.addAttribute("num", accountNum));
		account.ifPresent(x -> model.addAttribute("currentBalance", x.getBalance()));
		account.ifPresent(x -> model.addAttribute("accountNRIC", x.getCustomer().getNric().substring(5)));
		account.ifPresent(x -> model.addAttribute("accountDetails", x.getCustomer()));
		account.ifPresent(x -> model.addAttribute("accountType", x.getAccountType().getName()));

		// fetch all records for pagination
		Pageable paging = PageRequest.of(page - 1, pageLimit, Sort.by(Direction.DESC, "time"));

		Optional<Page<Transaction>> records = account.map(c -> transactionRepo.findAllTransactionsWithPagination(c, paging));
		records.ifPresent(x -> model.addAttribute("transactionList", records.get()));	

		// Boolean Condition for alert box
		model.addAttribute("transactionCompletion", transactionCompletion);
		model.addAttribute("isFirstLoad", isFirstLoad);
		
		// Pagination for table
		model.addAttribute("page", page);
		model.addAttribute("pageLimit", pageLimit);
		model.addAttribute("lastPage", records.map(as -> as.getTotalPages()).orElse(1));
		model.addAttribute("BASE_URL", "/customer/accounts");

		return "TransactionHistory";
	}
}
