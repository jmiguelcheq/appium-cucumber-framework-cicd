@hybrid @regression
Feature: Login to Contact List UI using API-created user

  Scenario: User can login in UI using credentials created via API
    Given User registers a new account via Contact List API
    When User navigates to the Contact List login page
    And User logs in using the API-created credentials
    Then User should be redirected to the Contact List page