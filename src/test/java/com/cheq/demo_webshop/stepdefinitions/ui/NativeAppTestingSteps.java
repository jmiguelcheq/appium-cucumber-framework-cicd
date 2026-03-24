package com.cheq.demo_webshop.stepdefinitions.ui;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cheq.demo_webshop.manager.DriverManager;
import com.cheq.demo_webshop.utils.common.ConfigReader;
import com.cheq.demo_webshop.utils.ui.ApplicationSwitchUtil;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NativeAppTestingSteps {

    private final AndroidDriver driver = DriverManager.getDriver();
    private final ApplicationSwitchUtil appSwitch = new ApplicationSwitchUtil(driver);

//    private final By webSearchIcon = By.id("menuSearch");
    private final By webSearchInput = By.name("mobile_search");
    private final By nativeSearchIcon = AppiumBy.accessibilityId("Search");

    @Given("User is on the mobile web homepage")
    public void userIsOnMobileWebHomepage() {
        String url = System.getProperty("baseUrl", ConfigReader.get("baseUrl"));
        driver.get(url);
    }

    @Then("Search icon should be visible in mobile web")
    public void searchIconShouldBeVisibleInMobileWeb() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement searchIcon = wait.until(
                ExpectedConditions.visibilityOfElementLocated(webSearchInput)
        );

        Assert.assertTrue("Search icon is not visible in mobile web", searchIcon.isDisplayed());
    }

    @When("User switches to native shopping app")
    public void userSwitchesToNativeShoppingApp() {
        appSwitch.switchToNativeApp();
//        driver.context("NATIVE_APP");
        System.out.println("Current package: " + driver.getCurrentPackage());
        System.out.println("Current context: " + driver.getContext());
        System.out.println("Available contexts: " + driver.getContextHandles());
    }

    @Then("Search icon should be visible in native app")
    public void searchIconShouldBeVisibleInNativeApp() {
        driver.context("NATIVE_APP");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement searchIcon = wait.until(
                ExpectedConditions.visibilityOfElementLocated(nativeSearchIcon)
        );

        Assert.assertTrue("Search icon is not visible in native app", searchIcon.isDisplayed());
    }
}
