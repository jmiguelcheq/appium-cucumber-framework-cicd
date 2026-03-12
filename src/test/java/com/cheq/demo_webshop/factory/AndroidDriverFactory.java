package com.cheq.demo_webshop.factory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.net.URI;
import java.net.URL;

import com.cheq.demo_webshop.utils.ConfigReader;

/**
 * Factory class for creating AndroidDriver instances configured
 * for Chrome browser automation using Appium and UiAutomator2.
 */
public class AndroidDriverFactory {
    /**
     * Initializes the AndroidDriver instance for the current thread.
     *
     * Sets up the driver with UiAutomator2 options for Chrome browser automation
     * on an Android device.
     * @throws Exception
     */
    public static AndroidDriver loadDriver() throws Exception {

    	String env = System.getProperty("env", "dev");
        ConfigReader.loadProperties(env);

        String platformName = ConfigReader.get("platformName");
        String deviceName = ConfigReader.get("deviceName");
        String platformVersion = ConfigReader.get("platformVersion");
        String automationName = ConfigReader.get("automationName");
        String browserName = ConfigReader.get("browserName");
        String chromeDriverFilePath = ConfigReader.get("chromedriverPath");
        
        UiAutomator2Options options = new UiAutomator2Options();
        
        try {
            options.setPlatformName(platformName);
            options.setDeviceName(deviceName);
            options.setPlatformVersion(platformVersion);
            options.setAutomationName(automationName);
            options.setCapability("browserName", browserName);
            String chromeDriverPath = new File(chromeDriverFilePath).getAbsolutePath();
            options.setCapability("chromedriverExecutable", chromeDriverPath);

            URL url = URI.create("http://localhost:4723").toURL();
            AndroidDriver driver = new AndroidDriver(url, options);
            
            System.out.println("Appium Driver initialized successfully");
            
            return driver;
        } catch (Exception e) {
            throw new Exception("Error initializing the Appium driver: " + e.getMessage());
        }

    }

}
