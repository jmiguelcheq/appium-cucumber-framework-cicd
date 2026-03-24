package com.cheq.demo_webshop.pages;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;

import com.cheq.demo_webshop.utils.common.ElementActionUtils;

public class BookPage {

    private AndroidDriver driver;
    private ElementActionUtils elementActionUtils;

    public BookPage(AndroidDriver driver) {
        this.driver = driver;
        this.elementActionUtils = new ElementActionUtils(driver);
    }

    private By fictionBookTitle = By.xpath("//h2[@class='product-title']/a[normalize-space(text())='Fiction']");
    private By fictionBookPrice = By.xpath("//h2[@class='product-title']/a[text()='Fiction']/ancestor::div[@class='details']//span[contains(@class, 'actual-price')]");
    private By addToCartButton = By.xpath("//input[contains(@id, 'add-to-cart-button')]");
    private By shoppingCartLink = By.cssSelector("a[class='ico-cart'] span[class='cart-label']");


    public void isFictionBookVisible() {
        elementActionUtils.verifyElementVisible(fictionBookTitle);
    }

    public void isFictionBookPriceVisible() {
    	elementActionUtils.verifyElementVisible(fictionBookPrice);
    }

    public void clickFictionBook() {
    	elementActionUtils.tap(fictionBookTitle);
    }

    public void clickdAddToCart() {
        elementActionUtils.tap(addToCartButton);
    }

    public void clickShoppingCartLink() {
    	elementActionUtils.tap(shoppingCartLink);
    }
}
 