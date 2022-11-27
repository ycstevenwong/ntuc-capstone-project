package com.example.project.repository;

import com.example.project.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface AccountTypeRepo extends JpaRepository<AccountType, Long> {
    
    @Query("select a from AccountType a where a.name =:name")
    Optional<AccountType> findByName(@Param("name") String name);
}
