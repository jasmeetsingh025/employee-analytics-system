package com.example.v2;

import java.util.List;

public interface SalaryFilterStrategy {
    List<Employee> filter(List<Employee> employees);
}
