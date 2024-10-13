package com.automationteststore.tests.Registration;

import com.automationteststore.base.BaseTest;
import com.automationteststore.pages.*;
import com.automationteststore.testData.javaFiles.GlobalVars;
import com.automationteststore.utils.DataProviderUtils;
import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.automationteststore.ai.AutomationTestStoreRegistrationLoginTest.login_name;
import static com.automationteststore.constants.FrameworkConstants.loginname;

public class RegistrationTestDemo_Using_DataProvider_Excel_input extends DataProviderUtils {
    TopMenuPage topMenuPage;
    HomePage homePage;
    BaseTest baseTest;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    AccountSuccessPage accountSuccessPage;

    @Test(dataProvider = "dataProviderRegistrationData", dataProviderClass = DataProviderUtils.class)
    public void registrationTest_Using_Excel_Data(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, String p11, String p12, String p13) {
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p5);
        System.out.println(p6);
        System.out.println(p7);
        System.out.println(p8);
        System.out.println(p9);
        System.out.println(p10);
        System.out.println(p11);
        System.out.println(p12);
        System.out.println(p13);
        System.out.println("----------------------");
    }

    @Test(dataProvider = "dataProviderRegisterData", dataProviderClass = DataProviderUtils.class)
    public void registerTest_Using_Excel_Data(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, String p11, String p12, String p13) {
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p5);
        System.out.println(p6);
        System.out.println(p7);
        System.out.println(p8);
        System.out.println(p9);
        System.out.println(p10);
        System.out.println(p11);
        System.out.println(p12);
        System.out.println(p13);
        System.out.println("----------------------");
    }

    @Test(dataProvider = "dataProviderLoginPositiveData", dataProviderClass = DataProviderUtils.class)
    public void loginPositiveTest_Using_Excel_Data(String p1, String p2, String p3) {
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println("------------------------------------------------");
    }

    @Test(dataProvider = "dataProviderLoginNegativeData", dataProviderClass = DataProviderUtils.class)
    public void loginNegativeTest_Using_Excel_Data(String p1, String p2, String p3) {
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println("------------------------------------------------");
    }


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
            registrationPage.clickContinueButtonInRegisterPage();

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
