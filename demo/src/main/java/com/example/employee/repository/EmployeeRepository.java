package com.example.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employee.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartment(String department);
    
    // SELECT * FROM employees WHERE name = ?
    java.util.Optional<Employee> findByName(String name);
    
    // SELECT * FROM employees WHERE salary > ?
    List<Employee> findBySalaryGreaterThan(int salary);
}
