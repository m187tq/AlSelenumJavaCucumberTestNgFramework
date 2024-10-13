/*
package com.automationteststore.tests.Order;

// src/test/java/tests/AddToCartTest.java

import com.automationteststore.pages.*;
import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;




public class AddToCartTest {

    private WebDriver driver;
    private HomePage homePage;
    private ProductCategoryPage categoryPage;
    private CartPage cartPage;
    private ProductPage productPage;

    @BeforeClass
    public void setUp() {
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Initialize Page Objects
        homePage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), HomePage.class);
        categoryPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductCategoryPage.class);
        productPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ProductIDPage.class);
        categoryPage = new ProductCategoryPage(driver);


        // Navigate to home page
        driver.get("https://automationteststore.com/index.php");
    }

    @Test(dataProvider = "productData", dataProviderClass = TestData.class)
    public void addProductToCartTest(String category, String subCategory, String productName) {
        // Step 2: Add products to the cart from home page through category menu
        homePage.navigateToCategory(category);

        // Step 2: Navigate to subcategory
        categoryPage.navigateToSubCategory(subCategory);

        // Step 3: Verify navigation to product page (Assuming clicking product)
        categoryPage.addProductToCart(productName);
        // Note: After adding to cart, you might be redirected or a confirmation appears

        // For verification, navigate to cart
        cartPage.goToCart();

        // Step 4: Verify product added to cart
        Assert.assertTrue(cartPage.isProductInCart(productName), "Product not found in cart: " + productName);
    }

    @Test(dependsOnMethods = "addProductToCartTest", dataProvider = "productData", dataProviderClass = TestData.class)
    public void verifyCartDetailsTest(String category, String subCategory, String productName) {
        // Navigate to cart
        cartPage.goToCart();

        // Step 4: Verify products in the cart
        Assert.assertTrue(cartPage.isProductInCart(productName), "Product not found in cart: " + productName);

        // Step 8: Verify product name against price, unit price, and total price
        // Implement methods in CartPage to retrieve these details and assert

        // Example:
        // String unitPrice = cartPage.getUnitPrice(productName);
        // String totalPrice = cartPage.getTotalPrice(productName);
        // Assert.assertEquals(unitPrice, expectedUnitPrice);
        // Assert.assertEquals(totalPrice, expectedTotalPrice);
    }

    @Test(dependsOnMethods = "verifyCartDetailsTest")
    public void modifyCartTest() {
        // Step 9: Remove one product
        String productToRemove = "Product1"; // Replace with actual product name
        cartPage.removeProduct(productToRemove);
        Assert.assertFalse(cartPage.isProductInCart(productToRemove), "Failed to remove product: " + productToRemove);

        // Step 10: Increase product quantity
        String productToIncrease = "Product2"; // Replace with actual product name
        int newQuantity = 3;
        cartPage.updateProductQuantity(productToIncrease, newQuantity);
        // Add assertions to verify quantity update

        // Step 11: Decrease product quantity
        int decreasedQuantity = 1;
        cartPage.updateProductQuantity(productToIncrease, decreasedQuantity);
        // Add assertions to verify quantity update

        // Step 12: Verify total price
        String totalPrice = cartPage.getTotalPrice();
        // Assert based on expected total price
    }

    @Test(dependsOnMethods = "modifyCartTest")
    public void proceedToCheckoutTest() {
        // Step 13: Click on checkout
        cartPage.proceedToCheckout();

        // Verify navigation to checkout page
        // Initialize CheckoutPage and verify elements
        pages.CheckoutPage checkoutPage = new pages.CheckoutPage(driver);
        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed(), "Checkout page not displayed.");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}

*/
