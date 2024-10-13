package com.automationteststore.tests.Login;

import com.automationteststore.base.BaseTest;
import com.automationteststore.pages.AccountPage;
import com.automationteststore.pages.LoginPage;
import com.automationteststore.pages.TopMenuPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
//@Listeners(helperUtilities.listeners.Listeners.class)

public class LoginTestBaseWithInvalidCredentialsWithJsonInput extends BaseTest {
    TopMenuPage naviPage;
    LoginPage loginPage;
    AccountPage accountPage;

    @Test(description = "Login test with valid credentials", dataProvider = "getData", groups = {"LoginNegative"})
    public void loginTestValidCredentials(HashMap<String, String> input) throws Exception {
        naviPage = new TopMenuPage();
        loginPage = naviPage.clickOnLoginOrRegisterLink();
        accountPage = loginPage.loginApplication(input.get("loginName"), input.get("password"));
        Assert.assertTrue(loginPage.getErrorIncorrectLoginPasswordProvidedConfirmationMessage().contains(input.get("loginErrorMsg")));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        //List<HashMap<String, String>> data = getJsonDataToMap(
        //System.getProperty("user.dir") + "//src//test//java//testData//data//LoginNegative.json");
        //return new Object[][]{{data.get(0)}};
        //return new Object[][]{{data.get(0)}, {data.get(0)}}; //for array multiple testData.data set in the json file

        return new Object[0][];
    }

}