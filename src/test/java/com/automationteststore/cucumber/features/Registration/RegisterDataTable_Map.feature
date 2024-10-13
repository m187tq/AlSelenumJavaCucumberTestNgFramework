@RegisterDataTableMapSteps
Feature: Verification of Registration functionality

  Background: common steps among scenarios
    Given I navigate to Account Registration page

  Scenario: should be able to registration with valid credentials
    And I enter first and last name respectively in registration page
      | First Name | John |
      | Last Name  | Doe  |
    And I input unique emailAddress
    And I input valid telephone and fax numbers
      | Telephone | 074000000000  |
      | Fax       | 0740000000011 |
    And I enter a valid credentials in your address section in registration page
      | Company        | ABC Company Ltd     |
      | Address 1      | 1 Address Street    |
      | Address 2      | 2 Address North End |
      | City           | Manchester          |
      | Region / State | Lancashire          |
      | ZIP Code       | M9 5TF              |
    And I input unique login name
    And I input valid password and confirm password
      | Password         | Password@123 |
      | Password Confirm | Password@123 |
    And I can see Subscribe Yes is checked
    And I tick on Privacy Policy radio button
    And I tap on "Continue" button in register page
