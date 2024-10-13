@LoginMap
Feature: Login Functionality Using map

  Background: common steps
    Given I landed on Ecommerce website
    When I tap on "Login or register" to navigate to login page

  Scenario Outline: user should be able to login with valid credentials
    And I login in login page with valid credentials
      | Login name | webdriverio2 |
      | Password   | webdriverio2 |
    And I click on "Login" button in login page
    Then should be presented with the following validation message as "<loginValidationMessage>"
    And logoff application
    Examples:
      | loginValidationMessage |
      | Welcome back James     |