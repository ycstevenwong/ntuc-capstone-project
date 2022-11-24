package com.example.project.repository;

import com.example.project.model.AccountOpenRequest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface AccountRequestRepo extends JpaRepository<AccountOpenRequest, Long> {
}
