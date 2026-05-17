package com.example.invoice;

import java.util.ArrayList;
import java.util.List;

public class ObservableInvoices implements InvoiceSubject {
    private long invoiceId;
    private String status;
    private List<InvoiceObserver> observers = new ArrayList<>();

    public ObservableInvoices(long invoiceId, String status) {
        this.invoiceId = invoiceId;
        this.status = status;
    }

    @Override
    public void addObserver(InvoiceObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(InvoiceObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (InvoiceObserver observer : observers) {
            observer.onInvoiceChanged(this.invoiceId, this.status);
        }
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
        notifyObservers();
    }
}
