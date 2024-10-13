@NJA-1970
Feature: Login functionality

  Scenario Outline: should be able to login with valid credentials
    Given I navigate to Login page and login as a returning customer
    Then should be presented with the following validation message as "<loginValidationMessage>"
    And logoff application
    Examples:
      | loginValidationMessage |
      | Welcome back James     |



