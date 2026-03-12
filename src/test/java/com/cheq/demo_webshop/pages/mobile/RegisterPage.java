package com.cheq.demo_webshop.pages.mobile;
import com.cheq.demo_webshop.factory.RadioButtonFactory;
import com.cheq.demo_webshop.utils.ElementActionUtils;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;


public class RegisterPage {

    private AndroidDriver driver;
    private ElementActionUtils actions;

    private By firstNameInput = By.xpath("//input[@id='FirstName']");
    private By lastNameInput = By.xpath("//input[@id='LastName']");
    private By emailInput = By.xpath("//input[@id='Email']");
    private By passwordInput = By.xpath("//input[@id='Password']");
    private By confirmPasswordInput = By.xpath("//input[@id='ConfirmPassword']");
    private By registerButton = By.xpath("//input[@id='register-button']");
    private By registrationSuccessMessage = By.xpath("//div[@class='result' and contains(text(),'Your registration completed')]");

    // Constructor
    public RegisterPage(AndroidDriver driver) {
        this.driver = driver;
        this.actions = new ElementActionUtils(driver);
    }

    public void selectGender(String locatorType, String locatorValue) {
    	actions.tap(RadioButtonFactory.getRadioButtonStrategy(locatorType)
    			.getLocator(locatorValue));
    }

    public void enterFirstName(String firstName) {
    	actions.enterText(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
    	actions.enterText(lastNameInput, lastName);
    }

    public void enterEmail(String email) {
    	actions.enterText(emailInput, email);
    }

    public void enterPassword(String password) {
    	actions.enterText(passwordInput, password);
    }
    
    public void enterConfirmPassword(String confirmPassword) {
    	actions.enterText(confirmPasswordInput, confirmPassword);
    }

    public void clickRegisterButton() {
    	actions.tap(registerButton);
    }

    public void isRegistrationSuccessful() {
        actions.verifyElementVisible(registrationSuccessMessage);

    }
}
 