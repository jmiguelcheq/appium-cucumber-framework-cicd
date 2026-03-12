@ui @compare
Feature: Verify Adding Product to Compare List

  Scenario: Adding Product to Compare List
    Given User navigate to webshop login page
      | strategyType | locatorTag | message |
      | text         | a          | Log in  |
    When User should input valid login credentials
      | username           | password  |
      | johndoe99@mail.com | secret123 |
    And Verify successful login
    And Click the Category Menu
    And Select Apparel & Shoes category
    And Perform a swipe down gesture on the product list
    And Click the Next page button
    And Perform another swipe gesture until Wool Hat becomes visible
    And Tap the Wool Hat product
    And Tap Add to compare list
    And Verify the item is added to the comparison list
