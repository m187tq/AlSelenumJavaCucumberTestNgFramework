package com.automationteststore.tests.Login;

import com.automationteststore.annotations.FrameworkAnnotation;
import com.automationteststore.base.BaseTest;
import com.automationteststore.enums.CategoryType;
import com.automationteststore.pages.AccountPage;
import com.automationteststore.pages.LoginPage;
import com.automationteststore.pages.TopMenuPage;
import com.automationteststore.utils.TestDataUtils;
import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginTests extends BaseTest {
    TopMenuPage topmenuPage;
    LoginPage loginPage;
    AccountPage accountPage;

    @FrameworkAnnotation(author= {"BenZeta"},
    category = {CategoryType.UNIT,CategoryType.SMOKE})
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return TestDataUtils.getLoginData();
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String loginname, String pass, boolean isSuccessExpected) throws InterruptedException {
        topmenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        loginPage = topmenuPage.clickOnLoginOrRegisterLink();
        loginPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LoginPage.class);
        loginPage.enterLoginName("Webdriverio2");
        loginPage.enterPassword("Webdriverio2");
        accountPage = loginPage.clickLoginButtonInAccountLoginPage();

    }
}

