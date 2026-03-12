package com.cheq.demo_webshop.stepdefinitions.api;

import org.junit.Assert;

import com.cheq.demo_webshop.manager.ApiContext;
import com.cheq.demo_webshop.models.request.AddUserRequest;
import com.cheq.demo_webshop.models.request.LoginUserRequest;
import com.cheq.demo_webshop.models.response.AddUserResponse;
import com.cheq.demo_webshop.pages.api.AddUserApi;
import com.cheq.demo_webshop.pages.api.LoginUserApi;
import com.cheq.demo_webshop.utils.ApiTestDataGenerator;
import com.cheq.demo_webshop.utils.ResponseValidator;

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
        Assert.assertEquals(addUserRequest.getFirstName(), loginUserResponse.getUser().getFirstName());
        Assert.assertEquals(addUserRequest.getLastName(), loginUserResponse.getUser().getLastName());
        Assert.assertEquals(addUserRequest.getEmail(), loginUserResponse.getUser().getEmail());
        Assert.assertNotNull(loginUserResponse.getUser().getId());
    }

    @Then("Login API response should contain an authentication token")
    public void loginApiResponseShouldContainAnAuthenticationToken() {
        Assert.assertNotNull(loginUserResponse.getToken());
    }
}