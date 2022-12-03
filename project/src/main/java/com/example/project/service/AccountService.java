package com.example.project.service;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */

import java.sql.Timestamp;
import java.util.List;
import com.example.project.model.Account;
import java.math.BigDecimal;
import java.time.LocalDate;

public interface AccountService {


    List<Account> findByAccountType(Long id);

    /**
     * For this method
     * Just for testing the close account function, will delete when
     * combine with other use case
     */
    public List<Account> listAllAccountsWithoutCloseStatus();

    public Account selectAccountByAccountNumber(Long accountNumber);

    public Account saveAccount(Account account);

    public BigDecimal calculateInterest(Account account);

    public Timestamp calculateExpiryTime(Timestamp registerDate, Account account);

    public Timestamp calculateRenewTime(Account account);


}
