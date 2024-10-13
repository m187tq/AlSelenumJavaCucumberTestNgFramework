@RegistrationDataTableWithoutAssertions
Feature: Verification of Registration functionality

  Background: common steps among scenarios
    Given I landed on Ecommerce website
    When I tap on "Login or register" to navigate to login page
    And I can see the current page breadcrumb as "Login"
    And I can see "I AM A NEW CUSTOMER." and "RETURNING CUSTOMER"
    And I can see "Register Account" is checked by default
    And I click on continue button


  Scenario: should be able to registration with valid credentials
    And I input Your Personal sections with the following details:
      | FirstName | LastName | Telephone    | Fax           |
      | Firstname | Lastname | 074000000000 | 0740000000011 |
    And I input unique emailAddress
    And I input Your Address section with the following details:
      | Company        | Address1         | Address2       | City       | RegionOrState | ZipOrPostCode | Country        |
      | First Name Ltd | 1 Address Street | South Gorton 2 | Manchester | Lancashire    | M9 5TF        | United Kingdom |

    And I input unique login name
    And I input Login Details section with the following details:
      | Password     | Confirm-password |
      | Password@123 | Password@123     |
    And I can see Subscribe Yes is checked
    And I tick on Privacy Policy radio button
    And I tap on "Continue" button in register page
    And I can see the current page breadcrumb as "Success"
    Then I should see "YOUR ACCOUNT HAS BEEN CREATED!" in Account success page
    And I can see "Congratulations!" Your new account has been successfully created!
    And I logoff application


