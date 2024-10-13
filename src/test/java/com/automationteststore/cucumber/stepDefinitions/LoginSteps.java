package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.base.BaseTest;
import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.assertion.AssertionHelper;
import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.model.LoginModel;
import com.automationteststore.pages.*;
import com.automationteststore.testData.admin.AdminTestData;
import com.automationteststore.testData.javaFiles.GlobalVars;
import com.automationteststore.testData.login.LoginPageTestData;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
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
import java.util.Properties;


public class LoginSteps extends BaseTest {
    Page page;
    AccountPage accountPage;
    RegistrationPage register;
    TopMenuPage topMenuPage;
    LoginPage loginPage;
    String loginname;
    String password;
    String uRL;

    @Before("@tagLogin")
    public void testEmployerStart() throws Throwable {
        Properties properties = loadProperties();
        GlobalVarsHelper.getInstance().setURL(properties.getProperty("uitesting.url"));
        GlobalVarsHelper.getInstance().setCandidateURL(properties.getProperty("candidateuitesting.url"));
    }

    private Properties loadProperties() throws IOException {
        java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("my.properties");
        java.util.Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

    @DataTableType
    public LoginModel convertLoginModel(Map<String, String> entry) {
        return LoginModel.createLoginModel(entry);

    }

    @When("I should see in the login page as follows:")
    public void i_should_see_in_page_in_the_login_page(DataTable dataTable) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        AssertionHelper.updateTestStatus(page.getThisPageUrl().contains(dataTable.cell(1, 0)));
        AssertionHelper.updateTestStatus(page.getThisPageTitle().contains(dataTable.cell(1, 1)));
        AssertionHelper.updateTestStatus(page.getThisPageHeaderText().contains(dataTable.cell(1, 2)));
    }

    @When("I can see {string} and {string}")
    public void i_can_see_and(String newCustomerText, String returnCustomerText) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        Assert.assertTrue(loginPage.getNewCustomerTxt().equalsIgnoreCase(newCustomerText));
        Assert.assertTrue(loginPage.getReturningCustomerTxt().equalsIgnoreCase(returnCustomerText));

    }

    @When("I can see {string} is checked by default")
    public void i_can_see_is_checked_by_default(String accountRegisterText) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        AssertionHelper.updateTestStatus(loginPage.getRegisterAccountRadioButtonTxt().contains(accountRegisterText));
        AssertionHelper.updateTestStatus(loginPage.assertRegisterAccountRadioButtonChecked());

    }

    @When("can see {string} and {string}")
    public void can_see_and(String forgetPasswordLink, String forgetYourLoginLink) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        AssertionHelper.updateTestStatus(loginPage.getForgotYourPasswordLinkTxt().contains(forgetPasswordLink));
        AssertionHelper.updateTestStatus(loginPage.getForgotYourLoginLinkTxt().contains(forgetYourLoginLink));
    }

    @Given("I login application with loginname {string} and password {string}")
    public void i_login_application_with_loginname_and_password(String loginname, String password) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.enterLoginName(loginname);
        loginPage.enterPassword(password);
        accountPage = loginPage.clickLoginButtonInAccountLoginPage();
    }

    @Given("I enter invalid loginname {string} and password {string}")
    public void i_enter_invalid_loginname_and_password(String loginname, String password) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.enterLoginName(loginname);
        loginPage.enterPassword(password);

    }


    @And("I enter the newly created login name")
    public void i_enter_newly_created_login_name() {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.enterStaticLoginName();
    }

    @When("I enter newly created password {string}")
    public void i_enter_newly_created_password(String password) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.enterPassword(password);

    }



    @When("I click on login button in login page")
    public void i_click_on_login_button_in_login_page() {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        accountPage = loginPage.clickLoginButtonInAccountLoginPage();

    }

    @When("I click on {string} button in login page")
    public void i_click_on_loginButton_in_login_page(String loginButton) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        Assert.assertEquals(loginPage.getLoginButtonText(), loginButton);
        accountPage = loginPage.clickLoginButtonInAccountLoginPage();
    }

    @When("I click on login button")
    public void i_click_on_login_button() {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        accountPage = loginPage.clickLoginButtonInAccountLoginPage();
    }
    @When("I click on login")
    public void i_click_on_login() {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        accountPage = loginPage.clickLoginButtonInAccountLoginPage();
    }


    @When("I click on continue button")
    public void i_click_on_continue_button_in_login_page() {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register = loginPage.clickOnContinueButtonInLoginPage();
    }

    @When("I click on continue")
    public void i_click_on_continue_button_in_loginpage() {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        register = loginPage.clickOnContinueButtonInLoginPage();
    }

    @When("I tap {string} button in login page")
    public void i_tap_continue_button_in_login_page(String continueBtnText) throws InterruptedException {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        Assert.assertEquals(loginPage.getContinueButtonText(), continueBtnText);
        register = loginPage.clickOnContinueButtonInLoginPage();
    }

    @When("I login with the following credentials:")
    public void i_fill_in_the_following_login_credentials(DataTable dataTable) throws IOException {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.enterLoginName(dataTable.cell(1, 0));
        loginPage.enterPassword(dataTable.cell(1, 1));
        accountPage = loginPage.clickOnLoginButtonInAccountLoginPage();
    }

    @When("I login with valid credentials")
    public void i_login_with_valid_login_credentials(List<LoginModel> loginModelList) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.createLoginModel(loginModelList.stream().findFirst().get());
        accountPage = loginPage.clickLoginButtonInAccountLoginPage();
    }

    @When("I login with valid credentials:")
    public void i_login_with_credentials() {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        accountPage = loginPage.clickLoginButtonInAccountLoginPage();
    }

    @When("I login in login page as a returning customer")
    public void i_login_in_login_page_as_a_returning_customer(DataTable dataTable) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.enterLoginName(dataTable.cell(1, 0));
        loginPage.enterPassword(dataTable.cell(1, 1));
        accountPage = loginPage.clickLoginButtonInAccountLoginPage();
    }

    @When("^I enter UserName and Password$")
    public void iEnterUserNameAndPassword(DataTable data) throws Throwable {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        List<List<String>> table = data.cells();
        loginPage.enterLoginName(table.get(1).get(0).toString());
        loginPage.enterLoginName(table.get(1).get(1).toString());
    }

    @Then("^I click login link$")
    public void iClickLoginLink() throws Throwable {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        loginPage = topMenuPage.clickOnLoginOrRegisterLink();
    }

    @And("^I ensure application opened$")
    public void iEnsureApplicationOpened() throws Throwable {
        if (WebDrv.getInstance().getWebDriver() == null) {
            page = PageFactory.initElements(WebDrv.getInstance().openBrowser("", null), Page.class);
            page.navigateTo(GlobalVarsHelper.getHomePageUrl());

        } else {
            WebDrv.getInstance().getWebDriver().manage().deleteAllCookies();
            page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
            page.navigateTo(GlobalVarsHelper.getHomePageUrl());
        }

    }

    @When("I login in login page with valid credentials")
    public void i_login_in_login_page_with_valid_credentials(DataTable dataTable) {
        Map<String, String> map = dataTable.asMap(String.class, String.class);
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.enterLoginName(map.get("Login name"));
        loginPage.enterPassword(map.get("Password"));
    }


    @When("I login in login page")
    public void i_login_in_login_page(DataTable dataTable) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.enterLoginName(dataTable.cell(1, 0));
        loginPage.enterPassword(dataTable.cell(1, 1));

    }

    @When("I can see:")
    public void i_can_see(DataTable dataTable) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        AssertionHelper.updateTestStatus(loginPage.getNewCustomerTxt().contains(dataTable.cell(0, 0)));
        AssertionHelper.updateTestStatus(loginPage.getReturningCustomerTxt().contains(dataTable.cell(1, 0)));

    }

    @And("I should see basePage url and title in the My account page:")
    public void iShouldSeeUrlAndTitleInMyAccountPage(DataTable dataTable) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        AssertionHelper.updateTestStatus(page.getThisPageUrl().contains(dataTable.cell(1, 0)));
        AssertionHelper.updateTestStatus(page.getThisPageTitle().contains(dataTable.cell(1, 1)));
        AssertionHelper.updateTestStatus(page.getThisPageHeaderText().contains(dataTable.cell(1, 2)));

    }

    @Given("^I open the browser at \"([^\"]*)\"$")
    public void iOpenTheBrowserAt(String arg0) {
        uRL = arg0;
        page = PageFactory.initElements(WebDrv.getInstance().openBrowser("", null), Page.class);
        page.navigateTo(getFulUrl());
    }

    @Given("^I navigate to \"([^\"]*)\"$")
    public void iNavigateTo() throws Throwable {
        if (WebDrv.getInstance().getWebDriver() == null) {
            page = PageFactory.initElements(WebDrv.getInstance().openBrowser("", null), Page.class);
            page.navigateTo(getFulUrl());

        } else {
            WebDrv.getInstance().getWebDriver().manage().deleteAllCookies();
            page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
            page.navigateTo(getFulUrl());
        }

    }


    @Given("^I navigate to Account Login page \"([^\"]*)\"$")
    public void iNavigateTologinPage() throws Throwable {

        if (WebDrv.getInstance().getWebDriver() == null) {
            page = PageFactory.initElements(WebDrv.getInstance().openBrowser("", null), Page.class);
            page.navigateTo(getFulUrl());

        } else {
            WebDrv.getInstance().getWebDriver().manage().deleteAllCookies();
            page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
            page.navigateTo(getFulUrl());
        }

    }

    private String getFulUrl() {
        return GlobalVarsHelper.getHomePageUrl();

    }

    private String getHomePageUrl() {
        return GlobalVarsHelper.getHomePageUrl();

    }

    @When("^I enter the username \"([^\"]*)\"$")
    public void iEnterTheLoginName(String arg0) throws Throwable {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginname = arg0;
    }

    @And("I enter the password {string}")
    public void iEnterThePassword(String pass) throws Throwable {
        loginPage.enterPassword(pass);
    }

    @And("^I click on login button$")
    public void iClickOnLoginButton() throws Throwable {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.clickOnLoginButton();
    }

    @And("I tap on login button")
    public void clickOnSigInButton() throws Throwable {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.clickOnLoginButton();
    }

    @Then("^I am logged in$")
    public void iAmLoggedIn() throws Throwable {
        WebDrv.getInstance().getWebDriver().quit();
        // Assert here when code is ready
    }

    @Given("^I navigate to the page \"([^\"]*)\"$")
    public void iNavigateToThePage(String arg0) throws Throwable {
        uRL = arg0;
        loginPage = PageFactory.initElements(WebDrv.getInstance().openBrowser("", null), LoginPage.class);
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        page.navigateTo(GlobalVarsHelper.getInstance().getURL() + uRL);
    }

    @Given("^I open the page \"([^\"]*)\"$")
    public void iOpenThePage(String arg0) {
        uRL = arg0;
        loginPage = PageFactory.initElements(WebDrv.getInstance().openBrowser("", null), LoginPage.class);
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        page.navigateTo(GlobalVarsHelper.getInstance().getURL() + uRL);
    }

    @Then("^I entered a \"([^\"]*)\" in email field$")
    public void enterEmail(String loginname) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.enterLoginname(loginname);
    }

    @Then("^I entered \"([^\"]*)\" in password field$")
    public void enterPassword(String password) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.enterPassword(password);
    }


    @Then("^I navigate to employer login and login$")
    public void loginIntoEmployerPortal() throws Throwable {
        LoginSteps testLoginSteps = new LoginSteps();
        testLoginSteps.iNavigateTo();
        loginname = AdminTestData.EMP_EMAIL;
        password = AdminTestData.EMP_PASSWORD;
        iEnterTheLoginName(AdminTestData.EMP_EMAIL);
        iEnterThePassword(AdminTestData.EMP_PASSWORD);
        iClickOnLoginButton();
    }

    @Then("^I navigate to Account Login page and login as a returning customer$")
    public void iNavigateToLoginIntologinPageAndLoginAsAReturningCustomer() throws Throwable {
        LoginSteps loginSteps = new LoginSteps();
        loginSteps.iNavigateTo();
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        loginPage = topMenuPage.clickOnLoginOrRegisterLink();
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.enterLoginname(LoginPageTestData.LOGIN_NAME);
        loginPage.enterPassword(LoginPageTestData.PASSWORD);
        accountPage = loginPage.ClickLoginButton();
    }

    @Then("^I navigate to Login page and login as a returning customer$")
    public void iNavigateToLoginLoginPageAndLoginAsAReturningCustomer() throws Throwable {
        LoginSteps loginSteps = new LoginSteps();
        loginSteps.iNavigateTo();
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        loginPage = topMenuPage.clickOnLoginOrRegisterLink();
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        accountPage = loginPage.logon(LoginPageTestData.LOGIN_NAME, LoginPageTestData.PASSWORD);
    }

    @And("I login as the specified user")
    @And("^I login as Team Manager for the first time$")
    public void i_login_as_Team_Manager_for_the_first_time(DataTable data) throws Throwable {
        LoginSteps loginSteps = new LoginSteps();
        loginSteps.iNavigateTo();
        List<List<String>> userDetails = data.cells();
        iEnterTheLoginName(userDetails.get(0).get(0));
        iEnterThePassword(userDetails.get(0).get(1));
        iClickOnLoginButton();
    }

    @When("^I navigate to login page and login with invalid credentials$")
    public void i_navigate_to_login_page_and_login_with_invalid_credentials() throws Throwable {
        LoginSteps loginSteps = new LoginSteps();
        loginSteps.iNavigateTo();
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        loginPage = topMenuPage.clickOnLoginOrRegisterLink();
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.loginWithInvalidCredentials("wrong_loginname", "wrong_password");

    }

    @And("I login in login page as a returning customer with invalid login name {string} and invalid {string}")
    public void iLoginInLoginPageAsAReturningCustomerWithInvalidLoginNameAndInvalid(String loginname, String password) throws Throwable {
        LoginSteps loginSteps = new LoginSteps();
        loginSteps.iNavigateTo();
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        loginPage = topMenuPage.clickOnLoginOrRegisterLink();
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.enterLoginname(loginname);
        loginPage.enterPassword(password);
        loginPage.clickLoginButtonInAccountLoginPage();
    }

    @Given("should be presented with the following Error validation message as {string}")
    public void should_be_presented_with_the_following_error_validation_message_as(String errorValueMsg) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        Assert.assertTrue(loginPage.assertErrorMessageIsPresent());

    }

    @And("I am redirected to the login page as {string}")
    public void i_am_redirected_to_the_login_page_as(String accountLoginHeader) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        Assert.assertTrue(loginPage.assertPageIsLoginPage().contains(accountLoginHeader));
    }


    @Given("^I go to \"([^\"]*)\"$")
    public void iGoTo() throws Throwable {
        if (WebDrv.getInstance().getWebDriver() == null) {
            page = PageFactory.initElements(WebDrv.getInstance().openBrowser("", null), Page.class);
            page.navigateTo(getFulUrl());


        } else {
            WebDrv.getInstance().getWebDriver().manage().deleteAllCookies();
            page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
            page.navigateTo(getFulUrl());
        }
    }

    @Given("User is on the Home Page")
    public void user_is_on_the_home_page() {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        page.navigateTo(GlobalVars.getHomePageUrl());
    }

    @When("I navigate to the Login Page")
    public void user_navigate_to_the_login_page() {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        topMenuPage.navigateToLoginPage();
    }

    @When("enter username {string} and password {string}")
    public void user_enters_username_and_password(String loginname, String password) {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.enterLoginName(loginname);
        loginPage.enterPassword(password);
    }

    @When("I click on the Login button")
    public void user_clicks_on_the_login_button() {
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.clickLoginButtonInAccountLoginPage();

    }

    @Then("I should be redirected to the Account Page url contains {string}")
    public void user_should_be_redirected_to_the_account_page_url_contains(String urlPath) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        Assert.assertTrue(page.getThisPageUrl().contains(urlPath), "User is not on the Account Page.");
        Assert.assertTrue(new VerificationHelper(WebDrv.getInstance().getWebDriver()).getCurrentPageUrl().contains(urlPath), "User is not on the Account Page.");

    }


    @Then("I can see profile welcome message {string} should be displayed")
    public void a_success_message_should_be_displayed(String expectedMessage) {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        String actualMessage = topMenuPage.getWelcomeMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Welcome message not displayed as expected.");
    }


}
