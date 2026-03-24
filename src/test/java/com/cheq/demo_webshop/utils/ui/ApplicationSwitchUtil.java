package com.cheq.demo_webshop.utils.ui;

import java.util.Set;

import org.apache.logging.log4j.Logger;

import com.cheq.demo_webshop.constants.ApplicationType;
import com.cheq.demo_webshop.manager.ApplicationContextManager;
import com.cheq.demo_webshop.utils.common.ConfigReader;
import com.cheq.demo_webshop.utils.common.LoggerUtil;
import io.appium.java_client.android.AndroidDriver;

public class ApplicationSwitchUtil {

    private static final Logger logger = LoggerUtil.getLogger(ApplicationSwitchUtil.class);

    private final AndroidDriver driver;

    public ApplicationSwitchUtil(AndroidDriver driver) {
        this.driver = driver;
    }

    public void switchToNativeApp() {
        String appPackage = ConfigReader.get("appPackage");

        driver.activateApp(appPackage);

        switchToNativeContext();

        ApplicationContextManager.setCurrentApplication(ApplicationType.NATIVE_APP);
        logger.info("Switched to native app: {}", appPackage);
    }

    public void switchToNativeContext() {
        try {
            Set<String> contexts = driver.getContextHandles();
            logger.info("Available contexts before switching to native: {}", contexts);
        } catch (Exception e) {
            logger.warn("Unable to get context handles: {}", e.getMessage());
        }

        driver.context("NATIVE_APP");
        logger.info("Switched context to NATIVE_APP");
    }

    public void switchToChrome() {
        String chromePackage = ConfigReader.get("chromeAppPackage");
        driver.activateApp(chromePackage);

        ApplicationContextManager.setCurrentApplication(ApplicationType.CHROME);
        logger.info("Switched to Chrome app: {}", chromePackage);
    }

    public String getCurrentPackage() {
        String currentPackage = driver.getCurrentPackage();
        logger.info("Current foreground package: {}", currentPackage);
        return currentPackage;
    }
}
