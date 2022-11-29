package com.example.project.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.project.model.Employee;
import com.example.project.repository.EmployeeRepo;
 
public class CustomEmployeeDetailsService implements UserDetailsService {
 
    @Autowired
    private EmployeeRepo empRepo;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = empRepo.findByEmail(username);
        if (employee == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomEmployeeDetails(employee);
    }
 
}
//implementation
