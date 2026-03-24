package com.cheq.demo_webshop.factory;

import io.appium.java_client.android.options.UiAutomator2Options;

public class NativeAppOptionsFactory {

    private NativeAppOptionsFactory() {
    }

    public static UiAutomator2Options buildOptions(String platformName,
                                                   String deviceName,
                                                   String platformVersion,
                                                   String automationName,
                                                   String appPackage,
                                                   String appActivity,
                                                   boolean noReset) {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(platformName);
        options.setDeviceName(deviceName);
        options.setPlatformVersion(platformVersion);
        options.setAutomationName(automationName);
        options.setAppPackage(appPackage);
        options.setAppActivity(appActivity);
        options.setNoReset(noReset);

        return options;
    }
}
