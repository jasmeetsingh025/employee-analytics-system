package com.example.employee.service;

import com.example.employee.data.EmployeeDataStore;
import com.example.employee.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {


    public void getAverageSalaryByDepartment() {
        List<Employee> employees = EmployeeDataStore.getEmployees();
        employees.stream()
        .filter(e -> e.getDepartment() != null)
        .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)))
        .entrySet().stream()
        .filter(e -> e.getKey() != null)
        .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
    }

    public void printTopThreeEarners() {
        List<Employee> employees = EmployeeDataStore.getEmployees();
        employees.stream()
        .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
        .limit(3)
        .forEach(System.out::println);
    }

    public void printEmployeeCountByDepartment(){
        List<Employee> employees = EmployeeDataStore.getEmployees();
        employees.stream()
        .filter(e -> e.getDepartment() != null)
        .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
        .forEach((dept, count) -> System.out.println(dept + " -> " + count));
    }
}
