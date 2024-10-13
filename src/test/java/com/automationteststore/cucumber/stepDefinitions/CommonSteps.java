package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.base.BaseTest;
import com.automationteststore.helperutilities.Get.GetValue;
import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.assertion.AssertionHelper;
import com.automationteststore.helperutilities.javaScript.JavaScriptHelper;
import com.automationteststore.pages.*;
import com.automationteststore.webdriverutilities.WebDrv;
import com.google.common.util.concurrent.Uninterruptibles;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class CommonSteps extends BaseTest {
    Page page;
    TopMenuPage topMenuPage;
    HomePage homePage;
    ProductIdPage productIdPage;
    LoginPage loginPage;
    RegistrationPage register;
    AsideWidgetPage asideWidgetPage;
    AccountSuccessPage accountSuccessPage;
    LogoutPage logoutPage;
    AccountPage accountPage;
    NavigationMenuPage navigationMenuPage;
    ShoppingCartPage shoppingCartPage;

    @Given("I landed on Ecommerce website")
    public void i_landed_on_ecommerce_website() {
        topMenuPage = launchApplication();
    }

    @Given("I am on ecommerce website")
    public void iAmOnEcommerceWebsite() throws IOException {
        topMenuPage = launchApplication();
        List<WebElement> products = WebDrv.getInstance().getWebDriver().findElements(By.cssSelector("div.col-md-3.col-sm-6.col-xs-12"));
        new JavaScriptHelper(WebDrv.getInstance().getWebDriver()).scrollToElement(products.get(0));
    }



    @Given("^I landed on home page \"([^\"]*)\"$")
    public void i_landed_on_home_page(String url) {
        if (WebDrv.getInstance().getWebDriver() == null) {
            page = PageFactory.initElements(WebDrv.getInstance().openBrowser("", null), Page.class);
            page.navigateTo(url);

        } else {
            WebDrv.getInstance().getWebDriver().manage().deleteAllCookies();
            page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
            page.navigateTo(url);
        }

    }

    @Given("I navigate to Account Registration page")
    public void i_navigate_to_Account_registration_page() throws Throwable {
        if (WebDrv.getInstance().getWebDriver() == null) {
            page = PageFactory.initElements(WebDrv.getInstance().openBrowser("", null), Page.class);
            page.navigateTo(GlobalVarsHelper.getAccountCreatePageUrl());

        } else {
            WebDrv.getInstance().getWebDriver().manage().deleteAllCookies();
            page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
            page.navigateTo(GlobalVarsHelper.getAccountCreatePageUrl());
        }

    }


    // ********* Heading and Sub heading *****************//
    @Then("^the page heading_1 displayed should be \"([^\"]*)\"$")
    public void verifyPageHeading(String heading) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        page.assertOnHeadingText(heading);

    }


    @Then("^the page sub-heading displayed should be \"([^\"]*)\"$")
    public void the_page_heading_displayed_should_be(String subHeading) throws Throwable {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        page.assertOnSubHeadingTextMsg(subHeading);

    }

    @When("I can see page title {string} and page header {string}")
    public void i_can_see_page_title_and_page_header(String pageTitle, String pageHeader) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        AssertionHelper.updateTestStatus(page.getThisPageTitle().contains(pageTitle));
        AssertionHelper.updateTestStatus(page.getThisPageHeaderText().contains(pageHeader));

    }

    @When("I tap on {string} to navigate to login page")
    public void i_tap_on_to_navigate_to_login_page(String loginOrRegisterLink) throws InterruptedException {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        Assert.assertEquals(topMenuPage.getLoginOrRegisterLink(), loginOrRegisterLink);
        loginPage = topMenuPage.clickOnLoginOrRegisterLink();
    }

    //    @When("I navigate to login page")
    @When("I tap on Login or register button")
    public void i_tap_on_login_or_register_button() throws InterruptedException {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        loginPage = topMenuPage.clickOnLoginOrRegisterLink();
    }

    @Given("I verify if links are present:")
    public void i_verify_if_links_are_present(DataTable dataTable) {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        AssertionHelper.updateTestStatus(topMenuPage.assertTopMenuItemsListArePresent(dataTable.cell(0, 0)));
        AssertionHelper.updateTestStatus(topMenuPage.assertTopMenuItemsListArePresent(dataTable.cell(1, 0)));
        AssertionHelper.updateTestStatus(topMenuPage.assertTopMenuItemsListArePresent(dataTable.cell(2, 0)));
        AssertionHelper.updateTestStatus(topMenuPage.assertTopMenuItemsListArePresent(dataTable.cell(3, 0)));
    }

    @Given("I can see top menu page properties as follows:")
    public void i_can_see_top_menu_page_properties_as_follows(DataTable dataTable) {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        AssertionHelper.updateTestStatus(topMenuPage.getLoginOrRegisterLink().contains(dataTable.cell(0, 0)));
        AssertionHelper.updateTestStatus(topMenuPage.getTopMenuItemsList(dataTable.cell(1, 0)));
        AssertionHelper.updateTestStatus(topMenuPage.getTopMenuItemsList(dataTable.cell(2, 0)));
        AssertionHelper.updateTestStatus(topMenuPage.getTopMenuItemsList(dataTable.cell(3, 0)));
        AssertionHelper.updateTestStatus(topMenuPage.getTopMenuItemsList(dataTable.cell(4, 0)));

    }

    @Given("I can see page properties as follows:")
    public void i_can_see_page_properties_as_follows(DataTable dataTable) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        boolean status = page.getThisPageUrl().contains(dataTable.cell(1, 0));
        AssertionHelper.updateTestStatus(status);
        boolean result = page.getThisPageTitle().contains(dataTable.cell(1, 1));
        AssertionHelper.updateTestStatus(result);

    }

    @Then("I should see {string} and {string}")
    public void i_should_see_and(String pageTitle, String pageUrl) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        AssertionHelper.updateTestStatus(page.getThisPageTitle().contains(pageTitle));
        AssertionHelper.updateTestStatus(page.getThisPageUrl().contains(pageUrl));
    }

    @Then("I should see page title and url {string}, {string} respectively")
    public void i_should_see_page_title_and_url(String pageTitle, String pageUrl) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        AssertionHelper.updateTestStatus(page.getThisPageTitle().contains(pageTitle));
        AssertionHelper.updateTestStatus(page.getThisPageUrl().contains(pageUrl));
    }

    @And("I logout application")
    public void and_I_logout_application() {
        accountPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AccountPage.class);
        logoutPage = accountPage.clickOnLogoffButton();
        homePage = logoutPage.clickOnLogoutContinueButton();
    }


    @When("I can see page breadcrumb as follows:")
    public void i_can_see_page_breadcrumb_as_follows(DataTable dataTable) throws InterruptedException {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        AssertionHelper.updateTestStatus(page.assertCurrentPageBreadCrumbMenuIsDisplayedCorrectly(dataTable.cell(0, 0)));
        AssertionHelper.updateTestStatus(page.assertCurrentPageBreadCrumbMenuIsDisplayedCorrectly(dataTable.cell(1, 0)));

    }


    @When("I can see the current page breadcrumb as {string}")
    public void i_can_see_current_page_breadcrumb(String pageBreadcrumb) throws InterruptedException {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        Assert.assertTrue(page.assertCurrentPageBreadCrumbMenuIsDisplayedCorrectly(pageBreadcrumb));

    }
    @Then("I logoff application")
    public void i_logoff_application() throws IOException {
        accountSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AccountSuccessPage.class);
        accountPage = accountSuccessPage.clickContinueButtonInSuccessPage();
        accountPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AccountPage.class);
        logoutPage = accountPage.clickOnLogoffButton();
        logoutPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LogoutPage.class);
        homePage = logoutPage.clickOnLogoutContinueButton();

    }

    @Then("logoff application")
    public void logoff_application() {
        accountPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AccountPage.class);
        logoutPage = accountPage.clickOnLogoffButton();
        logoutPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LogoutPage.class);
        homePage = logoutPage.clickOnLogoutContinueButton();

    }

    @Then("I logoff")
    public void i_logoff() throws IOException {
        accountSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AccountSuccessPage.class);
        asideWidgetPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AsideWidgetPage.class);
        logoutPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LogoutPage.class);
        logoutPage = asideWidgetPage.tapLogoffLink();
        homePage = logoutPage.clickOnLogoutContinueButton();
    }
    @When("page header is displayed as {string}")
    public void page_header_display_as(String headerText) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        Assert.assertEquals(page.getThisPageHeaderText(), headerText);

    }

    @And("I should see in the page as follows:")
    public void i_should_see_in_the_page_as_follow(DataTable dataTable) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        AssertionHelper.updateTestStatus(page.getThisPageUrl().contains(dataTable.cell(1, 0)));
        AssertionHelper.updateTestStatus(page.getThisPageTitle().contains(dataTable.cell(1, 1)));

    }

    @Then("should be presented with the following validation message as {string}")
    public void should_be_presented_with_the_following_validation_message_as(String loginValidationMessage) {
        accountPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AccountPage.class);
        Assert.assertTrue(accountPage.getWelcomeMessageText().contains(loginValidationMessage));

    }

    @Then("I add the following items to cart in home page")
    public void i_add_the_following_items_to_cart_in_home_page(DataTable dataTable) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        page.addProductToCart(dataTable.cell(0, 0));
        page.addProductToCart(dataTable.cell(1, 0));
        page.addProductToCart(dataTable.cell(2, 0));
        page.addProductToCart(dataTable.cell(3, 0));

    }


    @And("I add the following items to cart in search result page:")
    public void i_add_products_to_cart_in_search_result_page(DataTable dataTable) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        page.addProductsToCart(dataTable.cell(0, 0));
        page.addProductsToCart(dataTable.cell(1, 0));
        page.addProductsToCart(dataTable.cell(2, 0));
        page.addProductsToCart(dataTable.cell(3, 0));

    }

    @Then("I add product {string} to basket cart")
    public void i_add_the_to_basket_cart(String productName) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        page.addProductToBasket(productName);

    }

    @And("I select color {string}")
    public void iSelectColor(String color) {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        //productIdPage.selectColor(color);

    }

    @And("I select size {string}")
    public void iSelectSize(String size) {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        //productIdPage.selectSize(size);
    }

    @And("I tap on the {string} button")
    public void iTapOnTheButton(String addToCartButton) {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        AssertionHelper.updateTestStatus(productIdPage.getAdd2Cart().getText().contains(addToCartButton));
        shoppingCartPage = productIdPage.clickAdd2Cart();
    }

    @And("I enter quantity {string}")
    public void iEnterQuantity(String qty) {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        productIdPage.enterQuantity(qty);
    }


    @And("I should see the followings dash tile options in My Account page:")
    public void iShouldSeeTheFollowingsDashTileOptionsInMyAccountPage(DataTable dataTable) {
        accountPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AccountPage.class);
        AssertionHelper.updateTestStatus(accountPage.getManageAddressBook().contains(dataTable.cell(0, 0)));
        AssertionHelper.updateTestStatus(accountPage.getOrderHistory().contains(dataTable.cell(1, 0)));
        AssertionHelper.updateTestStatus(accountPage.getDownloads().contains(dataTable.cell(2, 0)));
        AssertionHelper.updateTestStatus(accountPage.getTransactionHistory().contains(dataTable.cell(3, 0)));

    }

    @And("I can see {int} side Widget icons are displayed")
    public void iCanSeeSideWidgetIconsAreDisplayed(int sideWidgetIconCount) {
        accountPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AccountPage.class);
        Assert.assertEquals(accountPage.getSideWidgetIconCount(), sideWidgetIconCount);
    }

    @And("I can see the links in aside widget are displayed:")
    public void iCanSeeTheLinksInAsideWidgetAreDisplayed(DataTable dataTable) {
        asideWidgetPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AsideWidgetPage.class);
        AssertionHelper.updateTestStatus(asideWidgetPage.getAsideWidgetLinks().get(0).getText().contains(dataTable.cell(0, 0)));
        AssertionHelper.updateTestStatus(asideWidgetPage.getAsideWidgetLinks().get(1).getText().contains(dataTable.cell(1, 0)));
        AssertionHelper.updateTestStatus(asideWidgetPage.getAsideWidgetLinks().get(2).getText().contains(dataTable.cell(2, 0)));
        AssertionHelper.updateTestStatus(asideWidgetPage.getAsideWidgetLinks().get(3).getText().contains(dataTable.cell(3, 0)));
        AssertionHelper.updateTestStatus(asideWidgetPage.getAsideWidgetLinks().get(4).getText().contains(dataTable.cell(4, 0)));
        AssertionHelper.updateTestStatus(asideWidgetPage.getAsideWidgetLinks().get(5).getText().contains(dataTable.cell(5, 0)));
        AssertionHelper.updateTestStatus(asideWidgetPage.getAsideWidgetLinks().get(6).getText().contains(dataTable.cell(6, 0)));
        AssertionHelper.updateTestStatus(asideWidgetPage.getAsideWidgetLinks().get(7).getText().contains(dataTable.cell(7, 0)));
        AssertionHelper.updateTestStatus(asideWidgetPage.getAsideWidgetLinks().get(8).getText().contains(dataTable.cell(8, 0)));
        AssertionHelper.updateTestStatus(asideWidgetPage.getAsideWidgetLinks().get(9).getText().contains(dataTable.cell(9, 0)));

    }

    @Then("I quit browser")
    public void i_Quit_Browser() {
        WebDrv.getInstance().getWebDriver().quit();

    }

    @Then("I close the browser")
    public void i_Close_The_Browser() {
        if (WebDrv.getInstance().getWebDriver() == null) {
            System.out.println("Web driver is closing browser......");
            return;
        }
        WebDrv.getInstance().getWebDriver().close();

    }

    @Given("I can see in current page title and url as follows:")
    public void i_can_see_in_current_page_title_and_url_as_follows(DataTable dataTable) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        AssertionHelper.updateTestStatus(page.getThisPageTitle().contains(dataTable.cell(0, 0)));
        AssertionHelper.updateTestStatus(page.getThisPageUrl().contains(dataTable.cell(1, 0)));

    }

    @Given("I can see in current page url and title")
    public void i_can_see_in_current_page_url_and_title_as_follows(DataTable dataTable) throws InterruptedException {
        Thread.sleep(5000);
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        Assert.assertEquals(page.getThisPageUrl(), dataTable.cell(0, 0));
        Assert.assertEquals(page.getThisPageTitle(), dataTable.cell(1, 0));

    }

    @Given("I should see {string} link is displayed in the top menu")
    public void i_should_see_link_is_displayed_in_the_top_menu(String text) {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        AssertionHelper.updateTestStatus(new GetValue().getDesiredValue(topMenuPage.getTopMenuList(), WebElement::getText).contains(text));

    }

    @And("I can see current page header as {string}")
    public void iCanSeeThisCurrentPageHeaderAs(String headerText) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        Assert.assertTrue(page.getThisPageHeaderText().equalsIgnoreCase(headerText));
    }

    @Given("I am on the Automation Test Store homepage")
    public void i_am_on_the_automation_test_store_homepage() {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        topMenuPage.navigateToHomePage();
    }


    @And("tap on continues button")
    public void tapOnContinuesButton() {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        page.tapOnContinueButton();

    }

    @And("I hover {string} and click {string} from the dropdown list")
    public void iHoverAndClickFromTheDropdownList(String categoryName, String menuItem) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        page.iHoverOverProductCategoryMenuAndClickAMenuItem(categoryName, menuItem);
    }

    @And("I click on menu item {string}")
    public void iClickOnMenuItem(String menuItem) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        navigationMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), NavigationMenuPage.class);
        page.clickAnyElementMatchingText(navigationMenuPage.getCategories(), s -> s.getText().contains(menuItem));
    }

    @And("I hover over on {string}")
    public void iHoverOverOnCategoryName(String categoryName) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        navigationMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), NavigationMenuPage.class);
        //page.iHoverOverCategoryNameAndMenuItem(navigationMenuPage.getCategories(),s->s.getText().contains(categoryName));
    }

    @Then("I am redirected to the correct category page")
    public void iAmRedirectedToTheCorrectCategoryPage() {
        // Write code here that verifies the correct category page is displayed
    }

    @And("I should see page title and url \\{string}, \\{string} respectively")
    public void iShouldSeePageTitleAndUrlStringStringRespectively(String pageTitle, String url) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        Assert.assertTrue(page.getThisPageTitle().contains(pageTitle),"Title is not correct");
        Assert.assertTrue(page.getThisPageUrl().contains(url),"Url is not correct");

    }

    @Then("I should be redirected to the page titled {string} and url {string}")
    public void iShouldBeRedirectedToThePageTitledAndUrl(String pageTitle, String url) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        Assert.assertEquals(page.getThisPageTitle(), pageTitle);
        Assert.assertEquals(page.getThisPageUrl(), url);

    }

    @When("I applied {int} SECONDS wait mechanism")
    public void i_applied_seconds_wait_mechanism(Integer SECONDS) {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(SECONDS));
    }

    @Given("I navigate back to Home page")
    public void i_navigate_back_to_home_page() {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        page.clickOnLogoImage();
    }

    @And("I should see in current page {string} and {string}")
    public void iShouldSeeInCurrentPageAnd(String pageTitle, String pageUrl) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        Assert.assertTrue(page.getThisPageTitle().contains(pageTitle),"Title is not correct");
        Assert.assertTrue(page.getThisPageUrl().contains(pageUrl),"Url is not correct");

    }
}
