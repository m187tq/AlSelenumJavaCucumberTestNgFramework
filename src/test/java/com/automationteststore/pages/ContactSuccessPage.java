package com.automationteststore.pages;

import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ContactSuccessPage {

    private final Logger log = LogManager.getLogger(ContactSuccessPage.class);
    Page page;
    HomePage homePage;
    @FindBy(css = ".mb40 > p:nth-of-type(2)")
    private WebElement successfullySentMsg;
    @FindBy(css = ".mb40 > a[title='Continue']")
    private WebElement continueBtn;

    public String getContinueButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(continueBtn);

    }

    public String getSuccessfullySentMessage() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(successfullySentMsg);
    }

    public LoginPage clickOnContinueButton() {
        continueBtn.click();
        return new LoginPage();
    }

}



