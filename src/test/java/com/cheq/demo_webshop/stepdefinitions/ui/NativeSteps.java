package com.cheq.demo_webshop.stepdefinitions.ui;

import com.cheq.demo_webshop.manager.DriverManager;
import com.cheq.demo_webshop.pages.HomePage;
import com.cheq.demo_webshop.pages.LoginPage;
import com.cheq.demo_webshop.pages.NativePage;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class NativeSteps {
    AndroidDriver driver;

    private LoginPage loginPage;
    private HomePage homePage;
    private NativePage nativePage;

    public NativeSteps() {
        this.driver = DriverManager.getDriver();
        this.loginPage = new LoginPage(driver);
        this.homePage = new HomePage(driver);
        this.nativePage = new NativePage(driver);
    }

    @Given("User click next")
    public void user_click_next() {
        nativePage.clickNext();

    }

    @When("User click get started")
    public void user_click_get_started() {
        nativePage.clickGetStarted();
    }

    @When("User select india")
    public void user_select_india() throws InterruptedException {
        Thread.sleep(5000);
        nativePage.clickIndia();
        Thread.sleep(5000);
    }


}