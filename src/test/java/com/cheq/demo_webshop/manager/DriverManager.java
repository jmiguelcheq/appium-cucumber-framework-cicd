//package com.cheq.demo_webshop.manager;
//
//import io.appium.java_client.android.AndroidDriver;
//
///**
// * Provides a global access point for managing the AndroidDriver instance
// * used throughout the test session. Ensures that all tests use the same
// * driver, supporting mobile browser automation and resource management.
// */
//public class DriverManager {
//    private static AndroidDriver driver;
//
//    /**
//     * Retrieves the current AndroidDriver instance.
//     * This allows test classes to interact with the mobile browser.
//     *
//     * @return the active AndroidDriver instance, or null if not set
//     */
//    public static AndroidDriver getDriver() {
//        return driver;
//    }
//
//    /**
//     * Assigns the AndroidDriver instance to be used for automation.
//     * Typically called during test setup to initialize the driver.
//     *
//     * @param driverInstance the AndroidDriver to use for the session
//     */
//    public static void setDriver(AndroidDriver driverInstance) {
//        driver = driverInstance;
//    }
//}

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
