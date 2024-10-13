@Reg002
Feature: Verification of Registration functionality

  Scenario: common steps in scenarios
    Given I landed on Ecommerce website
    And I can see top menu page properties as follows:
      | Login or register |
      | SPECIALS          |
      | ACCOUNT           |
      | CART              |
      | CHECKOUT          |
    And I can see page properties as follows:
      | page url                         | page title                                  |
      | https://automationteststore.com/ | A place to practice your automation skills! |

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
    And I can see "Register Account" is checked by default
    And I tap "Continue" button in login page
    And I can see page properties as follows:
      | page url                                                    | page title     |
      | https://automationteststore.com/index.php?rt=account/create | Create Account |
    And I can see page title "Create Account" and page header "CREATE ACCOUNT"
    And I can see the current page breadcrumb as "Register"
    And I can see page breadcrumb as follows:
      | Home     |
      | Account  |
      | Register |
    And I should see "If you already have an account with us, please login at the login page."
    And I can see input sections as follows:
      | Your Personal Details |
      | Your Address          |
      | Login Details         |
      | Newsletter            |
    And I can see labels 16 first label "First Name:" and last "Subscribe:"
    And enter Your Personal Details section with:
      | First Name | Last Name | Telephone     | Fax           |
      | FirstName  | LastName  | 0740500000000 | 0740500000001 |
    And I input unique emailAddress
    And enter Your Address Details section with:
      | Company         | Address one | Address two | City       | Region or province | ZipCodeOrPostCode | Country        |
      | ABC Company Ltd | Address 1   | Address 2   | Manchester | Lancashire         | M18 7QT           | United Kingdom |
    And I input unique login name
    And enter Your Login Details section with:
      | Password | Password Confirm |
      | password | password         |
    And I tick on subscription option "Yes"
    And I can see Subscribe "Yes" is checked
    And I tick on "Privacy Policy" radio button
    And I can see "Privacy Policy" is ticked
    And I tap on "Continue" button in register page
    And I can see page properties as follows:
      | page url                                                     | page title                     |
      | https://automationteststore.com/index.php?rt=account/success | Your Account Has Been Created! |
    And I can see the current page breadcrumb as "Success"
    And I can see page breadcrumb as follows:
      | Home    |
      | Account |
      | Success |

    Then I should see "YOUR ACCOUNT HAS BEEN CREATED!" in Account success page
    And I can see "Congratulations!" Your new account has been successfully created!
    And I logoff application