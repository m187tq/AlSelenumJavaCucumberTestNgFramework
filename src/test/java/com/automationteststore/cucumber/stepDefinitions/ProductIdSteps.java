package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.base.BaseTest;
import com.automationteststore.pages.Page;
import com.automationteststore.pages.ProductIdPage;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProductIdSteps extends BaseTest {
    Page page;

    private ProductIdPage productIdPage;

    @Given("I verify product ID is correct {string}")
    public void i_navigate_to_the_product_page_with_product_id(String productId) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        Assert.assertTrue(page.getThisPageUrl().contains(productId));
    }

    @Then("I verify the product name is {string}")
    public void i_verify_the_product_name_is(String expectedName) {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        Assert.assertTrue(productIdPage.getProductNameText().equalsIgnoreCase(expectedName));
    }

    @Then("I verify the product price is {string}")
    public void i_verify_the_product_price_is(String expectedPrice) {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        Assert.assertTrue(productIdPage.getProductPriceText().equalsIgnoreCase(expectedPrice));
    }

    @When("I enter the quantity as {string}")
    public void i_enter_the_quantity_as(String quantity) {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        int qty = Integer.parseInt(quantity);
        productIdPage.enterQuantity(qty);
    }

    @When("I select the size as {string}")
    public void i_select_the_size_as(String size) {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        productIdPage.selectSize(size);
    }

    @When("I click on {string}")
    public void i_click_on(String buttonName) {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        Assert.assertTrue(productIdPage.getAddedToCartText().equalsIgnoreCase(buttonName));
            productIdPage.clickAddToCart();
    }

    @Then("I verify the Latest Products section is displayed")
    public void i_verify_the_latest_products_section_is_displayed() {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        productIdPage.verifyLatestProductsSectionIsDisplayed();
    }

    @Then("I verify the specific product {string} image is displayed")
    public void i_verify_the_specific_product_image_is_displayed() {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        Assert.assertTrue(productIdPage.verifySpecificProductImageIsDisplayed());

    }

    @Then("I verify the total price is {string}")
    public void i_verify_the_total_price_is(String expectedTotalPrice) {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        Assert.assertEquals(expectedTotalPrice, productIdPage.getProductPriceText());
    }

    @Then("I verify the description section is displayed")
    public void i_verify_the_description_section_is_displayed() {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        Assert.assertTrue(productIdPage.verifyDescriptionSectionIsDisplayed());
    }

    @Then("I verify the reviews section is displayed")
    public void i_verify_the_reviews_section_is_displayed() {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        Assert.assertTrue(productIdPage.verifyReviewsSectionIsDisplayed());
    }

    @Then("I verify the tags section is displayed")
    public void i_verify_the_tags_section_is_displayed() {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        Assert.assertTrue(productIdPage.verifyTagsSectionIsDisplayed());
    }

    @Then("I verify the product id {string}")
    public void iVerifyTheProductId(String productId) {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        Assert.assertTrue(productIdPage.getProductIdUrlPath().contains(productId));

    }

    @And("I verify print button is displayed")
    public void iVerifyPrintButtonIsDisplayed() {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        Assert.assertTrue(productIdPage.verifyPrintButtonIsDisplayed());
    }
}
