package com.automationteststore.pages;

import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InvoiceOrderPage {
    private final static Logger log = LogManager.getLogger(InvoiceOrderPage.class);
    @FindBy(css = ".mb40 > p:nth-of-type(2)")
    private WebElement invoiceHeadingText;
    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/h1[1]/span[1]")
    private WebElement orderDetailsHeadingText;
    @FindBy(css = ".maintext")
    private WebElement headerText;
    @FindBy(css = ".mb40 p:nth-child(6)")
    private WebElement thanksMessage;
    @FindBy(css = "a.btn.btn-default.mr10.mb20")
    private WebElement continueButton;


    public String getInvoiceHeadingText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(invoiceHeadingText);
    }

    public String getOrderDetailsHeadingText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(orderDetailsHeadingText);

    }

    public void clickContinueButton() {
        continueButton.click();

    }

    public String getContinueButton() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(continueButton);

    }

    public String getHeaderText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(headerText);

    }

    public boolean assertHeaderTextIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(headerText);

    }

    public String getThanksMessage() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(thanksMessage);

    }

    public boolean assertThanksMessageIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(thanksMessage);

    }


}
