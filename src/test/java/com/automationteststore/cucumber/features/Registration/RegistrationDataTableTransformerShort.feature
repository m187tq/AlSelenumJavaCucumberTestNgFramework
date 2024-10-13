@RegistrationDataTableTransformerShort
Feature: Verification of Registration functionality

  Scenario: should be able to registration with valid credentials
    Given I landed on Ecommerce website
    When I tap on "Login or register" to navigate to login page
    And I click on continue button
    And I input unique emailAddress
    And I enter all input fields with valid credentials
      | firstName | lastName | telephoneNumber | faxNumber     | companyName | address_One      | address_Two    | city_name  | region_or_state | zip_code | user_password | user_confirm_password |
      | Firstname | Lastname | 074000000000    | 0740000000011 | ABC Limited | 1 Address Street | South Gorton 2 | Manchester | Lancashire      | M9 5TF   | Password@123  | Password@123          |
    And I input unique login name
    And I tick on subscription option Yes
    And I tick on Privacy Policy radio button
    When I click the continue button
