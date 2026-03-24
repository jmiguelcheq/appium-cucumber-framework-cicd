package com.cheq.demo_webshop.stepdefinitions.api;

import com.cheq.demo_webshop.manager.ApiContext;
import com.cheq.demo_webshop.models.request.AddUserRequest;
import com.cheq.demo_webshop.models.request.LoginUserRequest;
import com.cheq.demo_webshop.models.response.AddUserResponse;
import com.cheq.demo_webshop.services.AddUserApi;
import com.cheq.demo_webshop.services.LoginUserApi;
import com.cheq.demo_webshop.utils.api.ApiTestDataGenerator;
import com.cheq.demo_webshop.utils.api.JsonSerializerUtil;
import com.cheq.demo_webshop.utils.api.ResponseValidator;
import com.cheq.demo_webshop.utils.common.AllureUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class LoginUserSteps {

    private final AddUserApi addUserApi = new AddUserApi();
    private final LoginUserApi loginUserApi = new LoginUserApi();

    private AddUserRequest addUserRequest;
    private LoginUserRequest loginUserRequest;
    private AddUserResponse loginUserResponse;
    private Response response;

    @Given("User has valid credentials for login API")
    public void userHasValidCredentialsForLoginApi() {
        addUserRequest = ApiTestDataGenerator.generateValidAddUserRequest();

        Response addUserApiResponse = addUserApi.addUser(addUserRequest);
        ResponseValidator.validateStatusCode(addUserApiResponse, 201);

        loginUserRequest = ApiTestDataGenerator.generateValidLoginUserRequest(
                addUserRequest.getEmail(),
                addUserRequest.getPassword()
        );

        AllureUtil.attachJson("API Request Body", JsonSerializerUtil.toJson(loginUserRequest));
    }

    @When("User sends the login user API request")
    public void userSendsTheLoginUserApiRequest() {
        response = loginUserApi.loginUser(loginUserRequest);
        loginUserResponse = loginUserApi.getLoginUserResponse(response);

        ApiContext.set("response", response);
        ApiContext.set("token", loginUserResponse.getToken());
        ApiContext.set("userId", loginUserResponse.getUser().getId());
        ApiContext.set("email", loginUserResponse.getUser().getEmail());
    }

    @Then("Login API response should contain the correct user details")
    public void loginApiResponseShouldContainTheCorrectUserDetails() {
        ResponseValidator.validateJsonFieldValue(response, "user.firstName", addUserRequest.getFirstName());
        ResponseValidator.validateJsonFieldValue(response, "user.lastName", addUserRequest.getLastName());
        ResponseValidator.validateJsonFieldValue(response, "user.email", addUserRequest.getEmail());
        ResponseValidator.validateJsonFieldExists(response, "user.id");
    }

    @Then("Login API response should contain an authentication token")
    public void loginApiResponseShouldContainAnAuthenticationToken() {
        ResponseValidator.validateJsonFieldExists(response, "token");

        AllureUtil.attachJson("API Response Body", response.getBody().asPrettyString());
    }
}