@Login
Feature: Login Functionality

  Background: common steps
    Given I landed on Ecommerce website
#    And I can see page properties as follows:
#      | page url                         | page title                                  |
#      | https://automationteststore.com/ | A place to practice your automation skills! |
#    And I can see top menu page properties as follows:
#      | Login or register |
#      | SPECIALS          |
#      | ACCOUNT           |
#      | CART              |
#      | CHECKOUT          |
#    And I should see "Login or register" link is displayed in the top menu
    When I tap on "Login or register" to navigate to login page
#    And I can see the current page breadcrumb as "Login"
#    And I can see page breadcrumb as follows:
#      | Home             |
#      | Register Account |
#      | Login            |
#    And I can see current page header as "Account Login"
#    And I can see "I am a new customer." and "Returning Customer"
#    And I can see "Register Account" is checked by default

  Scenario Outline: user should be able to login with valid credentials
    And I login in login page as a returning customer
      | loginname    | password     |
      | webdriverio2 | webdriverio2 |
    Then should be presented with the following validation message as "<loginValidationMessage>"
    And logoff application
    Examples:
      | loginValidationMessage |
      | Welcome back           |