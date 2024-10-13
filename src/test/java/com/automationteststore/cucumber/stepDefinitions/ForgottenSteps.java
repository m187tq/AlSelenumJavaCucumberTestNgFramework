package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.helperutilities.alert.AlertHelper;
import com.automationteststore.helperutilities.assertion.AssertionHelper;
import com.automationteststore.pages.*;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;

public class ForgottenSteps {
    TopMenuPage topMenuPage;
    AsideWidgetPage asideWidgetPage;
    AccountPage accountPage;
    RegistrationPage register;
    AccountSuccessPage success;
    LogoutPage logoutPage;
    HomePage homePage;
    AlertHelper alertHelper;

    Page page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
    LoginPage loginPage;
    ForgottenLoginnamePage forgottenLoginnamePage;
    ForgottenPasswordPage forgottenPasswordPage;

    @Given("I navigate to login page")
    public void i_navigate_to_login_page() throws InterruptedException {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        loginPage = topMenuPage.clickOnLoginOrRegisterLink();
    }

    @Given("I can see {string} and {string} sub headings texts")
    public void i_can_see_and_sub_headings_texts(String string, String string2) {

    }

    @Given("I can see {string} and {string} links")
    public void i_can_see_and_links(String forgotPasswordLink, String forgotLoginLink) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        AssertionHelper.updateTestStatus(loginPage.getForgotYourPasswordLinkTxt().contains(forgotPasswordLink));
        AssertionHelper.updateTestStatus(loginPage.getForgotYourLoginLinkTxt().contains(forgotLoginLink));

    }

    @Given("click on Forgot your Login link")
    public void click_on_forgot_your_login_link() {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        forgottenLoginnamePage = loginPage.clickForgetYourLoginLinkInLoginPage();

    }

    @Given("I enter last name {string} and email address {string}")
    public void i_enter_last_name_and_email_address(String lastName, String email) {
        forgottenLoginnamePage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ForgottenLoginnamePage.class);
        forgottenLoginnamePage.enterLastName(lastName);
        forgottenLoginnamePage.enterEmail(email);

    }

    @Given("I tap on Continue button in recovery forgotten Loginname Page")
    public void i_tap_on_continue_button_in_recovery_forgotten_loginname_page() {
        forgottenLoginnamePage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ForgottenLoginnamePage.class);
        forgottenLoginnamePage.clickOnContinueButtonInForgottenLoginnamePage();

    }

    @Given("I should a see confirmation message {string}")
    public void i_should_a_see_confirmation_message(String status) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        AssertionHelper.updateTestStatus(loginPage.getConfirmationMessage().contains(status));

    }

    @Given("click on Forgot your password link")
    public void click_on_forgot_your_password_link() {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        forgottenPasswordPage = loginPage.clickForgetYourPasswordLinkInLoginPage();

    }

    @Given("I should be redirected to the forgotten-password basePage")
    public void i_should_be_in_forgotten_password_page() {

    }

    @Given("I can see Enter the login name and e-mail address associated with your account")
    public void i_can_see_enter_the_login_name_and_e_mail_address_associated_with_your_account() {

    }

    @When("I enter login name {string} and email address {string}")
    public void i_enter_login_name_and_email_address(String loginName, String email) {
        forgottenPasswordPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ForgottenPasswordPage.class);
        forgottenPasswordPage.enterLoginname(loginName);
        forgottenPasswordPage.enterEmail(email);

    }

    @When("I tap on Continue button in recovery forgotten Password Page")
    public void i_tap_on_continue_button_in_recovery_forgotten_password_page() {
        forgottenPasswordPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), ForgottenPasswordPage.class);
        forgottenPasswordPage.clickOnContinueButtonInForgottenPasswordPage();

    }

}
