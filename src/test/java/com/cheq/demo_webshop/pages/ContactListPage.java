package com.cheq.demo_webshop.pages;

import org.openqa.selenium.By;

import com.cheq.demo_webshop.manager.DriverManager;
import com.cheq.demo_webshop.utils.ui.WaitUtil;

import io.appium.java_client.android.AndroidDriver;

public class ContactListPage {

    private final AndroidDriver driver;
    private final WaitUtil wait;

    private final By contactTable = By.id("myTable");
    private final By logoutButton = By.id("logout");

    public ContactListPage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WaitUtil(driver);
    }

    public boolean isContactListDisplayed() {
        try {
            wait.waitForElementVisible(contactTable, 10);
            return driver.findElement(contactTable).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogoutButtonVisible() {
        try {
            return driver.findElement(logoutButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
