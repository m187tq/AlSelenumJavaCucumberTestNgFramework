package com.automationteststore.cucumber.stepDefinitions;


import com.automationteststore.helperutilities.assertion.AssertionHelper;
import com.automationteststore.pages.Page;
import com.automationteststore.pages.ProductCategoryPage;
import com.automationteststore.pages.SearchResultPage;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class SearchResultPageSteps {
    Page page;
    SearchResultPage searchResultPage;


    @Then("^I can see search result for \"([^\"]*)\" filter")
    public void i_can_see_search_result_for_working_pattern_filter(String filterName) {

    }


    @Then("^I can see the jobs in search result")
    public void i_can_see_the_job_in_search_result() {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        //searchResultPage.assertJobTitle();
    }


    @Then("I can see {string} in search result for {string}")
    public void iCanSeeSearchResultFor(String noResult, String jobTitle) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);


    }

    @And("I can see {string} in the search result page")
    public void iCanSeeSearchResultInSearchResultPage(String searchResult) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        //searchResultPage.assertNoSearchResultFoundTextIsDisplayed()


    }

    @Then("I should see the search result for inputted data")
    public void i_can_should_search_results_for_inputted_data() {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        //searchResultPage
    }

    @When("I can see the search result for inputted data")
    public void i_can_see_the_search_result_for_inputted_data() {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        //searchResultPage.assertJobTitle();;
    }

    @Then("I should see result found in search result page")
    public void i_should_see_result_found_in_search_result_page() {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);

    }

    @And("I should see {string} in the search result page")
    public void iShouldSeeInTheSearchResultPage(String noResultFoundTxt) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        //searchResultPage.assertForNoResultFound(noResultFoundTxt);
    }

    @Then("I can see {string} job found for All vacancies at all locations!")
    public void iShouldSeeJobFoundForAllVacanciesAtAllLocations(String jobCount) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        //searchResultPage.assertForJobsCount(jobCount);

    }


    @And("I should see job advert status message:")
    public void iCanSeeJobAdvertStatusMessage(DataTable jobNowClosedMsg) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        //searchResultPage.assertForJobNowClosed(jobNowClosedMsg);

    }

    @And("I can see {string} search result")
    public void iCanSeeSearchResult(String searchResult) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);

    }

    @When("I can see the followings displayed in the search result page:")
    public void i_can_see_the_followings_displayed_in_the_search_result_page(DataTable dataTable) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        AssertionHelper.updateTestStatus(searchResultPage.getSearchCriteriaText().trim().contains(dataTable.cell(0, 0)));
        AssertionHelper.updateTestStatus(searchResultPage.getSearchProductDescriptionsRadioButtonLabel().contains(dataTable.cell(1, 0)));
        AssertionHelper.updateTestStatus(searchResultPage.getSearchProductModelRadioButtonLabel().contains(dataTable.cell(2, 0)));
        AssertionHelper.updateTestStatus(searchResultPage.getProductsMeetingSearchCriteriaText().contains(dataTable.cell(3, 0)));
        //AssertionHelper.updateTestStatus(searchResultPage.getNoSearchResultFoundText().contains(dataTable.cell(5,0)));
        //AssertionHelper.updateTestStatus(searchResultPage.getSearchResultFoundText().contains(dataTable.cell(6,0)));

    }

    @And("I can see page title and header as follows:")
    public void iCanSeePageUrlTitleAndHeaderAsFollows(DataTable dataTable) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        AssertionHelper.updateTestStatus(page.getThisPageTitle().contains(dataTable.cell(1, 1)));
        AssertionHelper.updateTestStatus(page.getThisPageHeaderText().contains(dataTable.cell(1, 2)));

    }

    @And("I can see {string} is selected by default")
    public void i_can_filter_the_products_in_search_result(String sortBy) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        AssertionHelper.updateTestStatus(searchResultPage.assertSortByDropdownItemIsSelected());
        AssertionHelper.updateTestStatus(searchResultPage.assertSortByDropdownDefaultItemIsSelected(sortBy));
        Assert.assertEquals(searchResultPage.getSortByDropdownDateOldNewText(), sortBy);

    }

    @Then("I should see the products in search result is greater {int}")
    public void i_can_see_the_products_in_search_result(int leastExpectedProductNumber) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        boolean status = searchResultPage.getSearchResultProductFoundCount() > leastExpectedProductNumber;
        AssertionHelper.updateTestStatus(status);

    }

    @And("I can see the search result for {string}")
    public void iCanSeeTheSearchResultFor(String itemName) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        Assert.assertTrue(searchResultPage.getSearchResultProductFound(itemName));
        Assert.assertTrue(searchResultPage.getSearchResultProductFoundCount() > 0);
    }

    @And("I select an item {string} from the all categories")
    public void i_select_an_item_from_the_all_categories() throws InterruptedException {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        searchResultPage.selectItemsFromAllCategories();

    }

    @And("I tap on the following search criteria buttons:")
    public void i_tap_on_the_following_search_criteria_buttons(DataTable dataTable) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        AssertionHelper.updateTestStatus(searchResultPage.getSearchProductDescriptionsRadioButtonLabel().contains(dataTable.cell(0, 0)));
        AssertionHelper.updateTestStatus(searchResultPage.getSearchProductModelRadioButtonLabel().contains(dataTable.cell(1, 0)));
        searchResultPage.clickSearchProductDescriptionsRadioButton();
        searchResultPage.clickSearchProductModelRadioButton();

    }

    @And("I can see the followings displayed in the Sort By dropdown search result page:")
    public void iCanSeeTheFollowingsDisplayedInTheSortByDropdownSearchResultPage(DataTable dataTable) throws InterruptedException {
        SearchResultPage searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        searchResultPage.clickSortByDropdownButton();
        AssertionHelper.updateTestStatus(searchResultPage.getSortByDropdownItems(dataTable.cell(0,0)));
        AssertionHelper.updateTestStatus(searchResultPage.getSortByDropdownItems(dataTable.cell(1,0)));
        AssertionHelper.updateTestStatus(searchResultPage.getSortByDropdownItems(dataTable.cell(2,0)));
        AssertionHelper.updateTestStatus(searchResultPage.getSortByDropdownItems(dataTable.cell(3,0)));
        AssertionHelper.updateTestStatus(searchResultPage.getSortByDropdownItems(dataTable.cell(4,0)));
        AssertionHelper.updateTestStatus(searchResultPage.getSortByDropdownItems(dataTable.cell(5,0)));
        AssertionHelper.updateTestStatus(searchResultPage.getSortByDropdownItems(dataTable.cell(6,0)));
        AssertionHelper.updateTestStatus(searchResultPage.getSortByDropdownItems(dataTable.cell(7,0)));

    }

    @And("I can see {string}")
    public void iCanSee(String searchResultFound) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        AssertionHelper.updateTestStatus(searchResultPage.getNoSearchResultFoundText().contains(searchResultFound));

    }


    @When("I select {string} to filter the search result")
    public void iSelect(String sortBy) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        searchResultPage.selectSortByDropdownItems(sortBy);

    }


    @And("tap on the {string} button")
    public void tapOnTheSearchButton(String Search) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        WebDrv.getInstance().getWebDriver().findElement(By.cssSelector("[title='" + Search + "']"));
        searchResultPage.clickSearchButton();
    }

    @And("tap on the search button")
    public void tapSearchButton(String Search) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        searchResultPage.clickSearchButton();
    }

    @And("I can see inputted data retained in the search criteria {string}")
    public void iCanSeeInputtedDataRetainedInTheSearchCriteriaBoxes(String categoryName) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        AssertionHelper.updateTestStatus(searchResultPage.getSearchCriteriaAttributeValue().equalsIgnoreCase(categoryName));

    }

    @And("I can see {string} is selected by default in search criteria")
    public void i_can_see_is_selected_by_default_in_search_criteria(String categoryText) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        searchResultPage.selectSearchCriteriaAllCategoriesByText(categoryText);


    }

    @And("I can see the research result per-page {string} {string}")
    public void iCanSeeTheTotalNumberOfProductsInPagination(String perPage, String rangeCount) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        AssertionHelper.updateTestStatus(searchResultPage.getSearchResultProductFoundCountInPagination().trim().contains(perPage));
        AssertionHelper.updateTestStatus(searchResultPage.getSearchResultProductFoundCountInPagination().trim().contains(rangeCount));


    }

    @And("I add products {string}, {string} and {string} to cart")
    public void iAddItemsToCart(String productName0, String productName1, String productName2) {
        searchResultPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), SearchResultPage.class);
        searchResultPage.addProductToCart(productName0);
        searchResultPage.addProductToCart(productName1);
        searchResultPage.addProductToCart(productName2);
    }


    @And("I add products to the cart")
    public void iAddProductsToTheCart(DataTable dataTable) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        page.addProductToCart(dataTable.cell(0, 0));
        page.addProductToCart(dataTable.cell(1, 0));
        page.addProductToCart(dataTable.cell(2, 0));
    }
}
