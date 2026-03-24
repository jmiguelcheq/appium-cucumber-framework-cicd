package com.cheq.demo_webshop.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import com.cheq.demo_webshop.utils.common.ElementActionUtils;

public class TempoPage {
    private AndroidDriver driver;
    private ElementActionUtils elementActionUtils;

    public TempoPage(AndroidDriver driver) {
        this.driver = driver;
        this.elementActionUtils = new ElementActionUtils(driver);
    }

    private By burgerMenu = By.xpath("//span[text()='Categories']");
    private By apparelMenu = By.xpath("//ul[@class='mob-top-menu show']//a[normalize-space(text())='Apparel & Shoes']");
    private By blueGreenSneakerLnk = By.xpath("//a[text()='Blue and green Sneaker']");
    private By addToCartBtn = By.xpath("//div[@class='add-to-cart-panel']//input[@value='Add to cart']");
    private By removeConfirmationLbl = By.xpath("//div[@class='order-summary-content']");
    private By nextBtn = By.xpath("//a[text()='Next']");
    private By woolHatLnk = By.xpath("//a[text()='Wool Hat']");
    private By addToCompareListBtn = By.xpath("//input[@value='Add to compare list']");
    private By compareProductLbl = By.xpath("//h1[text()='Compare products']");

    // Click the burger (category) menu
    public void clickBurgerMenu() {
        elementActionUtils.tap(burgerMenu);
    }

    // Click Apparel & Shoes category
    public void clickApparelShoesCategory() {
        elementActionUtils.tap(apparelMenu);
    }

    // Open Blue and Green Sneaker product page
    public void selectBlueGreenSneaker() {
        elementActionUtils.tap(blueGreenSneakerLnk);
    }

    // Click Add to Cart button
    public void clickAddToCart() {
        elementActionUtils.tap(addToCartBtn);
    }

    // Get the shopping cart empty confirmation text
    public void verifyItemRemove() {

        elementActionUtils.verifyElementVisible(removeConfirmationLbl);
    }

    // Click the Next button
    public void clickNextButton() {
        elementActionUtils.tap(nextBtn);
    }

    // Open Wool Hat product page
    public void clickWoolHat() {
        elementActionUtils.tap(woolHatLnk);
    }

    public void swipeUp(){
        elementActionUtils.swipe("down", 2);
    }

    public void swipeToWoolHat(){
        elementActionUtils.swipe("down", 1);
    }

    public void clickAddToCompareList(){
        elementActionUtils.tap(addToCompareListBtn);
    }

    public void verifyCompareProductSection(){
        elementActionUtils.verifyElementVisible(compareProductLbl);
    }

    public void verifyWoolHat(){
        elementActionUtils.verifyElementVisible(woolHatLnk);
    }

}
