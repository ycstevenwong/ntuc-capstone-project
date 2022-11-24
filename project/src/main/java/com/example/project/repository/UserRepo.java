package com.example.project.repository;

import com.example.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
public interface UserRepo extends JpaRepository<User,Long> {
}
