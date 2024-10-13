package com.automationteststore.tests.Order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class AutomationTestStoreOrder {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationteststore.com/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void addSingleProductToCart() {
        String productName = "Seaweed Conditioner";  // Example product name

        // Locate the product by its name
        List<WebElement> products = driver.findElements(By.cssSelector(".prdocutname"));
        for (WebElement product : products) {
            if (product.getText().equalsIgnoreCase(productName)) {
                // Click on the product to go to its details page
                product.click();

                // Add the product to the cart
                driver.findElement(By.cssSelector("a[class='cart']")).click();
                break;
            }
        }

        // Verify that the product was added to the cart
        WebElement cart = driver.findElement(By.cssSelector("a[href*='checkout/cart']"));
        cart.click();

        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart-info tbody tr"));
        boolean productInCart = false;
        for (WebElement item : cartItems) {
            if (item.getText().contains(productName)) {
                productInCart = true;
                break;
            }
        }

        Assert.assertTrue(productInCart, "Product was not added to the cart.");
    }

    @Test
    public void addMultipleProductsToCart() {
        String[] productNames = {"Seaweed Conditioner", "Eau Parfum Spray"};  // List of product names

        for (String productName : productNames) {
            // Go back to the homepage before searching for the next product
            driver.navigate().to("https://automationteststore.com/");

            // Locate the product by its name
            List<WebElement> products = driver.findElements(By.cssSelector(".prdocutname"));
            for (WebElement product : products) {
                if (product.getText().equalsIgnoreCase(productName)) {
                    // Click on the product to go to its details page
                    product.click();

                    // Add the product to the cart
                    driver.findElement(By.cssSelector("a[class='cart']")).click();
                    break;
                }
            }
        }

        // Verify that all products were added to the cart
        WebElement cart = driver.findElement(By.cssSelector("a[href*='checkout/cart']"));
        cart.click();

        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart-info tbody tr"));
        for (String productName : productNames) {
            boolean productInCart = false;
            for (WebElement item : cartItems) {
                if (item.getText().contains(productName)) {
                    productInCart = true;
                    break;
                }
            }
            Assert.assertTrue(productInCart, productName + " was not added to the cart.");
        }
    }
}
