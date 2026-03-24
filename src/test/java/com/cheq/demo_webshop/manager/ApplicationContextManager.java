package com.cheq.demo_webshop.manager;

import com.cheq.demo_webshop.constants.ApplicationType;

public class ApplicationContextManager {

    private static final ThreadLocal<ApplicationType> currentApplication = new ThreadLocal<>();

    private ApplicationContextManager() {
    }

    public static void setCurrentApplication(ApplicationType applicationType) {
        currentApplication.set(applicationType);
    }

    public static ApplicationType getCurrentApplication() {
        ApplicationType applicationType = currentApplication.get();
        if (applicationType == null) {
            throw new IllegalStateException("Current application is not initialized for this thread");
        }
        return applicationType;
    }

    public static boolean isChrome() {
        return getCurrentApplication() == ApplicationType.CHROME;
    }

    public static boolean isNativeApp() {
        return getCurrentApplication() == ApplicationType.NATIVE_APP;
    }

    public static void unload() {
        currentApplication.remove();
    }
}
