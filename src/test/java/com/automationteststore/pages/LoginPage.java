package com.automationteststore.pages;

import com.automationteststore.enums.WaitStrategy;
import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.ScrollPage;
import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.helperutilities.javaScript.JavaScriptHelper;
import com.automationteststore.model.LoginModel;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.datatable.DataTable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

import static com.automationteststore.pages.RegistrationPage.login_name;

public class LoginPage extends Page {
    private final static Logger log = LogManager.getLogger(LoginPage.class);
    Page page;
    RegistrationPage register;
    private ForgottenPasswordPage forgottenPasswordPage;
    private ForgottenLoginnamePage forgottenLoginnamePage;
    // WebElements for login fields
    @FindBy(id = "loginFrm_loginname")
    private WebElement loginName;
    @FindBy(id = "loginFrm_password")
    private WebElement password;
    @FindBy(css = ".alert.alert-error")
    private WebElement errorMessage;
    @FindBy(how = How.CSS, using = ".maintext")
    private WebElement heading;
    @FindBy(css = "#header-logo > div > nav")
    private WebElement menuItemsLink;
    @FindBy(css = "label[for='accountFrm_accountregister']")
    private WebElement registerAccountCheckBox;
    @FindBy(how = How.ID, using = "username-error")
    private WebElement err_username;
    @FindBy(xpath = "//input[@id='loginFrm_loginname']")
    private WebElement loginnameField;
    @FindBy(xpath = "//input[@id='loginFrm_password']")
    private WebElement passwordField;
    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement loginButton;
    @FindBy(css = "button[title='Continue']")
    private WebElement continueButton;
    By Continue = By.cssSelector("button[title='Continue']");
    @FindBy(xpath = "//*[@id='maincontainer']/div/div/div/div[1]")
    private WebElement loginWithBlanksCredentialsTxt;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div/div/div[1]/text()")
    private WebElement loginWithBadPasswordTxt;
    @FindBy(css = "div.alert.alert-error.alert-danger")
    private WebElement errorIncorrectLoginOrPasswordProvidedTxt;
    @FindBy(css = "div.alert.alert-error.alert-danger")
    private List<WebElement> errorAlertMessageList;
    @FindBy(xpath = "//div[@class='alert alert-error alert-danger']")
    private WebElement errorAlertDangerLoginMessage;
    @FindBy(css = ".form-group.mb40.mt20")
    private WebElement byCreatingAnAccountText;
    @FindBy(xpath = "//h2[contains(text(),'Returning Customer')]")
    private WebElement returningCustomerText;
    @FindBy(css = ".col-sm-6.newcustomer > .heading2")
    private WebElement newCustomerTxt;
    @FindBy(css = "fieldset > a:nth-of-type(1)")
    private WebElement forgotYourPasswordLink;
    @FindBy(linkText = "Forgot your login?")
    private WebElement forgotYourLoginLink;
    @FindBy(css = "[class='col-sm-6 newcustomer'] .heading4")
    private WebElement checkoutOptionTxt;
    @FindBy(xpath = "//*[@id='email_create']")
    private WebElement registrationEmailAddress;
    @FindBy(css = ".alert.alert-success")
    private WebElement successAlertForgetLoginNameConfirmationMessage;

    public void navigateToAccountLoginPage() {
        if (WebDrv.getInstance().getWebDriver() == null) {
            page = PageFactory.initElements(WebDrv.getInstance().openBrowser("", null), Page.class);
            page.navigateTo(GlobalVarsHelper.getLoginPageUrl());
        } else {
            WebDrv.getInstance().getWebDriver().manage().deleteAllCookies();
            page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
            page.navigateTo(GlobalVarsHelper.getLoginPageUrl());
        }
    }

    public AccountPage login(String loginname, String pass) {
        loginName.sendKeys(loginname);
        password.sendKeys(pass);
        loginButton.click();
        return new AccountPage();
    }

    public String assertPageIsLoginPage() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(heading);

    }

    public boolean isAccountRegisterChecked() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isSelected(registerAccountCheckBox);

    }

    public void assertRegisterContentPresent() {
        WebElement helpTitle = WebDrv.getInstance().getWebDriver().findElement(By.xpath("//*[@data-test='registration-title']"));
        helpTitle.click();
        WebElement helpContentOne = WebDrv.getInstance().getWebDriver().findElement(By.xpath("//*[@data-test='registration-content-existing-organisation']"));
        Assert.assertEquals("Help title is not equal to expected", helpTitle.getText(), "Register as an employer");
        Assert.assertEquals("Help top content is not equal to expected", helpContentOne.getText(), "If your organisation already has an NHS Jobs account, you can ask your organisation's super-user to create an account for you.");
    }

    public String getContinueButtonText() {
        new JavaScriptHelper(WebDrv.getInstance().getWebDriver()).scrollToElement(continueButton);
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(continueButton);

    }

    private String getRegisterAccountCheckBoxText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(registerAccountCheckBox);

    }

    public void enterLoginName(String loginName) {
        loginnameField.sendKeys(loginName);
        log.info("Entered login name : " + loginName);

    }

    public void enterStaticLoginName() {
        loginnameField.sendKeys(login_name);
        log.info("Entered login name : " + login_name);

    }

    public AccountPage clickOnLoginButtonInAccountLoginPage() {
        //ScrollPage.scrollToViewAndClickLoginButton();
        loginButton.click();
        log.info("Clicked on : " + continueButton.getText());
        return new AccountPage();
    }

    public void tapOnLoginButton() throws Throwable {
        click(loginButton);
    }

    public AccountPage clickLoginButtonInAccountLoginPage() {
        loginButton.click();
        return new AccountPage();

    }

    public CheckoutConfirmationPage clickLoginButton() {
        loginButton.click();
        return new CheckoutConfirmationPage();
    }


    public String getLoginButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(loginButton);

    }

    public boolean isLoginSuccessful() {
        return !errorMessage.isDisplayed();

    }

    public AccountPage ClickLoginButton() throws IOException {
        log.info("Getting text:: " + getContinueButtonText());
        getContinueButtonText();
        loginButton.click();
        return new AccountPage();
    }

    public AccountPage loginApplication(String loginName, String password) throws Exception {
        enterLoginName(loginName);
        enterPassword(password);
        clickLoginButton();
        return new AccountPage();
    }

    public CheckoutConfirmationPage loginApplicationAsReturningCustomer(String loginName, String password) throws Exception {
        enterLoginName(loginName);
        enterPassword(password);
        clickLoginButton();
        return new CheckoutConfirmationPage();
    }

    public void clickForgetYourLogin() {
        forgotYourLoginLink.click();
        log.info("Clicked on : " + forgotYourLoginLink.getText());

    }

    public ForgottenLoginnamePage clickForgetYourLoginLinkInLoginPage() {
        forgotYourLoginLink.click();
        log.info("Clicked on : " + forgotYourLoginLink.getText());
        return new ForgottenLoginnamePage();
    }

    public String getForgotYourPasswordLinkTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(forgotYourPasswordLink);

    }

    public String getForgotYourLoginLinkTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(forgotYourLoginLink);

    }

    public String getErrorIncorrectLoginPasswordProvidedConfirmationMessage() throws InterruptedException {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(errorAlertDangerLoginMessage);
    }

    public String getErrorAlertDangerLoginMessage() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(errorAlertDangerLoginMessage);

    }

    public String getErrorIncorrectLoginPasswordProvidedConfirmationMessageTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(errorAlertDangerLoginMessage);

    }

    public String getReturningCustomerTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(returningCustomerText);

    }

    public boolean isReturningCustomerNotDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isNotDisplayed(returningCustomerText);

    }

    public String getCheckoutOptionTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(checkoutOptionTxt);

    }

    public boolean isCheckoutOptionSelected() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isSelected(checkoutOptionTxt);

    }

    public String getNewCustomerTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(newCustomerTxt);

    }

    public RegistrationPage navigateToRegisterPage() {
        continueButton.click();
        //log.info("Clicked on : " + continueButton.getText());
        return new RegistrationPage();

    }

    public RegistrationPage clickOnContinueButtonInLoginPage() {
        continueButton.click();
        log.info("Clicked on continue button");
        return new RegistrationPage();
    }

    public String getHeadingText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(heading);
    }

    public boolean assertRegisterAccountRadioButtonChecked() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isSelected(registerAccountCheckBox);

    }

    public String getRegisterAccountRadioButtonTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(registerAccountCheckBox);

    }

    public String getByCreatingAccountToShopFasterText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(byCreatingAnAccountText);

    }

    public boolean assertRegisterAccountIsSelectedByDefault() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isSelected(registerAccountCheckBox);
    }

    public String getConfirmationMessage() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(successAlertForgetLoginNameConfirmationMessage);
    }

    public ForgottenPasswordPage clickForgetYourPasswordLinkInLoginPage() {
        forgotYourPasswordLink.click();
        log.info("Clicked on : " + forgotYourPasswordLink.getText());
        return new ForgottenPasswordPage();
    }

    public void createLoginModel(String loginName, String password) {
        loginnameField.sendKeys(loginName);
        passwordField.sendKeys(password);
    }

    public void createLoginModel(LoginModel loginModel) {
        loginnameField.sendKeys(loginModel.getLoginname());
        passwordField.sendKeys(loginModel.getPassword());

    }

    public void loginWithInvalidCredentials(String loginname, String pass) {
        loginnameField.clear();
        loginnameField.sendKeys(loginname);
        log.info("Entered loginname : " + loginname);
        passwordField.clear();
        passwordField.sendKeys(pass);
        log.info("Entered password : " + pass);
        loginButton.click();
        log.info("Clicked on: " + loginname + " button");
    }

    public AccountPage logon(String loginname, String passWord) {
        enterLoginname(loginname);
        enterPassword(passWord);
        clickOnLoginButton();
        log.info("Clicked on: " + loginname + " button");
        return new AccountPage();
    }

    public void enterLoginname(String login_name) {
        loginnameField.clear();
        loginnameField.sendKeys(login_name);
        log.info("Entered login name : " + login_name);
    }

    public void enterPassword(String pass) {
        passwordField.clear();
        passwordField.sendKeys(pass);
        log.info("Entered password : " + pass);
    }

    public void clickOnLoginButton() {
        loginButton.click();
        log.info("Clicked on: " + loginButton + " button");

    }


    public void assertMenuItemExists(String name) {
        List<WebElement> menuName = menuItemsLink.findElements(By.tagName("li"));
        for (WebElement li : menuName) {
            if (li.getText().contains(name)) {
                Assert.assertEquals(li.getText(), name);
                System.out.println(li.getText());
            }
        }
    }

    public boolean assertErrorMessageIsPresent() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(errorMessage);

    }

    public void assertOnErrorMessages(DataTable dataTable) {
        List<String> expectedErrorMsgs = dataTable.asList();
        for (int i = 0; i < errorAlertMessageList.size(); i++) {
            Assert.assertEquals("Error Message did not match", expectedErrorMsgs.get(i), errorAlertMessageList.get(i).getText());
        }
    }

    public void assertOnErrorMessages(int i) {
        Assert.assertTrue(errorAlertMessageList.get(i).isDisplayed(), "Error message " + (i + 1) + " is not displayed");
    }

    public AccountPage logonApplication(String loginname, String password) {
        loginnameField.clear();
        loginnameField.sendKeys(loginname);

        passwordField.clear();
        passwordField.sendKeys(password);

        loginButton.click();
        return new AccountPage();
    }

    public String retrieveEmailPasswordNotMatchingWarningMessageText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(errorIncorrectLoginOrPasswordProvidedTxt);

    }


}
