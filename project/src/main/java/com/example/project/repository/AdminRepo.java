package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.model.AccountType;

public interface AdminRepo extends JpaRepository<AccountType,Long>{
	
}
