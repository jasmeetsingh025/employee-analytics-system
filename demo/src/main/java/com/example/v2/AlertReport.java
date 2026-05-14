package com.example.v2;

import java.util.concurrent.ExecutionException;

public class AlertReport implements EmployeeReport {
    @Override
    public void generateReport() {
        AlertService alertService = new AlertService();
        try {
            alertService.checkBudgetAlerts();
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        }
    }
    
}
