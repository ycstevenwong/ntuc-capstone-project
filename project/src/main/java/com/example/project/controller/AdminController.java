package com.example.project.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.model.Account;
import com.example.project.model.AccountType;
import com.example.project.repository.AdminRepo;
import com.example.project.service.AccountService;
import com.example.project.service.AdminService;

@Controller
public class AdminController {
	@Autowired
	AdminService adminService;
	@Autowired
	AdminRepo adminRepo;
	@Autowired
	AccountService accountService;
	//View Account Type
	@GetMapping("/viewproduct")
	public String viewAccountType(Model model){
		List<AccountType> accountTypesList = adminRepo.findAll();
		model.addAttribute("accountTypesList", accountTypesList);
		model.addAttribute("totalAccountType", accountTypesList.size());
		AccountType ac = new AccountType();
		model.addAttribute("ac", ac);
		// String errorMsg = "";
		// model.addAttribute("errorMsg", errorMsg);
		return "AccountType/viewAccountType";
	}

	//Show Account Type Form
	// @GetMapping("/addproduct")
	// public String accountTypeForm(Model model) {
	// 	AccountType ac = new AccountType();
	// 	model.addAttribute("ac", ac);
	// 	return "AccountType/addAccountType";
	// }

	//Add Account Type to Database when click Sumbit button
	@PostMapping("/addproduct")
	public String addAccountType(@ModelAttribute("ac") @Valid AccountType ac, BindingResult result) {
		if(result.hasErrors()) {
			return "AccountType/addAccountType";
		}
		adminService.createAcccountType(ac);
		return "redirect:/viewproduct";	
	}

	// Show Edit Page when click Edit button
	@GetMapping("/editproduct/{id}")
	public String editAccountType(@PathVariable Long id,Model model){
		Optional<AccountType> ac = adminRepo.findById(id);
		model.addAttribute("ac", ac);
		return "AccountType/editAccountType";
	}

	// Save the Account Type to Database click Submit button
	@PutMapping("/saveproduct")
	public String saveAccountType(@Valid AccountType editedAccountType, BindingResult result){
		Long id = editedAccountType.getId();
		if(result.hasErrors()) {
			return "redirect:/editproduct/"+id.toString();
		}
		adminService.createAcccountType(editedAccountType);
		return "redirect:/viewproduct";
	}
	@GetMapping("/deleteproduct/{id}")
	public String deleteAccountType(@PathVariable Long id, Model model,RedirectAttributes redirectAttributes){
		String errorMsg;
		List<Account> accounts = accountService.findByAccountType(id);
		System.out.println(accounts.size());
		if(accounts.size()>0){
			errorMsg = "Not able to Delete";
			// Use RedirectAttributes if returning to a redirect page.
			redirectAttributes.addFlashAttribute("errorMsg", errorMsg);
			return "redirect:/viewproduct";
		}
		errorMsg = "";
		model.addAttribute("errorMsg", errorMsg);
		adminRepo.deleteById(id);
		return "redirect:/viewproduct";
	}
}