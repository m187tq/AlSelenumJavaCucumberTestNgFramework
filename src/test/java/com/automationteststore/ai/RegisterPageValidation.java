package com.automationteststore.ai;

import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegisterPageValidation {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        WebDrv.getInstance().getWebDriver().get("https://automationteststore.com/index.php?rt=account/create");
        WebDrv.getInstance().getWebDriver().manage().window().maximize();
        WebDrv.getInstance().getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
    }

    @Test
    public void testRegistrationPage() {
        // Assertions for static elements (adjust as needed)
        Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(By.xpath("//h1[contains(text(),'Create Account')]")).isDisplayed(), "Create Account heading not displayed");
        Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(By.xpath("//p[contains(text(),'If you already have an account')]")).isDisplayed(), "Login page link text not displayed");
        Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(By.xpath("//h4[text()='Your Personal Details']")).isDisplayed(), "Personal Details heading not displayed");
        // ... similar assertions for other headings, labels, etc.

        // Assertions for input fields
        Assert.assertEquals(WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_firstname")).getAttribute("name"), "firstname", "Firstname field name incorrect");
        Assert.assertEquals(WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_lastname")).getAttribute("placeholder"), "", "Lastname field placeholder incorrect");
        // ... similar assertions for other input fields

        // Assertions for dropdown menus
        Select countryDropdown = new Select(WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_country_id")));
        Assert.assertEquals(countryDropdown.getFirstSelectedOption().getText(), "United Kingdom", "Default country selection incorrect");

        Select regionDropdown = new Select(WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_zone_id")));
        Assert.assertTrue(regionDropdown.getOptions().size() > 1, "Region dropdown doesn't have options");

        // Assertions for radio buttons and checkboxes
        WebElement newsletterYes = WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_newsletter1"));
        Assert.assertFalse(newsletterYes.isSelected(), "Newsletter 'Yes' should be unselected by default");

        WebElement agreeCheckbox = WebDrv.getInstance().getWebDriver().findElement(By.id("AccountFrm_agree"));
        Assert.assertFalse(agreeCheckbox.isSelected(), "Agree checkbox should be unselected by default");

        // Assertions for buttons
        WebElement continueButton = WebDrv.getInstance().getWebDriver().findElement(By.xpath("//button[contains(text(),'Continue')]"));
        Assert.assertTrue(continueButton.isEnabled(), "Continue button should be enabled");
    }

    @AfterMethod
    public void tearDown() {
        WebDrv.getInstance().getWebDriver().quit();
    }
}