package com.example.v2;

import java.util.List;

public class EmployeeDataStore {
    public static List<Employee> getEmployees() {
        return List.of(
            new Employee("Jasmeet",  "Engineering", 115000),
            new Employee("Rahul",    "Engineering",  195000),
            new Employee("Anika",    "Engineering",  78000),
            new Employee("Priya",    "Marketing",    72000),
            new Employee("Sameer",   "Marketing",    68000),
            new Employee("Divya",    "Marketing",    85000),
            new Employee("Lakhan",   "HR",           55000),
            new Employee("Taran",    "HR",           61000),
            new Employee("Ghost",     null,          40000)  // kept your null dept idea
            );
    }
}
