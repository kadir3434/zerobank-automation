@Regression
@Login
Feature: Login
  As a user, I should be able to login zero bank application

  @Login_with_valid_credentials
  Scenario: Login with valid credentials
    Given user is on login page
    Then user logs in with valid credentials
    And user verifies that "Account Summary" page subtitle is displayed
    And user verifies that page title is "Zero - Account Summary"


  Scenario: Login with invalid credentials
    Given user is on login page
    Then user tries to log in with invalid credentials
    And user verifies that "Login and/or password are wrong." error message is displayed.

  Scenario: Login with invalid credentials
    Given user is on login page
    Then user tries to login with "" and "" blank credentials
    And user verifies that "Login and/or password are wrong." error message is displayed.