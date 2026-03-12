package com.cheq.demo_webshop.pages.components;
import com.cheq.demo_webshop.factory.TextLabelFactory;
import com.cheq.demo_webshop.utils.ElementActionUtils;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;


public class HeaderComponent {

    private AndroidDriver driver;
    private ElementActionUtils elementActionUtils;

    // Locators
    private By logoutLink = By.cssSelector(".ico-logout");
    private By loginLink = By.cssSelector(".ico-login");
    private By registerLink = By.cssSelector(".ico-register");

    // Constructor
    public HeaderComponent(AndroidDriver driver) {
        this.driver = driver;
        this.elementActionUtils = new ElementActionUtils(driver);
    }

    public void clickLoginLink(String strategyType, String htmlTag, String message) {
    	elementActionUtils.tap(TextLabelFactory.getStrategy(
				strategyType).getLocator(htmlTag, message));  
    }

    public void isLogoutLinkDisplayed() {
        elementActionUtils.verifyElementVisible(logoutLink);
    }

    public void clickLogout() {
        elementActionUtils.tap(logoutLink);
    }

    public void isLoginLinkDisplayed() {
    	elementActionUtils.verifyElementVisible(loginLink);
    }

    public void isRegisterLinkDisplayed() {
        elementActionUtils.verifyElementVisible(registerLink);
    }

    public void clickRegisterLink() {
        elementActionUtils.tap(registerLink);
    }

    public void swipeToBottom() {
        elementActionUtils.swipe("up", 2);
    }
}
 