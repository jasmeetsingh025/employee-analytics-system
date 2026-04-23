# Employee Analytics System

A backend Java project built to demonstrate core Java 8 features 
in a realistic enterprise scenario.

## Features

### Stream Analytics
- Average salary per department
- Top 3 earners across the company
- Employee count per department

### Async Data Loading (CompletableFuture)
- Parallel async loading of employee and budget data
- thenCombine to merge two independent futures
- Budget vs actual salary report
- Exception handling with exceptionally()

### Budget Alert System
- Automatically detects departments exceeding budget
- Built as an async pipeline using CompletableFuture + Streams

### Safe Data Access (Optional)
- Employee search by name
- Null-safe handling with ifPresentOrElse and orElse

## Java 8 Concepts Demonstrated
- Lambda expressions
- Stream API (filter, map, collect, groupingBy, summingInt)
- CompletableFuture (supplyAsync, thenCombine, thenAccept)
- Optional (ofNullable, ifPresentOrElse, orElse)
- Method references
- Functional interfaces