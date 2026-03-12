package com.cheq.demo_webshop.stepdefinitions.ui.cicd;

import com.cheq.demo_webshop.manager.DriverManager;
import com.cheq.demo_webshop.pages.mobile.HomePage;
import com.cheq.demo_webshop.pages.mobile.LoginPage;
import com.cheq.demo_webshop.utils.ConfigReader;
import com.cheq.demo_webshop.utils.ResponseValidator;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.*;
import io.restassured.RestAssured; 
import io.restassured.response.Response;
//import static org.assertj.core.api.Assertions.assertThat;

public class ApiSteps {
	
    private String baseUrl; 
    private Response response;
    
	AndroidDriver driver;

    public ApiSteps() {
    	this.driver = DriverManager.getDriver();
    }
    
    @Given("I have the calculator base URL") 
    public void baseUrl() { 
    	String env = System.getProperty("env", "dev");
        ConfigReader.loadProperties(env);
    	baseUrl = ConfigReader.get("baseUrl");
	}
    
    @When("I send a GET request to the base URL") 
    public void get() { 
    	response = RestAssured.get(baseUrl); 
	}
    
    @Then("the response status should be {int}") 
    public void verify(Integer expected) { 
    	ResponseValidator.validateStatusCode(response, 200);
//    	assertThat(response.statusCode()).isEqualTo(expected); 
	}
}