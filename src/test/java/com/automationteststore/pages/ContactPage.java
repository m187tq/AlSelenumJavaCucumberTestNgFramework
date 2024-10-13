package com.automationteststore.pages;

import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class ContactPage {
    private final Logger log = LogManager.getLogger(ContactPage.class);
    @FindBy(css = ".element_error.has-error")
    public List<WebElement> errorList;

    Page page;
    @FindBy(css = "#field_13 > .help-block > .element_error")
    private WebElement inquiryBoxError;
    @FindBy(css = "#ContactUsFrm_first_name")
    private WebElement firstNameBox;
    @FindBy(css = "#ContactUsFrm_email")
    private WebElement emailBox;
    @FindBy(css = "#ContactUsFrm_enquiry")
    private WebElement inquiryBox;
    @FindBy(css = ".col-md-1 > .btn")
    private WebElement resetBtn;
    @FindBy(css = ".col-md-6 > .btn")
    private WebElement submitBtn;
    @FindBy(css = "#field_11 > .help-block > .element_error")
    private WebElement firstNameError;
    @FindBy(css = "#field_12 > .help-block > .element_error")
    private WebElement emailBoxError;
    @FindBy(css = "div:nth-of-type(3)  .element_error.has-error")
    private WebElement inquiryBoxErrorTxt;
    @FindBy(css = ".col-md-6.pull-right")
    private WebElement phoneNumber;
    @FindBy(css = ".col-md-6.pull-left")
    private WebElement address;
    @FindBy(css = ".heading3")
    private WebElement contactUsFormTxt;
    @FindBy(css = ".required")
    private WebElement requiredRedColorSign;

    public WebElement getInquiryBoxError() {
        return inquiryBoxError;

    }

    public List<WebElement> getErrorList() {
        return errorList;

    }

    public boolean assertFirstNameIsDisplayedAndEnabled() {
        boolean isEnabled = firstNameBox.isEnabled();
        boolean isDisplayed = firstNameBox.isDisplayed();
        return isDisplayed && isEnabled;
    }

    public void inputFirstName(String firstname) {
        firstNameBox.sendKeys(firstname);

    }

    public WebElement emailBox() {
        return this.emailBox;

    }

    public void inputEmail(String email) {
        emailBox.sendKeys(email);

    }

    public WebElement inquiryBox() {
        return inquiryBox;

    }

    public void inputInquiry(String msg) {
        inquiryBox.sendKeys(msg);

    }

    public WebElement resetButton() {
        return resetBtn;

    }

    public void clickOnResetButton() {
        resetBtn.click();

    }

    public String getSubmitButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(submitBtn);

    }

    public ContactSuccessPage tapOnSubmitBtn() {
        submitBtn.click();
        return new ContactSuccessPage();
    }

    public String getErrorFirstNameBoxMsg() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(firstNameError);

    }

    public String getErrorEmailBoxMsg() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(emailBoxError);

    }

    public String getErrorInquiryBoxMsg() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(phoneNumber);

    }

    public boolean assertPhoneNumberIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(phoneNumber);

    }

    public String getPhoneNumberTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(phoneNumber);

    }

    public WebElement address() {
        return address;

    }

    public String getAddressText() {
        return address.getText();

    }

    public WebElement contactUsFormText() {
        return this.contactUsFormTxt;

    }

    public WebElement requiredRedColorSign() {
        return this.requiredRedColorSign;

    }

    public String getContactDetailsText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(contactUsFormTxt);
    }

}



