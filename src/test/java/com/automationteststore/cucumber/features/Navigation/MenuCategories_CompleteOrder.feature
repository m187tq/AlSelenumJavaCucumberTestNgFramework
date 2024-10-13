@Menu_CompleteOrder_E2E_3
Feature: Menu Functionality

  Scenario: common steps
    Given I landed on Ecommerce website
    And I can see in current page title and url as follows:
      | A place to practice your automation skills! |
      | https://automationteststore.com             |
    And I hover "SKINCARE" and click "Face" from the dropdown list
    And I add the following items to cart in home page
      | TOTAL MOISTURE FACIAL CREAM       |
      | Body Cream by Bulgari             |
      | Creme Precieuse Nuit 50ml         |
      | Jasmin Noir Body Lotion 6.8 fl oz |
    And I hoverOver items cart and click on checkout icon from dropdown
    And page header is displayed as "ACCOUNT LOGIN"
    And I login in login page as a returning customer
      | username     | password     |
      | webdriverio2 | webdriverio2 |
    And I can see the current page breadcrumb as "Confirm"
    And I click Edit Cart button
    And I removed "Benefit Bella Bamba" item from cart
    And I update product in cart by quantity:
      | Product                 | Quantity |
      | SKINSHEEN BRONZER STICK | 8        |
    And I update product in cart by quantity:
      | Product                    | Quantity |
      | ABSOLUE EYE PRECIOUS CELLS | 4        |
    And click "Update" button
    And I can see product name
      | Total Moisture Facial Cream |
    And I tap Checkout button
    And I tap on "Confirm Order" in checkout confirmation page
    And I can see the current page breadcrumb as "Success"
    And I can see "YOUR ORDER HAS BEEN PROCESSED!" is Displayed
    And I click on Continue button in Checkout Success page
    And I can see in current page title and url as follows:
      | A place to practice your automation skills! |
      | https://automationteststore.com             |
