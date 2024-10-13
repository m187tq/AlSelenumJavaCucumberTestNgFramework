@LoginDataTableTransformer
Feature: Login Page - Login Functionality
  As a customer
  I want to be able to login to the application
  So that I can use the application

  Background: common steps in scenarios
    Given I landed on Ecommerce website
    And I can see page properties as follows:
      | page url                         | page title                                  |
      | https://automationteststore.com/ | A place to practice your automation skills! |
    And I can see top menu page properties as follows:
      | Login or register |
      | SPECIALS          |
      | ACCOUNT           |
      | CART              |
      | CHECKOUT          |
    And I verify if links are present:
      | SPECIALS |
      | ACCOUNT  |
      | CART     |
      | CHECKOUT |
    When I tap on "Login or register" to navigate to login page
    And I can see page properties as follows:
      | page url                                                   | page title    |
      | https://automationteststore.com/index.php?rt=account/login | Account Login |
    And I can see the current page breadcrumb as "Login"
    And I can see page breadcrumb as follows:
      | Home             |
      | Register Account |
      | Login            |
    And I can see:
      | I AM A NEW CUSTOMER. |
      | RETURNING CUSTOMER   |
    And I can see "I AM A NEW CUSTOMER." and "RETURNING CUSTOMER"
    And I can see "Register Account" is checked by default
    And can see "Forgot your password?" and "Forgot your login?"

  Scenario: 01 - Successful login with valid credentials
    When I login with valid credentials
      | loginname       | password    |
      | datastudioplace | Manchester1 |
    Then should be presented with the following validation message as "Welcome back"
    And I can see the current page breadcrumb as "Account"
    And I can see 9 side Widget icons are displayed
    And I can see the links in aside widget are displayed:
      | Account Dashboard    |
      | My wish list         |
      | Edit account details |
      | Change password      |
      | Manage Address Book  |
      | Order history        |
      | Transaction history  |
      | Downloads            |
      | Notifications        |
      | Logoff               |
    And I should see the followings dash tile options in My Account page:
      | Manage Address Book |
      | Order history       |
      | Downloads           |
      | Transaction history |
#    And I logout application

#  Scenario Outline: 02 - Successful login with valid credentials
#    And I login application with loginname "<loginName>" and password "<password>"
#    Then should be presented with the following validation message as "<loginValidationMessage>"
#    And I logout application
#    And I quit browser
#    Examples:
#      | loginName       | password    | loginValidationMessage |
#      | datastudioplace | Manchester1 | Welcome back           |
#
#  Scenario Outline: 03 - Unsuccessful login with invalid credentials
#    And I enter invalid loginname "<loginName>" and password "<password>"
#    And I click on login button in login page
#    Then should be presented with the following error validation message "<loginErrorValidationMessage>"
#    And I quit browser
#    Examples:
#      | loginName         | password     | loginErrorValidationMessage                  |
#      | datastudioplace00 | Manchester00 | Error: Incorrect login or password provided. |
#      |                   |              | Error: Incorrect login or password provided. |
#
#  Scenario Outline: 04 - Successful login with valid credentials with DataTable
#    When I login with the following credentials:
#      | loginname       | password    |
#      | datastudioplace | Manchester1 |
#    Then should be presented with the following validation message as "<loginValidationMessage>"
#    And I logout application
#    And I quit browser
#    Examples:
#      | loginValidationMessage |
#      | Welcome back           |