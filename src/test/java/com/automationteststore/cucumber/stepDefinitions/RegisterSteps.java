package com.automationteststore.cucumber.stepDefinitions;


import com.automationteststore.base.BaseTest;
import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.assertion.AssertionHelper;
import com.automationteststore.model.RegistrationModel;
import com.automationteststore.pages.*;
import com.automationteststore.testData.register.RegisterTestData;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class RegisterSteps extends BaseTest {

    Page page;
    TopMenuPage topMenuPage;
    LoginPage loginPage;
    RegistrationPage register;
    AccountPage accountPage;
    LogoutPage logoutPage;
    HomePage homePage;

    @DataTableType
    public RegistrationModel convertRegistrationModel(Map<String, String> entry) {
        return RegistrationModel.createAnAccountModel(entry);
    }

    private String getRegistrationUrl() {
        return GlobalVarsHelper.getAccountCreatePageUrl();

    }

    @Given("I navigate to the Registration page")
    public void i_navigate_to_the_registration_page() throws Throwable {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        topMenuPage = launchApplication();
        topMenuPage.clickOnLoginOrRegisterLink();
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.clickOnContinueButtonInLoginPage();
    }

    @Given("I navigate to registration page")
    public void iNavigateToRegistrationPage() throws Throwable {
        topMenuPage = launchApplication();
        loginPage = topMenuPage.clickOnLoginOrRegisterLink();
        loginPage.clickOnContinueButtonInLoginPage();

    }

    @Then("I create an account")
    public void iCreateAnAccount() throws Throwable {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterFirstName(RegisterTestData.FIRST_NAME);
        register.enterLastName(RegisterTestData.LAST_NAME);
        register.enterEmailAddress();
        register.enterTelephoneNumber(RegisterTestData.TELEPHONE);
        register.enterFaxNumber(RegisterTestData.FAX);
        register.enterCompanyName(RegisterTestData.COMPANY);
        register.enterAddress1(RegisterTestData.ADDRESS1);
        register.enterAddress2(RegisterTestData.ADDRESS2);
        register.enterCity(RegisterTestData.CITY);
        register.selectRegionOrState(RegisterTestData.REGION_STATE);
        register.enterPostalOrZipCode(RegisterTestData.ZIP_CODE);
        //register.selectCountry(RegisterTestData.COUNTRY);
        register.enterLoginName();
        register.enterPassword(RegisterTestData.PASSWORD);
        register.enterConfirmPassword(RegisterTestData.PASSWORD);
        register.tickOnSubscribeAsYes();
        register.checkOnIAgreeToPrivacyPolicyRadioButton();
        register.clickContinueButtonInRegisterPage();
    }

    @Then("^I navigate to registration page and create an account$")
    public void iNavigateToRegistrationPageAndCreateAnAccount() throws Throwable {
        RegisterSteps registerSteps = new RegisterSteps();
        registerSteps.iNavigateToRegistrationPage();
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterFirstName(RegisterTestData.FIRST_NAME);
        register.enterLastName(RegisterTestData.LAST_NAME);
        register.enterEmailAddress();
        register.enterTelephoneNumber(RegisterTestData.TELEPHONE);
        register.enterFaxNumber(RegisterTestData.FAX);
        register.enterCompanyName(RegisterTestData.COMPANY);
        register.enterAddress1(RegisterTestData.ADDRESS1);
        register.enterAddress2(RegisterTestData.ADDRESS2);
        register.enterCity(RegisterTestData.CITY);
        register.selectRegionOrState(RegisterTestData.REGION_STATE);
        register.enterPostalOrZipCode(RegisterTestData.ZIP_CODE);
        register.selectCountry(RegisterTestData.COUNTRY);
        register.enterLoginName();
        register.enterPassword(RegisterTestData.PASSWORD);
        register.enterConfirmPassword(RegisterTestData.PASSWORD);
        register.tickOnSubscribeAsYes();
        register.checkOnIAgreeToPrivacyPolicyRadioButton();
        register.clickContinueButtonInRegisterPage();
    }


    @When("I should see {string}")
    public void i_should_see(String alreadyRegisteredText) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        AssertionHelper.updateTestStatus(register.getIfYouAlreadyHaveAnAccountWithUs().contains(alreadyRegisteredText));

    }

    @When("I can see input sections as follows:")
    public void i_can_see_input_sections_as_follows(DataTable dataTable) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        AssertionHelper.updateTestStatus(register.getYourPersonalDetailsTxt().contains(dataTable.cell(0, 0)));
        AssertionHelper.updateTestStatus(register.getYourAddressText().contains(dataTable.cell(1, 0)));
        AssertionHelper.updateTestStatus(register.getLoginDetailsSectionTxt().contains(dataTable.cell(2, 0)));
        AssertionHelper.updateTestStatus(register.getNewsletterTxt().contains(dataTable.cell(3, 0)));

    }

    @When("I can see labels {int} first label {string} and last {string}")
    public void i_can_see_labels_first_label_and_last(Integer labelCount, String firstLabel, String lastLabel) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        AssertionHelper.updateTestStatus(register.assertLabelCountAndSizeAndPositions(labelCount, firstLabel, lastLabel));
    }

    @When("I input firstName {string}")
    public void i_input_first_name(String firstName) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterFirstName(firstName);

    }

    @When("I input lastName {string}")
    public void i_input_last_name(String lastName) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterLastName(lastName);

    }

    @When("I input unique emailAddress")
    public void i_input_unique_email_address() {
        this.register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterEmailAddress();
    }

    @When("I enter unique email address and login name")
    public void i_enter_unique_email_address_login_name() {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterEmailAddress();
        register.inputUniqueLoginName();
    }

    @When("I input telephone {string}")
    public void i_input_telephone(String telephone) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterTelephoneNumber(telephone);

    }

    @When("I input fax {string}")
    public void i_input_fax(String fax) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterFaxNumber(fax);

    }

    @When("I input company {string}")
    public void i_input_company(String coyName) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterCompanyName(coyName);

    }

    @When("I input Address 1 {string}")
    public void i_input_address_one(String address1) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterAddress1(address1);

    }

    @When("I input Address 2 {string}")
    public void i_input_address_two(String address2) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterAddress2(address2);

    }

    @When("I input city {string}")
    public void i_input_city(String city) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterCity(city);

    }

    @When("I select Region or State {string}")
    public void i_select_region_or_province(String regionOrProvince) throws InterruptedException {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.selectRegionOrState(regionOrProvince);

    }

    @When("I input zipCode or PostCode {string}")
    public void i_input_zip_code_or_post_code(String postcode) {
        register.enterPostalOrZipCode(postcode);

    }

    @When("I select Country {string}")
    public void i_select_country_from_the_dropdown(String country) throws InterruptedException {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.selectCountry(country);

    }

    @When("I input unique login name")
    public void i_input_unique_login_name() {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.inputUniqueLoginName();

    }

    @When("I input unique static login name")
    public void i_input_unique_static_login_name() {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterLoginName();

    }

    @When("I input password {string}")
    public void i_input_password(String password) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterPassword(password);

    }

    @When("I input password confirm {string}")
    public void i_input_confirm_password(String comPassword) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterConfirmPassword(comPassword);
    }


    @When("I tick on subscription option {string}")
    public void i_tick_on_subscription_option(String yesOptionText) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        Assert.assertTrue(register.getSubscribeYesOptionText().contains(yesOptionText));
        register.tickOnSubscribeAsYes();
    }

    @When("I tick on subscription option Yes")
    public void i_tick_on_subscription_option_yes() {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.tickOnSubscribeAsYes();

    }

    @When("I can see Subscribe Yes is checked")
    public void i_can_see_subscribe_is_checked() {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        AssertionHelper.updateTestStatus(register.assertSubscribeYesIsSelected());

    }

    @When("I can see Subscribe {string} is checked")
    public void i_can_see_subscribe_Yes_is_checked(String yesOptionText) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        Assert.assertTrue(register.getSubscribeYesOptionText().contains(yesOptionText));
        AssertionHelper.updateTestStatus(register.assertSubscribeYesIsSelected());

    }

    @When("I tick on Privacy Policy radio button")
    public void i_tick_on_radio_button() {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.checkOnIAgreeToPrivacyPolicyRadioButton();

    }

    @When("I tick on {string} radio button")
    public void i_tick_on_privacy_policy_radio_button(String privacyPolicyText) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        Assert.assertEquals(register.getPrivacyPolicyText(), privacyPolicyText);
        register.checkOnIAgreeToPrivacyPolicyRadioButton();
    }

    @When("I can see {string} is ticked")
    public void i_can_see_privacy_policy_is_ticked(String privacyPolicyText) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        AssertionHelper.updateTestStatus(register.assertPrivacyPolicyIsSelected());
        Assert.assertEquals(register.getPrivacyPolicyText(), privacyPolicyText);

    }

    @When("I tap on {string} button in register page")
    public void i_tap_on_button_in_register_page(String continueButtonText) throws IOException {
        if (register.getContinueButtonTxt().contains(continueButtonText)) {
            register.clickContinueButtonInRegisterPage();
        }
    }

    @When("I tap on {string} button")
    public void i_tap_on_button(String continueButtonText) throws IOException {
        if (register.getContinueButtonTxt().contains(continueButtonText)) {
            register.clickContinueButton();
        }
    }

    @When("I click the continue button")
    public void i_click_the_continue_button() throws IOException {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.clickContinueButtonInRegisterPage();
    }

    @When("I click continue button")
    public void i_click_continue_button() throws IOException {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.navigateToAccountSuccessPage();
    }


    @When("I enter the following details in input fields")
    public void i_input_following_details_in_input_fields(List<RegistrationModel> registrationModelList) throws InterruptedException {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        this.register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.createRegistrationModel(registrationModelList.stream().findFirst().get());

    }

    @When("I input the following details in registration input fields")
    public void i_input_following_details_in_registration_input_fields(DataTable dataTable) throws InterruptedException {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        this.register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterFirstName(map.get("First Name"));
        register.enterLastName(map.get("Last Name"));
        register.enterTelephoneNumber(map.get("Telephone"));
        register.enterFaxNumber(map.get("Fax"));
        register.enterCompanyName("Company");
        register.enterAddress1(map.get("Address 1"));
        register.enterAddress2(map.get("Address 2"));
        register.enterCity(map.get("City"));
        register.selectRegionOrState(map.get("Region / State"));
        register.enterPostalOrZipCode(map.get("ZIP Code"));
        register.enterPassword(map.get("Password"));
        register.enterConfirmPassword(map.get("Password Confirm"));


    }

    @And("I enter first and last name respectively in registration page")
    public void iEnterFirstAndLastNameRespectivelyInRegistrationPage(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterFirstName(map.get("First Name"));
        register.enterLastName(map.get("Last Name"));
    }

    @And("I input valid telephone and fax numbers")
    public void iInputValidTelephoneAndFaxNumbers(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterTelephoneNumber(map.get("Telephone"));
        register.enterFaxNumber(map.get("Fax"));
    }

    @And("I enter a valid credentials in your address section in registration page")
    public void iEnterAValidCredentialsInYourAddressSectionInRegistrationPage(DataTable dataTable) throws InterruptedException {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterCompanyName("Company");
        register.enterAddress1(map.get("Address 1"));
        register.enterAddress2(map.get("Address 2"));
        register.enterCity(map.get("City"));
        register.selectRegionOrState(map.get("Region / State"));
        register.enterPostalOrZipCode(map.get("ZIP Code"));
    }

    @And("I input valid password and confirm password")
    public void iInputValidPasswordAndConfirmPassword(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterPassword(map.get("Password"));
        register.enterConfirmPassword(map.get("Password Confirm"));
    }

    @When("I enter all input fields with valid credentials")
    public void i_input_following_details_with_valid_credentials(List<RegistrationModel> registrationModelList) throws InterruptedException {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        this.register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.createRegistrationModel(registrationModelList.stream().findFirst().get());

    }

    @When("I input Your Personal sections with the following details:")
    public void i_input_your_personal_sections_with_the_following_details(DataTable dataTable) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterFirstName(dataTable.cell(1, 0));
        register.enterLastName(dataTable.cell(1, 1));
        register.enterEmailAddress();
        register.enterTelephoneNumber(dataTable.cell(1, 2));
        register.enterFaxNumber(dataTable.cell(1, 3));

    }

    @When("I input Your Address section with the following details:")
    public void i_input_your_address_section_with_the_following_details(DataTable dataTable) throws InterruptedException {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterCompanyName(dataTable.cell(1, 0));
        register.enterAddress1(dataTable.cell(1, 1));
        register.enterAddress2(dataTable.cell(1, 2));
        register.enterCity(dataTable.cell(1, 3));
        register.selectRegionOrState(dataTable.cell(1, 4));
        register.enterPostalOrZipCode(dataTable.cell(1, 5));
        register.selectCountry(dataTable.cell(1, 6));

    }

    @When("I input Login Details section with the following details:")
    public void i_input_login_details_section_with_the_following_details(DataTable dataTable) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.inputUniqueLoginName();
        register.enterPassword(dataTable.cell(1, 0));
        register.enterConfirmPassword(dataTable.cell(1, 1));
        register.tickOnSubscribeAsYes();

    }

    @Then("I should see red warning error messages:")
    public void i_should_see_red_warning_error_messages(DataTable dataTable) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        AssertionHelper.updateTestStatus(register.getAgreeToPrivacyPolicyErrorWarning().contains(dataTable.cell(0, 0)));

    }

    @When("I can see error messages summary is displayed")
    public void i_can_see_error_messages_summary_is_displayed() {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        AssertionHelper.updateTestStatus(register.assertAllAlertErrorDangerSummaryText());

    }

    @Then("I should see red error warning messages:")
    public void i_should_see_red_error_warning_messages(DataTable dataTable) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        AssertionHelper.updateTestStatus(register.getLoginNameErrorWarning().contains(dataTable.cell(0, 0)));
        AssertionHelper.updateTestStatus(register.getFirstNameErrorWarning().contains(dataTable.cell(1, 0)));
        AssertionHelper.updateTestStatus(register.getLastNameErrorWarning().contains(dataTable.cell(2, 0)));
        AssertionHelper.updateTestStatus(register.getEmailErrorWarning().contains(dataTable.cell(3, 0)));
        AssertionHelper.updateTestStatus(register.getAddress1ErrorWarning().contains(dataTable.cell(4, 0)));
        AssertionHelper.updateTestStatus(register.getCityErrorWarning().contains(dataTable.cell(5, 0)));
        AssertionHelper.updateTestStatus(register.getZipCodeErrorWarning().contains(dataTable.cell(6, 0)));
        AssertionHelper.updateTestStatus(register.getStateProvinceErrorWarning().contains(dataTable.cell(7, 0)));
        AssertionHelper.updateTestStatus(register.getPasswordErrorWarning().contains(dataTable.cell(8, 0)));
    }

    @When("enter Your Personal Details section with:")
    public void enter_your_personal_details_section_with(DataTable dataTable) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterFirstName(dataTable.cell(1, 0));
        register.enterLastName(dataTable.cell(1, 1));
        register.enterTelephoneNumber(dataTable.cell(1, 2));
        register.enterFaxNumber(dataTable.cell(1, 3));
    }

    @When("enter Your Address Details section with:")
    public void enter_your_address_details_section_with(DataTable dataTable) throws InterruptedException {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterCompanyName(dataTable.cell(1, 0));
        register.enterAddress1(dataTable.cell(1, 1));
        register.enterAddress2(dataTable.cell(1, 2));
        register.enterCity(dataTable.cell(1, 3));
        register.selectRegionOrState(dataTable.cell(1, 4));
        register.enterPostalOrZipCode(dataTable.cell(1, 5));
        register.selectCountry(dataTable.cell(1, 6));

    }

    @When("enter Your Login Details section with:")
    public void enter_your_login_details_section_with(DataTable dataTable) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register.enterPassword(dataTable.cell(1, 0));
        register.enterConfirmPassword(dataTable.cell(1, 1));
    }

    @And("I tap on logoff button")
    public void iTapOnLogoffButton() {
        accountPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AccountPage.class);
        logoutPage = accountPage.clickOnLogoffButton();
    }

    @And("click continues button")
    public void clickContinuesButton() {
        logoutPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LogoutPage.class);
        homePage = logoutPage.clickOnLogoutContinueButton();
    }

    @And("I tap on continue")
    public void iTapOnContinueButton() {
        logoutPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LogoutPage.class);
        homePage = logoutPage.clickOnLogoutContinueButton();
    }


    @Given("I navigate to the account registration page")
    public void iNavigateToTheAccountRegistrationPage() throws InterruptedException {
        if (WebDrv.getInstance().getWebDriver() == null) {
            page = PageFactory.initElements(WebDrv.getInstance().openBrowser("", null), Page.class);
            page.navigateTo(GlobalVarsHelper.getHomePageUrl());

        } else {
            WebDrv.getInstance().getWebDriver().manage().deleteAllCookies();
            page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
            page.navigateTo(GlobalVarsHelper.getHomePageUrl());
        }

        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        loginPage = topMenuPage.clickOnLoginOrRegisterLink();
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        register = loginPage.clickOnContinueButtonInLoginPage();
    }

    @Given("I am in registration page")
    public void iAmInRegistrationPage() {
        if (WebDrv.getInstance().getWebDriver() == null) {
            page = PageFactory.initElements(WebDrv.getInstance().openBrowser("", null), Page.class);
            page.navigateTo(GlobalVarsHelper.getAccountCreatePageUrl());

        } else {
            WebDrv.getInstance().getWebDriver().manage().deleteAllCookies();
            page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
            page.navigateTo(GlobalVarsHelper.getAccountCreatePageUrl());
        }
    }

    @When("I enter the following details")
    public void i_enter_the_following_details(DataTable dataTable) {
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        List<List<String>> cells = dataTable.cells();
        register.enterFirstName(cells.get(1).get(0));
        register.enterLastName(cells.get(1).get(1));
        register.enterEmailAddress();
        register.enterTelephoneNumber(cells.get(1).get(2));
        register.enterFaxNumber(cells.get(1).get(3));
        register.enterCompanyName(cells.get(1).get(4));
        register.enterAddress1(cells.get(1).get(5));
        register.enterAddress2(cells.get(1).get(6));
        register.enterCity(cells.get(1).get(7));
        register.selectProvinceOrState(cells.get(1).get(8));
        register.enterPostalOrZipCode(cells.get(1).get(9));
        register.enterLoginName();
        register.enterPassword(cells.get(1).get(10));
        register.enterConfirmPassword(cells.get(1).get(11));
    }

}
