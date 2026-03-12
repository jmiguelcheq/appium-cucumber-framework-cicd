package com.cheq.demo_webshop.pages.mobile;
import com.cheq.demo_webshop.factory.DropdownFactory;
import com.cheq.demo_webshop.utils.ElementActionUtils;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartPage {

    private AndroidDriver driver;
    private ElementActionUtils actions;


    public CartPage(AndroidDriver driver) {
        this.driver = driver;
        this.actions = new ElementActionUtils(driver);
    }

    private By removeCheckbox = By.xpath("//input[@name='removefromcart']");
    private By updateCartButton = By.xpath("//input[contains(@value, 'Update')]");


    public void isRemoveCheckboxVisible() {
    	actions.verifyElementVisible(removeCheckbox);
    }

    public void clickRemoveCheckbox() {
    	actions.tap(removeCheckbox);
    }

    public void verifySubCategory(String bookTitle) {
        By bookLocator = By.xpath("//a[text()='" + bookTitle + "']");
        actions.verifyElementVisible(bookLocator);
    }

    public void isOrderSummaryVisible(String bookTitle) {
        By bookLocator = By.xpath("//a[@class='product-name' and text()='" + bookTitle + "']");
        actions.verifyElementVisible(bookLocator);
    }
    
    public void clickUpdateCartButton() {
    	actions.tap(updateCartButton);
    }
    
 // In 'MultipleProductsPage.java'
    public void selectCountry(String strategyType, String locatorValue, String selectedValue) {
        WebElement dropdown = driver.findElement(By.id(locatorValue));
        DropdownFactory.getStrategy(strategyType).select(dropdown, selectedValue);
    }
}
 