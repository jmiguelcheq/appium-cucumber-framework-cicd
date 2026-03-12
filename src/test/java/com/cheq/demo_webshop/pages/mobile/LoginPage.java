
package com.cheq.demo_webshop.pages.mobile;

import org.openqa.selenium.By;
import io.appium.java_client.android.AndroidDriver;

import com.cheq.demo_webshop.utils.ElementActionUtils;

public class LoginPage {
    private AndroidDriver driver;
    private ElementActionUtils elementActionUtils;

    private By EMAIL_TXT = By.id("Email");
    private By PASSWORD_TXT = By.id("Password");
    private By LOGIN_BTN = By.xpath("//input[@value='Log in']");

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        this.elementActionUtils = new ElementActionUtils(driver);
    }

    public void enterEmail(String userEmail) {
        elementActionUtils.enterText(EMAIL_TXT, userEmail);
    }

    public void enterPassword(String userPassword) {
        elementActionUtils.enterText(PASSWORD_TXT, userPassword);
    }

    public void clickLoginBtn() {
        elementActionUtils.tap(LOGIN_BTN);
    }

    public void clickBooksCategory(String categoryName) {
        By category = By.xpath("//ul[@class='top-menu']//a[normalize-space()='" + categoryName + "']");
        elementActionUtils.tap(category);
    }
}


