package com.example.v2;

public final class DepartmentContext {

    private static final ThreadLocal<String> CURRENT = new ThreadLocal<>();

    private DepartmentContext() {
    }

    public static void set(String department) {
        CURRENT.set(department);
    }

    public static String get() {
        return CURRENT.get();
    }

    public static void clear() {
        CURRENT.remove();
    }
}
