package com.automationteststore.ai;

import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class RegistrationPageWithPOM_Assertions {

    private WebDriver driver;
    // Locators for personal details
    private By firstNameInput = By.id("AccountFrm_firstname");
    private By lastNameInput = By.id("AccountFrm_lastname");
    private By emailInput = By.id("AccountFrm_email");
    private By telephoneInput = By.id("AccountFrm_telephone");
    private By faxInput = By.id("AccountFrm_fax");
    // Locators for address details
    private By companyInput = By.id("AccountFrm_company");
    private By address1Input = By.id("AccountFrm_address_1");
    private By address2Input = By.id("AccountFrm_address_2");
    private By cityInput = By.id("AccountFrm_city");
    private By regionSelect = By.id("AccountFrm_zone_id");
    private By zipInput = By.id("AccountFrm_postcode");
    private By countrySelect = By.id("AccountFrm_country_id");
    // Locators for login details
    private By loginNameInput = By.id("AccountFrm_loginname");
    private By passwordInput = By.id("AccountFrm_password");
    private By confirmPasswordInput = By.id("AccountFrm_confirm");
    // Locators for newsletter and agreement
    private By newsletterYesRadio = By.id("AccountFrm_newsletter1");
    private By newsletterNoRadio = By.id("AccountFrm_newsletter0");
    private By privacyPolicyCheckbox = By.id("AccountFrm_agree");
    private By continueButton = By.cssSelector("button[title='Continue']");

    public RegistrationPageWithPOM_Assertions(WebDriver driver) {
        this.driver = driver;
    }

    public void enterPersonalDetails(String firstName, String lastName, String email, String telephone, String fax) {
        try {
            WebDrv.getInstance().getWebDriver().findElement(firstNameInput).sendKeys(firstName);
            Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(firstNameInput).getAttribute("value").equals(firstName), "First name not entered correctly");

            WebDrv.getInstance().getWebDriver().findElement(lastNameInput).sendKeys(lastName);
            Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(lastNameInput).getAttribute("value").equals(lastName), "Last name not entered correctly");

            WebDrv.getInstance().getWebDriver().findElement(emailInput).sendKeys(email);
            Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(emailInput).getAttribute("value").equals(email), "Email not entered correctly");

            WebDrv.getInstance().getWebDriver().findElement(telephoneInput).sendKeys(telephone);
            Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(telephoneInput).getAttribute("value").equals(telephone), "Telephone not entered correctly");

            WebDrv.getInstance().getWebDriver().findElement(faxInput).sendKeys(fax);
            Assert.assertEquals(fax, WebDrv.getInstance().getWebDriver().findElement(faxInput).getAttribute("value"), "Fax not entered correctly");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail("Element not found: " + e.getMessage());
        }
    }

    public void enterAddressDetails(String company, String address1, String address2, String city, String region, String zip, String country) {
        try {
            WebDrv.getInstance().getWebDriver().findElement(companyInput).sendKeys(company);
            Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(companyInput).getAttribute("value").equals(company), "Company not entered correctly");

            WebDrv.getInstance().getWebDriver().findElement(address1Input).sendKeys(address1);
            Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(address1Input).getAttribute("value").equals(address1), "Address 1 not entered correctly");

            WebDrv.getInstance().getWebDriver().findElement(address2Input).sendKeys(address2);
            Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(address2Input).getAttribute("value").equals(address2), "Address 2 not entered correctly");

            WebDrv.getInstance().getWebDriver().findElement(cityInput).sendKeys(city);
            Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(cityInput).getAttribute("value").equals(city), "City not entered correctly");

            Select regionDropdown = new Select(WebDrv.getInstance().getWebDriver().findElement(regionSelect));
            regionDropdown.selectByVisibleText(region);
            Assert.assertTrue(regionDropdown.getFirstSelectedOption().getText().equals(region), "Region not selected correctly");

            WebDrv.getInstance().getWebDriver().findElement(zipInput).sendKeys(zip);
            Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(zipInput).getAttribute("value").equals(zip), "Zip code not entered correctly");

            Select countryDropdown = new Select(WebDrv.getInstance().getWebDriver().findElement(countrySelect));
            countryDropdown.selectByVisibleText(country);
            Assert.assertTrue(countryDropdown.getFirstSelectedOption().getText().equals(country), "Country not selected correctly");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail("Element not found: " + e.getMessage());
        }
    }

    public void enterLoginDetails(String loginName, String password) {
        try {
            WebDrv.getInstance().getWebDriver().findElement(loginNameInput).sendKeys(loginName);
            Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(loginNameInput).getAttribute("value").equals(loginName), "Login name not entered correctly");

            WebDrv.getInstance().getWebDriver().findElement(passwordInput).sendKeys(password);
            // We don't assert password values due to security concerns

            WebDrv.getInstance().getWebDriver().findElement(confirmPasswordInput).sendKeys(password);
            // We don't assert password values due to security concerns
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail("Element not found: " + e.getMessage());
        }
    }

    public void subscribeToNewsletter(boolean subscribe) {
        try {
            if (subscribe) {
                WebDrv.getInstance().getWebDriver().findElement(newsletterYesRadio).click();
                Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(newsletterYesRadio).isSelected(), "Newsletter 'Yes' not selected");
            } else {
                WebDrv.getInstance().getWebDriver().findElement(newsletterNoRadio).click();
                Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(newsletterNoRadio).isSelected(), "Newsletter 'No' not selected");
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail("Element not found: " + e.getMessage());
        }
    }

    public void agreeToPrivacyPolicy() {
        try {
            WebDrv.getInstance().getWebDriver().findElement(privacyPolicyCheckbox).click();
            Assert.assertTrue(WebDrv.getInstance().getWebDriver().findElement(privacyPolicyCheckbox).isSelected(), "Privacy policy not agreed to");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail("Element not found: " + e.getMessage());
        }
    }

    public void clickContinue() {
        try {
            WebDrv.getInstance().getWebDriver().findElement(continueButton).click();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail("Element not found: " + e.getMessage());
        }
    }
}
