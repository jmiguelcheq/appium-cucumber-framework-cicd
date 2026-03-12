package com.cheq.demo_webshop.factory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.URI;
import java.net.URL;

import com.cheq.demo_webshop.utils.ConfigReader;

/**
 * Factory class for creating AndroidDriver instances configured
 * for Chrome browser automation using Appium and UiAutomator2.
 */
public class AndroidDriverFactory {

    public static AndroidDriver loadDriver() throws Exception {

        String env = System.getProperty("env", "dev");
        ConfigReader.loadProperties(env);

        String platformName = System.getProperty("platformName", ConfigReader.get("platformName"));
        String deviceName = System.getProperty("deviceName", ConfigReader.get("deviceName"));
        String platformVersion = System.getProperty("platformVersion", ConfigReader.get("platformVersion"));
        String automationName = System.getProperty("automationName", ConfigReader.get("automationName"));
        String browserName = System.getProperty("browserName", ConfigReader.get("browserName"));
        String urlPath = System.getProperty("appium.server.url", ConfigReader.get("urlPath"));

        UiAutomator2Options options = new UiAutomator2Options();

        try {
            options.setPlatformName(platformName);
            options.setDeviceName(deviceName);

            if (platformVersion != null && !platformVersion.isBlank()) {
                options.setPlatformVersion(platformVersion);
            }

            options.setAutomationName(automationName);
            options.setCapability("browserName", browserName);

            URL url = URI.create(urlPath).toURL();
            AndroidDriver driver = new AndroidDriver(url, options);

            System.out.println("Appium Driver initialized successfully");
            System.out.println("platformName = " + platformName);
            System.out.println("deviceName = " + deviceName);
            System.out.println("platformVersion = " + platformVersion);
            System.out.println("browserName = " + browserName);
            System.out.println("appiumServerUrl = " + urlPath);

            return driver;

        } catch (Exception e) {
            throw new Exception("Error initializing the Appium driver: " + e.getMessage(), e);
        }
    }
}
