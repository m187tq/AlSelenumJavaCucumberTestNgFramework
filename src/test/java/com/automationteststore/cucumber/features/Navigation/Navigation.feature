@NavigationMenu
Feature: Navigation Menu

  As a user
  I want to navigate through the website
  So that I can access different sections and return to the homepage after each navigation

  Scenario Outline: Click on each menu item and return to homepage
    Given I landed on Ecommerce website
    And I can see page properties as follows:
      | page url                         | page title                                  |
      | https://automationteststore.com/ | A place to practice your automation skills! |
    And I can see top menu page properties as follows:
      | Login or register |
      | SPECIALS          |
      | ACCOUNT           |
      | CART              |
      | CHECKOUT          |
    #When I hover over menu item "<MenuItem>" and click on the menu item dropdown "<MenuItemDropdownItem>"
    When I hover "<MenuItem>" and click "<MenuItemDropdownItem>" from the dropdown list
    #Then I should be on the "<MenuItemPath|" page
    #And I return to the homepage

    Examples:
      | MenuItem              | MenuItemDropdownItem |
      | Apparel & accessories | Shoes                |
#      | Apparel & accessories | T-shirts             |
#      | Makeup                | Cheeks               |
#      | Makeup                | Eyes                 |
#      | Makeup                | Face                 |
#      | Makeup                | Lips                 |
#      | Makeup                | Nails                |
#      | Makeup                | Value Sets           |
#      | Skincare              | Eyes                 |
#      | Skincare              | Face                 |
#      | Skincare              | Gift Ideas & Sets    |
#      | Skincare              | Hands & Nails        |
#      | Skincare              | Sun                  |
#      | Fragrance             | Men                  |
#      | Fragrance             | Women                |
#      | Men                   | Body & Shower        |
#      | Men                   | Fragrance Sets       |
#      | Men                   | Pre-Shave & Shaving  |
#      | Men                   | Skincare             |
#      | Hair Care             | Conditioner          |
#      | Hair Care             | Shampoo              |
#      | Books                 | Audio CD             |
#      | Books                 | Paperback            |

