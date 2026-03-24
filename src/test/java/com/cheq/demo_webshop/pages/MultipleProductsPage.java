package com.cheq.demo_webshop.pages;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;

import com.cheq.demo_webshop.utils.common.ElementActionUtils;


public class MultipleProductsPage {

    private AndroidDriver driver;
    private ElementActionUtils elementActionUtils;

    public MultipleProductsPage(AndroidDriver driver) {
        this.driver = driver;
        this.elementActionUtils = new ElementActionUtils(driver);
    }

    // Locators
    private By searchBox = By.id("small-searchterms");
    private By searchButton = By.xpath("//input[@value='Search']");
    private By cartLink = By.xpath("//span[@class='cart-label']");


    public void searchForProduct(String keyword) {
    	elementActionUtils.enterText(searchBox, keyword);
    }

    public void clickSearchButton() {
    	elementActionUtils.tap(searchButton);
    }

    public void clickProduct(String productName) {
    	By itemLink = By.xpath("//a[contains(text(), '" + productName + "')]");
    	elementActionUtils.tap(itemLink);
    }

    public void addToCart() {
    	By addToCartButton = By.xpath("//input[contains(@id,'add-to-cart-button')]");
    	elementActionUtils.tap(addToCartButton);
    }

    public void clickShoppingCart() {
        elementActionUtils.tap(cartLink);
    }

    public void verifyProductPrice(String productName, String expectedPrice) {
        By priceLocator = By.xpath("//a[text()='" + productName + "']/ancestor::tr//span[@class='product-unit-price']");
        elementActionUtils.verifyText(priceLocator, expectedPrice);

    }
}
 