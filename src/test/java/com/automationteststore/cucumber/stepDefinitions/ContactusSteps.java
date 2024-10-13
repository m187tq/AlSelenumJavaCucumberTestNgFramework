package com.automationteststore.cucumber.stepDefinitions;


import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.assertion.AssertionHelper;
import com.automationteststore.pages.*;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;


public class ContactusSteps {
    FooterPage footerPage;
    Page page;
    ContactPage contactPage;
    LoginPage loginPage;
    ContactSuccessPage contactSuccessPage;

    @Given("I navigate to contactus Page")
    public void iNavigateToContactusPage() throws IOException {
        //topMenuPage.navigateToHomePage();
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        AssertionHelper.updateTestStatus(page.getThisPageTitle().contains(GlobalVarsHelper.getHomePageTitle()));
        contactPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ContactPage.class);
        contactPage = footerPage.clickOnContactusLink();
    }

    @And("I can see contact details {string}, {string} and {string} are displayed")
    public void iCanSeeContactDetailsIsDisplayedAnd(String contactusTxt, String address, String phoneNumber) {
        contactPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ContactPage.class);
        AssertionHelper.updateTestStatus(contactPage.getContactDetailsText().contains(contactusTxt));
        AssertionHelper.updateTestStatus(contactPage.getAddressText().contains(address));
        AssertionHelper.updateTestStatus(contactPage.getPhoneNumberTxt().contains(phoneNumber));
    }

    @And("clicks on the submit button")
    public void clicksOnTheSubmitButton() throws IOException {
        contactPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ContactPage.class);
        contactSuccessPage = contactPage.tapOnSubmitBtn();
    }

    @Then("I should get confirmation warning massage firstname {string}")
    public void iShouldGetConfirmationWarningMassageFirstname(String errorFirstnameMsg) {
        contactPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ContactPage.class);
        AssertionHelper.updateTestStatus(contactPage.getErrorFirstNameBoxMsg().contains(errorFirstnameMsg));
    }

    @Then("I should get confirmation warning massage email {string}")
    public void iShouldGetConfirmationWarningMassageEmail(String errorEmailMsg) {
        contactPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ContactPage.class);
        AssertionHelper.updateTestStatus(contactPage.getErrorEmailBoxMsg().trim().contains(errorEmailMsg));
    }

    @Then("I should get confirmation warning massage inquiry {string}")
    public void iShouldGetConfirmationWarningMassageInquiry(String warningMassageInquiry) {
        contactPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ContactPage.class);
        AssertionHelper.updateTestStatus(contactPage.getErrorInquiryBoxMsg().contains(warningMassageInquiry));
    }

    @When("I inputted first name as {string} email Address as {string} and inquiry as {string}")
    public void iInputtedFirstNameAsEmailAddressAsAndInquiryAs(String firstname, String email, String inquiryMsg) {
        contactPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ContactPage.class);
        contactPage.inputFirstName(firstname);
        contactPage.inputEmail(email);
        contactPage.inputInquiry(inquiryMsg);
    }

    @Then("I can see {string} button and {string}")
    public void iCanSeeContinueButtonAnd(String continueBtn, String submitSuccessMsg) {
        contactSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ContactSuccessPage.class);
        AssertionHelper.updateTestStatus(contactSuccessPage.getContinueButtonText().equalsIgnoreCase(continueBtn));
        AssertionHelper.updateTestStatus(contactSuccessPage.getSuccessfullySentMessage().equalsIgnoreCase(submitSuccessMsg));
    }

    @And("I tap on the continue button")
    public void iTapOnTheContinueButton() throws IOException {
        contactSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ContactSuccessPage.class);
        loginPage = contactSuccessPage.clickOnContinueButton();
    }

}



