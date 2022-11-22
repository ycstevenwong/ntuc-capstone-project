package com.example.project.Pojo;


import com.example.project.Enum.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
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

    @Column(name="user_nric",unique = true)
    private String nric;
    @Column(name = "user_pwd")
    private String pwd;
    @Column(name = "user_name",length=32)
    private String name;
    @Column(name = "user_age")
    private Long age;

    @Column(name="user_gender")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column(name="user_birth_date")
    private LocalDate userBirth;

    @Column(name="user_phone")
    private String phone;

    @Column(name = "user_address")
    private String address;

    @Column(name = "nominee_nric")
    private String nomineeNric;

    @Column(name = "nominee_name")
    private String nomineeName;

    @Column(name = "register_time", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp timestamp;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> userRoles = new HashSet(0);

    @OneToMany(mappedBy = "user")
    private Set<Account> accounts = new HashSet<>(0);

}
