@AILogin
Feature: User Login

  Scenario Outline: Verify successful user login with valid credentials
    Given I landed on Ecommerce website
    And I navigate to the Login Page
    And enter username "<loginname>" and password "<password>"
    When I click on the Login button
    Then I should be redirected to the Account Page url contains "account"
    And I can see profile welcome message "<message>" should be displayed
    Examples:
      | loginname    | password     | message      |
      | webdriverio2 | webdriverio2 | Welcome back |