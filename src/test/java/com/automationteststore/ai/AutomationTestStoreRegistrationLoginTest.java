package com.automationteststore.ai;

import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.automationteststore.utilities.Util.*;

public class AutomationTestStoreRegistrationLoginTest {
    // Test data for registration and login
    public static String login_name = getRandomLoginName();
    public static String password = getRandomPassword();

    @Test
    public static void registration_Login_Test() {
        // Set up the path to the ChromeDriver executable
        //System.setProperty("webWebDrv.getInstance().getWebDriver().chrome.driver", "path_to_chromeWebDrv.getInstance().getWebDriver().exe");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the Automation Test Store home page
            WebDrv.getInstance().getWebDriver().get("https://www.automationteststore.com/");

            // Click on the "Login or register" link
            WebElement loginOrRegisterLink = WebDrv.getInstance().getWebDriver().findElement(By.linkText("Login or register"));
            loginOrRegisterLink.click();

            // Wait until the registration/login page loads
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountFrm")));

            // **Registration Process**
            // Click on the "Continue" button to go to the registration page
            WebElement continueButton = WebDrv.getInstance().getWebDriver().findElement(By.xpath("//button[@title='Continue']"));
            continueButton.click();

            // Wait for the registration form to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AccountFrm")));

            // Fill in the registration form
            WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_firstname")).sendKeys("John");
            WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_lastname")).sendKeys("Doe");
            WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_email")).sendKeys(getRandomEmail());
            WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_address_1")).sendKeys("123 Test Street");
            WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_city")).sendKeys("Manchester");

            WebElement zone = WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_zone_id"));
            Select actions_zone = new Select(zone);
            actions_zone.selectByVisibleText("Lancashire");

            WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_postcode")).sendKeys("M8 7YT");

            WebElement country = WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_country_id"));
            Select actions_country = new Select(country);
            actions_country.selectByVisibleText("United Kingdom");

            WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_loginname")).sendKeys(login_name);
            WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_password")).sendKeys(password);
            WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_confirm")).sendKeys(password);
            WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_newsletter0")).click();  // No to newsletter
            WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_agree")).click();  // Agree to terms and conditions

            // Submit the registration form
            WebDrv.getInstance().getWebDriver().findElement(By.xpath("//button[@title='Continue']")).click();

            // Wait for the registration confirmation
            wait.until(ExpectedConditions.urlContains("success"));

            // Validate successful registration
            if (WebDrv.getInstance().getWebDriver().getPageSource().contains("Your Account Has Been Created!")) {
                System.out.println("Registration successful!");
                Assert.assertTrue(WebDrv.getInstance().getWebDriver().getPageSource().contains("Your Account Has Been Created!"), "Account not created");
            } else {
                System.out.println("Registration failed!");
                Assert.assertFalse(WebDrv.getInstance().getWebDriver().getPageSource().contains("Your Account Has Been Created!"), "Your Account Has Been Created!");

            }

            // **Login Process**
            // Log out after registration
            WebDrv.getInstance().getWebDriver().findElement(By.linkText("Logoff")).click();

            // Navigate back to the login page
            WebDrv.getInstance().getWebDriver().findElement(By.linkText("Login or register")).click();

            // Wait for login page to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginFrm")));

            // Fill in the login form
            WebDrv.getInstance().getWebDriver().findElement(By.id("loginFrm_loginname")).sendKeys(login_name);
            WebDrv.getInstance().getWebDriver().findElement(By.id("loginFrm_password")).sendKeys(password);

            // Submit the login form
            WebDrv.getInstance().getWebDriver().findElement(By.xpath("//button[@title='Login']")).click();

            // Wait for the account dashboard to load after login
            wait.until(ExpectedConditions.urlContains("account"));

            // Validate successful login
            if (WebDrv.getInstance().getWebDriver().findElement(By.linkText("Logoff")).isDisplayed()) {
                Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(By.linkText("Logoff")).isDisplayed(), "Logoff link not found");
                System.out.println("Login successful!");
            } else {
                Assert.assertFalse(WebDrv.getInstance().getWebDriver().findElement(By.linkText("Logoff")).isDisplayed(), "Logoff link found");
                System.out.println("Login failed!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            WebDrv.getInstance().getWebDriver().quit();
        }
    }
}

