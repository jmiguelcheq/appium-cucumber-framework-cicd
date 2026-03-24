package com.cheq.demo_webshop.stepdefinitions.api;

import com.cheq.demo_webshop.constants.ApiResponseType;
import com.cheq.demo_webshop.manager.ApiContext;
import com.cheq.demo_webshop.models.request.AddUserRequest;
import com.cheq.demo_webshop.models.response.AddUserResponse;
import com.cheq.demo_webshop.services.AddUserApi;
import com.cheq.demo_webshop.utils.api.ApiTestDataGenerator;
import com.cheq.demo_webshop.utils.api.JsonSerializerUtil;
import com.cheq.demo_webshop.utils.api.ResponseValidator;
import com.cheq.demo_webshop.utils.common.AllureUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class AddUserApiSteps {

    private final AddUserApi addUserApi = new AddUserApi();
    private AddUserRequest addUserRequest;
    private AddUserResponse addUserResponse;
    private Response response;

    @Given("User prepares a valid add user request payload")
    public void userPreparesAValidAddUserRequestPayload() {
        addUserRequest = ApiTestDataGenerator.generateValidAddUserRequest();

        AllureUtil.attachJson("API Request Body", JsonSerializerUtil.toJson(addUserRequest));
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

    @Then("API response should contain the created user details")
    public void apiResponseShouldContainTheCreatedUserDetails() {
        ResponseValidator.validateResponseType(response, ApiResponseType.JSON);
        ResponseValidator.validateJsonFieldValue(response, "user.firstName", addUserRequest.getFirstName());
        ResponseValidator.validateJsonFieldValue(response, "user.lastName", addUserRequest.getLastName());
        ResponseValidator.validateJsonFieldValue(response, "user.email", addUserRequest.getEmail());
        ResponseValidator.validateJsonFieldExists(response, "user.id");
    }

    @Then("API response should contain an authentication token")
    public void apiResponseShouldContainAnAuthenticationToken() {
        ResponseValidator.validateJsonFieldExists(response, "token");

        AllureUtil.attachJson("API Response Body", response.getBody().asPrettyString());
    }
}
