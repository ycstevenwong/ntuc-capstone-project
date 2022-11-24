package com.example.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nric", unique = true)
    @NotEmpty(message = "You can not leave the NRIC blank!")
    private String nric;

    @Column(name = "pwd")
    @NotEmpty(message = "Please fill in your password!")
    private String pwd;

    @Column(name = "name", length = 32)
    @NotEmpty(message = "You can not leave the Name blank!")
    private String name;

    @Column(name = "age")
    @NotNull(message = "Please fill in your Age!")
    private Long age;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "email_address", unique = true)
    @Pattern(
            message = "Please enter a valid email and you can not leave it blank!",
            regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$")
    private String emailAddress;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please pick your birth date!")
    private Date birthDate;

    @Column(name = "phone")
    @NotEmpty(message = "Please fill in your contact number!")
    @Pattern(
            message = "Please enter a valid contact number (Only numbers are allowed)",
            regexp = "^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")
    private String phone;

    @Column(name = "address")
    @NotEmpty(message = "Please fill in your address!")
    private String address;

    @Column(name = "nominee_nric")
    private String nomineeNric;

    @Column(name = "nominee_name")
    private String nomineeName;

    @Column(name = "register_time", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp timestamp;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> userRoles = new HashSet<>(0);

    @OneToMany(mappedBy = "user")
    private Set<Account> accounts = new HashSet<>(0);

    @OneToMany(mappedBy = "user")
    private Set<AccountOpenRequest> requests = new HashSet<>(0);
}

