@RegisterAndLoginAndLogout
Feature: Verification of Registration functionality

  Scenario Outline: common steps in scenarios
    Given I landed on Ecommerce website
    When I tap on "Login or register" to navigate to login page
    And I tap "Continue" button in login page
    And I input firstName "FirstName"
    And I input lastName "LastName"
    And I input unique emailAddress
    And I input telephone "074000000000"
    And I input fax "074000000001"
    And I input company "ABC Company Ltd"
    And I input Address 1 "1 Address Street"
    And I input Address 2 "Gorton South"
    And I input city "Manchester"
    And I select Region or State "Lancashire"
    And I input zipCode or PostCode "M18 7QT"
    And I select Country "United Kingdom"
    And I input unique static login name
    And I input password "password@123"
    And I input password confirm "password@123"
    And I tick on subscription option "Yes"
    And I can see Subscribe "Yes" is checked
    And I tick on "Privacy Policy" radio button
    And I can see "Privacy Policy" is ticked
    And I tap on "Continue" button in register page
    And I can see the current page breadcrumb as "Success"
    Then I should see "YOUR ACCOUNT HAS BEEN CREATED!" in Account success page
    And I logoff
    When I tap on "Login or register" to navigate to login page
    And I enter the newly created login name
    And I enter the password "password@123"
    And I click on login button in login page
    Then should be presented with the following validation message as "<loginValidationMessage>"
    And logoff application
    Examples:
      | loginValidationMessage |
      | Welcome back           |



