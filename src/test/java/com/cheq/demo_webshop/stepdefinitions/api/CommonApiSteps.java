package com.cheq.demo_webshop.stepdefinitions.api;

import com.cheq.demo_webshop.manager.ApiContext;
import com.cheq.demo_webshop.utils.ResponseValidator;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class CommonApiSteps {

    @Then("API response status code should be {int}")
    public void apiResponseStatusCodeShouldBe(Integer expectedStatusCode) {

        Response response = ApiContext.getResponse();

        ResponseValidator.validateStatusCode(response, expectedStatusCode);
    }
}