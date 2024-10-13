@RegistrationWithWaitStrategy
Feature: Verification of Registration functionality

  Scenario: create an account with valid credentials with wait-strategy
    Given I landed on Ecommerce website
    When I tap on "Login or register" to navigate to login page
    And I click on continue
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
    #And I select Country "United Kingdom"
    And I select Country "United Kingdom"
    And I input unique login name
    And I input password "password@123"
    And I input password confirm "password@123"
    And I tick on subscription option "Yes"
    And I can see Subscribe "Yes" is checked
    And I tick on "Privacy Policy" radio button
    And I can see "Privacy Policy" is ticked
    And I tap on "Continue" button
    Then I should see "YOUR ACCOUNT HAS BEEN CREATED!" in Account success page
    And I logoff application