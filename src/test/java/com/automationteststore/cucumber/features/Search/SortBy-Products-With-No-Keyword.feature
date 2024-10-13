@search
Feature: Sort By the Date Old > New products in search
  As a user
  I want to sort by products
  So that I can find products that are more recent

  Scenario:
    Given I landed on Ecommerce website
    And I search for products with ""
    And I can see the current page breadcrumb as "Search"
    And I can see the followings displayed in the search result page:
      | Search Criteria                      |
      | Search in product descriptions       |
      | Search in product model              |
      | Products meeting the search criteria |
    And I can see "There is no product that matches the search criteria."


