@Registration1
Feature: Verification of Registration functionality
  As a customer
  I want to be able to register my account
  So that I can start shopping


  Background: common steps among scenarios
    Given I landed on Ecommerce website
    When I tap on "Login or register" to navigate to login page
    And I can see the current page breadcrumb as "Login"
    And I can see "I AM A NEW CUSTOMER." and "RETURNING CUSTOMER"
    And I can see "Register Account" is checked by default
    And I click on continue button
    And I can see page title "Create Account" and page header "CREATE ACCOUNT"
    And I can see the current page breadcrumb as "Register"
    And I should see "If you already have an account with us, please login at the login page."
    And I can see input sections as follows:
      | Your Personal Details |
      | Your Address          |
      | Login Details         |
      | Newsletter            |
    And I can see labels 16 first label "First Name:" and last "Subscribe:"

  @verifyCompulsoryFieldsWithoutSubscribeAndPrivacyPolicy
  Scenario: Registration with blank - verify compulsory fields
    And I tap on "Continue" button in register page
    And I can see error messages summary is displayed
    Then I should see red warning error messages:
      | Error: You must agree to the Privacy Policy! |
    And I quit browser


  @verifyCompulsoryFieldWithSubscribeAndPrivacyPolicy
  Scenario: Registration with blank - verify compulsory fields
    And I can see Subscribe Yes is checked
    And I tick on Privacy Policy radio button
    #And I can see Privacy Policy is ticked
    And I tap on "Continue" button in register page
    Then I should see red error warning messages:
      | Login name must be alphanumeric only and between 5 and 64 characters! |
      | First Name must be between 1 and 32 characters!                       |
      | Last Name must be between 1 and 32 characters!                        |
      | Email Address does not appear to be valid!                            |
      | Address 1 must be between 3 and 128 characters!                       |
      | City must be between 3 and 128 characters!                            |
      | Zip/postal code must be between 3 and 10 characters!                  |
      | Please select a region / state!                                       |
      | Password must be between 4 and 20 characters!                         |
    And I quit browser