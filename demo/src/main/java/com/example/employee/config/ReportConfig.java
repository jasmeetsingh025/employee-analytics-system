package com.example.employee.config;

public class ReportConfig {
    private final String reportType;
    private final String department;
    private final int minSalary;
    private final boolean includeAlerts;
    private final int maxResults;

    private ReportConfig(Builder builder) {
        this.reportType = builder.reportType;
        this.department = builder.department;
        this.minSalary = builder.minSalary;
        this.includeAlerts = builder.includeAlerts;
        this.maxResults = builder.maxResults;
    }

    public static class Builder {
        //# Required fields
        private final String reportType;

        //# Optional Fields
        private String department;
        private int minSalary;
        private boolean includeAlerts;
        private int maxResults;

        // Builder constructor takes only required fields
        public Builder(String reportType) {
            this.reportType = reportType;
        }

        // Each setter returns Builder — enables chaining
        public Builder Department(String department) {
            this.department = department;
            return this;
        }

        public Builder MinSalary(int minSalary) {
            this.minSalary = minSalary;
            return this;
        }

        public Builder IncludeAlerts(boolean includeAlerts) {
            this.includeAlerts = includeAlerts;
            return this;
        }

        public Builder MaxResults(int maxResults) {
            this.maxResults = maxResults;
            return this;
        }

        public ReportConfig build() {
            // Validate before building
            if(minSalary < 0) {
                throw new IllegalArgumentException(
                    "Minimum salary cannot be negative"
                );
            }
            return new ReportConfig(this);
        }
    }

    @Override
    public String toString() {
        return "ReportConfig(reportType=" + reportType + ", department=" + department + ", minSalary=" + minSalary + ", includeAlerts=" + includeAlerts + ", maxResults=" + maxResults + ")";
    }
}
