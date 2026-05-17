package com.example.invoice;

public interface InvoiceObserver {
    void onInvoiceChanged(long invoiceId, String newStatus);
}
