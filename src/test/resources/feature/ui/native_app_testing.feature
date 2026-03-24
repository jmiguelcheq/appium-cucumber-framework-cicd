Feature: Application Switching Validation
	
  @ui @mobileWeb
  Scenario: Verify switching from mobile web to native app with UI validation
    Given User is on the mobile web homepage
    Then Search icon should be visible in mobile web
    When User switches to native shopping app
    Then Search icon should be visible in native app