package com.example.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.data.EmployeeDataStore;
import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeSearchService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeSearchService searchService;

    public EmployeeController(EmployeeSearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return EmployeeDataStore.getEmployees();
    }

    // GET /api/employees/department/Engineering
    @GetMapping("/department/{department}")
    public List<Employee> getByDepartment(@PathVariable("department") String department) {
        return EmployeeDataStore.getEmployees().stream()
            .filter(e -> department.equals(e.getDepartment()))
            .toList();
    }

    // GET /api/employees/search?name=Jasmeet
    @GetMapping("/search")
    public ResponseEntity<Employee> getByName(@RequestParam("name") String name) {
        return searchService.findEmployeeByName(name)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
}
