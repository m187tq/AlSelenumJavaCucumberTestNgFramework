@Login_HoverAble_Button
Feature: Login Functionality

  Scenario: common steps
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
    When I hover over Account menu item link
    Then I should see "Login" and "Check Your Order" links
    And I tap on Login options
    And I can see the current page breadcrumb as "Login"
    And I can see page breadcrumb as follows:
      | Home             |
      | Register Account |
      | Login            |
    And I can see current page header as "Account Login"
    And I login in login page as a returning customer
      | loginname    | password     |
      | webdriverio2 | webdriverio2 |
    And I click on "Login" button in login page
    Then should be presented with the following validation message as "Welcome back James"
    And logoff application
