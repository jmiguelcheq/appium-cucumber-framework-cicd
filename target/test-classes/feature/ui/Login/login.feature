@ui @login
Feature: Login Functionality

  Scenario: Login
    Given User navigate to webshop login page
      | strategyType | locatorTag | message |
      | text         | a          | Log in  |
    When User should input valid login credentials
      | username           | password  |
      | johndoe99@mail.com | secret123 |
    And Verify successful login
    And User click logout button
    Then Verify successful logout


