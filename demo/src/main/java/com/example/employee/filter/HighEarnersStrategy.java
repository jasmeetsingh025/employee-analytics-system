package com.example.employee.filter;

import com.example.employee.model.Employee;

import java.util.Comparator;
import java.util.List;

public class HighEarnersStrategy implements SalaryFilterStrategy {
    @Override
    public List<Employee> filter(List<Employee> employees) {
        return employees.stream()
                .filter(e -> e.getSalary() > 90000)
                .sorted(Comparator.comparingInt(Employee::getSalary).reversed()).limit(3).toList();
    }
}
