package com.automationteststore.tests.Registration;

import com.automationteststore.base.BaseTest;
import com.automationteststore.helperutilities.assertion.AssertionHelper;
import com.automationteststore.pages.*;
import com.automationteststore.testData.javaFiles.GlobalVars;
import com.automationteststore.webdriverutilities.WebDrv;
import com.github.javafaker.Faker;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;

//@Listeners(helperUtilities.listeners.Listeners.class)

public class Registration_Using_Faker extends BaseTest {
    TopMenuPage naviPage;
    HomePage homePage;
    LoginPage loginPage;
    RegistrationPage register;
    AccountSuccessPage accountSuccessPage;
    Faker faker = new Faker(Locale.UK);

    @Test(description = "Registration Test Using Faker Input")
    public void RegistrationTestUsingFakerInput() throws IOException, InterruptedException {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        loginPage = topMenuPage.clickOnLoginOrRegisterLink();
        register = loginPage.clickOnContinueButtonInLoginPage();
        register.enterFirstName(faker.name().firstName());
        register.enterLastName(faker.name().lastName());
        register.enterEmail(faker.internet().emailAddress());
        register.enterTelephoneNumber(faker.phoneNumber().phoneNumber());
        register.enterFaxNumber(faker.phoneNumber().cellPhone());
        register.enterCompanyName(faker.company().name());
        register.enterAddress1(faker.address().streetAddress());
        register.enterAddress2(faker.address().secondaryAddress());
        register.enterCity(faker.address().city());
        register.selectRegionOrState(GlobalVars.REGION_STATE);
        register.enterPostalOrZipCode(faker.address().zipCode());
        register.inputLoginName(faker.name().username());
        register.enterPassword("Password");
        register.enterConfirmPassword("Password");
        register.tickOnSubscribeAsYes();
        register.checkOnIAgreeToPrivacyPolicyRadioButton();
        accountSuccessPage = register.clickContinueButtonInRegisterPage();
        boolean status = accountSuccessPage.getCongratulationsAccountSuccessfullyCreatedText().contains(GlobalVars.CONGRATULATION_SUCCESSFULLY_CREATED_MESSAGE);
        AssertionHelper.updateTestStatus(status);
        Assert.assertTrue(status, "Message is not present");

    }


}
