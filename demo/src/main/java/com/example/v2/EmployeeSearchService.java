package com.example.v2;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeSearchService {

    public Optional<Employee> findEmployeeByName(String name) {
        return EmployeeDataStore.getEmployees().stream()
        .filter(e -> e.getName() != null && e.getName().equalsIgnoreCase(name))
        .findFirst();
    }

    public void printEmployeeDetails(String name) {
        findEmployeeByName(name).ifPresentOrElse(
            System.out::println, 
            () -> System.out.println("Employee " + name + " not found"));
    }

    public void printHighestPaidEmployeeInEachDept() {
        EmployeeDataStore.getEmployees().stream()
        .filter(e -> e.getDepartment() != null)
        .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))))
        .forEach((dept, employee) -> System.out.println(dept + " -> " + employee.get().getName()));
    }
}
