package com.example.project.repository;

import com.example.project.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
