package com.example.project.repository;

import com.example.project.model.Account;

import com.example.project.model.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;


import org.springframework.stereotype.Repository;


/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

    @Query("select a from Account a where a.customer = :customer")
    Iterable<Account> findAllCustomerAccounts(@Param("customer") Customer customer);

    @Query("select a from Account a where a.customer = :customer")
    Page<Account> findAllCustomerAccountsWithPagination(@Param("customer") Customer customer, Pageable pageable);

    @Query(value="SELECT * FROM accounts a WHERE a.account_type_id = :id",nativeQuery=true)
    List<Account> findByAccountType(@Param("id") Long id);
}
