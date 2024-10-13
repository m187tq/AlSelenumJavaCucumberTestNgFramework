package com.automationteststore.tests.Login;

import com.automationteststore.base.BaseTest;
import com.automationteststore.helperutilities.assertion.AssertionHelper;
import com.automationteststore.pages.AccountPage;
import com.automationteststore.pages.LoginPage;
import com.automationteststore.pages.TopMenuPage;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.*;

//@Listeners(helperUtilities.listeners.Listeners.class)

public class login_validation_jdbc_connection extends BaseTest {
    private static final Logger log = LogManager.getLogger(login_validation_jdbc_connection.class);

    TopMenuPage naviPage;
    LoginPage loginPage;
    AccountPage accountPage;
    String driverName = "com.mysql.cj.jdbc.Driver";
    String dataBaseName = "customers";
    String hostname = "localhost";
    String username = "root";
    String password = "root";
    String port = "3306";
    String query = "SELECT * from userlogin where User";
    String connectionUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dataBaseName + "";
    String executeQueryForDev = "" + query + " = 'dev';";
    String executeQueryTester = "SELECT * from userlogin where User = 'tester';";
    String executeQueryTestManager = "SELECT * from userlogin where User = 'testmanager';";
    String executeQueryTester1 = "SELECT * from userlogin where User = 'tester1';";
    String executeQueryTester2 = "SELECT * from userlogin where User = 'tester2';";
    String executeQuerySdet = "SELECT * from userlogin where User = 'sdet';";

    @BeforeMethod
    public void setUpDatabaseConnection() throws Exception {
        naviPage = new TopMenuPage();
        loginPage = naviPage.clickOnLoginOrRegisterLink();
        Class.forName(driverName);

    }

    @Test(priority = 1)
    public void connectDatabaseDevByColumnIndex() throws SQLException, IOException {
        Connection con = DriverManager.getConnection(connectionUrl, username, password);
        ResultSet rs = con.createStatement().executeQuery(executeQueryForDev);
        while (rs.next()) {
            loginPage.enterLoginName(rs.getString(2));
            log.info(" :: " + rs.getString(2));
            loginPage.enterPassword(rs.getString(3));
            log.info(" :: " + rs.getString(3));
            accountPage = loginPage.clickOnLoginButtonInAccountLoginPage();
            AssertionHelper.updateTestStatus(accountPage.getWelcomeMessageText().contains(rs.getString(4)));
            WebDrv.getInstance().getWebDriver().quit();

        }

    }

    @Test(priority = 2)
    public void connectDatabaseTestManagerByColumnLabel() throws SQLException, IOException {
        Connection con = DriverManager.getConnection(connectionUrl, username, password);
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(executeQueryTestManager);
        while (rs.next()) {
            loginPage.enterLoginName(rs.getString("LoginName"));
            log.info(" :: " + rs.getString("LoginName"));
            loginPage.enterPassword(rs.getString("Password"));
            log.info(" :: " + rs.getString("Password"));
            accountPage = loginPage.clickOnLoginButtonInAccountLoginPage();
            AssertionHelper.updateTestStatus(accountPage.getWelcomeMessageText().contains(rs.getString("Validation_Confirmation_Message")));
            WebDrv.getInstance().getWebDriver().quit();

        }

    }

    @Test(priority = 3)
    public void connectDatabaseTesterByColumnLabel() throws SQLException, IOException, InterruptedException {
        Connection con = DriverManager.getConnection(connectionUrl, username, password);
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(executeQueryTester);
        while (rs.next()) {
            loginPage.enterLoginName(rs.getString("LoginName"));
            log.info(" :: " + rs.getString("LoginName"));
            loginPage.enterPassword(rs.getString("Password"));
            log.info(" :: " + rs.getString("Password"));
            loginPage.clickOnLoginButtonInAccountLoginPage();
            AssertionHelper.updateTestStatus(loginPage.getErrorIncorrectLoginPasswordProvidedConfirmationMessage().contains(rs.getString("Validation_Confirmation_Message")));
            WebDrv.getInstance().getWebDriver().quit();

        }

    }

    @Test(priority = 4)
    public void connectDatabaseTester1ByColumnLabel() throws SQLException, IOException, InterruptedException {
        Connection con = DriverManager.getConnection(connectionUrl, username, password);
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(executeQueryTester1);
        while (rs.next()) {
            loginPage.enterLoginName(rs.getString("LoginName"));
            log.info(" :: " + rs.getString("LoginName"));
            loginPage.enterPassword(rs.getString("Password"));
            log.info(" :: " + rs.getString("Password"));
            loginPage.clickOnLoginButtonInAccountLoginPage();
            AssertionHelper.updateTestStatus(loginPage.getErrorIncorrectLoginPasswordProvidedConfirmationMessage().contains(rs.getString("Validation_Confirmation_Message")));
            WebDrv.getInstance().getWebDriver().quit();

        }

    }

    @Test(priority = 5)
    public void connectDatabaseTester2ByColumnLabel() throws SQLException, IOException, InterruptedException {
        Connection con = DriverManager.getConnection(connectionUrl, username, password);
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(executeQueryTester2);
        while (rs.next()) {
            loginPage.enterLoginName(rs.getString("LoginName"));
            log.info(" :: " + rs.getString("LoginName"));
            loginPage.enterPassword(rs.getString("Password"));
            log.info(" :: " + rs.getString("Password"));
            loginPage.clickOnLoginButtonInAccountLoginPage();
            AssertionHelper.updateTestStatus(loginPage.getErrorIncorrectLoginPasswordProvidedConfirmationMessage().contains(rs.getString("Validation_Confirmation_Message")));
            WebDrv.getInstance().getWebDriver().quit();

        }

    }

    @Test(priority = 6)
    public void connectDatabaseSdetByColumnLabel() throws SQLException, IOException, InterruptedException {
        Connection con = DriverManager.getConnection(connectionUrl, username, password);
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(executeQuerySdet);
        while (rs.next()) {
            loginPage.enterLoginName(rs.getString("LoginName"));
            log.info(" :: " + rs.getString("LoginName"));
            loginPage.enterPassword(rs.getString("Password"));
            log.info(" :: " + rs.getString("Password"));
            loginPage.clickOnLoginButtonInAccountLoginPage();
            AssertionHelper.updateTestStatus(loginPage.getErrorIncorrectLoginPasswordProvidedConfirmationMessage().contains(rs.getString("Validation_Confirmation_Message")));
            WebDrv.getInstance().getWebDriver().quit();

        }

        con.close();
    }

}