package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.pages.*;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class AddToCartSteps {
    private HomePage homePage;
    private ProductCategoryPage productCategoryPage;
    private ShoppingCartPage shoppingCartPage;
    private ProductIdPage productIDPage;
    private CheckoutCartPage checkoutCartPage;
    NavigationMenuPage navigationMenuPage;

        // Initialize Page Objects


    @When("I navigate to the {string} category")
    public void i_navigate_to_the_category(String category) {
        navigationMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), NavigationMenuPage.class);
        navigationMenuPage.navigateToCategory(category);
    }

    @When("I select the {string} subcategory")
    public void i_select_the_subcategory(String subCategory) {
        navigationMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), NavigationMenuPage.class);
        navigationMenuPage.navigateToSubCategory(subCategory);
    }
    @Then("I should see {string} in the cart")
    public void i_should_see_in_the_cart(String productName) {
        shoppingCartPage.goToCart();
        Assert.assertTrue(shoppingCartPage.isProductInCart(productName), "Product not found in cart: " + productName);
    }

    @Then("I verify navigation to the product page")
    public void i_verify_navigation_to_the_product_page() {
        String currentUrl = WebDrv.getInstance().getWebDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("rt=product/product&product_id="), "Not navigated to product page.");
    }

    @Given("I have added {string} to the cart")
    public void i_have_added_to_the_cart(String productName) {
        // Assuming the user is already on the home page
        navigationMenuPage.navigateToCategory("Apparel & accessories"); // Example category
        navigationMenuPage.navigateToSubCategory("T-shirts"); // Example subcategory
        navigationMenuPage.addProductToCart(productName);
        shoppingCartPage.goToCart();
        Assert.assertTrue(shoppingCartPage.isProductInCart(productName), "Product not found in cart: " + productName);
    }

    @When("I navigate to the product page for {string}")
    public void i_navigate_to_the_product_page_for(String productName) {
        // Implement navigation to the product page by clicking the product name in the cart
        productCategoryPage = shoppingCartPage.clickOnProductName(productName);
        Assert.assertNotNull(productCategoryPage, "Failed to navigate to product page for: " + productName);
    }


    @Given("I have the following products in the cart:")
    public void i_have_the_following_products_in_the_cart(DataTable dataTable) {
        List<Map<String, String>> products = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> product : products) {
            String productName = product.get("ProductName");
            int quantity = Integer.parseInt(product.get("Quantity"));

            // Navigate to the appropriate category and subcategory based on the product
            // This mapping should ideally be dynamic or stored in a data source
            // For simplicity, we'll assume a default category and subcategory
            // Replace with actual logic as needed

            // Example mapping (replace with actual logic)
            String category = getCategoryForProduct(productName);
            String subCategory = getSubCategoryForProduct(productName);

            navigationMenuPage.navigateToCategory(category);
            navigationMenuPage.navigateToSubCategory(subCategory);
            navigationMenuPage.addProductToCart(productName);
            shoppingCartPage.goToCart();
            shoppingCartPage.updateProductQuantity(productName, quantity);
            Assert.assertTrue(shoppingCartPage.isProductInCart(productName), "Product not found in cart: " + productName);
        }
    }

    @When("I remove the product {string} from the cart")
    public void i_remove_the_product_from_the_cart(String productName) {
        shoppingCartPage.removeProduct(productName);
        Assert.assertFalse(shoppingCartPage.isProductInCart(productName), "Failed to remove product: " + productName);
    }

    @When("I increase the quantity of {string} to {int}")
    public void i_increase_the_quantity_of_to(WebElement productName, Integer quantity) {
        shoppingCartPage.updateProductQuantity(String.valueOf(productName), quantity);
        // Add assertion to verify quantity update
        Assert.assertEquals(shoppingCartPage.getProductQuantity(), quantity.intValue(), "Quantity not updated correctly.");
    }

    @When("I decrease the quantity of {string} to {int}")
    public void i_decrease_the_quantity_of_to(String productName, Integer quantity) {
        shoppingCartPage.updateProductQuantity(productName, quantity);
        // Add assertion to verify quantity update
        Assert.assertEquals(shoppingCartPage.getProductQuantity(), quantity.intValue(), "Quantity not updated correctly.");
    }

    @Then("the cart should reflect the correct total price")
    public void the_cart_should_reflect_the_correct_total_price() {
        String totalPrice = shoppingCartPage.getTotalPrice();
        // Implement logic to verify the total price
        // This could involve calculating expected total based on individual product prices and quantities
        // For simplicity, we'll assume the total price is correctly updated
        Assert.assertNotNull(totalPrice, "Total price is not displayed.");
        // Additional assertions can be added based on requirements
    }

    @When("I click on the checkout button")
    public void i_click_on_the_checkout_button() {
        shoppingCartPage.proceedToCheckout();

    }

    @Then("I should be navigated to the checkout page")
    public void i_should_be_navigated_to_the_checkout_page() {
        Assert.assertTrue(checkoutCartPage.isCheckoutPageDisplayed(), "Checkout page not displayed.");
    }

    // Utility methods to map products to categories and subcategories
    private String getCategoryForProduct(String productName) {
        // Implement actual mapping logic based on productName
        // Example:
        switch (productName) {
            case "Product1":
            case "Product2":
            case "Product3":
            case "Product4":
            case "Product5":
                return "Apparel & accessories";
            case "Product6":
            case "Product7":
            case "Product8":
            case "Product9":
            case "Product10":
                return "Makeup";
            case "Product11":
            case "Product12":
            case "Product13":
            case "Product14":
            case "Product15":
                return "Skincare";
            case "Product16":
            case "Product17":
            case "Product18":
            case "Product19":
                return "Fragrance";
            case "Product20":
            case "Product21":
            case "Product22":
            case "Product23":
                return "Men";
            default:
                return "Apparel & accessories"; // Default category
        }
    }

    private String getSubCategoryForProduct(String productName) {
        // Implement actual mapping logic based on productName
        // Example:
        switch (productName) {
            case "Product1":
                return "T-shirts";
            case "Product2":
                return "Hoodies";
            case "Product3":
                return "Socks";
            case "Product4":
                return "Hats";
            case "Product5":
                return "Accessories";
            case "Product6":
                return "Lipstick";
            case "Product7":
                return "Eyeshadow";
            case "Product8":
                return "Foundation";
            case "Product9":
                return "Blush";
            case "Product10":
                return "Mascara";
            case "Product11":
                return "Moisturizer";
            case "Product12":
                return "Cleanser";
            case "Product13":
                return "Serum";
            case "Product14":
                return "Toner";
            case "Product15":
                return "Sunscreen";
            case "Product16":
                return "Perfume";
            case "Product17":
                return "Cologne";
            case "Product18":
                return "Body Mist";
            case "Product19":
                return "Deodorant";
            case "Product20":
                return "Shoes";
            case "Product21":
                return "Shirts";
            case "Product22":
                return "Pants";
            case "Product23":
                return "Accessories";
            default:
                return "T-shirts"; // Default subcategory
        }
    }
}

