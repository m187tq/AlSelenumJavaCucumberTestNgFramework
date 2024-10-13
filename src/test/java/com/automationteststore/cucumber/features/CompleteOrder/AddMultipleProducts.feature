Feature: Add Multiple Products to Cart

  Scenario: User adds multiple different products to the cart
    Given the user is on the Automation Test Store homepage
    When the user selects the product "Seaweed Conditioner" from "Skincare" category
    And adds the product to the cart
    And selects the product "Curls to Straight Shampoo" from "Haircare" category
    And adds the product to the cart
    Then the cart should contain the following products:
      | Product Name              | Quantity |
      | Seaweed Conditioner       | 1        |
      | Curls to Straight Shampoo | 1        |
    And the grand total should be the sum of all product totals
