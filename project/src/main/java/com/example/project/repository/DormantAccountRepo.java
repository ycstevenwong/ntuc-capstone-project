package com.example.project.repository;

import com.example.project.model.DormantAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DormantAccountRepo extends JpaRepository<DormantAccount, Long> {

}
