package com.example.project.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "account_open_request")
public class AccountOpenRequest {
    @Id
    @Column(name = "request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "account_type")
    private String type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountOpenStatus status;

}
