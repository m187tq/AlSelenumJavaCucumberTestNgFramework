Feature: User Registration, Login, and Core Ecommerce Functionalities

  Background:
    Given I on the login page

  Scenario Outline: Successful User Login
    When I enter valid username "<username>" and password "<password>"
    And I click the "Login" button
    Then should be redirected to the dashboard
    And a success message should be displayed
    Examples:
      | username | password    |
      | testuser | password123 |

  Scenario Outline: Unsuccessful User Login with Invalid Credentials
    When I enter invalid username "<username>" and password "<password>"
    And I click the "Login" button
    Then an error message should be displayed indicating invalid credentials
    And should remain on the login page
    Examples:
      | username    | password      |
      | invaliduser | wrongpassword |

  Scenario Outline: Password Reset Functionality
    When I click the "Forgot Password" link
    And I enter a valid email address "<email>"
    And I click the "Submit" button
    Then a password reset email should be sent to the provided email address
    And a success message should be displayed
    Examples:
      | email                |
      | testuser@example.com |

  Scenario: User Registration with Valid Data
    Given I on the registration page
    When I enter all required user information
    And I click the "Register" button
    Then a user account should be created successfully
    And I should be redirected to the login page or homepage
    And a success message should be displayed

  Scenario Outline: User Registration with Invalid Data
    Given I on the registration page
    When I enter invalid or incomplete user information with "<invalid_data>"
    And I click the "Register" button
    Then error messages should be displayed indicating the specific errors
    And I should remain on the registration page
    Examples:
      | invalid_data         |
      | testuser@example.com |
      |                      |
      | invalidemail         |

  Scenario: User Logout Functionality
    Given I is logged in
    When I click the "Logout" button or link
    Then I should be logged out and redirected to the login page

  Scenario Outline: Search Functionality with Valid Search Term
    Given I is on a page with a search bar
    When I enter a valid search term "<search_term>" in the search bar
    And I click the "Search" button or presses Enter
    Then relevant search results should be displayed
    Examples:
      | search_term |
      | laptop      |

  Scenario Outline: Search Functionality with Invalid or No Search Term
    Given I is on a page with a search bar
    When I enter an "<invalid_search_term>" or leaves the search bar empty
    And I click the "Search" button or presses Enter
    Then a "No results found" message or similar should be displayed
    Examples:
      | invalid_search_term |
      | #$%^&*              |
      |                     |

  Scenario: Adding an Item to the Cart
    Given I is on a product page
    When I selects product options (if applicable)
    And I click the "Add to Cart" button
    Then the item should be added to the cart
    And the cart icon should update to reflect the added item
    And a success message should be displayed

  Scenario: Removing an Item from the Cart
    Given I can see items in the cart
    When I navigate to the cart page
    And I click the "Remove" or "Delete" button next to an item
    Then item should be removed from the cart
    And cart total should be updated

  Scenario: Updating Item Quantity in the Cart
    Given I has items in the cart
    When I navigate to the cart page
    And I changes the quantity of an item
    And I click the "Update Cart" button
    Then the cart total should be updated to reflect the new quantity

  Scenario: Proceeding to Checkout from the Cart
    Given I can see items in the cart
    When I navigate to the cart page
    And I click the "Proceed to Checkout" or similar button
    Then I should be redirected to the checkout page

  Scenario: Successful Checkout with Valid Payment and Shipping Information
    Given I on the checkout page with items in the cart
    When I enter valid shipping and billing information
    And I selects a shipping method
    And I enter valid payment information
    And I click the "Place Order" or similar button
    Then the order should be placed successfully
    And an order confirmation page should be displayed with order details
    And a confirmation email should be sent

  Scenario: Unsuccessful Checkout with Invalid Payment Information
    Given I on the checkout page with items in the cart
    When I enter invalid payment information
    And I click the "Place Order" or similar button
    Then an error message should be displayed indicating invalid payment information
    And the order should not be placed

  Scenario: Viewing Order History for Logged-in Users
    Given I logged in
    When I navigate to the "Order History" or "My Orders" page
    Then past orders should be displayed with relevant details

  Scenario: Contact Form Submission with Valid Data
    Given I on the contact page
    When I enter valid contact information
    And I click the "Submit" button
    Then the contact form should be submitted successfully
    And a success message should be displayed

  Scenario: Contact Form Submission with Invalid or Missing Data
    Given I is on the contact page
    When I enter invalid or incomplete contact information
    And I click the "Submit" button
    Then error messages should be displayed indicating the specific errors

  Scenario: Subscribing to a Newsletter with a Valid Email Address
    Given I can see newsletter subscription
    When I enter a valid email address
    And I click the "Subscribe" button
    Then the subscription should be successful
    And a confirmation message should be displayed

  Scenario: Unsubscribing from a Newsletter
    Given I subscribed to the newsletter
    When I click the unsubscribe link in a newsletter email
    Then I should be unsubscribed
    And a confirmation message should be displayed

  Scenario: Filtering or Sorting Products
    Given I on a product listing page with filtering/sorting options
    When I selects filtering or sorting criteria
    Then the products should be filtered or sorted according to the selected criteria

  Scenario: Product Reviews and Ratings
    Given I is on a product page
    When I views existing product reviews and ratings
    And (if applicable) submits a product review and rating
    Then reviews and ratings should be displayed correctly
    And new reviews should be submitted and displayed (if applicable)

  Scenario: Social Media Sharing Functionality
    Given I is on a page with social media sharing buttons
    When I click a social media sharing button
    Then the relevant social media sharing window or page should open

  Scenario: Creating a User Profile
    Given I logged in
    When I goes to the "Profile" or "Account Settings" page
    And I enter or updates profile information
    And I click the "Save" or similar button
    Then the profile information should be updated successfully
    And a success message should be displayed

  Scenario: Changing User Password

    Given I logged in
    When I goes to the "Profile" or "Account Settings" page
    And I enter the current password, new password, and confirms the new password
    And I click the "Change Password" or similar button
    Then the password should be changed successfully
    And a success message should be displayed

  Scenario: Deleting a User Account

    Given I logged in
    When I goes to the "Profile" or "Account Settings" page
    And I confirm the account deletion
    And I click the "Delete Account" or similar button
    Then I account should be deleted successfully
    And I should be logged out and redirected to the login page
    And a confirmation message should be displayed