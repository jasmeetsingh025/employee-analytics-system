package com.example.invoice;

public class AuditLogger implements InvoiceObserver {
    @Override
    public void onInvoiceChanged(long invoiceId, String newStatus) {
        System.out.println("Audit logged: Invoice " + invoiceId + " status changed to " + newStatus);
    }
}
