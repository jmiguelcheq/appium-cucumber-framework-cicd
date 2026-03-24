package com.cheq.demo_webshop.pages;
import com.cheq.demo_webshop.pages.components.HeaderComponent;
import com.cheq.demo_webshop.utils.common.ElementActionUtils;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;


public class HomePage {

    private AndroidDriver driver;
    public HeaderComponent header;


    // Constructor
    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        this.header = new HeaderComponent(driver);
    }

}
 