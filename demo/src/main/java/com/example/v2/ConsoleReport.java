package com.example.v2;

import java.util.concurrent.ExecutionException;

public class ConsoleReport implements EmployeeReport {
    @Override
    public void generateReport() {
        AsyncDataService asyncDataService = new AsyncDataService();
        try {
            asyncDataService.printBudgetVsActualReport();
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        }
    }
}
