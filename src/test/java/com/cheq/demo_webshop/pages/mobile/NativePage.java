package com.cheq.demo_webshop.pages.mobile;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import com.cheq.demo_webshop.utils.ElementActionUtils;

import io.appium.java_client.android.AndroidDriver;

public class NativePage {
    private AndroidDriver driver;
    private ElementActionUtils elementActionUtils;


    private By NEXT_BTN = By.id("org.simple.clinic.staging:id/nextButton");
    private By GET_STARTED_BTN = By.xpath("//android.widget.Button[@resource-id='org.simple.clinic.staging:id/getStartedButton']");
    private By INDIA_RADIOBTN = AppiumBy.androidUIAutomator("new UiSelector().text(\"India\")");


    public NativePage(AndroidDriver driver) {
        this.driver = driver;
        this.elementActionUtils = new ElementActionUtils(driver);
    }

    public void clickNext() {
        elementActionUtils.tap(NEXT_BTN);
    }

    public void clickGetStarted() {

        elementActionUtils.tap(GET_STARTED_BTN);
    }

    public void clickIndia() {
        elementActionUtils.tap(INDIA_RADIOBTN);
    }

    public void clickBooksCategory(String categoryName) {
        By category = By.xpath("//ul[@class='top-menu']//a[normalize-space()='" + categoryName + "']");
        elementActionUtils.tap(category);
    }
}
