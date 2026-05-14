package com.example.v2;

import java.util.concurrent.ExecutionException;

public class SummaryReport implements EmployeeReport {
    @Override
    public void generateReport() {
        AsyncDataService asyncDataService = new AsyncDataService();
        try {
            asyncDataService.printHeadCountAndTotalSalary();
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        }
    }
}
