package com.example.project.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.project.model.Transaction;

public interface TransHistRepository extends JpaRepository<Transaction, BigInteger> {}