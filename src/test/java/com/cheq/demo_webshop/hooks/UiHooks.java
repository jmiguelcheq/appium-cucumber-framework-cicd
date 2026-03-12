package com.cheq.demo_webshop.hooks;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.apache.logging.log4j.Logger;

import com.cheq.demo_webshop.factory.AndroidDriverFactory;
import com.cheq.demo_webshop.manager.DriverManager;
import com.cheq.demo_webshop.utils.AllureEnvironmentUtil;
import com.cheq.demo_webshop.utils.AllureUtil;
import com.cheq.demo_webshop.utils.LoggerUtil;
import com.google.common.collect.ImmutableMap;

public class UiHooks {

    private static final Logger logger = LoggerUtil.getLogger(UiHooks.class);
    private AndroidDriver driver;
    private AllureUtil allureUtil;

    @Before(value = "@ui", order = 1)
    public void setUpUi(Scenario scenario) throws Exception {
        String url = com.cheq.demo_webshop.utils.ConfigReader.get("baseUrl");

        driver = AndroidDriverFactory.loadDriver();
        driver.get(url);

        DriverManager.setDriver(driver);

        allureUtil = new AllureUtil(driver);
        AllureEnvironmentUtil.writeEnvironment(
        	    ImmutableMap.<String, String>builder()
        	        .put("OS", System.getProperty("os.name"))
        	        .put("Environment", System.getProperty("env", "dev"))
        	        .put("ExecutionType", "UI")
        	        .build()
        	);

        logger.info("UI setup completed for scenario: {}", scenario.getName());
    }

    @After(order = 0, value = "@ui")
    public void tearDownUi(Scenario scenario) {
        if (driver != null) {
            driver.quit();
            logger.info("Driver closed for scenario: {}", scenario.getName());
        }
        DriverManager.unload();
    }

    @After(order = 1, value = "@ui")
    public void captureFailure(Scenario scenario) {
        if (scenario.isFailed() && allureUtil != null) {
            allureUtil.captureAndAttachScreenshot();
        }
    }

    @AfterStep(value = "@ui")
    public void afterEachStep(Scenario scenario) {
        if (allureUtil != null) {
            allureUtil.captureAndAttachScreenshot();
        }
    }
}