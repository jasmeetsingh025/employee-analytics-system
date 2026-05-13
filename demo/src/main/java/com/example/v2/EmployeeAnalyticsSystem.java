package com.example.v2;

import java.util.concurrent.Semaphore;

/**
 * Entry point for running employee analytics. Owns a small simulated "DB pool"
 * ({@link Semaphore} with 3 permits) and delegates parallel report work to
 * {@link ReportGenerator}, which uses:
 * <ul>
 *   <li>{@link java.util.concurrent.CountDownLatch} — block until all three data-source tasks finish</li>
 *   <li>{@link Semaphore} — at most three concurrent simulated DB uses</li>
 *   <li>{@link DepartmentContext} — {@link ThreadLocal} department label per worker thread</li>
 * </ul>
 */
public class EmployeeAnalyticsSystem {

    private final Semaphore dbConnectionPool = new Semaphore(2);
    private final ReportGenerator reportGenerator = new ReportGenerator(dbConnectionPool);

    public void runParallelReports() {
        reportGenerator.generateReport();
    }
}
