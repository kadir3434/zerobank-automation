@Account_Activity
Feature: Account Activity
  As a user should be able to see Account Activity page

  Background: open Login page and logs in succesfully
    Given user is on login page
    Then user logs in with valid credentials
    Then user navigates to "Account Activity" page

  Scenario: Account Activity Scenario
    Then user verifies that page title is "Zero - Account Activity"

  Scenario: Account DropDown Default option
    Then user verifies that account DropDown default value is "Savings"

  Scenario:  Account DropDown Options
    Then user verifies Account DropDown options are
      | Savings     |
      | Checking    |
      | Savings     |
      | Loan        |
      | Credit Card |
      | Brokerage   |

  Scenario: Transactions table
    Then user verifies Transactions table has
      | Date        |
      | Description |
      | Deposit     |
      | Withdrawal  |
