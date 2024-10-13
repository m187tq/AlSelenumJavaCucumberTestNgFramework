@LoginShort
Feature: Login Functionality

  Background: common steps
    Given I landed on Ecommerce website
    When I tap on "Login or register" to navigate to login page

  Scenario Outline: user should be able to login with valid credentials
    And I login in login page as a returning customer
      | loginname    | password     |
      | webdriverio2 | webdriverio2 |
    Then should be presented with the following validation message as "<loginValidationMessage>"
    And logoff application
    Examples:
      | loginValidationMessage |
      | Welcome back           |