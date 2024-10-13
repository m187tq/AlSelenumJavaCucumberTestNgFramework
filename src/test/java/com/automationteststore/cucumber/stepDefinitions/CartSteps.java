package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.base.BaseTest;
import com.automationteststore.pages.HomePage;
import com.automationteststore.pages.ProductIdPage;
import com.automationteststore.pages.ShoppingCartPage;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class CartSteps extends BaseTest {
    HomePage homePage;
    ProductIdPage productIdPage;
    ShoppingCartPage shoppingCartPage;


    @When("I add the product {string} to the cart")
    public void i_add_the_product_to_the_cart(String productName) {
        homePage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), HomePage.class);
        homePage.addProductToCart(productName);
        productIdPage.clickAddToCart();
    }

    @Then("the cart should contain {string}")
    public void the_cart_should_contain(String productName) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        shoppingCartPage.navigateToCart();
        boolean isInCart = shoppingCartPage.isProductInCart(productName);
        Assert.assertTrue(isInCart, "Product '" + productName + "' was not found in the cart.");
    }

    @When("I add the following products to the cart:")
    public void i_add_the_following_products_to_the_cart(DataTable dataTable) {
        List<String> productNames = dataTable.asList(String.class);
        for (String productName : productNames) {
            homePage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), HomePage.class);
            homePage.addProductToCart(productName);
            productIdPage.clickAddToCart();
            homePage.navigateToHomePage(); // Navigate back to home after adding to cart
        }
    }

    @Then("the cart should contain the following products:")
    public void the_cart_should_contain_the_following_products(DataTable dataTable) {
        List<String> expectedProducts = dataTable.asList(String.class);
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        shoppingCartPage.navigateToCart();
        for (String productName : expectedProducts) {
            boolean isInCart = shoppingCartPage.isProductInCart(productName);
            Assert.assertTrue(isInCart, "Product '" + productName + "' was not found in the cart.");
        }
    }


    @Given("the user is on the Automation Test Store homepage")
    public void the_user_is_on_the_Automation_Test_Store_homepage() {
        homePage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), HomePage.class);
        homePage.navigateToHomePage();
    }

    @When("the user navigates to the {string} category")
    public void the_user_navigates_to_the_category(String category) {
        homePage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), HomePage.class);
        homePage.selectCategory(category);
    }

    @When("selects the product {string}")
    public void selects_the_product(String productName) {
        homePage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), HomePage.class);
        homePage.selectProduct(productName);
    }

    @When("adds the product to the cart")
    public void adds_the_product_to_the_cart() {
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        productIdPage.addToCart();
    }

    @Then("the cart should contain {string} with quantity {int}")
    public void the_cart_should_contain_with_quantity(String productName, Integer quantity) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        shoppingCartPage.navigateToCart();
        boolean isProductInCart = shoppingCartPage.verifyProductInCart(productName, quantity);
        Assert.assertTrue(isProductInCart, "Product " + productName + " with quantity " + quantity + " is not in the cart.");
    }

    @Then("the total price should equal the unit price")
    public void the_total_price_should_equal_the_unit_price() {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        boolean isTotalEqual = shoppingCartPage.verifyTotalPriceEqualsUnitPrice();
        Assert.assertTrue(isTotalEqual, "Total price does not equal unit price.");
    }

    @When("the user selects the product {string} from {string} category")
    public void the_user_selects_the_product_from_category(String productName, String category) {
        homePage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), HomePage.class);
        homePage.selectCategory(category);
        homePage.selectProduct(productName);
    }

    @Then("the grand total should be the sum of all product totals")
    public void the_grand_total_should_be_the_sum_of_all_product_totals() {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        double calculatedTotal = shoppingCartPage.calculateSumOfProductTotals();
        double displayedTotal = shoppingCartPage.getGrandTotal();
        Assert.assertEquals(displayedTotal, calculatedTotal, "Grand total does not match the sum of product totals.");
    }

    @Given("the cart contains {string}")
    public void the_cart_contains(String productName) {
        homePage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), HomePage.class);
        homePage.navigateToHomePage();
        homePage.selectProduct(productName);
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        productIdPage.addToCart();
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        shoppingCartPage.navigateToCart();
        boolean isProductInCart = shoppingCartPage.verifyProductInCart(productName, 1);
        Assert.assertTrue(isProductInCart, "Product " + productName + " was not added to the cart.");
    }

    @When("the user removes {string} from the cart")
    public void the_user_removes_from_the_cart(String productName) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        shoppingCartPage.removeProductFromCart(productName);
    }

    @Then("the cart should no longer contain {string}")
    public void the_cart_should_no_longer_contain(String productName) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        boolean isProductInCart = shoppingCartPage.isProductInCart(productName);
        Assert.assertFalse(isProductInCart, "Product " + productName + " is still present in the cart.");
    }

    @Then("the grand total should be updated accordingly")
    public void the_grand_total_should_be_updated_accordingly() {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        double grandTotal = shoppingCartPage.getGrandTotal();
        Assert.assertEquals(grandTotal, 0.0, "Grand total should be $0.00 after removing the product.");
    }

    @Given("the cart contains {string} with quantity {int}")
    public void the_cart_contains_with_quantity(String productName, Integer quantity) {
        homePage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), HomePage.class);
        homePage.navigateToHomePage();
        homePage.selectProduct(productName);
        productIdPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIdPage.class);
        productIdPage.addToCart();
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        shoppingCartPage.navigateToCart();
        shoppingCartPage.updateProductQuantity(productName, quantity);
        boolean isProductInCart = shoppingCartPage.verifyProductInCart(productName, quantity);
        Assert.assertTrue(isProductInCart, "Product " + productName + " with quantity " + quantity + " is not in the cart.");
    }

    @When("the user updates the quantity of {string} to {int}")
    public void the_user_updates_the_quantity_of_to(String productName, Integer newQuantity) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        shoppingCartPage.updateProductQuantity(productName, newQuantity);
    }

    @Then("the cart should show {string} with quantity {int}")
    public void the_cart_should_show_with_quantity(String productName, Integer quantity) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        boolean isProductInCart = shoppingCartPage.verifyProductInCart(productName, quantity);
        Assert.assertTrue(isProductInCart, "Product " + productName + " with quantity " + quantity + " is not correctly displayed in the cart.");
    }

    @Then("the total price for {string} should equal {int} times the unit price")
    public void the_total_price_for_should_equal_times_the_unit_price(String productName, Integer multiplier) {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
        double expectedTotal = shoppingCartPage.getUnitPrice(productName) * multiplier;
        double actualTotal = shoppingCartPage.getTotalPrice(productName);
        Assert.assertEquals(actualTotal, expectedTotal, "Total price for " + productName + " does not equal " + multiplier + " times the unit price.");
    }

    @Given("the user has added the following products to the cart:")
    public void the_user_has_added_the_following_products_to_the_cart(DataTable dataTable) {
        List<Map<String, String>> products = dataTable.asMaps(String.class, String.class);
        homePage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), HomePage.class);
        homePage.navigateToHomePage();
        for (Map<String, String> product : products) {
            String productName = product.get("Product Name");
            String category = product.get("Category");
            int quantity = Integer.parseInt(product.get("Quantity"));
            homePage.selectCategory(category);
            homePage.selectProduct(productName);
            productIdPage.addToCart();
            shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);
            shoppingCartPage.navigateToCart();
            if (quantity > 1) {
                shoppingCartPage.updateProductQuantity(productName, quantity);
            }
            homePage.navigateToHomePage();
        }
    }

    @Then("the cart should display the correct product names")
    public void the_cart_should_display_the_correct_product_names() {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);

    }

    @Then("each product should have the correct unit price and total price")
    public void each_product_should_have_the_correct_unit_price_and_total_price() {
        shoppingCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ShoppingCartPage.class);

    }


}

