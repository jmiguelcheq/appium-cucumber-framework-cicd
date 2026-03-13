package com.cheq.demo_webshop.factory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.net.URI;
import java.net.URL;

import com.cheq.demo_webshop.utils.ConfigReader;

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

        String chromeDriverFilePath = System.getProperty("chromedriverPath", ConfigReader.get("chromedriverPath"));
        String chromedriverExecutableDir = System.getProperty("chromedriverExecutableDir", "");
        String chromedriverChromeMappingFile = System.getProperty("chromedriverChromeMappingFile", "");

        UiAutomator2Options options = new UiAutomator2Options();

        try {
            options.setPlatformName(platformName);
            options.setDeviceName(deviceName);

            if (platformVersion != null && !platformVersion.isBlank()) {
                options.setPlatformVersion(platformVersion);
            }

            options.setAutomationName(automationName);
            options.setCapability("browserName", browserName);

            options.setCapability("showChromedriverLog", true);
            options.setCapability("nativeWebScreenshot", true);

            // Priority 1: explicit local chromedriver path
            if (chromeDriverFilePath != null && !chromeDriverFilePath.isBlank()) {
                File chromeDriverFile = new File(chromeDriverFilePath);
                if (chromeDriverFile.exists()) {
                    options.setCapability("chromedriverExecutable", chromeDriverFile.getAbsolutePath());
                    System.out.println("Using local chromedriverExecutable = " + chromeDriverFile.getAbsolutePath());
                } else {
                    System.out.println("Configured chromedriverPath not found: " + chromeDriverFile.getAbsolutePath());
                }
            }

            // Priority 2: CI / autodownload directory
            if (chromedriverExecutableDir != null && !chromedriverExecutableDir.isBlank()) {
                options.setCapability("chromedriverExecutableDir", chromedriverExecutableDir);
                System.out.println("Using chromedriverExecutableDir = " + chromedriverExecutableDir);
            }

            if (chromedriverChromeMappingFile != null && !chromedriverChromeMappingFile.isBlank()) {
                options.setCapability("chromedriverChromeMappingFile", chromedriverChromeMappingFile);
                System.out.println("Using chromedriverChromeMappingFile = " + chromedriverChromeMappingFile);
            }

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