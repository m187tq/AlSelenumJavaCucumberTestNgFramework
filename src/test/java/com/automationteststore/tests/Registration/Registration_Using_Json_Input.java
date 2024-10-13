package com.automationteststore.tests.Registration;

import com.automationteststore.base.BaseTest;
import com.automationteststore.helperutilities.assertion.AssertionHelper;
import com.automationteststore.pages.LoginPage;
import com.automationteststore.pages.RegistrationPage;
import com.automationteststore.pages.AccountSuccessPage;
import com.automationteststore.pages.TopMenuPage;
import com.automationteststore.utils.JsonReaderUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.automationteststore.testData.javaFiles.GlobalVars.JSON_DATA_TO_MAP;
import static com.automationteststore.testData.javaFiles.GlobalVars.SYSTEM_GET_PROPERTY;

//@Listeners(helperUtilities.listeners.Listeners.class)

public class Registration_Using_Json_Input extends BaseTest {

    TopMenuPage topMenuPage;
    LoginPage loginPage;
    RegistrationPage register;
    AccountSuccessPage accountSuccessPage;

    @Test(dataProvider = "getData", groups = {"Register"}, description = "RegistrationTest_Through_LoginOption")
    public void RegistrationTestAsideWidgetJsonInput(HashMap<String, String> input) throws IOException, InterruptedException {
        topMenuPage = new TopMenuPage();
        AssertionHelper.updateTestStatus(topMenuPage.assertLoginOrRegisterLinkIsDisplayed());
        AssertionHelper.updateTestStatus(topMenuPage.getLoginOrRegisterLink().contains(input.get("loginOrRegister")));
        loginPage = topMenuPage.clickOnLoginOrRegisterLink();
        register = loginPage.clickOnContinueButtonInLoginPage();
        register.enterFirstName(input.get("firstname"));
        register.enterLastName(input.get("lastname"));
        register.enterEmailAddress();
        register.enterTelephoneNumber(input.get("telephone"));
        register.enterFaxNumber(input.get("fax"));
        register.enterCompanyName(input.get("coyName"));
        register.enterAddress1(input.get("address1"));
        register.enterAddress2(input.get("address2"));
        register.enterCity(input.get("city"));
        register.selectRegionOrState(input.get("region/State"));
        register.enterPostalOrZipCode(input.get("postcode"));
        AssertionHelper.updateTestStatus(register.getCountrySelectedByIPGeoLocationText().contains(input.get("country")));
        register.inputUniqueLoginName();
        register.enterPassword(input.get("password"));
        register.enterConfirmPassword(input.get("password"));
        register.tickOnSubscribeAsYes();
        register.checkOnIAgreeToPrivacyPolicyRadioButton();
        accountSuccessPage = register.clickContinueButtonInRegisterPage();
        AssertionHelper.updateTestStatus(accountSuccessPage.getYourAccountHasBeenCreatedText().equalsIgnoreCase(input.get("yourAccountHasBeenCreatedMsg")));
        AssertionHelper.updateTestStatus(accountSuccessPage.assertCongratulationsAccountSuccessfullyCreatedTextIsDisplayed());
        AssertionHelper.updateTestStatus(accountSuccessPage.getCongratulationsAccountSuccessfullyCreatedText().contains(input.get("congratulationsYourAccountSuccessfullyCreatedMsg")));
        AssertionHelper.updateTestStatus(accountSuccessPage.assertContinueButtonIsEnabledAndDisplayed());
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        JsonReaderUtils jsonReaderUtils = new JsonReaderUtils();
        List<HashMap<String, String>> data = jsonReaderUtils.getJsonDataToMap(SYSTEM_GET_PROPERTY + JSON_DATA_TO_MAP);
        return new Object[][]{{data.get(0)}};
        //return new Object[][]{{data.get(0)}, {data.get(1)}, {data.get(2)}}; //for array multiple testData.data set in the json file
    }

}

