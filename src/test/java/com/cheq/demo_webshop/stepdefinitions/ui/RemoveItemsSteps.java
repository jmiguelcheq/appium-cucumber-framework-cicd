package com.cheq.demo_webshop.stepdefinitions.ui;

import com.cheq.demo_webshop.manager.DriverManager;
import com.cheq.demo_webshop.pages.*;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;

public class RemoveItemsSteps {

    AndroidDriver driver;
    private HomePage homePage;
    private RegisterPage registerPage;
    private TempoPage tempoPage;
    private CartPage cartPage;
    private MultipleProductsPage multipleProductsPage;

    public RemoveItemsSteps() {
        this.driver = DriverManager.getDriver();
        this.homePage = new HomePage(driver);
        this.registerPage = new RegisterPage(driver);
        this.tempoPage = new TempoPage(driver);
        this.cartPage = new CartPage(driver);
        this.multipleProductsPage = new MultipleProductsPage(driver);

    }

    @And("Click the Category Menu")
    public void click_the_category_menu() {
        tempoPage.clickBurgerMenu();
    }
    @And("Select Apparel & Shoes category")
    public void select_apparel_shoes_category() {
        tempoPage.clickApparelShoesCategory();
    }
    @And("Select a Blue and Green Sneaker product")
    public void select_a_blue_and_green_sneaker_product() {
        tempoPage.selectBlueGreenSneaker();
    }
    @And("Click the Add to Cart button")
    public void click_the_add_to_cart_button() {
        tempoPage.clickAddToCart();
    }
    @And("Click the Shopping cart link at the top")
    public void click_the_shopping_cart_link_at_the_top() {
        multipleProductsPage.clickShoppingCart();
    }
    @And("Check the Remove checkbox beside the product")
    public void check_the_remove_checkbox_beside_the_product() {
        cartPage.clickRemoveCheckbox();
    }
    @And("Click the Update shopping cart button")
    public void click_the_update_shopping_cart_button() {
        cartPage.clickUpdateCartButton();
    }
    @And("Verify that the Shopping Cart is empty")
    public void verify_that_the_shopping_cart_is_empty() {
        tempoPage.verifyItemRemove();
    }
}
