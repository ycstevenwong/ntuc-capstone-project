package com.example.project.repository;

import com.example.project.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface AccountRepo extends JpaRepository<Account, Long> {

    @Query("select a from Account a where lower(a.status) <> 'closed' ")
    public List<Account> findAllAccountsWithoutCloseStatus();

    public Account findAccountByAccountNumber(Long accountNumber);
}
