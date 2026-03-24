package com.cheq.demo_webshop.pages;

import org.openqa.selenium.By;

import com.cheq.demo_webshop.manager.DriverManager;
import com.cheq.demo_webshop.utils.common.ElementActionUtils;
import com.cheq.demo_webshop.utils.ui.WaitUtil;

import io.appium.java_client.android.AndroidDriver;

public class ContactListLoginPage {

    private final AndroidDriver driver;
    private final ElementActionUtils elementActions;
    private final WaitUtil wait;

    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By submitButton = By.id("submit");

    public ContactListLoginPage() {
        this.driver = DriverManager.getDriver();
        this.elementActions = new ElementActionUtils(driver);
        this.wait = new WaitUtil(driver);
    }

    public void waitForLoginPageToLoad() {
        wait.waitForElementVisible(emailInput, 10);
    }

    public void enterEmail(String email) {
        elementActions.enterText(emailInput, email);
    }

    public void enterPassword(String password) {
        elementActions.enterText(passwordInput, password);
    }

    public void clickSubmit() {
        elementActions.tap(submitButton);
    }
}
