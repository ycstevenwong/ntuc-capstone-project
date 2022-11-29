package com.example.project.service;

import com.example.project.model.Account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface AccountService {

    /**
     * For this method
     * Just for testing the close account function, will delete when
     * combine with other use case
     */
    public List<Account> listAllAccountsWithoutCloseStatus();

    public Account selectAccountByAccountNumber(Long accountNumber);

    public Account saveAccount(Account account);

    public BigDecimal calculateInterest(Account account);

    public LocalDate calculateExpiryTime(LocalDate registerDate, Account account);

    public LocalDate calculateRenewTime(LocalDate expiryDate, Account account);

}
