@RegistrationWithComplexDataTableUsingMap
Feature: Verification of Registration functionality

  Background: common steps among scenarios
    Given I landed on Ecommerce website
    When I tap on "Login or register" to navigate to login page
    And I click on continue button

  Scenario Outline: should be able to registration with valid credentials
    And I enter the following details
      | First Name | Last Name | Telephone Number | Fax Number    | Company Name        | Address1         | Address2       | City       | Region or State | ZIP Code or Postal Code | Password     | Confirm Password |
      | Firstname  | Lastname  | 074000000000     | 0740000000011 | ABC Company Limited | 1 Address Street | South Gorton 2 | Manchester | Lancashire      | M9 5TF                  | Password@123 | Password@123     |
    And I tick on subscription option "Yes"
    And I tick on "Privacy Policy" radio button
    And I tap on "Continue" button in register page
    Then I should see "<page-header>" in Account success page
    And I should see in current page "<page-title>" and "<page-url>"
    Then I should see "<page-header>" and "<registerValidationMessage>" message
    Examples:
      | page-title                     | page-url        | page-header                    | registerValidationMessage |
      | Your Account Has Been Created! | account/success | YOUR ACCOUNT HAS BEEN CREATED! | Congratulations           |