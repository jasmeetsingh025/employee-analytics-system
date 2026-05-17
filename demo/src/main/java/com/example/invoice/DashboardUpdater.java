package com.example.invoice;

public class DashboardUpdater implements InvoiceObserver {
    @Override
    public void onInvoiceChanged(long invoiceId, String newStatus) {
        System.out.println("Dashboard updated: Invoice " + invoiceId + " status changed to " + newStatus);
    }
}
