package com.cheq.demo_webshop.stepdefinitions.ui;

import java.util.List;
import java.util.Map;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.datatable.DataTable;

import com.cheq.demo_webshop.manager.DriverManager;
import com.cheq.demo_webshop.pages.mobile.HomePage;
import com.cheq.demo_webshop.pages.mobile.RegisterPage;

import io.cucumber.java.en.*;

public class RegisterSteps {

	AndroidDriver driver;
    private HomePage homePage;
    private RegisterPage registerPage;
    
    public RegisterSteps() {
    	this.driver = DriverManager.getDriver();
    	this.homePage = new HomePage(driver);
    	this.registerPage = new RegisterPage(driver);
    }

    @Given("User navigate to webshop registration page")
    public void user_navigate_to_webshop_registration_page() throws InterruptedException {
    	Thread.sleep(1000);
    	homePage.header.isRegisterLinkDisplayed();
        homePage.header.clickRegisterLink(); 
    }

    @When("User should input valid credentials")
    public void user_should_input_valid_credentials(DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);

        String firstName = credentials.get(0).get("firstName");
        String lastName = credentials.get(0).get("lastName");
        String email = credentials.get(0).get("email");
        String password = credentials.get(0).get("password");

        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastName);
        registerPage.enterEmail(email);
        registerPage.enterPassword(password);
        registerPage.enterConfirmPassword(password);

    }

    @And("User click register button")
    public void user_click_register_button() {
    	registerPage.clickRegisterButton();
    }

    @Then("Verify registration is complete")
    public void verify_registration_is_complete() {
    	registerPage.isRegistrationSuccessful();
    }
    
    @Then("The following radio buttons should be selected:")
    public void the_following_radio_buttons_should_be_selected(DataTable dataTable) throws InterruptedException {
    	List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
    	Map<String, String> row = data.get(1);
    	String locatorType = row.get("locatorType").trim();
    	String locatorValue = row.get("locatorValue").trim();
    	registerPage.selectGender(locatorType, locatorValue);
    }
 
}

 