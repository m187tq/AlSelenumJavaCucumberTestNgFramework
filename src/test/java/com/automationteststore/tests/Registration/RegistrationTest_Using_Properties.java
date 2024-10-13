package com.automationteststore.tests.Registration;

import com.automationteststore.base.BaseTest;
import com.automationteststore.helperutilities.assertion.AssertionHelper;
import com.automationteststore.pages.LoginPage;
import com.automationteststore.pages.RegistrationPage;
import com.automationteststore.pages.AccountSuccessPage;
import com.automationteststore.pages.TopMenuPage;
import com.automationteststore.testData.javaFiles.GlobalVars;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

import static com.automationteststore.utils.PropertyUtils.loadPropertyFile;

//@Listeners(helperUtilities.listeners.Listeners.class)

public class RegistrationTest_Using_Properties extends BaseTest {
    Properties prop;
    TopMenuPage topMenuPage;
    LoginPage loginPage;
    RegistrationPage register;
    AccountSuccessPage accountSuccessPage;

    @Test(description = "Registration Using Properties Input")
    public void RegistrationTestUsingPropertiesInput() throws IOException, InterruptedException {
        //prop = new ReadPropertyFile().loadPropertiesFile(GlobalVars.GLOBAL_PROPERTIES);
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
        AssertionHelper.updateTestStatus(register.getCountrySelectedByIPGeoLocationText().contains(prop.getProperty("country")));
        register.inputUniqueLoginName();
        register.enterPassword(prop.getProperty("password"));
        register.enterConfirmPassword(prop.getProperty("password"));
        register.tickOnSubscribeAsYes();
        register.checkOnIAgreeToPrivacyPolicyRadioButton();
        accountSuccessPage = register.clickContinueButtonInRegisterPage();
        AssertionHelper.updateTestStatus(accountSuccessPage.assertCongratulationsAccountSuccessfullyCreatedTextIsDisplayed());
        AssertionHelper.updateTestStatus(accountSuccessPage.getYourAccountHasBeenCreatedText().equalsIgnoreCase(prop.getProperty("accountCreatedHeading")));
        AssertionHelper.updateTestStatus(accountSuccessPage.getCongratulationsAccountSuccessfullyCreatedText().contains(prop.getProperty("congratulationsMessage")));
        AssertionHelper.updateTestStatus(accountSuccessPage.assertContinueButtonIsEnabledAndDisplayed());

    }


}
