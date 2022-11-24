package com.example.project;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.project.model.Transaction;

interface TransHistRepository extends JpaRepository<Transaction, BigInteger> {}