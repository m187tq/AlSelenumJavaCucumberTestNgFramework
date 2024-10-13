Feature: Update Product Quantity in Cart

  Scenario: User updates the quantity of a product in the cart
    Given the cart contains "Seaweed Conditioner" with quantity 1
    When the user updates the quantity of "Seaweed Conditioner" to 3
    Then the cart should show "Seaweed Conditioner" with quantity 3
    And the total price for "Seaweed Conditioner" should equal 3 times the unit price
