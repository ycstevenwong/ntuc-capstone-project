package com.example.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {}