package com.automationteststore.tests.Login;

import com.automationteststore.pages.AccountPage;
import com.automationteststore.pages.LoginPage;
import com.automationteststore.pages.TopMenuPage;
import com.automationteststore.utils.DataProviderUtils;
import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTests_Using_DataProvider_Excel {
    WebDriver driver;
    TopMenuPage topMenuPage;
    LoginPage loginPage;
    AccountPage accountPage;

    @Test(dataProvider = "dataProviderLoginPositiveData", dataProviderClass = DataProviderUtils.class)
    public void testLogin(String loginname, String password) throws InterruptedException {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        loginPage = this.topMenuPage.clickOnLoginOrRegisterLink();
        loginPage.enterLoginName(loginname);
        loginPage.enterPassword(password);
        accountPage = this.loginPage.clickLoginButtonInAccountLoginPage();
        boolean isLoginSuccessful = accountPage.isLoginSuccessful();
        Assert.assertTrue(isLoginSuccessful);
        WebDrv.getInstance().getWebDriver().quit();

    }

}

