package com.cheq.demo_webshop.hooks;

import org.apache.logging.log4j.Logger;

import com.cheq.demo_webshop.manager.ApiContext;
import com.cheq.demo_webshop.utils.AllureEnvironmentUtil;
import com.cheq.demo_webshop.utils.LoggerUtil;
import com.google.common.collect.ImmutableMap;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApiHooks {

    private static final Logger logger = LoggerUtil.getLogger(ApiHooks.class);

    @Before(value = "@api", order = 1)
    public void setUpApi(Scenario scenario) {
        ApiContext.clear();

        AllureEnvironmentUtil.writeEnvironment(
            ImmutableMap.<String, String>builder()
                .put("OS", System.getProperty("os.name"))
                .put("Environment", System.getProperty("env", "dev"))
                .put("ExecutionType", "API")
                .build()
        );

        logger.info("API setup completed for scenario: {}", scenario.getName());
    }

    @After(value = "@api", order = 2)
    public void tearDownApi(Scenario scenario) {
        ApiContext.clear();
        logger.info("API context cleared for scenario: {}", scenario.getName());
    }
}