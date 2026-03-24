package com.cheq.demo_webshop.manager;

import io.appium.java_client.android.AndroidDriver;

/**
 * Provides thread-safe access to the AndroidDriver instance
 * used throughout the test session.
 */
public class DriverManager {

    private static final ThreadLocal<AndroidDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * Retrieves the current AndroidDriver instance.
     *
     * @return the active AndroidDriver instance, or null if not set
     */
    public static AndroidDriver getDriver() {
        return driverThreadLocal.get();
    }

    /**
     * Assigns the AndroidDriver instance to be used for automation.
     *
     * @param driverInstance the AndroidDriver to use for the session
     */
    public static void setDriver(AndroidDriver driverInstance) {
        driverThreadLocal.set(driverInstance);
    }

    /**
     * Clears the current thread's driver reference.
     */
    public static void unload() {
        driverThreadLocal.remove();
    }
}
