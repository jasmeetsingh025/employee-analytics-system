package com.example.employee.filter;

import com.example.employee.data.EmployeeDataStore;
import com.example.employee.model.Employee;

import java.util.List;

public class EmployeeFilterContext {
    private SalaryFilterStrategy strategy;

    public EmployeeFilterContext(SalaryFilterStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SalaryFilterStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeFilter() {
        List<Employee> employees = EmployeeDataStore.getEmployees();
        List<Employee> filteredEmployees = strategy.filter(employees);
        System.out.println("Filter Strategy: " + strategy.getClass().getSimpleName());
        filteredEmployees.forEach(System.out::println);
    }
}
