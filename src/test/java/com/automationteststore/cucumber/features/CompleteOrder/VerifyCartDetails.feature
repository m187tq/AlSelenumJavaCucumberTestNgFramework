Feature: Verify Cart Details

  Scenario: User verifies the product list and prices in the cart
    Given the user has added the following products to the cart:
      | Product Name              | Category | Quantity |
      | Seaweed Conditioner       | Skincare | 1        |
      | Curls to Straight Shampoo | Haircare | 1        |
    When the user navigates to the cart
    Then the cart should display the correct product names
    And each product should have the correct unit price and total price
    And the grand total should be the sum of all product totals
