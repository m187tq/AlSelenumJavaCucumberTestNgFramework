@CategoryMenuBar
Feature: Navigation and Shopping Cart

  Background:
    Given I landed on Ecommerce website

  Scenario: Hover over Home Category menu item and navigate to Login page
    And I hover over Home and click on sub menu category dropdown "Account"
    And I navigate back to Home page

  Scenario: Hover over Apparel & accessories Category menu item and navigate to subcategory page
    And I hover over "Apparel & accessories" and click on "Shoes" from the sub-menu dropdown
    And I navigate back to Home page

  Scenario: Hover over Makeup Category menu item and navigate to subcategory page
    And I hover over "Makeup" and click on "Lip" from the sub-menu dropdown
    And I navigate back to Home page

  Scenario: Hover over Skincare menu item and navigate to subcategory page
    And I hover over "Skincare" and click on "Face" from the sub-menu dropdown
    And I navigate back to Home page

  Scenario: Hover over Fragrance Category menu item and navigate to subcategory page
    And I hover over "Fragrance" and click on "Men" from the sub-menu dropdown
    And I navigate back to Home page

  Scenario: Hover over Category menu item and navigate to subcategory page
    And I hover over "Men" and click on "Fragrance Sets" from the sub-menu dropdown
    And I navigate back to Home page

  Scenario: Hover over Haircare Category menu item and navigate to subcategory page
    And I hover over "Haircare" and click on "Shampoo" from the sub-menu dropdown
    And I navigate back to Home page

  Scenario: Hover over Books Category menu item and navigate to subcategory page
    And I hover over "Books" and click on "Paperback" from the sub-menu dropdown
    And I navigate back to Home page