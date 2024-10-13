package com.automationteststore.pages;

import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.helperutilities.javaScript.JavaScriptHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditProfilePage {
    private final Logger log = LogManager.getLogger(EditProfilePage.class);
    @FindBy(xpath = "//*[@id='AccountFrm']/div[2]/div/button")
    private WebElement continueEditButton;
    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/h1[1]/span[1]")
    private WebElement myAccountInformationHeadingText;
    @FindBy(name = "firstname")
    private WebElement firstNameBox;
    @FindBy(xpath = "//*[@id='AccountFrm_lastname']")
    private WebElement lastNameBox;
    @FindBy(id = "AccountFrm_email")
    private WebElement emailBox;
    @FindBy(css = "#AccountFrm_telephone")
    private WebElement telephoneBox;
    @FindBy(css = "#AccountFrm_fax")
    private WebElement faxBox;

    public String userOnAccountEditPage() {
        log.info("User is on Account Edit Page");
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(myAccountInformationHeadingText);

    }

    public void enterFirstName(String firstName) {
        firstNameBox.clear();
        firstNameBox.sendKeys(firstName);
        log.info("Entered first name : " + firstName);
    }

    public void enterLastName(String lastname) {
        lastNameBox.clear();
        lastNameBox.sendKeys(lastname);
        log.info("Entered last name : " + lastname);
    }

    public void enterFreshEmail() {
        String email = System.currentTimeMillis() + "@gmail.com";
        emailBox.clear();
        emailBox.sendKeys(email);
        log.info("Entered email : " + email);
    }

    public void enterTelephone(String telephone) {
        telephoneBox.clear();
        telephoneBox.sendKeys(telephone);
        log.info("Entered telephone : " + telephone);
    }

    public void enterFax(String fax) {
        faxBox.clear();
        faxBox.sendKeys(fax);
        log.info("Entered fax : " + fax);
    }

    public AccountPage clickOnContinueEditButton() throws InterruptedException {
        new JavaScriptHelper(WebDrv.getInstance().getWebDriver()).scrollIntoViewAndClick(continueEditButton);
        return new AccountPage();
    }

    public void updatingProfileDetails(String firstname, String lastname, String telephone, String fax) {
        System.out.println(" about editing Profile Details....");
        enterFirstName(firstname);
        enterLastName(lastname);
        enterFreshEmail();
        enterTelephone(telephone);
        enterFax(fax);
        System.out.println("edited Profile Details....");

    }

}
