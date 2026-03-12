@ui @remove
Feature: Verify Successful Removal of Item

  Scenario: Remove Products from Cart
    Given User navigate to webshop login page
      | strategyType | locatorTag | message |
      | text         | a          | Log in  |
    When User should input valid login credentials
      | username           | password  |
      | johndoe99@mail.com | secret123 |
    And Verify successful login
    And Click the Category Menu
    And Select Apparel & Shoes category
    And Select a Blue and Green Sneaker product
    And Click the Add to Cart button
    And Click the Shopping cart link at the top
    And Check the Remove checkbox beside the product
    And Click the Update shopping cart button
    Then Verify that the Shopping Cart is empty



