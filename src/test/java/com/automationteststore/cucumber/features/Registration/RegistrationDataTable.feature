@RegistrationDataTable
Feature: Verification of Registration functionality

  Background: common steps among scenarios
    Given I landed on Ecommerce website
    When I tap on "Login or register" to navigate to login page
    And I can see the current page breadcrumb as "Login"
    And I can see "I AM A NEW CUSTOMER." and "RETURNING CUSTOMER"
    And I can see "Register Account" is checked by default
    And I click on continue button
    And I can see page title "Create Account" and page header "CREATE ACCOUNT"
    And I can see the current page breadcrumb as "Register"
    And I should see "If you already have an account with us, please login at the login page."
    And I can see input sections as follows:
      | Your Personal Details |
      | Your Address          |
      | Login Details         |
      | Newsletter            |

    And I can see labels 16 first label "First Name:" and last "Subscribe:"

  Scenario Outline:: should be able to registration with valid credentials
    And I input the following details in registration input fields
      | firstName | lastName | telephoneNumber | faxNumber     | companyName | address_One    | address_Two    | city_name  | region_or_state | zip_code | user_password | user_confirm_password |
      | Firstname | Lastname | 074000000000    | 0740000000011 | ABC Ltd     | Address Street | South Gorton 2 | Manchester | Lancashire      | M9 5TF   | Password@123  | Password@123          |
    And I input unique emailAddress
    And I input unique Login name
    And I can see Subscribe Yes is checked
    And I tick on Privacy Policy radio button
    And I tap on "Continue" button in register page
    And I can see the current page breadcrumb as "Success"
    Then I should see "YOUR ACCOUNT HAS BEEN CREATED!" in Account success page
    And I can see "Congratulations!" Your new account has been successfully created!
    And I should see "<page-Title>" and "<page-url>"
    Then I should see "<page-header>" and "<registerValidationMessage>" message
    And I logoff application
    Examples:
      | page-Title                     | page-url        | page-header                    | registerValidationMessage |
      | Your Account Has Been Created! | account/success | YOUR ACCOUNT HAS BEEN CREATED! | Congratulations           |

