package com.automationteststore.pages;

import com.automationteststore.enums.WaitStrategy;
import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.helperutilities.select.DropDownHelper;
import com.automationteststore.model.RegistrationModel;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static com.automationteststore.utilities.Util.getRandomString;

public class RegistrationPage extends Page {
    private final static Logger log = LogManager.getLogger(RegistrationPage.class);
    private final String registrationPageUrl = "https://automationteststore.com/index.php?rt=account/create";

    public static final String login_name = getRandomString();
    public static final String loginname = String.valueOf(System.currentTimeMillis());
    private final By address2Box = By.xpath("//input[@id='AccountFrm_address_2']");

    Page page;
    AccountSuccessPage accountSuccessPage;
    String loginName = System.currentTimeMillis() + "Gen";


    @FindBy(xpath = "//a[contains(text(),'Register')]")
    private WebElement registerLinkText;
    @FindBy(css = ".heading1")
    private WebElement createAccountText;
    @FindBy(xpath = "//body[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/p[1]")
    private WebElement ifYouAlreadyHaveAnAccountWithUs;
    @FindBy(css = "#AccountFrm")
    private WebElement form;
    @FindBy(css = "label.control-label.col-sm-4")
    private List<WebElement> inputLabels;

    @FindBy(xpath = "//h4[contains(text(),'Your Personal Details')]")
    private WebElement yourPersonalDetailsTxt;
    @FindBy(id = "AccountFrm_firstname")
    private WebElement firstNameField;
    @FindBy(id = "AccountFrm_lastname")
    private WebElement lastNameField;
    @FindBy(id = "AccountFrm_email")
    private WebElement emailField;
    @FindBy(id = "AccountFrm_telephone")
    private WebElement telephoneField;
    @FindBy(id = "AccountFrm_fax")
    private WebElement faxField;
    @FindBy(xpath = "//h4[contains(text(),'Your Address')]")
    private WebElement yourAddressText;
    @FindBy(id = "AccountFrm_company")
    private WebElement companyField;
    @FindBy(id = "AccountFrm_address_1")
    private WebElement address1Field;
    @FindBy(id = "AccountFrm_address_2")
    private WebElement address2Field;
    @FindBy(id = "AccountFrm_city")
    private WebElement cityField;
    @FindBy(id = "AccountFrm_zone_id")
    private WebElement stateDropdown;
    @FindBy(id = "AccountFrm_postcode")
    private WebElement postcodeField;
    @FindBy(id = "AccountFrm_postcode")
    private WebElement zipCodeField;
    @FindBy(id = "AccountFrm_country_id")
    private WebElement countryDropdown;
    @FindBy(id = "AccountFrm_country_id")
    private WebElement countryDropdownButton;
    @FindBy(xpath = "//h4[contains(text(),'Login Details')]")
    private WebElement loginDetailsSectionText;
    @FindBy(id = "AccountFrm_loginname")
    private WebElement loginNameField;
    @FindBy(id = "AccountFrm_password")
    private WebElement passwordField;
    @FindBy(id = "AccountFrm_confirm")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "//h4[contains(text(),'Newsletter')]")
    private WebElement newsletterSectionText;
    @FindBy(id = "AccountFrm_newsletter0")
    private WebElement noNewsletterOption;
    @FindBy(id = "AccountFrm_newsletter1")
    private WebElement yesNewsletterOption;
    @FindBy(name = "account[group_accept]")
    private WebElement checkboxPrivacyPolicy;
    @FindBy(name = "account[newsletter]")
    private List<WebElement> newsletterRadioButtons;


    @FindBy(css = "label[for='AccountFrm_newsletter1']")
    private WebElement subscribeYesRadioButtonLabel;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/div[1]/form/div[4]/fieldset/div/div/label")
    private List<WebElement> subscribeRadioButtonLabels;


    @FindBy(css = "[type='radio']")
    private List<WebElement> radioBtns;
    @FindBy(css = "[type='radio']")
    private List<WebElement> radioBtnsLabels;
    @FindBy(xpath = "//label[@for='AccountFrm_newsletter1']")
    private WebElement subscribeYesRadioButtonText;
    @FindBy(css = "#AccountFrm_newsletter0")
    private WebElement subscribeNORadioButton;
    @FindBy(xpath = "//*[@id=\"AccountFrm_agree\"]")
    private WebElement agreeToPrivacyPolicyButton;
    @FindBy(css = "label[class='col-md-6 mt20 mb40'] a b")
    private WebElement agreeToPrivacyPolicyText;
    @FindBy(css = "button[title='Continue']")
    private WebElement continueButton;
    private final By Continue = By.cssSelector("button[title='Continue']");
    @FindBy(css = "#AccountFrm_firstname")
    private WebElement firstNameErrorTxt;
    @FindBy(css = "#AccountFrm_lastname")
    private WebElement lastNameErrorTxt;
    @FindBy(css = "#AccountFrm_email")
    private WebElement emailErrorTxt;
    @FindBy(css = "div.alert.alert-error.alert-danger")
    private WebElement allAlertErrorDangerSummaryList;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/div[1]")
    private WebElement alertErrorDangerItemized;
    @FindBy(css = "#AccountFrm_address_1")
    private WebElement address1ErrorTxt;
    @FindBy(css = "#AccountFrm_city")
    private WebElement cityErrorTxt;
    @FindBy(css = "#AccountFrm_zone_id")
    private WebElement regionStateErrorTxt;
    @FindBy(css = "#AccountFrm_postcode")
    private WebElement zipCodeErrorTxt;
    @FindBy(css = "#AccountFrm_password")
    private WebElement passwordErrorTxt;
    @FindBy(xpath = "//b[contains(text(),'Privacy Policy')]")
    private WebElement privacyPolicyErrorWarning;
    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]")
    private WebElement youMustAgreeToThePrivacyPolicyErrorTxt;
    @FindBy(xpath = "//span[contains(text(),'')]")
    private List<WebElement> allHighlightedWarningRedMessageErrorTxt;
    @FindBy(xpath = "//span[contains(text(),'First Name must be between 1 and 32 characters!')]")
    private WebElement firstNameErrorWarning;
    @FindBy(xpath = "//span[contains(text(),'Last Name must be between 1 and 32 characters!')]")
    private WebElement lastNameErrorWarning;
    @FindBy(xpath = "//span[contains(text(),'Email Address does not appear to be valid!')]")
    private WebElement emailErrorWarning;
    @FindBy(xpath = "//span[contains(text(),'Address 1 must be between 3 and 128 characters!')]")
    private WebElement address1ErrorWarning;
    @FindBy(xpath = "//span[contains(text(),'City must be between 3 and 128 characters!')]")
    private WebElement cityErrorWarning;
    @FindBy(xpath = "//span[contains(text(),'Zip/postal code must be between 3 and 10 characters!')]")
    private WebElement zipCodeErrorWarning;
    @FindBy(xpath = "//span[contains(text(),'Please select a region / state!')]")
    private WebElement StateProvinceErrorWarning;
    @FindBy(xpath = "//span[contains(text(),'Login name must be alphanumeric only and between 5')]")
    private WebElement loginNameErrorWarning;
    @FindBy(xpath = "//span[contains(text(),'Password must be between 4 and 20 characters!')]")
    private WebElement passwordErrorWarning;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div/div/div[1]")
    private WebElement agreeToPrivacyPolicyErrorWarning;
    @FindBy(xpath = "//*[contains(text(),'*')]")
    private List<WebElement> asterisks;
    @FindBy(css = "span.input-group-addon")
    private List<WebElement> mandatoryfields;
    // Get Success Message or Error Messages
    @FindBy(css = ".success")
    private WebElement successMessage;
    @FindBy(css = ".error")
    private List<WebElement> errorMessages;


    public void navigateToRegistrationPage() {
        if (WebDrv.getInstance().getWebDriver() == null) {
            page = PageFactory.initElements(WebDrv.getInstance().openBrowser("", null), Page.class);
            page.navigateTo(GlobalVarsHelper.getAccountCreatePageUrl());

        } else {
            WebDrv.getInstance().getWebDriver().manage().deleteAllCookies();
            page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
            page.navigateTo(GlobalVarsHelper.getAccountCreatePageUrl());
        }
    }

    // Actions for filling out the form
    public void fillRegistrationForm(String fName, String lName, String userEmail, String addr, String userCity,
                                     String userState, String userPostcode, String userCountry,
                                     String userLoginName, String userPassword, String userConfirmPassword) {
        firstNameField.sendKeys(fName);
        lastNameField.sendKeys(lName);
        emailField.sendKeys(userEmail);
        address1Field.sendKeys(addr);
        cityField.sendKeys(userCity);
        stateDropdown.sendKeys(userState);
        postcodeField.sendKeys(userPostcode);
        countryDropdown.sendKeys(userCountry);
        loginNameField.sendKeys(userLoginName);
        passwordField.sendKeys(userPassword);
        confirmPasswordField.sendKeys(userConfirmPassword);
        yesNewsletterOption.click();
        agreeToPrivacyPolicyButton.click();
    }

    public AccountPage clickOnContinue() {
        continueButton.click();
        return new AccountPage();
    }

    public void enterPersonalDetails(String firstName, String lastName, String email, String telephone, String fax) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        telephoneField.sendKeys(telephone);
        faxField.sendKeys(fax);
    }

    public void enterAddressDetails(String company, String address1, String address2, String city, String region, String zip, String country) {
        companyField.sendKeys(company);
        address1Field.sendKeys(address1);
        address1Field.sendKeys(address2);
        cityField.sendKeys(city);
        Select regionDropdown = new Select(stateDropdown);
        regionDropdown.selectByVisibleText(region);
        postcodeField.sendKeys(zip);
        Select select = new Select(countryDropdown);
        select.selectByVisibleText(country);
    }

    public void enterLoginDetails(String loginName, String password) {
        loginNameField.sendKeys(loginName);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);

    }

    public void yesSubscribeToNewsletter() {
        yesNewsletterOption.click();
    }

    public void noSubscribeToNewsletter() {
        noNewsletterOption.click();
    }

    public void agreeToPrivacyPolicy() {
        checkboxPrivacyPolicy.click();
    }

    public void clickContinue() {
        continueButton.click();

    }

    public WebElement getErrorMessage() {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        return page.getErrorMessage();
    }

    public WebElement getContinueButton() {
        return continueButton;

    }

    public void navigateToRegisterPage() {
        WebDrv.getInstance().getWebDriver().get(GlobalVarsHelper.getAccountCreatePageUrl());
    }

    public void clickYesNoRadio(String radioOption) {
        switch (radioOption.toLowerCase()) {
            case "Yes":
                yesNewsletterOption.click();
                break;
            case "No":
                subscribeNORadioButton.click();
                break;
        }
    }

    public String getCreateAccountTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(createAccountText);

    }

    public boolean assertLabelCountAndSizeAndPositions(int labels_Count, String firstLabel, String secondLabel) {
        int size = inputLabels.size();
        System.out.println("Size of labels: " + size);
        for (WebElement inputLabel : inputLabels) {
            String labelsText = inputLabel.getText();
            System.out.println(labelsText);
            if (size == labels_Count || inputLabels.get(0).getText().trim().contains(firstLabel) || inputLabels.get(15).getText().trim().contains(secondLabel)) {
                return true;

            }
        }
        return false;
    }

    public void assertPolicyPrivacyLinkIsDisplayed() {
        Assert.assertTrue(agreeToPrivacyPolicyText.isDisplayed(), "Policy privacy is not displayed");
    }

    public String getIfYouAlreadyHaveAnAccountWithUs() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(ifYouAlreadyHaveAnAccountWithUs);

    }

    public String getYourPersonalDetailsTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(yourPersonalDetailsTxt);

    }

    public void enterFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        log.info("Entered first name : " + firstName);

    }

    public void enterLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        log.info("Entered last name : " + lastName);

    }

    public void enterEmailAddress() {
        String email = System.currentTimeMillis() + "@gmail.com";
        emailField.clear();
        emailField.sendKeys(email);
        log.info("Entered email : " + email);
    }

    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
        log.info("Entered email : " + email);
    }

    public void enterTelephoneNumber(String telephone) {
        telephoneField.clear();
        telephoneField.sendKeys(telephone);
        log.info("Entered telephone : " + telephone);

    }

    public void enterFaxNumber(String fax) {
        faxField.clear();
        faxField.sendKeys(fax);
        log.info("Entered fax : " + fax);

    }

    public void enterYourPersonalDetailsSection(String firstName, String lastName, String telephone, String fax) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmailAddress();
        enterTelephoneNumber(telephone);
        enterFaxNumber(fax);

    }

    public String getYourAddressText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(yourAddressText);

    }

    public void enterCompanyName(String companyName) {
        companyField.clear();
        companyField.sendKeys(companyName);
        log.info("Entered company name : " + companyName);

    }

    public void enterAddress1(String address1) {
        address1Field.clear();
        address1Field.sendKeys(address1);
        log.info("Entered address 1 : " + address1);

    }

    public void enterAddress2(String address2) {
        address2Field.clear();
        address2Field.sendKeys(address2);
        log.info("Entered address 2 : " + address2);
    }

    public RegistrationPage enterAddressTwo(String address2) {
        this.page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        this.page.sendKeys(address2Box, address2, WaitStrategy.VISIBLE);
        log.info("Entered address 2 : " + address2);
        return this;
    }

    public RegistrationPage enterCity(String cityName) {
        cityField.clear();
        cityField.sendKeys(cityName);
        log.info("Entered city : " + cityName);
        return this;
    }

    public void selectRegionOrState(String regionOrStateName) {
        this.page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        this.page.selectFromTheDropdown(e -> e.selectByVisibleText(regionOrStateName), stateDropdown);

    }

    public void selectProvinceOrState(String regionOrStateName) {
        new DropDownHelper(WebDrv.getInstance().getWebDriver()).selectUsingVisibleText(stateDropdown, regionOrStateName);

    }

    public void enterPostalOrZipCode(String zipCode) {
        postcodeField.clear();
        postcodeField.sendKeys(zipCode);
        log.info("Entered zip or postal code : " + zipCode);

    }

    public void selectCountry(String countryName) throws InterruptedException {
        /*new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(3))
                .until((s) -> countryDropdown.isDisplayed());*/
        new DropDownHelper(WebDrv.getInstance().getWebDriver()).selectUsingVisibleText(countryDropdown, countryName);
        log.info("Selected country : " + countryName);
    }

    public void selectCountryName(String countryName) {
        this.page.selectFromTheDropdown((s) -> s.selectByVisibleText(countryName), countryDropdown);
        log.info("Selected country : " + countryName);

    }

    public String getLoginDetailsSectionTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(loginDetailsSectionText);
    }

    public void enterLoginName() {
        loginNameField.clear();
        loginNameField.sendKeys(loginname);
        log.info("Entered login name : " + loginname);

    }

    public void inputLoginName(String loginname) {
        loginNameField.clear();
        loginNameField.sendKeys(loginname);
        log.info("Entered login name : " + loginname);

    }

    public void inputUniqueLoginName() {
        loginNameField.clear();
        loginNameField.sendKeys(login_name);
        log.info("Entered login name : " + login_name);
        System.out.println("Entered Login name:: " + login_name);

    }

    public void inputUniqueStaticLoginName() {
        loginNameField.clear();
        loginNameField.sendKeys(login_name);
        log.info("Entered login name : " + login_name);
        System.out.println("Entered login name:: " + login_name);

    }

    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
        log.info("Entered password : " + password);

    }

    public void enterConfirmPassword(String password) {
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(password);
        log.info("Entered password confirm : " + password);

    }

    public String getNewsletterTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(newsletterSectionText);

    }

    public void tickOnSubscribeAsYes() {
        yesNewsletterOption.click();
        log.info("Ticked on subscribe as yes");

    }

    public void selectYesNewsletterOption() {
        yesNewsletterOption.click();

    }

    public String getSubscribeYesRadioButtonLabel() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(subscribeYesRadioButtonLabel);

    }

    public boolean assertSubscribeYesIsSelected() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isSelected(yesNewsletterOption);

    }

    public boolean assertSubscribeNoIsSelected() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isSelected(subscribeNORadioButton);

    }

    public void clickOnSubscribeAsNo() {
        subscribeNORadioButton.click();
        log.info("Clicked on subscribe as no");

    }

    public void checkOnIAgreeToPrivacyPolicyRadioButton() {
        agreeToPrivacyPolicyButton.click();
        log.info("Checked on I agree to privacy policy radio button");

    }

    public boolean assertPrivacyPolicyIsSelected() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isSelected(agreeToPrivacyPolicyButton);

    }

    public String getAgreeToPrivacyPolicyText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(agreeToPrivacyPolicyText);

    }

    public AccountSuccessPage clickOnContinueButtonInRegistrationPage() throws InterruptedException {
        continueButton.click();
        log.info("Clicked on continue button in registration basePage");
        accountSuccessPage = new AccountSuccessPage();
        return accountSuccessPage;
    }

    public String getFirstNameErrorWarning() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(firstNameErrorWarning);

    }

    public String getLastNameErrorWarning() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(lastNameErrorWarning);

    }

    public String getEmailErrorWarning() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(emailErrorWarning);

    }

    public String retrieveDuplicateEmailAddressWarning() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(emailErrorWarning);
    }

    public String getAddress1ErrorWarning() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(address1ErrorWarning);

    }

    public String getCityErrorWarning() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(cityErrorWarning);

    }

    public String getZipCodeErrorWarning() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(zipCodeErrorWarning).trim();

    }

    public String getStateProvinceErrorWarning() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(StateProvinceErrorWarning);

    }

    public String getLoginNameErrorWarning() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(loginNameErrorWarning);

    }

    public String getPasswordErrorWarning() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(passwordErrorWarning);

    }

    public String getAgreeToPrivacyPolicyErrorWarning() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(agreeToPrivacyPolicyErrorWarning);

    }

    public String getContinueBtnTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(continueButton);

    }

    public void tapContinueButton() {
        continueButton.click();
        log.info("Click on continue button in registration basePage");

    }

    public int getCountOfAllMandatoryRedIcons() {
        int size = mandatoryfields.size();
        log.info("Size of labels: " + size);
        System.out.println("Size of labels: " + size);
        return size;
    }

    public boolean assertForAlertErrorDangerTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(alertErrorDangerItemized);

    }

    public Boolean isSubscribeAsYesTicked() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isSelected(yesNewsletterOption);

    }

    public boolean isPrivacyPolicyIsTicked() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isSelected(agreeToPrivacyPolicyButton);

    }

    public String getSubscribeAsYes() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(yesNewsletterOption);

    }

    public String getContinueButtonTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(continueButton);

    }

    public AccountSuccessPage clickContinueButtonInRegisterPage() throws IOException {
        this.page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        continueButton.click();
        log.info("Click on continue button");
        return new AccountSuccessPage();
    }

    public AccountSuccessPage clickContinueButton() {
        this.page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        continueButton.click();
        //this.page.click(Continue, WaitStrategy.CLICKABLE);
        log.info("Click on continue button with wait-strategy");
        return new AccountSuccessPage();
    }

    public AccountSuccessPage navigateToAccountSuccessPage() {
        continueButton.click();
        log.info("clicked on Continue button");
        return new AccountSuccessPage();
    }

    public boolean assertCountrySelectedByIPGeoLocationIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(countryDropdown);

    }

    public String getCountrySelectedByIPGeoLocationText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(countryDropdown);

    }

    public String getSubscribeYesRadioButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(subscribeYesRadioButtonText);
    }

    public boolean assertAllAlertErrorDangerSummaryText() {
        new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.getExplicitWait())).until(ExpectedConditions.visibilityOf(allAlertErrorDangerSummaryList));
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(allAlertErrorDangerSummaryList);
    }

    public String getPrivacyPolicyText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(agreeToPrivacyPolicyText);

    }

    public String getSubscribeYesOptionText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(subscribeYesRadioButtonText);

    }

    public List<WebElement> getAsterisks() {
        return asterisks;

    }

    public void assertInputLabel(int i, String labelName) {
        Assert.assertEquals("Input label not matching", labelName, inputLabels.get(i).getText());

    }

    public void assertRadioButtonsLabel(int i, String labelName) {
        Assert.assertTrue(subscribeRadioButtonLabels.get(i).getText().contains(labelName));
    }

    public String getPersonalDetailsLabel() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(yourPersonalDetailsTxt);

    }

    public void createRegistrationModel(
            String firstname,
            String lastName,
            String fax,
            String company_name,
            String address1,
            String address2,
            String cityName,
            String regionOrStateName,
            String zipOrPostalCode,
            String password,
            String confirmPassword) throws InterruptedException {
        firstNameField.sendKeys(firstname);
        lastNameField.sendKeys(lastName);
        faxField.sendKeys(fax);
        companyField.sendKeys(company_name);
        address1Field.sendKeys(address1);
        address2Field.sendKeys(address2);
        cityField.sendKeys(cityName);
        selectRegionOrState(regionOrStateName);
        postcodeField.sendKeys(zipOrPostalCode);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmPassword);

    }

    public void createRegistrationModel(RegistrationModel regModel) throws InterruptedException {
        firstNameField.sendKeys(regModel.getFirstName());
        lastNameField.sendKeys(regModel.getLastName());
        telephoneField.sendKeys(regModel.getTelephone());
        faxField.sendKeys(regModel.getFax());
        companyField.sendKeys(regModel.getCompany());
        address1Field.sendKeys(regModel.getAddress1());
        address2Field.sendKeys(regModel.getAddress2());
        cityField.sendKeys(regModel.getCity());
        selectRegionOrState(regModel.getRegionOrState());
        postcodeField.sendKeys(regModel.getZipCode());
        passwordField.sendKeys(regModel.getPassword());
        confirmPasswordField.sendKeys(regModel.getConfirmPassword());

    }

    public void assertRadioButtonsUnchecked() {
        for (int i = 0; i < radioBtns.size(); i++) {
            Assert.assertFalse(radioBtns.get(i).isSelected(), (i + 1) + " Radio button is selected by default ");
        }
    }

    // Fill Registration Form
    public void fillRegistrationForm(String firstName, String lastName, String email, String address,
                                     String city, String state, String postcode, String country,
                                     String telephone, String fax, String loginName, String password,
                                     String confirmPassword, String newsletterPreference) {
        sendKeysAction(firstNameField, firstName);
        sendKeysAction(lastNameField, lastName);
        sendKeysAction(emailField, email);
        sendKeysAction(address1Field, address);
        sendKeysAction(cityField, city);
        selectFromDropdown(stateDropdown, state);
        sendKeysAction(postcodeField, postcode);
        selectFromDropdown(countryDropdown, country);
        sendKeysAction(telephoneField, telephone);
        sendKeysAction(faxField, fax);
        sendKeysAction(loginNameField, loginName);
        sendKeysAction(passwordField, password);
        sendKeysAction(confirmPasswordField, confirmPassword);
        selectRadioButton(newsletterRadioButtons, newsletterPreference);
        checkCheckbox(checkboxPrivacyPolicy);
    }

    // Submit Registration Form
    public void submitRegistration() {
        clickAction(continueButton);
    }

    public List<String> getErrorMessages() {
        return errorMessages.stream()
                .map(WebElement::getText)
                .collect(java.util.stream.Collectors.toList());
    }

    public AccountSuccessPage registerWithMandatoryFields(String firstNameText, String lastNameText, String emailText, String telephoneText, String passwordText) {
        firstNameField.sendKeys(firstNameText);
        lastNameField.sendKeys(lastNameText);
        emailField.sendKeys(emailText);
        telephoneField.sendKeys(telephoneText);
        passwordField.sendKeys(passwordText);
        confirmPasswordField.sendKeys(passwordText);
        agreeToPrivacyPolicyButton.click();
        continueButton.click();
        return new AccountSuccessPage();

    }

    public AccountSuccessPage registerWithAllFields(String firstNameText, String lastNameText, String emailText, String telephoneText, String passwordText) {
        firstNameField.sendKeys(firstNameText);
        lastNameField.sendKeys(lastNameText);
        emailField.sendKeys(emailText);
        telephoneField.sendKeys(telephoneText);
        passwordField.sendKeys(passwordText);
        confirmPasswordField.sendKeys(passwordText);
        yesNewsletterOption.click();
        agreeToPrivacyPolicyButton.click();
        continueButton.click();
        return new AccountSuccessPage();

    }

    public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarning, String expectedFirstNameWarning, String expectedLastNameWarning, String expectedEmailWarning, String expectedPasswordWarning) {

        boolean privacyPolicyWarningStatus = privacyPolicyErrorWarning.getText().contains(expectedPrivacyPolicyWarning);
        boolean firstNameWarningStatus = firstNameErrorWarning.getText().equals(expectedFirstNameWarning);
        boolean lastNameWarningStatus = lastNameErrorWarning.getText().equals(expectedLastNameWarning);
        boolean emailWarningStatus = emailErrorWarning.getText().equals(expectedEmailWarning);
        boolean passwordWarningStatus = passwordErrorWarning.getText().equals(expectedPasswordWarning);
        return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus && passwordWarningStatus;
    }

}
