Feature: User Registration on Ecommerce Platform

  Background:
    Given I on the homepage "https://automationteststore.com"

  Scenario: Registering an Account with Mandatory Fields
    When I click on the "My Account" drop-down menu
    And I click on the "Register" option
    And I enter new account details into the mandatory fields
    And I click on the "Continue" button
    Then I should be logged in and taken to the "Account Success" page
    And a confirmation email should be sent to the registered email address

  Scenario: Verify "Thank you for registering" email
    When I click on the "My Account" drop-down menu
    And I click on the "Register" option
    And I enter new account details into the mandatory fields
    And I click on the "Continue" button
    Then a confirmation email for registering the account should be sent
    And the email subject, body, and from address should be verified
    And there should be a link to the login page in the email body
    When I click on the login page link from the email
    Then I should be taken to the login page

  Scenario: Registering an Account with All Fields
    When I click on the "My Account" drop-down menu
    And I click on the "Register" option
    And I enter new account details into all the fields
    And I click on the "Continue" button
    Then I should be logged in and taken to the "Account Success" page

  Scenario: Notification Messages for Mandatory Fields
    When I click on the "My Account" drop-down menu
    And I click on the "Register" option
    And I does not enter anything into the fields
    And I click on the "Continue" button
    Then warning messages should be displayed for the respective mandatory fields

  Scenario: Registering an Account with Newsletter 'Yes'
    When I click on the "My Account" drop-down menu
    And I click on the "Register" option
    And I enter new account details into all the fields
    And I click on the "Yes" radio option for the newsletter
    And I click on the "Continue" button
    Then I should be logged in and taken to the "Account Success" page
    When I click on the "Subscribe/unsubscribe to newsletter" option
    Then the "Yes" option should be displayed as selected by default

  Scenario: Registering an Account with Newsletter 'No'
    When I click on the "My Account" drop-down menu
    And I click on the "Register" option
    And I enter new account details into all the fields
    And I click on the "No" radio option for the newsletter
    And I click on the "Continue" button
    Then I should be logged in and taken to the "Account Success" page
    When I click on the "Subscribe/unsubscribe to newsletter" option
    Then the "No" option should be displayed as selected by default

  Scenario: Different Ways of Navigating to 'Register Account' Page
    When I click on the "My Account" drop-down menu
    And I click on the "Register" option
    Then I should be taken to the "Register Account" page
    When I click on the "My Account" drop-down menu
    And I click on the "Login" option
    And I click on the "Continue" button inside the "New Customer" box
    Then I should be taken to the "Register Account" page
    When I click on the "My Account" drop-down menu
    And I click on the "Login" option
    And I click on the "Register" option from the right column options
    Then I should be taken to the "Register Account" page