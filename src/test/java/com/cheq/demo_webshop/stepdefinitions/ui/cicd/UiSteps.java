package com.cheq.demo_webshop.stepdefinitions.ui.cicd;

import com.cheq.demo_webshop.pages.mobile.cicd.LandingPage;
import com.cheq.demo_webshop.manager.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class UiSteps {

	AndroidDriver driver;

    private LandingPage landingPage;
    
    public UiSteps() {
        this.driver = DriverManager.getDriver();
        this.landingPage = new LandingPage(driver);
    }
    
    @Given("User is in the calculator page") 
    public void checkPage() {
    	landingPage.isHeaderTextVisible();
    }
    
    @Then("User should see the calculator elements") 
    public void verify() {
    	landingPage.isNumberOneElementVisible();
    	landingPage.isNumberTwoElementVisible();
    	landingPage.isOperationElementVisible();
    	landingPage.isComputeButtonElementVisible();
    	landingPage.isResultLabelElementVisible();
    }
}
