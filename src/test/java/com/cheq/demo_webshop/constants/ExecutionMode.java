package com.cheq.demo_webshop.constants;

public enum ExecutionMode {
    MOBILE_WEB,
    NATIVE_APP;

    public static ExecutionMode from(String value) {
        if (value == null) {
            throw new IllegalArgumentException("executionMode cannot be null");
        }

        return switch (value.trim().toLowerCase()) {
            case "mobileweb" -> MOBILE_WEB;
            case "nativeapp" -> NATIVE_APP;
            default -> throw new IllegalArgumentException("Unsupported executionMode: " + value);
        };
    }
}
