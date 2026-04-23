package com.example.v2;

import java.util.Optional;

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
}
