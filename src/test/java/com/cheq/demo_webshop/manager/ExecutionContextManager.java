package com.cheq.demo_webshop.manager;

import com.cheq.demo_webshop.constants.ExecutionMode;
import com.cheq.demo_webshop.utils.common.ConfigReader;

public class ExecutionContextManager {

    private static final ThreadLocal<ExecutionMode> executionMode = new ThreadLocal<>();

    public static void setExecutionMode() {
        String mode = ConfigReader.get("executionMode");
        executionMode.set(ExecutionMode.from(mode));
    }
    
    public static void setExecutionMode(String mode) {
        executionMode.set(ExecutionMode.from(mode));
    }

    public static ExecutionMode getExecutionMode() {
        ExecutionMode mode = executionMode.get();
        
        if (mode == null) {
            throw new IllegalStateException("Execution mode is not initialized for this thread");
        }
        
        return mode;
    }

    public static boolean isNativeApp() {
        return getExecutionMode() == ExecutionMode.NATIVE_APP;
    }

    public static boolean isMobileWeb() {
        return getExecutionMode() == ExecutionMode.MOBILE_WEB;
    }

    public static void unload() {
        executionMode.remove();
    }
}
