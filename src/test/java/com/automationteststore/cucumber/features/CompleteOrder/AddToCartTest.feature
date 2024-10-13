Feature: Add products to cart and manage cart in Automation Test Store

  Background:
    Given I landed on Ecommerce website

  Scenario Outline: Add a product to the cart from a specific category and subcategory and verify
    When I navigate to the "<Category>" category
    And I select the "<SubCategory>" subcategory
    And I add the product "<ProductName>" to the cart
    Then I should see "<ProductName>" in the cart
    And I verify navigation to the product page

    Examples:
      | Category               | SubCategory  | ProductName   |
      | Apparel & accessories  | T-shirts     | Product1      |
      | Apparel & accessories  | Hoodies      | Product2      |
      | Apparel & accessories  | Socks        | Product3      |
      | Apparel & accessories  | Hats         | Product4      |
      | Apparel & accessories  | Accessories  | Product5      |
      | Makeup                 | Lipstick     | Product6      |
      | Makeup                 | Eyeshadow    | Product7      |
      | Makeup                 | Foundation   | Product8      |
      | Makeup                 | Blush        | Product9      |
      | Makeup                 | Mascara      | Product10     |
      | Skincare               | Moisturizer  | Product11     |
      | Skincare               | Cleanser     | Product12     |
      | Skincare               | Serum        | Product13     |
      | Skincare               | Toner        | Product14     |
      | Skincare               | Sunscreen    | Product15     |
      | Fragrance              | Perfume      | Product16     |
      | Fragrance              | Cologne      | Product17     |
      | Fragrance              | Body Mist    | Product18     |
      | Fragrance              | Deodorant    | Product19     |
      | Men                    | Shoes        | Product20     |
      | Men                    | Shirts       | Product21     |
      | Men                    | Pants        | Product22     |
      | Men                    | Accessories  | Product23     |

  Scenario Outline: Verify all elements on the product page
    Given I have added "<ProductName>" to the cart
    When I navigate to the product page for "<ProductName>"
    Examples:
      | ProductName |
      | Product1    |
      | Product2    |
      | Product3    |
      | Product4    |
      | Product5    |
      | Product6    |
      | Product7    |
      | Product8    |
      | Product9    |
      | Product10   |
      | Product11   |
      | Product12   |
      | Product13   |
      | Product14   |
      | Product15   |
      | Product16   |
      | Product17   |
      | Product18   |
      | Product19   |
      | Product20   |
      | Product21   |
      | Product22   |
      | Product23   |

  Scenario: Modify the cart by removing and updating product quantities
    Given I have the following products in the cart:
      | ProductName | Quantity |
      | Product1    | 2        |
      | Product2    | 1        |
      | Product3    | 3        |
    When I remove the product "Product1" from the cart
    And I increase the quantity of "Product2" to 3
    And I decrease the quantity of "Product3" to 1
    Then the cart should reflect the correct total price

  Scenario: Proceed to checkout
    Given I have products in my cart
    When I click on the checkout button
    Then I should be navigated to the checkout page
