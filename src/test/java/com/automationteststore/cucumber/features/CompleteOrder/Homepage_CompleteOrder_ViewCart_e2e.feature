@Homepage_CompleteOrder_E2E_2
Feature: Online ordering

  Scenario: should be able to place an order
    Given I landed on Ecommerce website
    And I can see in current page title and url as follows:
      | A place to practice your automation skills! |
      | https://automationteststore.com             |
    And I add the following items to cart in home page
      | TOTAL MOISTURE FACIAL CREAM |
      | SKINSHEEN BRONZER STICK     |
      | BENEFIT BELLA BAMBA         |
      | ABSOLUE EYE PRECIOUS CELLS  |
    And I hoverOver items cart and click on view Cart icon from dropdown
    And I can see product and per unit price:
      | SKINSHEEN BRONZER STICK |
      | $29.50                  |
    And I can see product and per unit price:
      | SKINSHEEN BRONZER STICK |
      | $29.50                  |
    And I should see the product name "Skinsheen Bronzer Stick" in the cart
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
    And I can see the current page breadcrumb as "Login"
    And I can see "I am a new customer." and "Returning Customer"
    And I login in login page as a returning customer
      | username     | password     |
      | webdriverio2 | webdriverio2 |
    And I can see the following buttons are displayed:
      | Edit Ship     |
      | Edit Payment  |
      | Edit Coupon   |
      | Edit Cart     |
      | Back          |
      | Confirm Order |
    And I click Edit Cart button
    And I removed "Skinsheen Bronzer Stick" item from cart
    And click "Update" button
    And I tap on checkout button
    And I tap on "Confirm Order" in checkout confirmation page
    And I can see the current page breadcrumb as "Success"
    And I can see "YOUR ORDER HAS BEEN PROCESSED!" is Displayed
    And I click on Continue button in Checkout Success page
    And I can see in current page title and url as follows:
      | A place to practice your automation skills! |
      | https://automationteststore.com             |
    And I am back on ecommerce website