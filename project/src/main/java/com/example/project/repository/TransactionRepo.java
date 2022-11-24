package com.example.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

	
}
