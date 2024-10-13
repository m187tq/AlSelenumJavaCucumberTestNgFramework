package com.automationteststore.tests.Registration;

import com.automationteststore.base.BaseTest;
import com.automationteststore.helperutilities.assertion.AssertionHelper;
import com.automationteststore.pages.*;
import com.automationteststore.testData.javaFiles.GlobalVars;
import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.automationteststore.testData.javaFiles.GlobalVars.SHEET_NAME_REGISTER;

public class RegistrationTest_Using_ReadExcel_Excel_input extends BaseTest {
    TopMenuPage topMenuPage;
    HomePage homePage;
    BaseTest baseTest;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    AccountSuccessPage accountSuccessPage;
    AccountPage accountPage;
    LogoutPage logoutPage;


    @Test()
    public void readExcel_Data() throws IOException, InterruptedException {
        List<Map<String, String>> excelData = readExcelData(GlobalVars.REGISTRATIONDATA_EXCEL_FILE_PATH, SHEET_NAME_REGISTER);
        for (Map<String, String> row : excelData) {
            //homePage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), HomePage.class);
            topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
            loginPage = topMenuPage.clickOnLoginOrRegisterLink();
            registrationPage = loginPage.clickOnContinueButtonInLoginPage();
            registrationPage.enterFirstName(row.get("FirstName"));
            registrationPage.enterLastName(row.get("LastName"));
            registrationPage.enterEmailAddress();
            registrationPage.enterTelephoneNumber(row.get("Telephone"));
            registrationPage.enterFaxNumber(row.get("Fax"));
            registrationPage.enterCompanyName(row.get("CompanyName"));
            registrationPage.enterAddress1(row.get("AddressOne"));
            registrationPage.enterAddress2(row.get("AddressTwo"));
            registrationPage.enterCity(row.get("City"));
            registrationPage.selectRegionOrState(row.get("Province"));
            registrationPage.enterPostalOrZipCode(row.get("PostCode"));
            registrationPage.inputUniqueLoginName();
            registrationPage.enterPassword(row.get("Password"));
            registrationPage.enterConfirmPassword(row.get("Password"));
            registrationPage.tickOnSubscribeAsYes();
            registrationPage.checkOnIAgreeToPrivacyPolicyRadioButton();
            accountSuccessPage = registrationPage.clickContinueButtonInRegisterPage();
            AssertionHelper.updateTestStatus(accountSuccessPage.getCongratulationsAccountSuccessfullyCreatedText().contains("Congratulations! Your new account has been successfully created!"));
            accountPage = accountSuccessPage.clickOnContinueAccountSuccessButton();
            logoutPage = accountPage.clickOnLogoffButton();
            homePage = logoutPage.clickOnLogoutContinueButton();

        }
    }
}
