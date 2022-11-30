package com.example.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project.model.Account;
import com.example.project.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    // Pagination related
    @Query("select t from Transaction t where t.account = :account")
    Page<Transaction> findAllTransactionsWithPagination(@Param("account") Account account, Pageable pageable);

}
