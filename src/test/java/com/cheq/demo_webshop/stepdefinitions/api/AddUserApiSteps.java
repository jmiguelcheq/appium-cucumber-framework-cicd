package com.cheq.demo_webshop.stepdefinitions.api;

import com.cheq.demo_webshop.manager.ApiContext;
import com.cheq.demo_webshop.models.request.AddUserRequest;
import com.cheq.demo_webshop.pages.api.AddUserApi;
import com.cheq.demo_webshop.utils.ApiTestDataGenerator;
import com.cheq.demo_webshop.utils.ResponseValidator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import com.cheq.demo_webshop.models.response.AddUserResponse;

public class AddUserApiSteps {

    private final AddUserApi addUserApi = new AddUserApi();
    private AddUserRequest addUserRequest;
    private AddUserResponse addUserResponse;
    private Response response;

    @Given("User prepares a valid add user request payload")
    public void userPreparesAValidAddUserRequestPayload() {
    	addUserRequest = ApiTestDataGenerator.generateValidAddUserRequest();
    }

    @When("User sends the add user API request")
    public void userSendsTheAddUserApiRequest() {
        response = addUserApi.addUser(addUserRequest);
        addUserResponse = addUserApi.getAddUserResponse(response);

        ApiContext.set("response", response);
        ApiContext.set("token", addUserResponse.getToken());
        ApiContext.set("userId", addUserResponse.getUser().getId());
        ApiContext.set("email", addUserResponse.getUser().getEmail());
    }

//    @Then("API response status code should be {int}")
//    public void apiResponseStatusCodeShouldBe(Integer expectedStatusCode) {
//        ResponseValidator.validateStatusCode(response, expectedStatusCode);
//    }

    @Then("API response should contain the created user details")
    public void apiResponseShouldContainTheCreatedUserDetails() {
    	ResponseValidator.validateFieldEquals(response, "user.firstName", addUserRequest.getFirstName());
//    	org.junit.Assert.assertEquals(addUserRequest.getFirstName(), addUserResponse.getUser().getFirstName());
        org.junit.Assert.assertEquals(addUserRequest.getLastName(), addUserResponse.getUser().getLastName());
        org.junit.Assert.assertEquals(addUserRequest.getEmail(), addUserResponse.getUser().getEmail());
        org.junit.Assert.assertNotNull(addUserResponse.getUser().getId());
    }

    @Then("API response should contain an authentication token")
    public void apiResponseShouldContainAnAuthenticationToken() {
    	org.junit.Assert.assertNotNull(addUserResponse.getToken());
    }
}