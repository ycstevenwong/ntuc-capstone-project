package com.example.project.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.model.AccountType;
import com.example.project.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminService adminservice;
	@GetMapping("/add")
	public String addAccount(Model model) {
		AccountType ac = new AccountType();
		model.addAttribute("ac", ac);
		return "addAccountType";
	}
	@PostMapping("/add")
	public String addAccountType(@ModelAttribute("ac") @Valid AccountType ac, BindingResult result) {
		adminservice.createAcccountType(ac);
		if(result.hasErrors()) {
			return "addAccountType";
			}
		return "addAccountSuccess";	
		}
	}

