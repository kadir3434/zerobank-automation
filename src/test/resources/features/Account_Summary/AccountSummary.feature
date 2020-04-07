# new feature
# Tags: optional
@Regression
@Account_Summary
Feature: Account Summary
  As a user, I should be able to see Account Summary page

  Background: open Login page and logs in succesfully
    Given user is on login page
    Then user logs in with valid credentials

  Scenario: user verifies page title and Account Types
    Then user verifies that page title is "Zero - Account Summary"
    And user verifies that "Cash Accounts" account type is displayed
    And user verifies that "Investment Accounts" account type is displayed
    And user verifies that "Cash Accounts" account type is displayed
    And user verifies that "Credit Accounts" account type is displayed
    And user verifies that "Loan Accounts" account type is displayed

  Scenario: user verifies account types with datatable
    Then user verifies that All Account Types are displayed
    |Cash Accounts      |
    |Investment Accounts|
    |Credit Accounts    |
    |Loan Accounts      |

  Scenario: user verifies Credit Accounts column names
    Then user verifies Credit Accounts column names
      | Account     |
      | Credit Card |
      | Balance     |