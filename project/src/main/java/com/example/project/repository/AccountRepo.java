package com.example.project.repository;

import com.example.project.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface AccountRepo extends JpaRepository<Account, Long> {
}
