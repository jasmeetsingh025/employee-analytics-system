package com.example.v2;

public class EmployeeReportFactory {
    public static EmployeeReport createReport(String reportType) {
        return switch (reportType) {
            case "console" -> new ConsoleReport();
            case "summary" -> new SummaryReport();
            case "alert" -> new AlertReport();
            default -> throw new IllegalArgumentException("Invalid report type: " + reportType);
        };
    }
}
