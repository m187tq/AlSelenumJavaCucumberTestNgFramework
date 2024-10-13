Feature: Add Single Product to Cart

  Scenario: User adds a single product to the cart
    Given the user is on the Automation Test Store homepage
    When the user navigates to the "Skincare" category
    And selects the product "Seaweed Conditioner"
    And adds the product to the cart
    Then the cart should contain "Seaweed Conditioner" with quantity 1
    And the total price should equal the unit price
