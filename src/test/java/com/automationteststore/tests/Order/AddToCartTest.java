package com.automationteststore.tests.Order;

import com.automationteststore.helperutilities.javaScript.JavaScriptHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddToCartTest {

    public static void main(String[] args) {

        // Initialize the WebDriver
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the home page
        driver.get("https://automationteststore.com/");

        // Test Case 1: Add a single product
        addProductToCart(driver, wait, "Benefit Bella Bamba");

        // Test Case 2: Add multiple products
        addProductToCart(driver, wait, "Benefit Bella Bamba");
        addProductToCart(driver, wait, "Absolute Anti-Age Spot Treatment");

        // Close the browser
        driver.quit();
    }

    public static void addProductToCart(WebDriver driver, WebDriverWait wait, String productName) {
        List<WebElement> productElements = driver.findElements(By.className("productcart"));
        for (WebElement productElement : productElements) {
            String outerHTML = productElement.getAttribute("outerHTML");
            if (outerHTML.contains(productName)) {
                WebElement addToCartButton = productElement.findElement(By.xpath(".//a[text()='Add to Cart']"));
                new JavaScriptHelper(WebDrv.getInstance().getWebDriver()).scrollToElementAndClick(addToCartButton);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success']")));

                // (Optional) Add assertions to verify success message and cart icon update

                return; // Exit the loop after adding the product
            }
        }
        // If the product is not found, throw an exception or handle it appropriately
        System.out.println("Product not found: " + productName);
    }
}
