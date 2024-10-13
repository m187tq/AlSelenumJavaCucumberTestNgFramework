package com.automationteststore.tests.Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ForgottenCredentialsTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize the WebDriver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the login page
        driver.get("https://automationteststore.com/index.php?rt=account/login");
    }

    @Test
    public void testForgottenPassword() {
        // Click the "Forgotten Password" link
        WebElement forgottenPasswordLink = driver.findElement(By.linkText("Forgotten Password"));
        forgottenPasswordLink.click();

        // Enter a valid email address
        WebElement emailField = driver.findElement(By.id("forgotten_password_email"));
        emailField.sendKeys("your_valid_email@example.com"); // Replace with a valid email

        // Click the "Continue" button
        WebElement continueButton = driver.findElement(By.xpath("//button[text()='Continue']"));
        continueButton.click();

        // Wait for the success message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success']")));

        // Assertion: Verify the success message
        String successMessage = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertTrue(successMessage.contains("An email with a link to reset your password has been sent."));

        // (Optional) Add assertions to verify email received
    }

    @Test
    public void testForgottenLoginName() {
        // Click the "Forgotten Login Name" link
        WebElement forgottenLoginNameLink = driver.findElement(By.linkText("Forgotten Login Name"));
        forgottenLoginNameLink.click();

        // Enter a valid email address
        WebElement emailField = driver.findElement(By.id("forgotten_login_email"));
        emailField.sendKeys("your_valid_email@example.com"); // Replace with a valid email

        // Click the "Continue" button
        WebElement continueButton = driver.findElement(By.xpath("//button[text()='Continue']"));
        continueButton.click();

        // Wait for the success message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success']")));

        // Assertion: Verify the success message
        String successMessage = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertTrue(successMessage.contains("An email with your login name has been sent."));

        // (Optional) Add assertions to verify email received
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}