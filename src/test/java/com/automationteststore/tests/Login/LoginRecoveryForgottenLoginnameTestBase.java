package com.automationteststore.tests.Login;

import com.automationteststore.base.BaseTest;
import com.automationteststore.pages.ForgottenLoginnamePage;
import com.automationteststore.pages.LoginPage;
import com.automationteststore.pages.TopMenuPage;
import com.automationteststore.testData.javaFiles.GlobalVars;
import org.testng.annotations.Test;

//@Listeners(helperUtilities.listeners.Listeners.class)

public class LoginRecoveryForgottenLoginnameTestBase extends BaseTest {
    public ForgottenLoginnamePage forgottenLoginnamePage;
    TopMenuPage naviPage;
    LoginPage loginPage;

    @Test
    public void loginForgottenPasswordTest() throws InterruptedException {
        naviPage = new TopMenuPage();
        naviPage.goToHomePage();
        loginPage = naviPage.clickOnLoginOrRegisterLink();
        loginPage.enterLoginName(GlobalVars.getLogin());
        loginPage.enterPassword(GlobalVars.getWrongPassword());
        loginPage.clickLoginButtonInAccountLoginPage();
        forgottenLoginnamePage = loginPage.clickForgetYourLoginLinkInLoginPage();
        forgottenLoginnamePage = new ForgottenLoginnamePage();
        forgottenLoginnamePage.enterLastName(GlobalVars.getRecoveryLoginnameLastName());
        forgottenLoginnamePage.enterEmail(GlobalVars.getEmailPassword());
        loginPage = forgottenLoginnamePage.clickOnContinueButtonInForgottenLoginnamePage();
/*        boolean result_success = loginPage.getSuccessMessage().contains(GlobalVars.getRecoveryLoginnameAlertSuccessMsg());
        AssertionHelper.updateTestStatus(result_success);*/


    }

}