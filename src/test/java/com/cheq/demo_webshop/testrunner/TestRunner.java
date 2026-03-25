package com.cheq.demo_webshop.testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/feature/",
    glue = {"com.cheq.demo_webshop.stepdefinitions", "com.cheq.demo_webshop.hooks", 
      "com.cheq.demo_webshop.listener"},
    plugin = {
    		"pretty", 
    		"junit:target/cucumber-junit.xml", // added for launchable
    		"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", 
    		"com.cheq.demo_webshop.listener.StepListener"
	},
    monochrome = true,
    tags="@mobileWeb"
)
public class TestRunner {
}

 