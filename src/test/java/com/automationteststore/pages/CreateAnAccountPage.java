package com.automationteststore.pages;

import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.automationteststore.pages.Page.*;

public class CreateAnAccountPage {
    Page page;
    AccountSuccessPage accountSuccessPage;
    String email = System.currentTimeMillis() + "Gen";
    String loginname = System.currentTimeMillis() + "Gen";
    private String registrationPageUrl = "https://automationteststore.com/index.php?rt=account/create";
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
    private WebElement zipCodeField;

    @FindBy(id = "AccountFrm_country_id")
    private WebElement countryDropdown;

    @FindBy(id = "AccountFrm_loginname")
    private WebElement loginNameField;

    @FindBy(id = "AccountFrm_password")
    private WebElement passwordField;

    @FindBy(id = "AccountFrm_confirm")
    private WebElement confirmPasswordField;

    @FindBy(id = "AccountFrm_newsletter1")
    private WebElement newsletterYesRadio;

    @FindBy(id = "AccountFrm_newsletter0")
    private WebElement newsletterNoRadio;

    @FindBy(id = "AccountFrm_agree")
    private WebElement privacyPolicyCheckbox;

    @FindBy(xpath = "//button[@title='Continue']")
    private WebElement continueButton;
    @FindBy(css = ".success")
    private WebElement successMessage;
    @FindBy(css = ".error")
    private List<WebElement> errorMessages;

    // Navigate to Registration Page
    public void navigateToRegistrationPage() {
        WebDrv.getInstance().getWebDriver().get(registrationPageUrl);
    }

    // Fill Registration Form
    public void fillRegistrationForm(String firstName, String lastName, String email, String company, String address1, String address2,
                                     String city, String state, String postcode, String country,
                                     String telephone, String fax, String loginName, String password,
                                     String confirmPassword, String newsletterPreference) {
        sendKeysAction(firstNameField, firstName);
        sendKeysAction(lastNameField, lastName);
        sendKeysAction(emailField, email);
        sendKeysAction(telephoneField, telephone);
        sendKeysAction(faxField, fax);
        sendKeysAction(companyField, company);
        sendKeysAction(address1Field, address1);
        sendKeysAction(address2Field, address2);
        sendKeysAction(cityField, city);
        selectFromDropdown(stateDropdown, state);
        sendKeysAction(zipCodeField, postcode);
        selectFromDropdown(countryDropdown, country);
        sendKeysAction(loginNameField, loginName);
        sendKeysAction(passwordField, password);
        sendKeysAction(confirmPasswordField, confirmPassword);
        selectRadioButton((List<WebElement>) newsletterYesRadio, newsletterPreference);
        checkCheckbox(privacyPolicyCheckbox);
    }

    // Submit Registration Form
    public void submitRegistration() {
        clickAction(continueButton);
    }

    // Get Success Message or Error Messages


    public String getSuccessMessage() {
        return successMessage.getText();
    }

    public List<String> getErrorMessages() {
        return errorMessages.stream()
                .map(WebElement::getText)
                .collect(java.util.stream.Collectors.toList());
    }
}
