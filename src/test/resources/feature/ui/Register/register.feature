@ui 
Feature: Register Functionality

  Scenario: Register
    Given User navigate to webshop registration page
    When User should input valid credentials
      | genderName  | firstName | lastName | email            | password  |
  	  | gender-male | James     | Fornis   | jjIv143@mail.com | james1234 |
    And User click register button
    Then Verify registration is complete

