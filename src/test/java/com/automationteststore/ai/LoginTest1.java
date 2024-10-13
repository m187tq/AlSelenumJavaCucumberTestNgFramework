package com.automationteststore.ai;

import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest1 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        //driver = new ChromeDriver();
        WebDrv.getInstance().getWebDriver().get("https://automationteststore.com/index.php?rt=account/login");
        WebDrv.getInstance().getWebDriver().manage().window().maximize();
        WebDrv.getInstance().getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
    }

    @Test
    public void testValidLogin() {
        // Locate login form elements
        WebElement loginNameField = WebDrv.getInstance().getWebDriver().findElement(By.id("loginFrm_loginname"));
        WebElement passwordField = WebDrv.getInstance().getWebDriver().findElement(By.id("loginFrm_password"));
        WebElement loginButton = WebDrv.getInstance().getWebDriver().findElement(By.xpath("//button[@title='Login']"));

        // Enter login credentials
        loginNameField.sendKeys("webdriverio2");
        passwordField.sendKeys("webdriverio2");
        loginButton.click();

        // Add assertions to verify successful login
        // Example: Check for a welcome message or user-specific element
    }

    @Test
    public void testInvalidLogin() {
        // Locate login form elements
        WebElement loginNameField = WebDrv.getInstance().getWebDriver().findElement(By.id("loginFrm_loginname"));
        WebElement passwordField = WebDrv.getInstance().getWebDriver().findElement(By.id("loginFrm_password"));
        WebElement loginButton = WebDrv.getInstance().getWebDriver().findElement(By.xpath("//button[@title='Login']"));

        // Enter invalid login credentials
        loginNameField.sendKeys("invalid_username");
        passwordField.sendKeys("invalid_password");

        // Submit the login form
        loginButton.click();

        // Add assertions to verify login failure
        // Example: Check for an error message
    }

    @AfterMethod
    public void tearDown() {
        WebDrv.getInstance().getWebDriver().quit();
    }
}
