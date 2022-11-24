package com.example.project.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TransactionRecord {
    Timestamp time;
    BigDecimal amount;
    long receipent_account;
    long sender_account;
    String receipent_userName;
    String sender_userName;
    String action;

    public TransactionRecord() {
    }

    public TransactionRecord(Timestamp time, BigDecimal amount, long receipent_account, long sender_account,
            String receipent_userName, String sender_userName, String action) {
        this.time = time;
        this.amount = amount;
        this.receipent_account = receipent_account;
        this.sender_account = sender_account;
        this.receipent_userName = receipent_userName;
        this.sender_userName = sender_userName;
        this.action = action;
    }

    public Timestamp getTime() {
        return this.time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getReceipent_account() {
        return this.receipent_account;
    }

    public void setReceipent_account(long receipent_account) {
        this.receipent_account = receipent_account;
    }

    public long getSender_account() {
        return this.sender_account;
    }

    public void setSender_account(long sender_account) {
        this.sender_account = sender_account;
    }

    public String getReceipent_userName() {
        return this.receipent_userName;
    }

    public void setReceipent_userName(String receipent_userName) {
        this.receipent_userName = receipent_userName;
    }

    public String getSender_userName() {
        return this.sender_userName;
    }

    public void setSender_userName(String sender_userName) {
        this.sender_userName = sender_userName;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "{" +
                " time='" + getTime() + "'" +
                ", amount='" + getAmount() + "'" +
                ", receipent_account='" + getReceipent_account() + "'" +
                ", sender_account='" + getSender_account() + "'" +
                ", receipent_userName='" + getReceipent_userName() + "'" +
                ", sender_userName='" + getSender_userName() + "'" +
                ", action='" + getAction() + "'" +
                "}";
    }
}
