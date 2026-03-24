package com.cheq.demo_webshop.factory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URI;
import java.net.URL;

import org.apache.logging.log4j.Logger;

import com.cheq.demo_webshop.constants.ExecutionMode;
import com.cheq.demo_webshop.manager.ExecutionContextManager;
import com.cheq.demo_webshop.utils.common.ConfigReader;
import com.cheq.demo_webshop.utils.common.LoggerUtil;

public class AndroidDriverFactory {
	
    private static final Logger logger = LoggerUtil.getLogger(AndroidDriverFactory.class);

    public static AndroidDriver loadDriver() throws Exception {

        String env = System.getProperty("env", "dev");
        ConfigReader.loadProperties(env);

        String platformName = System.getProperty("platformName", ConfigReader.get("platformName"));
        String deviceName = System.getProperty("deviceName", ConfigReader.get("deviceName"));
        String platformVersion = System.getProperty("platformVersion", ConfigReader.get("platformVersion"));
        String automationName = System.getProperty("automationName", ConfigReader.get("automationName"));
        String urlPath = System.getProperty("appium.server.url", ConfigReader.get("urlPath"));

        ExecutionMode executionMode = ExecutionContextManager.getExecutionMode();

        try {
            UiAutomator2Options options;

            if (executionMode == ExecutionMode.MOBILE_WEB) {
                String browserName = System.getProperty("browserName", ConfigReader.get("browserName"));

                String chromedriverExecutableDir = System.getProperty(
                        "chromedriverExecutableDir",
                        ConfigReader.get("chromedriverExecutableDir")
                );

                String chromedriverChromeMappingFile = System.getProperty(
                        "chromedriverChromeMappingFile",
                        ConfigReader.get("chromedriverChromeMappingFile")
                );

                boolean chromedriverAutodownload = Boolean.parseBoolean(
                        System.getProperty("chromedriverAutodownload", ConfigReader.get("chromedriverAutodownload"))
                );

                options = MobileBrowserOptionsFactory.buildOptions(
                        platformName,
                        deviceName,
                        platformVersion,
                        automationName,
                        browserName,
                        chromedriverExecutableDir,
                        chromedriverChromeMappingFile,
                        chromedriverAutodownload
                );

                logger.info("Initializing Appium driver in MOBILE_WEB mode");
                logger.info("browserName = {}", browserName);
                logger.info("chromedriverAutodownload = {}", chromedriverAutodownload);

            } else if (executionMode == ExecutionMode.NATIVE_APP) {
                String appPackage = System.getProperty("appPackage", ConfigReader.get("appPackage"));
                String appActivity = System.getProperty("appActivity", ConfigReader.get("appActivity"));
                boolean noReset = Boolean.parseBoolean(
                        System.getProperty("noReset", ConfigReader.get("noReset"))
                );

                options = NativeAppOptionsFactory.buildOptions(
                        platformName,
                        deviceName,
                        platformVersion,
                        automationName,
                        appPackage,
                        appActivity,
                        noReset
                );

                logger.info("Initializing Appium driver in NATIVE_APP mode");
                logger.info("appPackage = {}", appPackage);
                logger.info("appActivity = {}", appActivity);
                logger.info("noReset = {}", noReset);

            } else {
                throw new IllegalStateException("Unsupported execution mode: " + executionMode);
            }

            URL url = URI.create(urlPath).toURL();
            AndroidDriver driver = new AndroidDriver(url, options);

            logger.info("Appium Driver initialized successfully");
            logger.info("platformName = {}", platformName);
            logger.info("deviceName = {}", deviceName);
            logger.info("platformVersion = {}", platformVersion);
            logger.info("automationName = {}", automationName);
            logger.info("appiumServerUrl = {}", urlPath);
            logger.info("executionMode = {}", executionMode);

            return driver;

        } catch (Exception e) {
            logger.error("Error initializing the Appium driver", e);
            throw new Exception("Error initializing the Appium driver: " + e.getMessage(), e);
        }
    }
}
