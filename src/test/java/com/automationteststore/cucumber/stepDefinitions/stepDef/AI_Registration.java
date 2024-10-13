package com.automationteststore.cucumber.stepDefinitions.stepDef;

import com.automationteststore.pages.*;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class AI_Registration {
    Page page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
    RegistrationPage register = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);

    AccountPage accountPage;
    LogoutPage logoutPage;
    HomePage homePage;

    WebDriver driver;

    @Given("the user is on the homepage {string}")
    public void the_user_is_on_the_homepage(String url) {
        // Initialize WebDriver and navigate to the URL
        driver = new ChromeDriver();
        WebDrv.getInstance().getWebDriver().get(url);
    }

    @When("the user clicks on the {string} drop-down menu")
    public void the_user_clicks_on_the_drop_down_menu(String menuName) {
        // Find and click the specified drop-down menu
        // ... (Use appropriate locators and Selenium methods)
    }

    @When("the user clicks on the {string} option")
    public void the_user_clicks_on_the_option(String optionName) {
        // Find and click the specified option
        // ... (Use appropriate locators and Selenium methods)
    }

    @When("the user enters new account details into the mandatory fields")
    public void the_user_enters_new_account_details_into_the_mandatory_fields() {
        // Enter data into mandatory fields (First Name, Last Name, Email, etc.)
        // ... (Use appropriate locators and Selenium methods)
    }

    @When("the user enters new account details into all the fields")
    public void the_user_enters_new_account_details_into_all_the_fields() {
        // Enter data into all fields (including optional ones)
        // ... (Use appropriate locators and Selenium methods)
    }

    @When("I fill in the personal details")
    public void fillPersonalDetails() {
        // ... (Fill other personal details using the Page Object)
    }

    @When("I fill in the address details")
    public void fillAddressDetails() {
        // ... (Fill other address details)
    }

    @When("I fill in the login details")
    public void fillLoginDetails() {
        // ... (Fill other address details)
    }

    @When("I tick Newsletter radio button")
    public void tickNewsletterRadioButton() {
        // ... (Fill other address details)
    }

    @When("I submit the form")
    public void submitForm() {
        // Submit the form
        // ... (Use appropriate locators and Selenium methods)
    }

    @Given("I on the homepage {string}")
    public void iOnTheHomepage(String arg0) {
    }

    @When("I click on the {string} drop-down menu")
    public void iClickOnTheDropDownMenu(String arg0) {
    }

    @And("I click on the {string} option")
    public void iClickOnTheOption(String arg0) {
    }

    @And("I enter new account details into the mandatory fields")
    public void iEnterNewAccountDetailsIntoTheMandatoryFields() {
    }

    @And("I click on the {string} button")
    public void iClickOnTheButton(String arg0) {
    }

    @Then("I should be logged in and taken to the {string} page")
    public void iShouldBeLoggedInAndTakenToThePage(String arg0) {
    }

    @And("a confirmation email should be sent to the registered email address")
    public void aConfirmationEmailShouldBeSentToTheRegisteredEmailAddress() {
    }

    @Then("a confirmation email for registering the account should be sent")
    public void aConfirmationEmailForRegisteringTheAccountShouldBeSent() {
    }

    @And("the email subject, body, and from address should be verified")
    public void theEmailSubjectBodyAndFromAddressShouldBeVerified() {
    }

    @And("there should be a link to the login page in the email body")
    public void thereShouldBeALinkToTheLoginPageInTheEmailBody() {
    }

    @When("I click on the login page link from the email")
    public void iClickOnTheLoginPageLinkFromTheEmail() {
    }

    @Then("I should be taken to the login page")
    public void iShouldBeTakenToTheLoginPage() {
    }

    @And("I enter new account details into all the fields")
    public void iEnterNewAccountDetailsIntoAllTheFields() {
    }

    @And("I does not enter anything into the fields")
    public void iDoesNotEnterAnythingIntoTheFields() {
    }

    @Then("warning messages should be displayed for the respective mandatory fields")
    public void warningMessagesShouldBeDisplayedForTheRespectiveMandatoryFields() {
    }

    @And("I click on the {string} radio option for the newsletter")
    public void iClickOnTheRadioOptionForTheNewsletter(String arg0) {
    }

    @Then("the {string} option should be displayed as selected by default")
    public void theOptionShouldBeDisplayedAsSelectedByDefault(String arg0) {
    }

    @And("I click on the {string} button inside the {string} box")
    public void iClickOnTheButtonInsideTheBox(String arg0, String arg1) {
    }

    @And("I click on the {string} option from the right column options")
    public void iClickOnTheOptionFromTheRightColumnOptions(String arg0) {
    }


    // ... (Add more step definitions for other actions and assertions)
}

/*    *//*Example of how to use the AI_Registration class in a Cucumber test*//*

        // Locate the main container
        WebElement mainContainer = WebDrv.getInstance().getWebDriver().findElement(By.xpath("//div[@class='col-md-12 col-xs-12 mt20']"));

        // Verify the main heading
        WebElement mainHeading = mainContainer.findElement(By.className("heading1"));
        assertEquals("Create Account", mainHeading.findElement(By.className("maintext")).getText().trim());

// Verify the "login page" link
        WebElement loginPageLink = mainContainer.findElement(By.linkText("login page"));
        assertTrue(loginPageLink.isDisplayed());
        assertEquals("https://automationteststore.com/index.php?rt=account/login", loginPageLink.getAttribute("href"));

// "Your Personal Details" section
        WebElement personalDetailsSection = mainContainer.findElement(By.xpath("//h4[text()='Your Personal Details']/following-sibling::div[@class='registerbox form-horizontal']"));

// Input personal details
        WebElement firstNameInput = personalDetailsSection.findElement(By.id("AccountFrm_firstname"));
        firstNameInput.sendKeys("John");

        WebElement lastNameInput = personalDetailsSection.findElement(By.id("AccountFrm_lastname"));
        lastNameInput.sendKeys("Doe");

        WebElement emailInput = personalDetailsSection.findElement(By.id("AccountFrm_email"));
        emailInput.sendKeys("john.doe@example.com");

        WebElement telephoneInput = personalDetailsSection.findElement(By.id("AccountFrm_telephone"));
        telephoneInput.sendKeys("1234567890");

        WebElement faxInput = personalDetailsSection.findElement(By.id("AccountFrm_fax"));
        faxInput.sendKeys("0987654321");

// "Your Address" section
        WebElement addressSection = mainContainer.findElement(By.xpath("//h4[text()='Your Address']/following-sibling::div[@class='registerbox form-horizontal ']"));

// Input address details
        WebElement companyInput = addressSection.findElement(By.id("AccountFrm_company"));
        companyInput.sendKeys("Example Company");

        WebElement address1Input = addressSection.findElement(By.id("AccountFrm_address_1"));
        address1Input.sendKeys("123 Main Street");

        WebElement address2Input = addressSection.findElement(By.id("AccountFrm_address_2"));
        address2Input.sendKeys("Apt 4B");

        WebElement cityInput = addressSection.findElement(By.id("AccountFrm_city"));
        cityInput.sendKeys("Anytown");

// Select Region/State (assuming it's a dropdown)
        Select stateSelect = new Select(addressSection.findElement(By.id("AccountFrm_zone_id")));
        stateSelect.selectByVisibleText("Your State"); // Replace "Your State" with the actual state

        WebElement zipCodeInput = addressSection.findElement(By.id("AccountFrm_postcode"));
        zipCodeInput.sendKeys("12345");

// Select Country (assuming it's a dropdown)
        Select countrySelect = new Select(addressSection.findElement(By.id("AccountFrm_country_id")));
        countrySelect.selectByVisibleText("United States"); // Replace if needed

// "Login Details" section
        WebElement loginDetailsSection = mainContainer.findElement(By.xpath("//h4[text()='Login Details']/following-sibling::div[@class='registerbox form-horizontal']"));

// Input login details
        WebElement loginNameInput = loginDetailsSection.findElement(By.id("AccountFrm_loginname"));
        loginNameInput.sendKeys("johndoe123");

        WebElement passwordInput = loginDetailsSection.findElement(By.id("AccountFrm_password"));
        passwordInput.sendKeys("securePassword");

        WebElement confirmPasswordInput = loginDetailsSection.findElement(By.id("AccountFrm_confirm"));
        confirmPasswordInput.sendKeys("securePassword");

// "Newsletter" section
        WebElement newsletterSection = mainContainer.findElement(By.xpath("//h4[text()='Newsletter']/following-sibling::div[@class='registerbox form-horizontal']"));

// Select newsletter preference (Yes or No)
// ... (Locate and click the appropriate radio button)

// Agree to Privacy Policy
        WebElement privacyPolicyCheckbox = mainContainer.findElement(By.id("AccountFrm_agree"));
        privacyPolicyCheckbox.click();

// Click the final Continue button
        WebElement finalContinueButton = mainContainer.findElement(By.xpath("//button[contains(text(), 'Continue') and @type='submit']"));
        finalContinueButton.click();

   */