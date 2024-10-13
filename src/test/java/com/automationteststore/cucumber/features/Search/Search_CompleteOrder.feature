@Search_CompleteOrder
Feature: Verifying e-commerce Search to Order item functionality

  Scenario Outline: search keyword should be for Products meeting the search criteria
    Given I landed on Ecommerce website
    And I should see the search widget at the top right
    When I search for product category with "Men"
    And I applied 5 SECONDS wait mechanism
    And I can see inputted data retained in the search criteria "Men"
    And I can see the current page breadcrumb as "Search"
    And I can see the followings displayed in the search result page:
      | Search Criteria                                       |
      | Search in product descriptions                        |
      | Search in product model                               |
      | Products meeting the search criteria                  |
      | There is no product that matches the search criteria. |
    And I can see the followings displayed in the Sort By dropdown search result page:
      | Date Old > New   |
      | Name A - Z       |
      | Name Z - A       |
      | Price Low > High |
      | Price High > Low |
      | Rating Highest   |
      | Rating Lowest    |
      | Date New > Old   |
    And I add products to the cart
      | Men+Care Clean Comfort Deodorant           |
      | Euphoria Men Intense Eau De Toilette Spray |
      | ck IN2U Eau De Toilette Spray for Him      |
    Then I verify the product id "<productId>"
    Then I verify the product name is "<productName>"
    And I verify the product price is "<productPrice>"
    When I enter the quantity as "<quantity>"
    And I select the size as "<size>"
    Then I verify the Latest Products section is displayed
    And I verify the total price is "<totalPrice>"
    And I verify print button is displayed
    Then I verify the description section is displayed
    And I verify the reviews section is displayed
    And I verify the tags section is displayed
    And I click on "Add to Cart"
    Examples:
      | productId | productName                           | productPrice | quantity | size                       | totalPrice |
      | 78        | ck IN2U Eau De Toilette Spray for Him | $29.00       | 3        | 100ml $8.00 (672 In Stock) | $29.00    |

