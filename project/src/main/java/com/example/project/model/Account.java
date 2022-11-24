package com.example.project.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountType accountType;
}
