package com.automationteststore.tests.Login;

import com.automationteststore.base.BaseTest;
import com.automationteststore.pages.*;
import com.automationteststore.testData.javaFiles.GlobalVars;
import org.testng.annotations.Test;

import java.io.IOException;

//@Listeners(helperUtilities.listeners.Listeners.class)

public class LoginRecoveryForgottenPasswordTestBase extends BaseTest {
    public ForgottenPasswordPage forgottenPasswordPage;
    TopMenuPage naviPage;
    HomePage homePage;
    LoginPage loginPage;
    AccountPage accountPage;

    @Test
    public void loginForgottenPasswordTest() throws IOException, InterruptedException {
        naviPage = new TopMenuPage();
        homePage = new HomePage();
        naviPage.goToHomePage();
        loginPage = naviPage.clickOnLoginOrRegisterLink();
        loginPage.enterLoginName(GlobalVars.getLogin());
        loginPage.enterPassword(GlobalVars.getWrongPassword());
        accountPage = loginPage.clickOnLoginButtonInAccountLoginPage();
        forgottenPasswordPage = loginPage.clickForgetYourPasswordLinkInLoginPage();
        forgottenPasswordPage.enterLoginname(GlobalVars.RECOVERY_PASSWORD_LOGINNAME);
        forgottenPasswordPage.enterEmail(GlobalVars.getEmailPassword());
        loginPage = forgottenPasswordPage.clickOnContinueButtonInForgottenPasswordPage();
//        boolean result_success = loginPage.getSuccessMessage().contains(GlobalVars.getRecoveryPasswordAlertSuccessMsg());
//        Assert.assertTrue(result_success);

    }

}