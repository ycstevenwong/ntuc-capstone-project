package com.example.project.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
