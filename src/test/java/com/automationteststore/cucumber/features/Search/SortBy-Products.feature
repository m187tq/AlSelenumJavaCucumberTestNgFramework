@search001
Feature: Sort By the Date Old > New products in search
  As a user
  I want to sort by products
  So that I can find products that are more recent

  Background:
    Given I landed on Ecommerce website
    And I search for products with "Makeup"
    And I can see the current page breadcrumb as "Search"
    And I can see the followings displayed in the search result page:
      | Search Criteria                      |
      | Search in product descriptions       |
      | Search in product model              |
      | Products meeting the search criteria |


  Scenario Outline: 1-Sort By the Date Old > New products from search result
    And I can see "<Default Sort By>" is selected by default
    Then I should see the products in search result is greater 0
    And I quit browser
    Examples:
      | Default Sort By |
      | Date Old > New  |

  @2-SortBy-Name-A-Z
  Scenario Outline: 2-SortBy-Name-A-Z products from search result
    And I can see inputted data retained in the search criteria "<Category Name>"
    And I tap on the following search criteria buttons:
      | Search in product descriptions |
      | Search in product model        |
    And tap on the search button
    And I can see "<Default Sort By>" is selected by default
    And I can see "  Makeup" is selected by default in search criteria
    When I select "<Sort By>" to filter the search result
    Then I should see the products in search result is greater <Least Expected Number Of Search Result>
    And I can see the research result per-page "Per Page" "1 - 5 of 5"
    And I quit browser
    Examples:
      | Category Name | Default Sort By | Sort By    | Least Expected Number Of Search Result |
      | Makeup        | Date Old > New  | Name A - Z | 0                                      |

  Scenario Outline: 3-SortBy-Name-Z-A products from search result
    And I can see "<Default Sort By>" is selected by default
    When I select "<Sort By>" to filter the search result
    Then I should see the products in search result is greater 0
    And I quit browser
    Examples:
      | Default Sort By | Sort By    |
      | Date Old > New  | Name Z - A |

  Scenario Outline: 4-Price Low > High products from search result
    And I can see "<Default Sort By>" is selected by default
    When I select "<Sort By>" to filter the search result
    Then I should see the products in search result is greater 0
    And I quit browser
    Examples:
      | Default Sort By | Sort By          |
      | Date Old > New  | Price Low > High |


  Scenario Outline: 5-Price High > Low products from search result
    And I can see "<Default Sort By>" is selected by default
    When I select "<Sort By>" to filter the search result
    Then I should see the products in search result is greater 0
    And I quit browser
    Examples:
      | Default Sort By | Sort By          |
      | Date Old > New  | Price High > Low |


  Scenario Outline: 6-Rating Highest products from search result
    And I can see "<Default Sort By>" is selected by default
    When I select "<Sort By>" to filter the search result
    Then I should see the products in search result is greater 0
    And I quit browser
    Examples:
      | Default Sort By | Sort By        |
      | Date Old > New  | Rating Highest |

  Scenario Outline: 7-Rating Lowest products from search result
    And I can see "<Default Sort By>" is selected by default
    When I select "<Sort By>" to filter the search result
    Then I should see the products in search result is greater 0
    And I quit browser
    Examples:
      | Default Sort By | Sort By       |
      | Date Old > New  | Rating Lowest |

  Scenario Outline: 8-Date New > Old products from search result
    And I can see "<Default Sort By>" is selected by default
    When I select "<Sort By>" to filter the search result
    Then I should see the products in search result is greater 0
    And I quit browser
    Examples:
      | Default Sort By | Sort By        |
      | Date Old > New  | Date New > Old |