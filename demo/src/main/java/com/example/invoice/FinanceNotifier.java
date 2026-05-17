package com.example.invoice;

public class FinanceNotifier implements InvoiceObserver {
    @Override
    public void onInvoiceChanged(long invoiceId, String newStatus) {
        System.out.println("Finance notified: Invoice " + invoiceId + " status changed to " + newStatus);
    }
}
