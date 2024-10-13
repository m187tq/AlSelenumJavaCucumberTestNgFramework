Feature: Login Functionality

  Background:

    Given I on the ecommerce homepage
    When I logs in with valid credentials

  Scenario Outline: Verify logging into the Application using valid credentials
    When I click on 'My Account' drop menu
    And I click on 'Login' option
    And I enter valid email address '<email>'
    And I enter valid password '<password>'
    And I click on 'Login' button
    Then I should be taken to the 'Account' page
    Examples:
      | email               | password |
      | amotoori3@gmail.com | 12345    |

  Scenario Outline: Verify logging into the Application using invalid credentials
    When I click on 'My Account' drop menu
    And I click on 'Login' option
    And I enter invalid email address '<email>'
    And I enter invalid password '<password>'
    And I click on 'Login' button
    Then a warning message should be displayed with the text 'Warning: No match for E-Mail Address and/or Password.'
    Examples:
      | email               | password  |
      | xyzabc123@gmail.com | xyzabc123 |