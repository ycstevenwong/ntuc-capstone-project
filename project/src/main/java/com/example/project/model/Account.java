package com.example.project.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "account_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountType accountType;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Transaction> transactions = new ArrayList<>(0);

    @Column(name = "register_time", nullable = false, updatable = false)
    private Timestamp registerTime;

    @Column(name = "expiry_time", nullable = false)
    private Timestamp expiryTime;


    @Override
    public String toString() {
        return "{" +
                " accountNumber='" + getAccountNumber() + "'" +
                ", balance='" + getBalance() + "'" +
                ", status='" + getStatus() + "'" +
                ", customer='" + getCustomer() + "'" +
                ", accountType='" + getAccountType() + "'" +
                ", transactions='" + getTransactions() + "'" +
                ", registerTime='" + getRegisterTime() + "'" +
                ", numberOfRenew='" + getExpiryTime() + "'" +
                "}";
    }


}
