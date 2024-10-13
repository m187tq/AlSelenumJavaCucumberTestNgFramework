Feature: Add Products to Cart

  Scenario: Add a single product to the cart
    Given I am on the Automation Test Store homepage
    When I add the product "Seaweed Conditioner" to the cart
    Then the cart should contain "Seaweed Conditioner"

  Scenario: Add multiple products to the cart
    Given I am on the Automation Test Store homepage
    When I add the following products to the cart:
      | Product Name        |
      | Seaweed Conditioner |
      | Eau Parfum Spray    |
    Then the cart should contain the following products:
      | Product Name        |
      | Seaweed Conditioner |
      | Eau Parfum Spray    |
