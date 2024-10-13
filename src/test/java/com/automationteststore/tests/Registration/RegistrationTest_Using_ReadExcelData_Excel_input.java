package com.automationteststore.tests.Registration;

import com.automationteststore.base.BaseTest;
import com.automationteststore.pages.LoginPage;
import com.automationteststore.pages.RegistrationPage;
import com.automationteststore.pages.AccountSuccessPage;
import com.automationteststore.pages.TopMenuPage;
import com.automationteststore.testData.javaFiles.GlobalVars;
import com.automationteststore.utils.DataProviderUtils;
import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RegistrationTest_Using_ReadExcelData_Excel_input extends DataProviderUtils {
    TopMenuPage topMenuPage;
    BaseTest baseTest;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    AccountSuccessPage accountSuccessPage;


    @Test()
    public void readExcel_Data() throws IOException, InterruptedException {
        List<Map<String, String>> excelData = new BaseTest().readExcelData(GlobalVars.REGISTRATIONDATA_EXCEL_FILE_PATH, GlobalVars.SHEET_NAME_REGISTER);
        for (Map<String, String> row : excelData) {

            BaseTest baseTest = new BaseTest();
            topMenuPage = baseTest.launchApplication();
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
            //registrationPage.selectCountry(row.get("Country"));
            registrationPage.enterLoginName();
            registrationPage.enterPassword(row.get("Password"));
            registrationPage.enterConfirmPassword(row.get("Password"));
            registrationPage.tickOnSubscribeAsYes();
            registrationPage.checkOnIAgreeToPrivacyPolicyRadioButton();
            accountSuccessPage = registrationPage.clickContinueButtonInRegisterPage();
            Assert.assertEquals(accountSuccessPage.getCongratulationsAccountSuccessfullyCreatedText(), row.get("Expected_Header"));

/*			System.out.println(row);
			System.out.println("The row excel reader :: " + row.keySet());
			System.out.println("The row excel reader :: " + row.values());
			System.out.println("------------------------------------------------");
			System.out.println("The row excel reader :: " + row.entrySet());
			System.out.println("The row excel reader :: " + row.size());
			System.out.println("------------------------------------------------");*/

        }


    }
}
