package com.example.v2;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class AsyncDataService {

    public CompletableFuture<List<Employee>> loadEmployeesAsync() {
        CompletableFuture<List<Employee>> employeeList = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
            return EmployeeDataStore.getEmployees();
        });
        return employeeList;
    }

    public CompletableFuture<Map<String, Integer>> loadDepartmentBudgetsAsync() {
        CompletableFuture<Map<String, Integer>> deptBudget = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
            return Map.of("Engineering", 300000, "Marketing", 800000, "HR", 600000);
        });
        return deptBudget;
    }

    public void printBudgetVsActualReport() throws InterruptedException, ExecutionException {
        CompletableFuture<List<Employee>> employeeList = loadEmployeesAsync();
        CompletableFuture<Map<String, Integer>> deptBudget = loadDepartmentBudgetsAsync();

        employeeList.thenCombine(deptBudget, (employee, budgets) -> {

            Map<String, Integer> actual = employee.stream()
                    .filter(e -> e.getDepartment() != null)
                    .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingInt(Employee::getSalary)));

            return budgets.entrySet().stream()
                    .map(e -> {
                        String dept = e.getKey();
                        int budget = e.getValue();
                        int actualSal = actual.getOrDefault(dept, 0);
                        int remaining = budget - actualSal;
                        return dept + " | Budget " + budget + " | Actual " + actualSal + " | Remaining " + remaining;
                    }).toList();
        })
                .thenAccept(lines -> lines.forEach(System.out::println))
                .exceptionally(ex -> {
                    System.out.println("Error: " + ex.getMessage());
                    return null;
                })
                .get();
    }

}
