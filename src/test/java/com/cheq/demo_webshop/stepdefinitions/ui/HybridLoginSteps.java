package com.cheq.demo_webshop.stepdefinitions.ui;

import org.junit.Assert;

import com.cheq.demo_webshop.manager.ApiContext;
import com.cheq.demo_webshop.models.request.AddUserRequest;
import com.cheq.demo_webshop.models.response.AddUserResponse;
import com.cheq.demo_webshop.services.AddUserApi;
import com.cheq.demo_webshop.utils.api.ApiTestDataGenerator;
import com.cheq.demo_webshop.utils.api.JsonSerializerUtil;
import com.cheq.demo_webshop.utils.api.ResponseValidator;
import com.cheq.demo_webshop.utils.common.AllureUtil;
import com.cheq.demo_webshop.pages.ContactListLoginPage;
import com.cheq.demo_webshop.pages.ContactListPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import com.cheq.demo_webshop.manager.DriverManager;
import com.cheq.demo_webshop.manager.StepExecutionContext;
import com.cheq.demo_webshop.utils.common.ConfigReader;

public class HybridLoginSteps {

    private final AddUserApi addUserApi = new AddUserApi();
    private final ContactListLoginPage loginPage = new ContactListLoginPage();
    private final ContactListPage contactListPage = new ContactListPage();

    private AddUserRequest addUserRequest;
    private AddUserResponse addUserResponse;
    private Response response;

    @Given("User registers a new account via Contact List API")
    public void userRegistersANewAccountViaContactListApi() {
    	StepExecutionContext.setStepType("API");
    	
        addUserRequest = ApiTestDataGenerator.generateValidAddUserRequest();

        AllureUtil.attachText("API Method", "POST");
        AllureUtil.attachText("API Endpoint", "/users");
        AllureUtil.attachJson("API Request Body", JsonSerializerUtil.toJson(addUserRequest));

        response = addUserApi.addUser(addUserRequest);
        ResponseValidator.validateStatusCode(response, 201);

        addUserResponse = addUserApi.getAddUserResponse(response);

        ApiContext.set("response", response);
        ApiContext.set("email", addUserRequest.getEmail());
        ApiContext.set("password", addUserRequest.getPassword());
        ApiContext.set("userId", addUserResponse.getUser().getId());
        ApiContext.set("token", addUserResponse.getToken());
    }

    @When("User navigates to the Contact List login page")
    public void userNavigatesToTheContactListLoginPage() {
    	StepExecutionContext.setStepType("UI");
    	
        String env = System.getProperty("env", "dev");
        ConfigReader.loadProperties(env);

        String contactListUrl = ConfigReader.get("apiBaseUrl");

        DriverManager.getDriver().get(contactListUrl);
        loginPage.waitForLoginPageToLoad();
    }

    @When("User logs in using the API-created credentials")
    public void userLogsInUsingTheApiCreatedCredentials() {
    	StepExecutionContext.setStepType("UI");
    	
        String email = ApiContext.getString("email");
        String password = ApiContext.getString("password");

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickSubmit();
    }

    @Then("User should be redirected to the Contact List page")
    public void userShouldBeRedirectedToTheContactListPage() {
    	StepExecutionContext.setStepType("UI");
    	
        Assert.assertTrue("Contact List page should be displayed after successful login",
                contactListPage.isLogoutButtonVisible());
    }
}
