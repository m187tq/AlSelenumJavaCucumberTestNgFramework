package com.automationteststore.pages;

import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.By;

public class PasswordResetPage {

    Page page;
    // Locators
    By emailField = By.id("forgottenFrm_email");
    By resetButton = By.cssSelector("button[title='Continue']");
    By successMessage = By.cssSelector(".alert.alert-success");

    // Constructor

    // Methods to interact with elements
    public void enterEmail(String email) {
        WebDrv.getInstance().getWebDriver().findElement(emailField).sendKeys(email);
    }

    public void clickResetButton() {
        WebDrv.getInstance().getWebDriver().findElement(resetButton).click();

    }

    public String getSuccessMessage() {
        return WebDrv.getInstance().getWebDriver().findElement(successMessage).getText();

    }
}
