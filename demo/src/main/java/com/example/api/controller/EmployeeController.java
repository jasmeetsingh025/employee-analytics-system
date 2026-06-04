package com.example.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.exception.EmployeeNotFoundException;
import com.example.api.exception.InvalidReportTypeException;
import com.example.employee.data.EmployeeDataStore;
import com.example.employee.model.Employee;
import com.example.employee.report.EmployeeReport;
import com.example.employee.report.EmployeeReportFactory;
import com.example.employee.service.EmployeeSearchService;
import com.example.employee.service.EmployeeService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // GET /api/employees/department/Engineering
    @GetMapping("/department/{department}")
    public List<Employee> getByDepartment(@PathVariable String department) {
        return employeeService.getByDepartment(department);
    }

    // GET /api/employees/search?name=Jasmeet
    @GetMapping("/search")
    public ResponseEntity<Employee> getByName(@RequestParam String name) {
        return ResponseEntity.ok(employeeService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee saved = employeeService.save(employee);
        return ResponseEntity.status(201).body(saved);
    }
    
    @GetMapping("/reports/{type}")
    public ResponseEntity<String> generateReport(@PathVariable String type) {
        try {
            EmployeeReport report = EmployeeReportFactory.createReport(type);
            report.generateReport();
            return ResponseEntity.ok("Report generated successfully");
        } catch (IllegalArgumentException e) {
            throw new InvalidReportTypeException(type);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
