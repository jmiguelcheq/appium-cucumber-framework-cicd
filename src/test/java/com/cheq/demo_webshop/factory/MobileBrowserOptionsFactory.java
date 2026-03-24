package com.cheq.demo_webshop.factory;

import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.logging.log4j.Logger;

import com.cheq.demo_webshop.utils.common.LoggerUtil;

public class MobileBrowserOptionsFactory {

    private static final Logger logger = LoggerUtil.getLogger(MobileBrowserOptionsFactory.class);

    private MobileBrowserOptionsFactory() {}

    public static UiAutomator2Options buildOptions(
            String platformName,
            String deviceName,
            String platformVersion,
            String automationName,
            String browserName,
            String chromedriverExecutableDir,
            String chromedriverChromeMappingFile,
            boolean chromedriverAutodownload
    ) {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(platformName);
        options.setDeviceName(deviceName);

        if (platformVersion != null && !platformVersion.isBlank()) {
            options.setPlatformVersion(platformVersion);
        }

        options.setAutomationName(automationName);
        options.setCapability("showChromedriverLog", true);
        options.setCapability("nativeWebScreenshot", true);

        String normalizedBrowser = browserName == null ? "chrome" : browserName.trim().toLowerCase();

        switch (normalizedBrowser) {
            case "chrome":
                configureChrome(
                        options,
                        chromedriverExecutableDir,
                        chromedriverChromeMappingFile,
                        chromedriverAutodownload
                );
                break;

            case "firefox":
                // TODO: Add Firefox mobile browser capability configuration here.
                logger.warn("Firefox browser setup is not implemented yet.");
                throw new UnsupportedOperationException("Firefox browser setup is not implemented yet.");

            case "edge":
                // TODO: Add Edge mobile browser capability configuration here.
                logger.warn("Edge browser setup is not implemented yet.");
                throw new UnsupportedOperationException("Edge browser setup is not implemented yet.");

            default:
                logger.error("Unsupported browserName: {}", browserName);
                throw new IllegalArgumentException("Unsupported browserName: " + browserName);
        }

        return options;
    }

    private static void configureChrome(
            UiAutomator2Options options,
            String chromedriverExecutableDir,
            String chromedriverChromeMappingFile,
            boolean chromedriverAutodownload
    ) {
        options.setCapability("browserName", "Chrome");

        if (chromedriverAutodownload) {
            options.setCapability("chromedriverAutodownload", true);
            logger.info("ChromeDriver autodownload enabled.");
        }

        if (chromedriverExecutableDir != null && !chromedriverExecutableDir.isBlank()) {
            options.setCapability("chromedriverExecutableDir", chromedriverExecutableDir);
            logger.info("Using chromedriverExecutableDir = {}", chromedriverExecutableDir);
        }

        if (chromedriverChromeMappingFile != null && !chromedriverChromeMappingFile.isBlank()) {
            options.setCapability("chromedriverChromeMappingFile", chromedriverChromeMappingFile);
            logger.info("Using chromedriverChromeMappingFile = {}", chromedriverChromeMappingFile);
        }

        logger.info("Chrome mobile browser configuration applied.");
    }
}
