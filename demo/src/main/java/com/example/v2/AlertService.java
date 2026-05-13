package com.example.v2;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class AlertService {
    public void checkBudgetAlerts() throws InterruptedException, ExecutionException {
        AsyncDataService asyncDataService = new AsyncDataService();
        CompletableFuture<List<Employee>> employees = asyncDataService.loadEmployeesAsync();
        CompletableFuture<Map<String, Integer>> budgets = asyncDataService.loadDepartmentBudgetsAsync();

        employees.thenCombine(budgets, (employee, budget) -> {
            Map<String, Integer> actualSalary = employee.stream()
            .filter(s -> s.getDepartment() != null)
            .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingInt(Employee::getSalary)));

            return budget.entrySet().stream()
            .filter(e -> actualSalary.getOrDefault(e.getKey(), 0) > e.getValue())
            .map(e -> {
                String dept = e.getKey();
                int budgetValue = e.getValue();
                int actualSal = actualSalary.getOrDefault(dept, 0);
                return "Alert: " + dept + " over budget by " + (actualSal - budgetValue);
            }).collect(Collectors.toList());
        })
        .thenAccept(alerts -> alerts.forEach(System.out::println))
        .thenRun(() -> System.out.println("All alerts processed."))
        .exceptionally(ex -> {
            System.out.println("Error: " + ex.getMessage());
            return null;
        })
        .get();
    }
}
