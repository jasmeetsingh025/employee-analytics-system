package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.api.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getByName(String name) {
        return repository.findByName(name)
            .orElseThrow(() -> new EmployeeNotFoundException(name));
    }

    public List<Employee> getByDepartment(String department) {
        return repository.findByDepartment(department);
    }

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}