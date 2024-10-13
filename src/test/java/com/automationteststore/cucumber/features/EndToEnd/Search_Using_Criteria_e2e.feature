@SearchUsingFilterE2e
Feature: Verifying e-commerce Search to Order item functionality

  Scenario Outline: search keyword should be for Products meeting the search criteria
    Given I landed on Ecommerce website
    And I can see page properties as follows:
      | page url                         | page title                                  |
      | https://automationteststore.com/ | A place to practice your automation skills! |
    And I can see top menu page properties as follows:
      | Login or register |
      | SPECIALS          |
      | ACCOUNT           |
      | CART              |
      | CHECKOUT          |
    And I verify if links are present:
      | SPECIALS |
      | ACCOUNT  |
      | CART     |
      | CHECKOUT |
    And I can see search widget is displayed
    And I search for products with "Men"
    And I can see page url, title and header as follows:
      | Page Url      | Page Title | Page Header |
      | category_id=0 | Search     | SEARCH      |
    And I can see the current page breadcrumb as "Search"
    And I can see the followings displayed in the search result page:
      | Search Criteria                      |
      | Search in product descriptions       |
      | Search in product model              |
      | Products meeting the search criteria |
    And I can see inputted data retained in the search criteria "<Category Name>"
    And I tap on the following search criteria buttons:
      | Search in product descriptions |
      | Search in product model        |
    And tap on the "Search" button
    And I can see "<Default Sort By>" is selected by default
    And I can see "All Categories" is selected by default in search criteria
    When I select "<Sort By>" to filter the search result
    Then I should see the products in search result is greater <Least Expected Number Of Search Result>
    And I can see the research result per-page "Per Page" "1 - 20 of 35"
    And I add the following items to cart in search result page:
      | Benefit Bella Bamba                        |
      | ck One Gift Set                            |
      | Eye master                                 |
      | Euphoria Men Intense Eau De Toilette Spray |
    And I add product "Designer Men Casual Formal Double Cuffs Grandad Band Collar Shirt Elegant Tie" to basket cart
    And I select color "White  (999006 In Stock)"
    And I select size "Medium  "
    And I enter quantity "1"
    And I tap on the "Add to Cart" button
    And I hoverOver items cart and click on view Cart icon from dropdown
    And I can see current page submenu breadcrumbs "Basket" are correctly displayed
    And I can see product name, model, unit price, quantity, and total price:
      | name                | model  | unit price | quantity | total price |
      | Benefit Bella Bamba | 523755 | $28.00     | 2        | $56.00      |
    And I removed "Benefit Bella Bamba" item from cart
    And I tap Checkout button
    And I can see the current page breadcrumb as "Login"
    And I can see RETURNING CUSTOMER and I AM A NEW CUSTOMER. in login page
    And I login in login page as a returning customer:
      | username     | password     |
      | webdriverio2 | webdriverio2 |
    And I should be on CHECKOUT CONFIRMATION in the Checkout Confirmation page
    And I can see the current page breadcrumb as "Confirm"
    And I can see the following buttons are displayed:
      | Edit Ship     |
      | Edit Payment  |
      | Edit Coupon   |
      | Edit Cart     |
      | Back          |
      | Confirm Order |
    And  I click "Edit Cart" button in checkout_confirmation_page
    And I removed "ck One Gift Set" item from cart
    And I update product in cart by quantity:
      | Product                  | Quantity |
      | Dove Men +Care Body Wash | 4        |
    And click "Update" button
    And I can see product name, model, unit price, quantity, and total price:
      | name       | model    | unit price | quantity | total price |
      | Eye master | GRMBC003 | $82.00     | 2        | $164.00     |
    And I tap Checkout button
    And I should see Order Summary in checkout confirmation page
    And I tap on "Confirm Order" in checkout confirmation page
    And I should see "YOUR ORDER HAS BEEN PROCESSED!" in Checkout Success page
    And I should see "Your order #" number has been created
    And I should see "Thank you for shopping with us!" in the page
    And I should see "You can view your order details" by going to the invoice page
    And I should see You can view your order details by going to the "invoice page"
    And I click on "Continue" button in Checkout Success page
    And I am back on ecommerce website
    And I quit browser

    Examples:
      | Category Name | Default Sort By | Sort By    | Least Expected Number Of Search Result |
      | Men           | Date Old > New  | Name A - Z | 0                                      |
