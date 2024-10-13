package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.base.BaseTest;
import com.automationteststore.pages.Page;
import com.automationteststore.pages.SearchResultPage;
import com.automationteststore.pages.SearchWidgetPage;
import com.automationteststore.pages.TopMenuPage;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SearchSteps extends BaseTest {
    Page page;
    SearchWidgetPage searchWidgetPage;
    SearchResultPage searchResultPage;

    @And("I search for products with {string}")
    public void i_search_for_products_with(String productName) {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        searchWidgetPage.searchForProduct(productName);

    }

    @And("I search for product with {string}")
    public void i_search_for_product_as(String searchKeyword) {
        searchWidgetPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchWidgetPage.class);
        searchWidgetPage.searchForProduct(searchKeyword);
    }


    @And("I navigate to search result page")
    public void i_navigate_to_search_result_page() {
        searchWidgetPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchWidgetPage.class);
        searchResultPage = searchWidgetPage.navigateToSearchResultPage();
    }

    @And("I can see search widget is displayed")
    public void iCanSeeSearchWidgetIsDisplayed() {
        searchWidgetPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchWidgetPage.class);
        Assert.assertTrue(searchWidgetPage.assertSearchBoxIsDisplayed());

    }

    @And("I should see the search widget at the top right")
    public void i_should_see_the_search_widget_at_the_top_right() {
        searchWidgetPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchWidgetPage.class);
        Assert.assertTrue(searchWidgetPage.assertSearchBoxIsDisplayed(), "Search widget is not present on the page");
    }

    @When("I search for product with a keyword {string} and category {string}")
    public void iSearchForProductWithAKeywordAndCategory(String searchKeyword, String searchCategory) {
        searchWidgetPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchWidgetPage.class);
        searchWidgetPage.searchForProductWithKeywordAndCategory(searchKeyword, searchCategory);

    }

    @When("I search for product category with {string}")
    public void iSearchForProductCategoryWith(String categoryName) {
        searchWidgetPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchWidgetPage.class);
        searchWidgetPage.searchForProduct(categoryName);

    }


}