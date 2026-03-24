package com.cheq.demo_webshop.stepdefinitions.ui.cicd;

import com.cheq.demo_webshop.manager.ApiContext;
import com.cheq.demo_webshop.utils.common.AllureUtil;
import com.cheq.demo_webshop.utils.common.ConfigReader;
import com.cheq.demo_webshop.utils.api.ResponseValidator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CalculatorUptimeApiSteps {

    private String baseUrl;
    private Response response;

    @Given("I have the calculator base URL")
    public void baseUrl() {
        String env = System.getProperty("env", "dev");
        ConfigReader.loadProperties(env);
        baseUrl = ConfigReader.get("baseUrl");

        AllureUtil.attachText("API Method", "GET");
        AllureUtil.attachText("API Endpoint", baseUrl);
    }

    @When("I send a GET request to the base URL")
    public void get() {
        response = RestAssured.get(baseUrl);
        ApiContext.set("response", response);
    }

    @Then("the response status should be {int}")
    public void verify(Integer expected) {
        ResponseValidator.validateStatusCode(response, expected);
        AllureUtil.attachText("API Response Status", String.valueOf(response.getStatusCode()));
        AllureUtil.attachText("API Response Body", response.getBody().asPrettyString());
    }
}
