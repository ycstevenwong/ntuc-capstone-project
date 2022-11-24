package com.example.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account_types")
public class AccountType {

    @Id
    @Column(name = "account_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "interest_rate")
    private double interestRate;

    @OneToMany(mappedBy = "accountType")
    private Set<Account> accounts = new HashSet<>(0);

}
