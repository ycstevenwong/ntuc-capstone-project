package com.example.project.Pojo;

import com.example.project.Enum.AccountStatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name="account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "account_type_id")
    private AccountType accountType;


}
