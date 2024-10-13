package com.automationteststore.cucumber.stepDefinitions.stepDef;

import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.alert.AlertHelper;
import com.automationteststore.pages.*;
import com.automationteststore.webdriverutilities.WebDrv;
import com.github.javafaker.Faker;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.Properties;

public class AI_Login {
    AccountSuccessPage accountSuccessPage;
    CheckoutCartPage checkoutCartPage;
    AsideWidgetPage asideWidgetPage;
    AccountPage accountPage;
    RegistrationPage register;
    AccountSuccessPage success;
    CheckoutSuccessPage checkoutSuccessPage;
    LogoutPage logoutPage;
    HomePage homePage;
    AlertHelper alertHelper;
    Faker faker;
    Page page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
    TopMenuPage topMenuPage;
    LoginPage accountLoginPage;
    private String loginname;
    private String password;
    private LoginPage loginPage;
    private String uRL;

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
/*    @DataTableType
    public LoginModel convertLoginModel(Map<String, String> entry) {
        return LoginModel.createLoginModel(entry);

    }*/

    @Given("I on the ecommerce homepage")
    public void userIsOnHomepage() {
        // Code to navigate to the homepage (https://automationteststore.com)
    }

    @When("I logs in with valid credentials")
    public void userLogsInWithValidCredentials() {
        // Code to perform login with valid credentials
    }

    @When("I click on {string} drop menu")
    public void clickMyAccountDropmenu() {
        // Code to click on 'My Account' Dropmenu
    }

    @And("I click on {string} option")
    public void clickLoginOption() {
        // Code to click on 'Login' option
    }

    @And("I enter valid email address {string}")
    public void enterValidEmail(String email) {
        // Code to enter email in the 'E-Mail Address' field
    }

    @And("I enter valid password {string}")
    public void enterValidPassword(String password) {
        // Code to enter password in the 'Password' field
    }

    @And("I click on {string} button")
    public void clickLoginButton() {
        // Code to click on 'Login' button
    }

    @Then("I should be taken to the {string} page")
    public void verifyAccountPage() {
        // Code to verify that I is on the 'Account' page
    }

    @And("I enter invalid email address {string}")
    public void enterInvalidEmail(String email) {
        // Code to enter invalid email
    }

    @And("I enter invalid password {string}")
    public void enterInvalidPassword(String password) {
        // Code to enter invalid password
    }

    @Then("a warning message should be displayed with the text {string}")
    public void verifyWarningMessage() {
        // Code to verify the warning message
    }

// ... (Similarly define step definitions for other scenarios)

}

/*


// Locate the main container
        WebElement mainContainer = WebDrv.getInstance().getWebDriver().findElement(By.xpath("//div[@class='col-md-12 col-xs-12 mt20']"));

// Verify the main heading
        WebElement mainHeading = mainContainer.findElement(By.className("heading1"));
        Assert.assertEquals("Account Login", mainHeading.findElement(By.className("maintext")).getText().trim());

// "New Customer" section verification and actions
        WebElement newCustomerSection = mainContainer.findElement(By.className("newcustomer"));
        WebElement newCustomerHeading = newCustomerSection.findElement(By.className("heading2"));
        Assert.assertEquals("I am a new customer.", newCustomerHeading.getText());

// Verify the radio button is selected by default
        WebElement registerRadioButton = WebDrv.getInstance().getWebDriver().findElement(By.id("accountFrm_accountregister"));
        Assert.assertTrue(registerRadioButton.isSelected());

// Click the Continue button
        WebElement continueButton = newCustomerSection.findElement(By.xpath("//button[contains(text(), 'Continue')]"));
        continueButton.click();

// "Returning Customer" section verification and actions
        WebElement returningCustomerSection = mainContainer.findElement(By.className("returncustomer"));
        WebElement returningCustomerHeading = returningCustomerSection.findElement(By.className("heading2"));
        Assert.assertEquals("Returning Customer", returningCustomerHeading.getText());

        WebElement loginNameInput = returningCustomerSection.findElement(By.id("loginFrm_loginname"));
        WebElement passwordInput = returningCustomerSection.findElement(By.id("loginFrm_password"));

// Enter login credentials
        loginNameInput.sendKeys("your_username");
        passwordInput.sendKeys("your_password");

// Click the Login button
        WebElement loginButton = returningCustomerSection.findElement(By.xpath("//button[@type='submit' and contains(text(), 'Login')]"));
        loginButton.click();

// Add verification for successful login or error messages as needed
// ...
*/
