package com.example.api.exception;

public class InvalidReportTypeException extends RuntimeException {
    public InvalidReportTypeException(String message) {
        super("Invalid report type: " + message);
    }
}
