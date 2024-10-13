package com.automationteststore.tests.Registration;

import com.automationteststore.base.BaseTest;
import com.automationteststore.helperutilities.assertion.AssertionHelper;
import com.automationteststore.pages.LoginPage;
import com.automationteststore.pages.RegistrationPage;
import com.automationteststore.pages.AccountSuccessPage;
import com.automationteststore.pages.TopMenuPage;
import com.automationteststore.testData.javaFiles.GlobalVars;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Properties;

import static com.automationteststore.utils.PropertyUtils.loadPropertyFile;

//@Listeners(helperUtilities.listeners.Listeners.class)

public class Registration_Using_Properties extends BaseTest {
    final static Logger log = LogManager.getLogger(Registration_Using_Properties.class);
    Properties prop;
    TopMenuPage topMenuPage;
    LoginPage loginPage;
    RegistrationPage register;
    AccountSuccessPage accountSuccessPage;


    @Test()
    public void Registration_using_properties_input() throws Exception {
        prop = loadPropertyFile(GlobalVars.GLOBAL_PROPERTIES);
        topMenuPage = new TopMenuPage();
        loginPage = topMenuPage.clickOnLoginOrRegisterLink();
        register = loginPage.clickOnContinueButtonInLoginPage();
        register.enterFirstName(prop.getProperty("firstName"));
        register.enterLastName(prop.getProperty("lastName"));
        register.enterEmailAddress();
        register.enterTelephoneNumber(prop.getProperty("telephone"));
        register.enterFaxNumber(prop.getProperty("fax"));
        register.enterCompanyName(prop.getProperty("companyName"));
        register.enterAddress1(prop.getProperty("address1"));
        register.enterAddress2(prop.getProperty("address2"));
        register.enterCity(prop.getProperty("city"));
        register.selectRegionOrState(prop.getProperty("province"));
        register.enterPostalOrZipCode(prop.getProperty("postalCode"));
        register.inputUniqueLoginName();
        register.enterPassword(prop.getProperty("password"));
        register.enterConfirmPassword(prop.getProperty("password"));
        register.tickOnSubscribeAsYes();
        register.checkOnIAgreeToPrivacyPolicyRadioButton();
        accountSuccessPage = register.clickContinueButtonInRegisterPage();
        accountSuccessPage.clickOnContinueAccountSuccessButton();
        accountSuccessPage = register.clickContinueButtonInRegisterPage();
        boolean status = accountSuccessPage.getCongratulationsAccountSuccessfullyCreatedText().contains("Congratulations! Your new account has been successfully created!");
        AssertionHelper.updateTestStatus(status);
    }

    @Test
    public void verifyRegisteringAccountWithoutFillingAnyDetails() {
        register.clickOnContinueButton();
        Assert.assertTrue(
                register.displayStatusOfWarningMessages(
                        prop.getProperty("privacyPolicyWarning"),
                        prop.getProperty("firstNameWarning"),
                        prop.getProperty("lastNameWarning"),
                        prop.getProperty("emailWarning"),
                        prop.getProperty("passwordWarning")));

    }

}








