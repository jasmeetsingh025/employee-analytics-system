package com.example.v2;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("===== ANALYTICS =====");
        AnalyticsService analytics = new AnalyticsService();
        analytics.getAverageSalaryByDepartment();
        analytics.printTopThreeEarners();
        analytics.printEmployeeCountByDepartment();

        System.out.println("\n===== ASYNC REPORT =====");
        AsyncDataService async = new AsyncDataService();
        async.printBudgetVsActualReport();

        System.out.println("\n===== EMPLOYEE SEARCH =====");
        EmployeeSearchService search = new EmployeeSearchService();
        search.printEmployeeDetails("Jasmeet");
        search.printEmployeeDetails("Batman");
        search.printEmployeeDetails("Ghost");

        System.out.println("\n===== ALERT SERVICE =====");
        AlertService alert = new AlertService();
        alert.checkBudgetAlerts();

        System.out.println("\n===== HIGHEST PAID EMPLOYEE IN EACH DEPT =====");
        search.printHighestPaidEmployeeInEachDept();
    }
}
