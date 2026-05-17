package com.example.invoice;

public interface InvoiceSubject {
    void addObserver(InvoiceObserver observer);
    void removeObserver(InvoiceObserver observer);
    void notifyObservers();
}
