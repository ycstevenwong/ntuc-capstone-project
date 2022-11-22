package com.example.project.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_time", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp transactionTime;

    @Column(name = "transaction_amount")
    private BigDecimal amount;

    @Column(name = "tranfer_from")
    private String transFromAccount;

    @Column(name = "tranfer_to")
    private String transToAccount;

}
