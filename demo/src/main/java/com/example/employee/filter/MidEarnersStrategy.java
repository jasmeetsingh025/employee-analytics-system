package com.example.employee.filter;

import com.example.employee.model.Employee;

import java.util.Comparator;
import java.util.List;

public class MidEarnersStrategy implements SalaryFilterStrategy {
    @Override
    public List<Employee> filter(List<Employee> employees) {
        return employees.stream()
        .filter(e -> e.getSalary() >= 60000 && e.getSalary() <= 90000)
        .sorted(Comparator.comparingInt(Employee::getSalary))
        .limit(3)
        .toList();
    }
}
