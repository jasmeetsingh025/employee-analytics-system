package com.example.invoice;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n===== OBSERVER PATTERN =====");
        ObservableInvoices invoice = new ObservableInvoices(1001L, "PENDING");

        invoice.addObserver(new FinanceNotifier());
        invoice.addObserver(new AuditLogger());
        invoice.addObserver(new DashboardUpdater());

        invoice.setStatus("APPROVED");
        invoice.setStatus("PAID");
    }
}
