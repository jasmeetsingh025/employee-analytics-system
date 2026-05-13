package com.example.v2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ReportGenerator {

    private final Semaphore dbConnectionPool;

    public ReportGenerator(Semaphore dbConnectionPool) {
        this.dbConnectionPool = dbConnectionPool;
    }

    public void generateReport() {
        CountDownLatch latch = new CountDownLatch(3);
        try (ExecutorService executor = Executors.newFixedThreadPool(3)) {
            AsyncDataService asyncDataService = new AsyncDataService();
            AlertService alertService = new AlertService();
            EmployeeSearchService employeeSearchService = new EmployeeSearchService();
            System.out.println("================ EMPLOYEE REPORT GENERATION STARTED ================");

            executor.submit(() -> {
                try {
                    withSimulatedDbConnection("Finance slice: budget vs actual", () -> {
                        System.out.println("[DEPARTMENT REPORT] threadDeptContext=" + DepartmentContext.get());
                        asyncDataService.printBudgetVsActualReport();
                    });
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
            executor.submit(() -> {
                try {
                    withSimulatedDbConnection("Finance slice: budget alerts", () -> {
                        System.out.println("[BUDGET ALERT] threadDeptContext=" + DepartmentContext.get());
                        alertService.checkBudgetAlerts();
                    });
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
            executor.submit(() -> {
                try {
                    withSimulatedDbConnection("Finance slice: top earners by dept", () -> {
                        System.out.println("[TOP EARNERS] threadDeptContext=" + DepartmentContext.get());
                        employeeSearchService.printHighestPaidEmployeeInEachDept();
                    });
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
            try {
                latch.await();
                System.out.println("================ EMPLOYEE REPORT GENERATION COMPLETED ================");
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Takes one permit from the pool, sets {@link DepartmentContext}, sleeps to imitate query latency,
     * runs work, then clears context and releases the permit.
     */
    private void withSimulatedDbConnection(String departmentContext, ThrowingRunnable work) throws Exception {
        try {
            dbConnectionPool.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw e;
        }
        try {
            DepartmentContext.set(departmentContext);
            // Thread.sleep(5000);
            work.run();
        } finally {
            DepartmentContext.clear();
            dbConnectionPool.release();
        }
    }

    @FunctionalInterface
    private interface ThrowingRunnable {
        void run() throws Exception;
    }
}
