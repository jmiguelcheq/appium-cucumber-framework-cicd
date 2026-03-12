//package com.cheq.demo_webshop.hooks;
//
//import io.cucumber.java.Before;
//import io.cucumber.java.Scenario;
//import io.appium.java_client.android.AndroidDriver;
//import io.cucumber.java.After;
//import io.cucumber.java.AfterStep;
//
//import org.apache.logging.log4j.Logger;
//
//import com.cheq.demo_webshop.factory.AndroidDriverFactory;
//import com.cheq.demo_webshop.manager.DriverManager;
//import com.cheq.demo_webshop.utils.AllureUtil;
//import com.cheq.demo_webshop.utils.ConfigReader;
//import com.cheq.demo_webshop.utils.LoggerUtil;
//import com.google.common.collect.ImmutableMap;
//
///**
//* Cucumber hooks for managing WebDriver lifecycle and Allure reporting.
//* Handles setup, teardown, and reporting for each scenario and step.
//*/
//public class Hooks {
//    private static AndroidDriver driver;
//    private AllureUtil allureUtil;
//    private static final Logger logger = LoggerUtil.getLogger(Hooks.class);
//
//    /**
//     * Initializes WebDriver, loads configuration, and sets up Allure environment before each scenario.
//     *
//     * @param scenario the current Cucumber scenario
//     * @throws Exception 
//     */
//    @Before
//    public void setUp(Scenario scenario) throws Exception {
//        // Load environment-specific properties
//        String env = System.getProperty("env", "dev");
//        ConfigReader.loadProperties(env);
//
//        // Determine browser type and base URL
//        String url = ConfigReader.get("baseUrl");
//
//        // Create and configure WebDriver
//        driver = AndroidDriverFactory.loadDriver();
////        driver.manage().window().maximize();
//        driver.get(url);
//
//        // Make WebDriver globally accessible
//        DriverManager.setDriver(driver);
//
//        // Initialize Allure utility for reporting
//        allureUtil = new AllureUtil(driver);
//
//        // Write environment details to Allure report
//        allureUtil.writeAllureEnvironment(
//            ImmutableMap.<String, String>builder()
//                .put("OS", System.getProperty("os.name"))
//                .put("Environment", env)
//                .build()
//        );
//        logger.info("Starting scenario: " + scenario.getName());
//    }
//
//    /**
//     * Quits the WebDriver after each scenario to clean up resources.
//     *
//     * @param scenario the current Cucumber scenario
//     */
//    @After(order = 0)
//    public void tearDown(Scenario scenario) {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    /**
//     * Captures and attaches a screenshot to the Allure report if the scenario fails.
//     *
//     * @param scenario the current Cucumber scenario
//     */
//    @After(order = 1)
//    public void captureFailure(Scenario scenario) {
//        if (scenario.isFailed() && allureUtil != null) {
//            allureUtil.captureAndAttachScreenshot();
//        }
//    }
//
//    /**
//     * Captures and attaches a screenshot to the Allure report after each step.
//     *
//     * @param scenario the current Cucumber scenario
//     */
//    @AfterStep 
//    public void afterEachStep(Scenario scenario) {
//        if (allureUtil != null) {
//            allureUtil.captureAndAttachScreenshot();
//        }
//    }
//
//}
// 


package com.cheq.demo_webshop.hooks;

import org.apache.logging.log4j.Logger;

import com.cheq.demo_webshop.utils.ConfigReader;
import com.cheq.demo_webshop.utils.LoggerUtil;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    private static final Logger logger = LoggerUtil.getLogger(Hooks.class);

    @Before(order = 0)
    public void loadConfigAndLogScenario(Scenario scenario) {
        String env = System.getProperty("env", "dev");
        ConfigReader.loadProperties(env);
        logger.info("Starting scenario: {}", scenario.getName());
    }
}
