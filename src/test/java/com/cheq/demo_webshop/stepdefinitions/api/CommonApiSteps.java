package com.cheq.demo_webshop.stepdefinitions.api;

import com.cheq.demo_webshop.manager.ApiContext;
import com.cheq.demo_webshop.utils.api.ResponseValidator;
import com.cheq.demo_webshop.utils.common.AllureUtil;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;

public class CommonApiSteps {

    @Then("API response status code should be {int}")
    public void apiResponseStatusCodeShouldBe(Integer expectedStatusCode) {
        Response response = ApiContext.getResponse();

        ResponseValidator.logAndValidateStatus(response, expectedStatusCode);
        AllureUtil.attachText("API Response Status", String.valueOf(response.getStatusCode()));
    }
}