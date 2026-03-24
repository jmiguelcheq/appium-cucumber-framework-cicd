package com.cheq.demo_webshop.hooks;

import java.util.Collection;

import org.apache.logging.log4j.Logger;

import com.cheq.demo_webshop.constants.ApplicationType;
import com.cheq.demo_webshop.factory.AndroidDriverFactory;
import com.cheq.demo_webshop.manager.ApiContext;
import com.cheq.demo_webshop.manager.ApplicationContextManager;
import com.cheq.demo_webshop.manager.DriverManager;
import com.cheq.demo_webshop.manager.ExecutionContextManager;
import com.cheq.demo_webshop.manager.StepExecutionContext;
import com.cheq.demo_webshop.utils.common.AllureUtil;
import com.cheq.demo_webshop.utils.common.ConfigReader;
import com.cheq.demo_webshop.utils.common.LoggerUtil;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    private static final Logger logger = LoggerUtil.getLogger(Hooks.class);

    private AndroidDriver driver;
    private AllureUtil allureUtil;

    /**
     * Common hook for all scenarios
     */
    @Before(order = 0)
    public void loadConfigAndLogScenario(Scenario scenario) {
        String env = System.getProperty("env", "dev");
        ConfigReader.loadProperties(env);
        logger.info("Starting scenario: {}", scenario.getName());

        AllureUtil.createExecutorInfo();
        AllureUtil.writeCategories();
    }

    /**
     * API-specific setup for API and Hybrid scenarios
     */
    @Before(value = "@api or @hybrid", order = 1)
    public void setUpApi(Scenario scenario) {
        ApiContext.clear();
        logger.info("API setup completed for scenario: {}", scenario.getName());
    }

    /**
     * Execution mode setup for UI and Hybrid scenarios
     */
    @Before(value = "@ui or @hybrid", order = 1)
    public void setExecutionMode(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@mobileWeb")) {
            ExecutionContextManager.setExecutionMode("mobileWeb");
        } else if (scenario.getSourceTagNames().contains("@nativeApp")) {
            ExecutionContextManager.setExecutionMode("nativeApp");
        } else {
            ExecutionContextManager.setExecutionMode();
        }

        logger.info("Execution mode set to {} for scenario: {}",
                ExecutionContextManager.getExecutionMode(), scenario.getName());
    }

    /**
     * UI-specific setup for UI and Hybrid scenarios
     */
    @Before(value = "@ui or @hybrid", order = 2)
    public void setUpUi(Scenario scenario) throws Exception {
        String url = System.getProperty("baseUrl", ConfigReader.get("baseUrl"));

        driver = AndroidDriverFactory.loadDriver();
        DriverManager.setDriver(driver);

        if (ExecutionContextManager.isMobileWeb()) {
            ApplicationContextManager.setCurrentApplication(ApplicationType.CHROME);

            if (!scenario.getSourceTagNames().contains("@hybrid")) {
                driver.get(url);
            }
        } else {
            ApplicationContextManager.setCurrentApplication(ApplicationType.NATIVE_APP);
        }

        allureUtil = new AllureUtil(driver);

        logger.info("UI setup completed for scenario: {}", scenario.getName());
        logger.info("Current application set to {}", ApplicationContextManager.getCurrentApplication());
    }

    /**
     * Write Allure environment once after setup is ready
     */
    @Before(order = 3)
    public void writeAllureEnvironment(Scenario scenario) {
        String env = System.getProperty("env", "dev");
        String executionType = resolveExecutionType(scenario);

        ImmutableMap.Builder<String, String> envBuilder = ImmutableMap.<String, String>builder()
                .put("OS", System.getProperty("os.name"))
                .put("Environment", env)
                .put("ExecutionType", executionType);

        if (hasUiExecution(scenario)) {
            envBuilder.put("ExecutionMode", ExecutionContextManager.getExecutionMode().name());

            try {
                envBuilder.put("CurrentApplication", ApplicationContextManager.getCurrentApplication().name());
            } catch (Exception e) {
                logger.warn("Current application was not initialized when writing Allure environment");
            }
        }

        AllureUtil.writeEnvironment(envBuilder.build());
        logger.info("Allure environment written for scenario: {} with ExecutionType={}",
                scenario.getName(), executionType);
    }

    /**
     * UI step screenshot for UI and Hybrid scenarios
     */
    @AfterStep(value = "@ui or @hybrid")
    public void afterEachUiStep(Scenario scenario) {
        if (allureUtil == null) {
            return;
        }

        try {
            if (shouldCaptureScreenshotForStep(scenario)) {
                allureUtil.captureAndAttachScreenshot();
            }
        } catch (Exception e) {
            logger.warn("Skipping step screenshot for scenario {}: {}", scenario.getName(), e.getMessage());
        }
    }

    /**
     * UI failure screenshot for UI and Hybrid scenarios
     */
    @After(order = 2, value = "@ui or @hybrid")
    public void captureUiFailure(Scenario scenario) {
        if (scenario.isFailed() && allureUtil != null) {
            try {
                allureUtil.captureAndAttachScreenshot();
            } catch (Exception e) {
                logger.warn("Skipping failure screenshot for scenario {}: {}", scenario.getName(), e.getMessage());
            }
        }
    }

    /**
     * UI teardown for UI and Hybrid scenarios
     */
    @After(order = 1, value = "@ui or @hybrid")
    public void tearDownUi(Scenario scenario) {
        try {
            if (driver != null) {
                safelyTerminateApp(ConfigReader.get("appPackage"), "native app");
                safelyTerminateApp(ConfigReader.get("chromeAppPackage"), "Chrome");

                driver.quit();
                logger.info("Driver closed for scenario: {}", scenario.getName());
            }
        } finally {
            DriverManager.unload();
            ExecutionContextManager.unload();
            ApplicationContextManager.unload();
            driver = null;
            allureUtil = null;
        }
    }
    
    private void safelyTerminateApp(String packageName, String appName) {
        try {
            if (packageName != null && !packageName.trim().isEmpty()) {
                driver.terminateApp(packageName);
                logger.info("{} terminated successfully: {}", appName, packageName);
            }
        } catch (Exception e) {
            logger.warn("Unable to terminate {}: {}", appName, e.getMessage());
        }
    }

    /**
     * API teardown for API and Hybrid scenarios
     */
    @After(value = "@api or @hybrid", order = 3)
    public void tearDownApi(Scenario scenario) {
        ApiContext.clear();
        logger.info("API context cleared for scenario: {}", scenario.getName());
    }

    private String resolveExecutionType(Scenario scenario) {
        Collection<String> tags = scenario.getSourceTagNames();

        if (tags.contains("@hybrid")) {
            return "Hybrid";
        }
        if (tags.contains("@ui")) {
            return "UI";
        }
        if (tags.contains("@api")) {
            return "API";
        }
        return "Unknown";
    }

    private boolean shouldCaptureScreenshotForStep(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@ui")) {
            return true;
        }

        if (scenario.getSourceTagNames().contains("@hybrid")) {
            return "UI".equalsIgnoreCase(StepExecutionContext.getStepType());
        }

        return false;
    }

    private boolean hasUiExecution(Scenario scenario) {
        Collection<String> tags = scenario.getSourceTagNames();
        return tags.contains("@ui") || tags.contains("@hybrid");
    }
}
