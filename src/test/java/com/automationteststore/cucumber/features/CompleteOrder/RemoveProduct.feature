Feature: Remove Product from Cart

  Scenario: User removes a product from the cart
    Given the cart contains "Seaweed Conditioner"
    When the user removes "Seaweed Conditioner" from the cart
    Then the cart should no longer contain "Seaweed Conditioner"
    And the grand total should be updated accordingly
