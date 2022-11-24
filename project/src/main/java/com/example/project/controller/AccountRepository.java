package com.example.project.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.project.model.Account;

interface AccountRepository extends JpaRepository<Account, Long> {}	
