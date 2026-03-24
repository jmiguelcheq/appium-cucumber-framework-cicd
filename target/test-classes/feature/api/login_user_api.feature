@api @regression @loginUser
Feature: Login User API
	
  Scenario: User can log in successfully via API
    Given User has valid credentials for login API
    When User sends the login user API request
    Then API response status code should be 200
    And Login API response should contain the correct user details
    And Login API response should contain an authentication token