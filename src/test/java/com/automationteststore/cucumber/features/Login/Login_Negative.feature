@LoginNegative
Feature: Login Functionality
  As a customer,
  I want to not to be able login into my account with invalid credentials,
  So that I can access my account securely.

  Scenario Outline: User should NOT be able to login with invalid credentials: email address
    And I navigate to login page and login with invalid credentials
    Then should be presented with the following Error validation message as "<loginValidationErrorMessage>"
    #And I close the browser
    Examples:
      | loginValidationErrorMessage                           |
      | Warning: No match for E-Mail Address and/or Password. |


  Scenario Outline: User should NOT be able to login with invalid credentials with multiple combination
    And I login in login page as a returning customer with invalid login name "<login name>" and invalid "<password>"
    Then should be presented with the following Error validation message as "<loginValidationErrorMessage>"
    And I should see page title and url "Account Login", "rt=account/login" respectively
    Examples:
      | login name     | password      | loginValidationErrorMessage                           |
      | wrongLoginname | wrongPassword | Warning: No match for E-Mail Address and/or Password. |
      | loginname      | wrongPassword | Warning: No match for E-Mail Address and/or Password. |
      | wrongLoginname |               | Warning: No match for E-Mail Address and/or Password. |
      |                | wrongPassword | Warning: No match for E-Mail Address and/or Password. |
      |                |               | Warning: No match for E-Mail Address and/or Password. |
      | @@@@1234       | wrongPassword | Warning: No match for E-Mail Address and/or Password. |
      | _=!!!!!^&ouoo  |               | Warning: No match for E-Mail Address and/or Password. |
