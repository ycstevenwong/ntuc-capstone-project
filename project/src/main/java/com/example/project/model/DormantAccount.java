package com.example.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dormant_accounts")
public class DormantAccount {
    @Id
    @Column(name = "dormant_account_number")
    private Long dormantAccountNumber;

    @Column(name = "customer_id")
    private Long id;

    @Column(name = "status")
    private String status;

}
