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

	@PostMapping("/deposit")
	public String deposit(@RequestParam("deposit_amount") String depositAmount,
						@RequestParam("account_number") String accountNumber,
						HttpSession session, 
						RedirectAttributes redirectAttributes) {
		
		return "";
	}
	String int;
}
