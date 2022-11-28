package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.project.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Long>{
	@Query("SELECT e FROM Employee e WHERE e.email=:email")
	public Employee findByEmail(@Param("email") String email);
}
