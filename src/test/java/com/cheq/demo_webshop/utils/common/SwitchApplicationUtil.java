//package com.cheq.demo_webshop.utils.common;
//
//import io.appium.java_client.android.AndroidDriver;
//import com.google.common.collect.ImmutableMap;
//
//import java.time.Duration;
//import java.util.List;
//import java.util.Properties;
//import java.util.Set;
//import java.util.ArrayList;
//
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class SwitchApplicationUtil {
//    private AndroidDriver driver;
//    private ElementWaitUtil waitUtil;
//    private ScreenshotUtil screenshotUtil;
//    private ReporterUtil reportsUtil;
//    private String logLevel;
//
//    public SwitchApplicationUtil(AndroidDriver driver, ScreenshotUtil screenshotUtil) {
//        this.driver = driver;
//        this.screenshotUtil = screenshotUtil;
//        this.waitUtil = new ElementWaitUtil(driver, screenshotUtil);
//        this.reportsUtil = new ReporterUtil(driver, screenshotUtil);
//    }
//
//    /** Method to switch to a different application */
//    public void switchApplication(String packageName, String activityName) throws Exception {
//        try {
//            driver.executeScript("mobile: startActivity", ImmutableMap.of("package", packageName, "activity", activityName));
//            logLevel = "INFO";
//            reportsUtil.resultsReporter(logLevel, LogMessage.formatMessage(LogMessage.SWITCH_APPLICATION_SUCCESS_MESSAGE, packageName, activityName), "Switch Application");
//
//        } catch (Exception e) {
//            logLevel = "ERROR";
//            reportsUtil.resultsReporter(logLevel, LogMessage.formatMessage(LogMessage.SWITCH_APPLICATION_FAILED_MESSAGE, packageName, activityName, e.getMessage()), "Switch Application");
//            throw e;
//        }
//    }
//
//    /** Method to switch to the native app context */
//    public void switchToNativeAppContext() {
//        try {
//            Set<String> contextHandles = driver.getContextHandles();
//
//            for (String context : contextHandles) {
//                if (context.contains("NATIVE_APP")) {
//                    driver.context(context);
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Failed to switch to native app context: " + e.getMessage());
//        }
//    }
//
//    /** Method to switch to the Chromium context */
//    public void switchToChromiumContext() {
//        try {
//            Set<String> contextHandles = driver.getContextHandles();
//            if (contextHandles.contains("CHROMIUM")) {
//                driver.context("CHROMIUM");
//            }
//        } catch (Exception e) {
//            System.out.println("Failed to switch to Chromium context: " + e.getMessage());
//        }
//    }
//
//    public void waitForNewTab() {
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//            wait.until(d -> d.getWindowHandles().size() > 1); // Wait until there's more than 1 window handle
//        } catch (Exception e) {
//            System.out.println("Failed to wait for new tab: " + e.getMessage());
//        }
//    }
//
//    // Method to switch to the newest tab (the last one opened)
//    public void switchToNewestTab() {
//        try {
//            waitForNewTab();
//            List<String> currentHandles = new ArrayList<>(driver.getWindowHandles()); // FIXED HERE
//
//            if (currentHandles.isEmpty()) {
//                throw new Exception("No tabs available to switch.");
//            }
//
//            driver.switchTo().window(currentHandles.get(currentHandles.size() - 1)); // Switch to the last tab
//        } catch (Exception e) {
//            System.out.println("Failed to switch to newest tab: " + e.getMessage());
//        }
//    }
//
//
//}