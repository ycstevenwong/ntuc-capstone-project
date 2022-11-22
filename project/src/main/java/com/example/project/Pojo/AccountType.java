package com.example.project.Pojo;

import com.example.project.Enum.AccountTypeEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "account_type")
public class AccountType {

    @Id
    @Column(name = "account_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_type_name")
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum accountTypeName;

    @Column(name = "interest_rate")
    private double interestRate;

    @OneToMany(mappedBy = "accountType")
    private Set<Account> accounts = new HashSet<>(0);
}
