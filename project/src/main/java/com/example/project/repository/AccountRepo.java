package com.example.project.repository;

import com.example.project.model.Account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    @Query(value="SELECT * FROM accounts a WHERE a.account_type_id = :id",nativeQuery=true)
    List<Account> findByAccountType(@Param("id") Long id);
}
