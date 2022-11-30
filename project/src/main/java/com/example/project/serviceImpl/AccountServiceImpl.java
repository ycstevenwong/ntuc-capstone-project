package com.example.project.serviceImpl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.project.model.Account;
import com.example.project.repository.AccountRepo;
import com.example.project.service.AccountService;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

    @Resource
    AccountRepo accountRepo;

    @Override
    public List<Account> findByAccountType(Long id) {
        
        return accountRepo.findByAccountType(id);
    }
    

    @Override
    public List<Account> listAllAccountsWithoutCloseStatus() {
        return accountRepo.findAllAccountsWithoutCloseStatus();
    }

    @Override
    public Account selectAccountByAccountNumber(Long accountNumber) {
        return accountRepo.findAccountByAccountNumber(accountNumber);
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepo.saveAndFlush(account);
    }

    @Override
    public BigDecimal calculateInterest(Account account) {
        LocalDateTime registerTime = account.getRegisterTime().toLocalDateTime();
        Integer validYearsOfPeriod = account.getAccountType().getValidYearsOfPeriod();
        Integer totalYears = validYearsOfPeriod + account.getNumberOfRenew() * validYearsOfPeriod;
        LocalDateTime expiryTime = registerTime.plusYears(totalYears);
        LocalDateTime now = LocalDateTime.now();

        BigDecimal interestRate = new BigDecimal(String.valueOf(account.getAccountType().getInterestRate()));
        BigDecimal oneYear = new BigDecimal("365");
        long days;
        if(now.isBefore(expiryTime)){
            days = Duration.between(registerTime, now).toDays();
        } else{
            days = Duration.between(registerTime, expiryTime).toDays();
        }

        return bigDecimalCal(days,account,interestRate,oneYear);

    }

    private BigDecimal bigDecimalCal(long days, Account account, BigDecimal interestRate, BigDecimal oneYear){
        BigDecimal daysDecimal = new BigDecimal(String.valueOf(days));
        BigDecimal interest = account.getBalance().multiply(interestRate).multiply(daysDecimal);
        interest = interest.divide(oneYear,2,BigDecimal.ROUND_HALF_UP);
        return interest;
    }

    @Override
    public LocalDate calculateExpiryTime(LocalDate registerDate, Account account) {
        Integer validYearsOfPeriod = account.getAccountType().getValidYearsOfPeriod();
        Integer totalYears = validYearsOfPeriod + account.getNumberOfRenew() * validYearsOfPeriod;
        LocalDate expiryDate = registerDate.plusYears(totalYears);
        return expiryDate;
    }

    @Override
    public LocalDate calculateRenewTime(LocalDate expiryDate, Account account) {
        Integer validYearsOfPeriod = account.getAccountType().getValidYearsOfPeriod();
        LocalDate renewDate = expiryDate.plusYears(validYearsOfPeriod);
        return renewDate;
    }

}
