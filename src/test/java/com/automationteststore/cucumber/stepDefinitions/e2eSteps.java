package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.base.BaseTest;
import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.assertion.AssertionHelper;
import com.automationteststore.helperutilities.javaScript.JavaScriptHelper;
import com.automationteststore.pages.*;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;


public class e2eSteps extends BaseTest {

    TopMenuPage topMenuPage;
    CheckoutCartPage checkoutCartPage;
    Page page;
    HomePage homePage;
    ShoppingCartPage shoppingCartPage;
    LoginPage loginPage;
    CheckoutSuccessPage checkoutSuccessPage;
    CheckoutConfirmationPage checkoutConfirmationPage;
    ProductCategoryPage productCategoryPage;


    @Given("I am back on ecommerce website")
    public void iAmBackOnEcommerceWebsite() {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        page.isActivePage();
        AssertionHelper.updateTestStatus(page.getThisPageUrl().equalsIgnoreCase(GlobalVarsHelper.getHomePageUrl()));
    }

    @When("I can see page title {string} and page url {string} in the Home basePage")
    public void i_can_see_page_title_and_page_url(String pageTitle, String pageUrl) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        AssertionHelper.updateTestStatus(page.getThisPageTitle().contains(pageTitle));
        AssertionHelper.updateTestStatus(page.getThisPageUrl().contains(pageUrl));
    }

    @When("I search with a keyword {string}")
    public void iMoveToTheSearchAndInputAKeyword(String item) {

    }

    @And("I add product {string}, {string} and {string} to cart")
    public void iAddAnItemToCart(String productName0, String productName1, String productName2) {
        productCategoryPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductCategoryPage.class);
        productCategoryPage.addProductToCart(productName0);
        productCategoryPage.addProductToCart(productName1);
        productCategoryPage.addProductToCart(productName2);
    }

    @And("I add products {string} and {string} to cart")
    public void iAddProductsToCart(String productName0, String productName1) {
        productCategoryPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductCategoryPage.class);
        productCategoryPage.addProductToCart(productName0);
        productCategoryPage.addProductToCart(productName1);
    }

    @And("I add products {string} and {string} and {string} to basket")
    public void iAddSomeProductsToBasket(String productName0, String productName1, String productName2) {
        productCategoryPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductCategoryPage.class);
        productCategoryPage.addProductToCart(productName0);
        productCategoryPage.addProductToCart(productName1);
        productCategoryPage.addProductToCart(productName2);
    }

    @And("I add products to cart {string}, {string} and {string}")
    public void i_add_products_to_cart_and(String productName0, String productName1, String productName2) {
        homePage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), HomePage.class);
        homePage.addProductsToCart(productName0);
        homePage.addProductsToCart(productName1);
        homePage.addProductsToCart(productName2);

    }

    @And("I scroll to the products view")
    public void iScrollToProductView() {
        homePage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), HomePage.class);
        List<WebElement> products = homePage.getProductList();
        new JavaScriptHelper(WebDrv.getInstance().getWebDriver()).scrollToElement(products.get(0));

    }

    @Given("can see No Of Items in Cart and Total Amount are displayed as follows:")
    public void can_see_no_of_items_in_cart_and_total_amount_are_displayed_as_follows(DataTable dataTable) {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        AssertionHelper.updateTestStatus(dataTable.cell(1, 0).contains(topMenuPage.getCartTotalAmountText()));
        AssertionHelper.updateTestStatus(dataTable.cell(2, 0).contains(topMenuPage.getCartTotalQuantityText()));

    }

    @And("I hoverOver items cart and click on checkout icon from dropdown")
    public void i_hoverOver_items_cart_and_click_on_checkout_icon_from_dropdown() {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        checkoutCartPage = topMenuPage.mouseOverItemsCartLinkAndClickCheckoutIcon();
    }

    @And("I hoverOver items cart and click on view Cart icon from dropdown")
    public void i_hoverOver_items_cart_and_click_on_view_Cart_icon_from_dropdown() {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        shoppingCartPage = topMenuPage.mouseOverItemsCartLinkAndClickViewCartIcon();
    }

    @When("I should see {string} in checkout confirmation page")
    public void iShouldSeeInCheckoutConfirmationPage(String pageHeaderTitle) {
        checkoutConfirmationPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutConfirmationPage.class);
        AssertionHelper.updateTestStatus(checkoutConfirmationPage.getOrderSummaryText().trim().contains(pageHeaderTitle));

    }

    @Given("I tap on Confirm Order")
    public void i_tap_on_confirm_order() throws Exception {
        checkoutConfirmationPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutConfirmationPage.class);
        checkoutSuccessPage = checkoutConfirmationPage.clickOnConfirmOrderButton();
    }

    @Given("I tap on {string} in checkout confirmation page")
    public void i_tap_on_confirm_order_in_checkout_confirmation_page(String confirmOrderButtonText) throws Exception {
        checkoutConfirmationPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutConfirmationPage.class);
        Assert.assertEquals(checkoutConfirmationPage.getConfirmOrderButtonTxt(), confirmOrderButtonText);
        checkoutSuccessPage = checkoutConfirmationPage.clickOnConfirmOrderButton();

    }

    @Then("I should see {string} in Checkout Success page")
    public void i_should_see_in_checkout_success_page(String orderProcessed) throws InterruptedException {
        checkoutConfirmationPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutConfirmationPage.class);
        Assert.assertEquals(checkoutSuccessPage.getYourOrderHasBeenProcessedText(), orderProcessed);

    }

    @Then("I should see Your order number has been created")
    public void i_should_see_your_order_number_has_been_created() {
        checkoutSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutSuccessPage.class);
        AssertionHelper.updateTestStatus(checkoutSuccessPage.assertOrderNumberTextIsDisplayed());

    }

    @Then("I should see {string} in the page")
    public void i_should_see_thank_you_for_shopping_with_us_in_checkout_success_page(String thankMsg) {
        checkoutSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutSuccessPage.class);
        Assert.assertEquals(checkoutSuccessPage.getThankYouForShoppingWithUsText(), thankMsg);
    }

    @And("I click on Checkout Icon button")
    public void iClickCheckoutIconButton() {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        loginPage = topMenuPage.clickCheckoutIcon();

    }

    @And("I click Checkout Icon button")
    public void clickCheckoutIconButton() throws IOException {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        loginPage = topMenuPage.clickCheckoutCartDropdownIconButton();

    }

    @And("I click View Cart Icon button")
    public void clickViewCartIconButton() {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        shoppingCartPage = topMenuPage.clickViewCartDropdownIconButton();

    }


/*    @When("I login in login page as a returning customer")
    public void i_login_in_login_page_as_a_returning_customer(DataTable dataTable) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.enterLoginName(dataTable.cell(1, 0));
        loginPage.enterPassword(dataTable.cell(1, 1));
        checkoutConfirmationPage = loginPage.clickLoginButton();

    }*/

    @And("I navigate to shopping page")
    public void iNavigateToShoppingPage() throws IOException {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        topMenuPage.mouseOverItemLink();
        checkoutCartPage = topMenuPage.clickOnViewCartLink();

    }

    @And("I increased quantity by {}")
    public void iInputInQuantityFieldAndIncreasedBy(int number) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        shoppingCartPage.inputItemQuantity(number);

    }

    @And("I update {string} quantity in cart by {}")
    public void iUpdateProductQtyInTheCart(String productName, int qty) throws InterruptedException {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        shoppingCartPage.updateQuantityOfProductInTheCart(productName, qty);

    }

    @Given("I update product in cart by quantity:")
    public void i_update_product_in_cart_by_quantity(DataTable dataTable) throws InterruptedException {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        shoppingCartPage.updateQuantityOfProductInTheCart(dataTable.cell(1, 0), Integer.parseInt(dataTable.cell(1, 1)));
    }


    @Given("I can see product {string} and per unit price {}")
    public void i_can_see_product_and_per_unit_price(String productName, String unitPrice) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        Assert.assertTrue(shoppingCartPage.getUnitPriceOfProductInTheCart(productName).equalsIgnoreCase(unitPrice));
    }

    @Given("I can see product and per unit price:")
    public void i_can_see_product_and_per_unit_price(DataTable dataTable) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        Assert.assertTrue(shoppingCartPage.getUnitPriceOfProductInTheCart(dataTable.cell(0, 0)).equalsIgnoreCase(dataTable.cell(1, 0)));

    }

    @Given("I can see product name")
    public void i_can_see_product_name(DataTable dataTable) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        Assert.assertTrue(shoppingCartPage.getNameOfProductsInTheCart(dataTable.cell(0, 0)).contains(dataTable.cell(0, 0)));

    }

    @Given("I should see the product name {string} in the cart")
    public void i_can_see_product_and_total_price(String productName) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        Assert.assertTrue(shoppingCartPage.getNameOfProductsInTheCart(productName).contains(productName));

    }

    @Given("I can see product name and total price:")
    public void i_can_see_product_name_and_total_price(DataTable dataTable) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        Assert.assertEquals(shoppingCartPage.getTotalPriceOfProductInTheCart(dataTable.cell(0, 0)), dataTable.cell(0, 1));

    }

    @Given("click on update button")
    public void click_on_update_button() {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        shoppingCartPage.clickOnUpdateButton();

    }

    @Given("click {string} button")
    public void click_update_button(String updateBtn) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        Assert.assertEquals(shoppingCartPage.getUpdateButtonText(), updateBtn);
        shoppingCartPage.clickOnUpdateButton();
    }

    @Given("I can see current page submenu breadcrumbs {string} are correctly displayed")
    public void i_can_see_current_page_submenu_breadcrumbs_are_correctly_displayed(String breadcrumbText) throws InterruptedException {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        AssertionHelper.updateTestStatus(page.assertCurrentPageBreadCrumbMenuIsDisplayedCorrectly(breadcrumbText));
    }

    @And("I reduced quantity by {int}")
    public void iReducedQuantityBy(int number) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        shoppingCartPage.inputItemQuantity(number);

    }

    @And("I removed {string} item from cart")
    public void iRemovedSomeItemsFromCart(String productName) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        shoppingCartPage.removeProductFromCart(productName);

    }


/*    @And("I can see {string} and {string} in login page")
    public void iCanSeeReturningCustomerAndNewCustomerText(String returningCustomerTxt, String newCustomerTxt) {
        boolean status_returningCustomer = accountLoginPage.getReturningCustomerTxt().equalsIgnoreCase(returningCustomerTxt);
        AssertionHelper.updateTestStatus(status_returningCustomer);
        boolean status_newCustomer = accountLoginPage.getNewCustomerTxt().equalsIgnoreCase(newCustomerTxt);
        AssertionHelper.updateTestStatus(status_newCustomer);

    }*/

    @And("I can see {string}, {string}, {string}, {string}, {string} and {string} buttons are displayed")
    public void iCanSeeAndButtonsAreDisplayed(String editShippingTxt, String editPayTxt, String editCoupTxt, String editCartTxt, String backArrowBtnTxt, String confirmOrderBtnTxt) {
        checkoutConfirmationPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutConfirmationPage.class);
        AssertionHelper.updateTestStatus(checkoutConfirmationPage.getEditShippingButtonText().trim().contains(editShippingTxt));
        AssertionHelper.updateTestStatus(checkoutConfirmationPage.getEditPaymentButtonText().trim().contains(editPayTxt));
        AssertionHelper.updateTestStatus(checkoutConfirmationPage.getEditCouponButtonText().trim().contains(editCoupTxt));
        AssertionHelper.updateTestStatus(checkoutConfirmationPage.getEditCartButtonText().trim().contains(editCartTxt));
        Assert.assertTrue(checkoutConfirmationPage.getBackArrowButtonText().contains(backArrowBtnTxt));
        AssertionHelper.updateTestStatus(checkoutConfirmationPage.getConfirmOrderButtonTxt().trim().contains(confirmOrderBtnTxt));
    }

    @Given("I can see the following buttons are displayed:")
    public void i_can_see_the_following_buttons_are_displayed(DataTable dataTable) {
        checkoutConfirmationPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutConfirmationPage.class);
        AssertionHelper.updateTestStatus(checkoutConfirmationPage.getEditShippingButtonText().trim().contains(dataTable.cell(0, 0)));
        AssertionHelper.updateTestStatus(checkoutConfirmationPage.getEditPaymentButtonText().trim().contains(dataTable.cell(1, 0)));
        AssertionHelper.updateTestStatus(checkoutConfirmationPage.getEditCouponButtonText().trim().contains(dataTable.cell(2, 0)));
        AssertionHelper.updateTestStatus(checkoutConfirmationPage.getEditCartButtonText().trim().contains(dataTable.cell(3, 0)));
        Assert.assertTrue(checkoutConfirmationPage.getBackArrowButtonText().contains(dataTable.cell(4, 0)));
        AssertionHelper.updateTestStatus(checkoutConfirmationPage.getConfirmOrderButtonTxt().trim().contains(dataTable.cell(5, 0)));
    }

    @Given("I click Edit Cart button")
    public void i_click_edit_cart_button() throws IOException {
        checkoutConfirmationPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutConfirmationPage.class);
        checkoutConfirmationPage.clickOnEditCartButton();
    }

    @Given("I click {string} button in checkout_confirmation_page")
    public void i_click_edit_cart_button_in_checkout_confirmation_page(String confirmationButtonText) throws IOException {
        checkoutConfirmationPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutConfirmationPage.class);
        checkoutConfirmationPage.clickOnEditCartButton();
    }

    @And("I click on confirm order button")
    public void iClickOnConfirmOrderButton() throws Exception {
        checkoutConfirmationPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutConfirmationPage.class);
        AssertionHelper.updateTestStatus(checkoutConfirmationPage.getConfirmOrderButtonTxt().contains("Confirm"));
        checkoutSuccessPage = checkoutConfirmationPage.clickOnConfirmOrderButton();
    }

    @And("I tap on {string}")
    public void iTapConfirmOrderButtonCheckoutConfirmationPage(String confirmOrderButtonText) throws Exception {
        checkoutConfirmationPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutConfirmationPage.class);
        AssertionHelper.updateTestStatus(checkoutConfirmationPage.getConfirmOrderButtonTxt().contains(confirmOrderButtonText));
        checkoutSuccessPage = checkoutConfirmationPage.clickOnConfirmOrderButton();
    }

    @And("I am presented with {string}")
    public void iAmPresentedWithAnOderNumber(String orderNumberMsg) {
        checkoutSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutSuccessPage.class);
        boolean status = checkoutSuccessPage.getOrderNumberText().contains(orderNumberMsg);
        AssertionHelper.updateTestStatus(status);

    }

    @Then("I click on Continue button in Checkout Success page")
    public void i_click_on_continue_button_in_checkout_success_page() {
        checkoutSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutSuccessPage.class);
        homePage = checkoutSuccessPage.clickOnContinueCheckoutSuccessButton();
    }


    @And("I click on {string} button in Checkout Success page")
    public void iClicksOnContinue_checkout_success_page(String continueButton) {
        checkoutSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutSuccessPage.class);
        AssertionHelper.updateTestStatus(checkoutSuccessPage.getContinueButtonText().contains(continueButton));
        homePage = checkoutSuccessPage.clickOnContinueCheckoutSuccessButton();

    }

    @And("I am back to home page")
    public void iAmBackToHomePage() {


    }

    @And("I tap on checkout button")
    public void iTapOnCheckoutButton() {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        loginPage = shoppingCartPage.clickOnCheckoutButton();

    }

    @And("I tap {string} button")
    public void iTapCheckoutButton(String checkoutBtn) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        Assert.assertEquals(shoppingCartPage.getCheckoutButtonText(), checkoutBtn);
        loginPage = shoppingCartPage.clickOnCheckoutButton();

    }

    @And("I login with {string} and {string} as a returning customer")
    public void iLoginWithAsAReturningCustomer(String loginname, String password) throws IOException {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.enterLoginName(loginname);
        loginPage.enterPassword(password);
        checkoutConfirmationPage = loginPage.clickLoginButton();
    }

    @Given("I can see {string} and {string} in login page")
    public void i_can_see_returning_customer_and_i_am_a_new_customer(String returning_customer, String new_customer) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        Assert.assertEquals(loginPage.getReturningCustomerTxt(), returning_customer);
        Assert.assertEquals(loginPage.getNewCustomerTxt(), new_customer);

    }

    @And("I should be on {string} in the Checkout Confirmation page")
    public void iShouldBeOnCheckoutConfirmationPage(String headerTxt) {
        checkoutConfirmationPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutConfirmationPage.class);
        Assert.assertEquals(checkoutConfirmationPage.getPageHeadingText(), headerTxt);

    }

    @And("I should see order processed, your order number and a thanks message:")
    public void iShouldSeeOrderProcessedYourOrderNumberAndAThanksMessage(DataTable dataTable) {
        checkoutSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutSuccessPage.class);
        AssertionHelper.updateTestStatus(checkoutSuccessPage.getHeading().contains(dataTable.cell(0, 0)));
        AssertionHelper.updateTestStatus(checkoutSuccessPage.getOrderNumberText().contains(dataTable.cell(1, 0)));
        AssertionHelper.updateTestStatus(checkoutSuccessPage.getThankYouForShoppingWithUsText().contains(dataTable.cell(2, 0)));

    }

    @And("I should see {string} by going to the invoice page")
    public void iShouldSeeYouCanViewYourOrderDetailsByGoingToTheInvoicePage(String viewInvoiceMsg) {
        checkoutSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutSuccessPage.class);
        Assert.assertTrue(checkoutSuccessPage.getYouCanViewYourOrderDetailsText().contains(viewInvoiceMsg));
    }

    @And("I should see You can view your order details by going to the {string}")
    public void iShouldSeeYouCanViewYourOrderDetailsByGoingToThe(String invoicePage) {
        checkoutSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutSuccessPage.class);
        Assert.assertTrue(checkoutSuccessPage.getViewInvoiceLink().contains(invoicePage));

    }

    @And("I should see {string} number has been created")
    public void iShouldSeeNumberHasBeenCreated(String OrderNumberText) {
        checkoutSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutSuccessPage.class);
        Assert.assertTrue(checkoutSuccessPage.getInvoiceNumberText().contains(OrderNumberText));

    }

    @And("I tap Checkout button")
    public void iTapCheckoutButton() {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        loginPage = shoppingCartPage.clickOnCheckoutButton();
    }

    @And("I can see {string} is Displayed")
    public void iCanSeeYOURORDERHASBEENPROCESSEDIsDisplayed(String processedOrderMsg) throws InterruptedException {
        checkoutSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutSuccessPage.class);
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        Assert.assertTrue(checkoutSuccessPage.getYourOrderHasBeenProcessedText().contains(processedOrderMsg));
    }

    @Given("I on the login page")
    public void iOnTheLoginPage() {
    }

    @When("I enter valid username {string} and password {string}")
    public void iEnterValidUsernameAndPassword(String arg0, String arg1) {
    }

    @And("I click the {string} button")
    public void iClickTheButton(String arg0) {
    }

    @Then("should be redirected to the homepage\\/dashboard")
    public void shouldBeRedirectedToTheHomepageDashboard() {
    }

    @And("a success message should be displayed")
    public void aSuccessMessageShouldBeDisplayed() {
    }

    @When("I enter invalid username {string} and password {string}")
    public void iEnterInvalidUsernameAndPassword(String arg0, String arg1) {
    }

    @Then("an error message should be displayed indicating invalid credentials")
    public void anErrorMessageShouldBeDisplayedIndicatingInvalidCredentials() {
    }

    @And("should remain on the login page")
    public void shouldRemainOnTheLoginPage() {
    }

    @When("I click the {string} link")
    public void iClickTheLink(String arg0) {
    }

    @And("I enter a valid email address {string}")
    public void iEnterAValidEmailAddress(String arg0) {
    }

    @Then("a password reset email should be sent to the provided email address")
    public void aPasswordResetEmailShouldBeSentToTheProvidedEmailAddress() {
    }

    @Given("I on the registration page")
    public void iOnTheRegistrationPage() {
    }

    @When("I enter all required user information")
    public void iEnterAllRequiredUserInformation() {
    }

    @Then("a user account should be created successfully")
    public void aUserAccountShouldBeCreatedSuccessfully() {
    }

    @And("I should be redirected to the login page or homepage")
    public void iShouldBeRedirectedToTheLoginPageOrHomepage() {
    }

    @When("I enter invalid or incomplete user information with {string}")
    public void iEnterInvalidOrIncompleteUserInformationWith(String arg0) {
    }

    @Then("error messages should be displayed indicating the specific errors")
    public void errorMessagesShouldBeDisplayedIndicatingTheSpecificErrors() {
    }

    @And("I should remain on the registration page")
    public void iShouldRemainOnTheRegistrationPage() {
    }

    @Given("I is logged in")
    public void iIsLoggedIn() {
    }

    @When("I click the {string} button or link")
    public void iClickTheButtonOrLink(String arg0) {
    }

    @Then("I should be logged out and redirected to the login page")
    public void iShouldBeLoggedOutAndRedirectedToTheLoginPage() {
    }

    @Given("I is on a page with a search bar")
    public void iIsOnAPageWithASearchBar() {
    }

    @When("I enter a valid search term {string} in the search bar")
    public void iEnterAValidSearchTermInTheSearchBar(String arg0) {
    }

    @And("I click the {string} button or presses Enter")
    public void iClickTheButtonOrPressesEnter(String arg0) {
    }

    @Then("relevant search results should be displayed")
    public void relevantSearchResultsShouldBeDisplayed() {
    }

    @When("I enter an {string} or leaves the search bar empty")
    public void iEnterAnOrLeavesTheSearchBarEmpty(String arg0) {
    }

    @Then("a {string} message or similar should be displayed")
    public void aMessageOrSimilarShouldBeDisplayed(String arg0) {
    }

    @Given("I is on a product page")
    public void iIsOnAProductPage() {
    }

    @When("I selects product options \\(if applicable)")
    public void iSelectsProductOptionsIfApplicable() {
    }

    @Then("the item should be added to the cart")
    public void theItemShouldBeAddedToTheCart() {
    }

    @And("the cart icon should update to reflect the added item")
    public void theCartIconShouldUpdateToReflectTheAddedItem() {
    }

    @Given("I can see items in the cart")
    public void iCanSeeItemsInTheCart() {
    }

    @When("I navigate to the cart page")
    public void iNavigateToTheCartPage() {
    }

    @And("I click the {string} or {string} button next to an item")
    public void iClickTheOrButtonNextToAnItem(String arg0, String arg1) {
    }

    @Then("item should be removed from the cart")
    public void itemShouldBeRemovedFromTheCart() {
    }

    @And("cart total should be updated")
    public void cartTotalShouldBeUpdated() {
    }

    @Given("I has items in the cart")
    public void iHasItemsInTheCart() {
    }

    @And("I changes the quantity of an item")
    public void iChangesTheQuantityOfAnItem() {
    }

    @Then("the cart total should be updated to reflect the new quantity")
    public void theCartTotalShouldBeUpdatedToReflectTheNewQuantity() {
    }

    @And("I click the {string} or similar button")
    public void iClickTheOrSimilarButton(String arg0) {
    }

    @Then("I should be redirected to the checkout page")
    public void iShouldBeRedirectedToTheCheckoutPage() {
    }

    @Given("I on the checkout page with items in the cart")
    public void iOnTheCheckoutPageWithItemsInTheCart() {
    }

    @When("I enter valid shipping and billing information")
    public void iEnterValidShippingAndBillingInformation() {
    }

    @And("I selects a shipping method")
    public void iSelectsAShippingMethod() {
    }

    @And("I enter valid payment information")
    public void iEnterValidPaymentInformation() {
    }

    @Then("the order should be placed successfully")
    public void theOrderShouldBePlacedSuccessfully() {
    }

    @And("an order confirmation page should be displayed with order details")
    public void anOrderConfirmationPageShouldBeDisplayedWithOrderDetails() {
    }

    @And("a confirmation email should be sent")
    public void aConfirmationEmailShouldBeSent() {
    }

    @When("I enter invalid payment information")
    public void iEnterInvalidPaymentInformation() {
    }

    @Then("an error message should be displayed indicating invalid payment information")
    public void anErrorMessageShouldBeDisplayedIndicatingInvalidPaymentInformation() {
    }

    @And("the order should not be placed")
    public void theOrderShouldNotBePlaced() {
    }

    @Given("I logged in")
    public void iLoggedIn() {
    }

    @When("I navigate to the {string} or {string} page")
    public void iNavigateToTheOrPage(String arg0, String arg1) {
    }

    @Then("past orders should be displayed with relevant details")
    public void pastOrdersShouldBeDisplayedWithRelevantDetails() {
    }

    @Given("I on the contact page")
    public void iOnTheContactPage() {
    }

    @When("I enter valid contact information")
    public void iEnterValidContactInformation() {
    }

    @Then("the contact form should be submitted successfully")
    public void theContactFormShouldBeSubmittedSuccessfully() {
    }

    @Given("I is on the contact page")
    public void iIsOnTheContactPage() {
    }

    @When("I enter invalid or incomplete contact information")
    public void iEnterInvalidOrIncompleteContactInformation() {
    }

    @Given("I can see newsletter subscription")
    public void iCanSeeNewsletterSubscription() {
    }

    @When("I enter a valid email address")
    public void iEnterAValidEmailAddress() {
    }

    @Then("the subscription should be successful")
    public void theSubscriptionShouldBeSuccessful() {
    }

    @And("a confirmation message should be displayed")
    public void aConfirmationMessageShouldBeDisplayed() {
    }

    @Given("I subscribed to the newsletter")
    public void iSubscribedToTheNewsletter() {
    }

    @When("I click the unsubscribe link in a newsletter email")
    public void iClickTheUnsubscribeLinkInANewsletterEmail() {
    }

    @Then("I should be unsubscribed")
    public void iShouldBeUnsubscribed() {
    }

    @Given("I on a product listing page with filtering\\/sorting options")
    public void iOnAProductListingPageWithFilteringSortingOptions() {
    }

    @When("I selects filtering or sorting criteria")
    public void iSelectsFilteringOrSortingCriteria() {
    }

    @Then("the products should be filtered or sorted according to the selected criteria")
    public void theProductsShouldBeFilteredOrSortedAccordingToTheSelectedCriteria() {
    }

    @When("I views existing product reviews and ratings")
    public void iViewsExistingProductReviewsAndRatings() {
    }

    @And("\\(if applicable) submits a product review and rating")
    public void ifApplicableSubmitsAProductReviewAndRating() {
    }

    @Then("reviews and ratings should be displayed correctly")
    public void reviewsAndRatingsShouldBeDisplayedCorrectly() {
    }

    @And("new reviews should be submitted and displayed \\(if applicable)")
    public void newReviewsShouldBeSubmittedAndDisplayedIfApplicable() {
    }

    @Given("I is on a page with social media sharing buttons")
    public void iIsOnAPageWithSocialMediaSharingButtons() {
    }

    @When("I click a social media sharing button")
    public void iClickASocialMediaSharingButton() {
    }

    @Then("the relevant social media sharing window or page should open")
    public void theRelevantSocialMediaSharingWindowOrPageShouldOpen() {
    }

    @When("I goes to the {string} or {string} page")
    public void iGoesToTheOrPage(String arg0, String arg1) {
    }

    @And("I enter or updates profile information")
    public void iEnterOrUpdatesProfileInformation() {
    }

    @Then("the profile information should be updated successfully")
    public void theProfileInformationShouldBeUpdatedSuccessfully() {
    }

    @And("I enter the current password, new password, and confirms the new password")
    public void iEnterTheCurrentPasswordNewPasswordAndConfirmsTheNewPassword() {
    }

    @Then("the password should be changed successfully")
    public void thePasswordShouldBeChangedSuccessfully() {
    }

    @And("I confirm the account deletion")
    public void iConfirmTheAccountDeletion() {
    }

    @Then("I account should be deleted successfully")
    public void iAccountShouldBeDeletedSuccessfully() {

    }
}



