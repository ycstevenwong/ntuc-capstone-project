package com.example.project.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.TransactionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.project.model.Account;
import com.example.project.model.Transaction;
import com.example.project.model.TransactionRecord;
import com.example.project.repository.AccountRepository;
import com.example.project.repository.TransHistRepository;
import com.example.project.repository.UserRepo;

@Controller
@RequestMapping("/transhist")
public class TransHistController {
    @Autowired
    TransHistRepository transHistRepo;

    @Autowired
    AccountRepository accountRepo;

    @Autowired
    UserRepo userRepo;

    /*
     * Method to merge all transaction history for targeted account
     * Map to TransactionHist Page
     * Example URL: http://localhost:8080/transhist/display?accountNum=67890
     */
    @GetMapping("/display")
    public String display(@Param("accountNum") Long accountNum, Model model) {
        List<TransactionRecord> recordList = displayDeposit(accountNum);
        recordList.addAll(displayWithdrawal(accountNum));

        // sort based on time
        Collections.sort(recordList, new Comparator<TransactionRecord>() {
            public int compare(TransactionRecord m1, TransactionRecord m2) {
                return m1.getTime().compareTo(m2.getTime());
            }
        });
        model.addAttribute("recordList", recordList);

        return "TransactionHist";
    }

    /*
     * End point for testing ONLY retrieving deposit transaction history
     * Example URL: http://localhost:8080/transhist/test/deposit?accountNum=67890
     * Return ResponseBody
     */
    @GetMapping("/test/deposit")
    @ResponseBody
    public List<TransactionRecord> displayDeposit(@Param("accountNum") long accountNum) {
        List<Transaction> depositList = (List<Transaction>) transHistRepo.findAll();
        List<TransactionRecord> transactionRecords = new ArrayList<>();

        for (Transaction transaction : depositList) {
            if (transaction.getDeposit_account().equals(accountNum)) {
                Optional<Account> receipent_account = accountRepo.findById(accountNum);
                Optional<Account> sender_account = accountRepo.findById(transaction.getWithdraw_account());
                String receipent = receipent_account.get().getUser().getName();
                String sender = sender_account.get().getUser().getName();

                TransactionRecord tr = new TransactionRecord();
                tr.setAction("DEPOSIT");
                tr.setAmount(transaction.getAmount());
                tr.setTime(transaction.getTime());

                tr.setReceipent_account(accountNum);
                tr.setReceipent_userName(receipent);

                tr.setSender_account(transaction.getWithdraw_account());
                tr.setSender_userName(sender);
                transactionRecords.add(tr);
            }
        }
        return transactionRecords;

    }

    /*
     * End point for testing ONLY retrieving withdrawals transaction history
     * Example URL: http://localhost:8080/transhist/test/withdraw?accountNum=12345
     * Return ResponseBody
     */
    @GetMapping("/test/withdraw")
    @ResponseBody
    public List<TransactionRecord> displayWithdrawal(@Param("accountNum") Long accountNum) {
        List<Transaction> withdrawalList = (List<Transaction>) transHistRepo.findAll();
        List<TransactionRecord> transactionRecords = new ArrayList<>();

        for (Transaction transaction : withdrawalList) {
            if (transaction.getWithdraw_account().equals(accountNum)) {
                Optional<Account> receipent_account = accountRepo.findById(accountNum);
                Optional<Account> sender_account = accountRepo.findById(transaction.getDeposit_account());
                String receipent = receipent_account.get().getUser().getName();
                String sender = sender_account.get().getUser().getName();

                TransactionRecord tr = new TransactionRecord();
                tr.setAction("WITHDRAW");
                tr.setAmount(transaction.getAmount());
                tr.setTime(transaction.getTime());

                tr.setReceipent_account(accountNum);
                tr.setReceipent_userName(receipent);

                tr.setSender_account(transaction.getDeposit_account());
                tr.setSender_userName(sender);
                transactionRecords.add(tr);
            }
        }
        return transactionRecords;
    }
}
