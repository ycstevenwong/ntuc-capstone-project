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
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.model.AccountType;
import com.example.project.repository.AdminRepo;
import com.example.project.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	@Autowired
	AdminRepo adminRepo;

	//View Account Type
	@GetMapping("/view")
	public String viewAccountType(Model model){
		List<AccountType> accountTypesList = adminRepo.findAll();
		model.addAttribute("accountTypesList", accountTypesList); 
		return "AccountType/viewAccountType";
	}

	//Show Account Type Form
	@GetMapping("/add")
	public String accountTypeForm(Model model) {
		AccountType ac = new AccountType();
		model.addAttribute("ac", ac);
		return "AccountType/addAccountType";
	}

	//Add Account Type to Database when click Sumbit button
	@PostMapping("/add")
	public String addAccountType(@ModelAttribute("ac") @Valid AccountType ac, BindingResult result) {
		if(result.hasErrors()) {
			return "AccountType/addAccountType";
		}
		adminService.createAcccountType(ac);
		return "redirect:/admin/view";	
	}

	@GetMapping("/edit/{id}")
	public String editAccountType(@PathVariable Long id,Model model){
		Optional<AccountType> ac = adminRepo.findById(id);
		model.addAttribute("ac", ac);
		return "AccountType/editAccountType";
	}
	@PutMapping("/save")
	public String saveAccountType(@Valid AccountType editedAccountType, BindingResult result){
		Long id = editedAccountType.getId();
		if(result.hasErrors()) {
			return "redirect:/admin/edit/"+id.toString();
		}
		adminService.createAcccountType(editedAccountType);
		return "redirect:/admin/view";
	}
}

