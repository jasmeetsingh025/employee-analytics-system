package com.example.employee.data;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataSeeder {

    private final EmployeeRepository repository;

    public DataSeeder(EmployeeRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void seed() {
        // Only seed if table is empty
        if(repository.count() == 0) {
            repository.saveAll(List.of(
                new Employee("Jasmeet",  "Engineering", 115000),
                new Employee("Rahul",    "Engineering",  95000),
                new Employee("Anika",    "Engineering",  78000),
                new Employee("Priya",    "Marketing",    72000),
                new Employee("Sameer",   "Marketing",    68000),
                new Employee("Divya",    "Marketing",    85000),
                new Employee("Lakhan",   "HR",           55000),
                new Employee("Taran",    "HR",           61000),
                new Employee("Ghost",     "Unknown",     40000)
            ));
            System.out.println("✅ Database seeded with employee data");
        }
    }
}