package com.cheq.demo_webshop.manager;

public class StepExecutionContext {

    private static String stepType;

    private StepExecutionContext() {
    }

    public static void setStepType(String type) {
        stepType = type;
    }

    public static String getStepType() {
        return stepType;
    }

    public static void clear() {
        stepType = null;
    }
}
