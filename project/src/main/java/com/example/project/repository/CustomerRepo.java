package com.example.project.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project.model.Customer;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    @Query("select c from Customer c where c.name like %:name% and substring(c.nric, 6, 4) = :nricLast4Chars and c.birthDate = :birthDate")
    Optional<Customer> findCustomer(
        @Param("name") String name,
        @Param("nricLast4Chars") String nricLast4Chars,
        @Param("birthDate") LocalDate birthDate
    );
}
