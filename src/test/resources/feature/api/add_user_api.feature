@api @addUser
Feature: Add User API

  Scenario: User can be added successfully via API
    Given User prepares a valid add user request payload
    When User sends the add user API request
    Then API response status code should be 201
    And API response should contain an authentication token