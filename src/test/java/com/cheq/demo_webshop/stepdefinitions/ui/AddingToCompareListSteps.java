package com.cheq.demo_webshop.stepdefinitions.ui;

import com.cheq.demo_webshop.manager.DriverManager;
import com.cheq.demo_webshop.pages.*;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;

public class AddingToCompareListSteps {

    AndroidDriver driver;
    private HomePage homePage;
    private RegisterPage registerPage;
    private TempoPage tempoPage;
    private CartPage cartPage;
    private MultipleProductsPage multipleProductsPage;

    public AddingToCompareListSteps() {
        this.driver = DriverManager.getDriver();
        this.homePage = new HomePage(driver);
        this.registerPage = new RegisterPage(driver);
        this.tempoPage = new TempoPage(driver);
        this.cartPage = new CartPage(driver);
        this.multipleProductsPage = new MultipleProductsPage(driver);

    }

    @And("Perform a swipe down gesture on the product list")
    public void perform_a_swipe_down_gesture_on_the_product_list() {
        tempoPage.swipeUp();
    }
    @And("Click the Next page button")
    public void click_the_next_page_button() {
        tempoPage.clickNextButton();

    }
    @And("Perform another swipe gesture until Wool Hat becomes visible")
    public void perform_another_swipe_gesture_until_wool_hat_becomes_visible() {
        tempoPage.swipeToWoolHat();
    }
    @And("Tap the Wool Hat product")
    public void tap_the_wool_hat_product() {
        tempoPage.clickWoolHat();
    }
    @And("Tap Add to compare list")
    public void tap_add_to_compare_list() {
        tempoPage.swipeUp();
        tempoPage.clickAddToCompareList();
    }
    @And("Verify the item is added to the comparison list")
    public void verify_the_item_is_added_to_the_comparison_list() {
        tempoPage.verifyCompareProductSection();
        tempoPage.swipeUp();
        tempoPage.verifyWoolHat();
    }
}
