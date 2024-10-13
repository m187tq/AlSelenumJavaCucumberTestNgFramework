package com.automationteststore.pages;

import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgottenLoginnamePage {

    private final static Logger log = LogManager.getLogger(FooterPage.class);
    Page page;
    LoginPage loginPage;
    private WebDriver driver;
    @FindBy(css = "#forgottenFrm_email")
    private WebElement emailTxtField;
    @FindBy(css = "#forgottenFrm_lastname")
    private WebElement forgottenFrmLastName;
    @FindBy(css = ".maintext")
    private WebElement header;
    @FindBy(css = "h4.heading4")
    private WebElement hintHeading;
    @FindBy(css = "button[title='Continue']")
    private WebElement continueButton;

    public boolean isHeaderDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(header);

    }

    public boolean isHintHeaderDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(hintHeading);

    }

    public void enterLastName(String lastname) {
        forgottenFrmLastName.clear();
        forgottenFrmLastName.sendKeys(lastname);
        log.info("Entered last name : " + lastname);

    }

    public String getContinueBtnTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(continueButton);

    }

    public boolean isContinueBtnDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(continueButton);

    }

    public void enterEmail(String email) {
        emailTxtField.sendKeys(email);
        log.info("Entered email : " + email);

    }

    public LoginPage clickOnContinueButtonInForgottenLoginnamePage() {
        continueButton.click();
        return new LoginPage();
    }

}
