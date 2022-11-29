package com.example.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/transact")
public class TransactionController {

	@RequestMapping("/deposit")
	public String deposit() {
		
		return "Withdrawal_example_Form";
	}
	
	@RequestMapping("/withdrawal")
	public String withdrawal() {
		
		return "example_form";
	}

}
