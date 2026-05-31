package com.example.employee.filter;

import com.example.employee.model.Employee;

import java.util.List;

public interface SalaryFilterStrategy {
    List<Employee> filter(List<Employee> employees);
}
