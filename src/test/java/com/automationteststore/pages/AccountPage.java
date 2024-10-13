package com.automationteststore.pages;

import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;


public class AccountPage {

    private final static Logger log = LogManager.getLogger(AccountPage.class);
    EditProfilePage editProfilePage;
    @FindBy(linkText = "Logoff")
    WebElement logoffLink;
    @FindBy(css = ".subtext")
    private WebElement userProfileFirstName;


    @FindBy(css = "h1.heading")
    private WebElement myAccountTxt;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div[2]/div[1]/div/ul/li[1]")
    private WebElement accountDashboardTxt;
    @FindBy(css = "a > .fa.fa-unlock")
    private WebElement logoffIcon;
    @FindBy(css = ".dash-tile-header")
    private List<WebElement> dashTileHeaders;
    @FindBy(xpath = "/html/body/div/div[2]/div/div[1]/div/ul/li/a/i")
    private List<WebElement> sideWidgetIcons;
    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[2]/div[1]")
    private List<WebElement> sideWidgetAccountDashboardLinks;
    @FindBy(xpath = "//a[@data-original-title='Edit account details']")
    private WebElement editAccountDetailsLink;
    @FindBy(css = "#customer_menu_top")
    private WebElement welcomeBackMessage;
    @FindBy(xpath = "/html/body/div/div[2]/div/div[1]/div/ul/li[9]/a/i")
    private WebElement logout;
    @FindBy(css = ".alert.alert-success")
    private WebElement successYourAccountSuccessfullyUpdatedTxt;

    public boolean isLogoffLinkDisplayed() {
        return logoffLink.isDisplayed();

    }

    public void clickLogoff() {
        logoffLink.click();
        log.info("User Logged Out Successfully");
    }

    public String getAccountDashboardTxt() {
        return accountDashboardTxt.getText();

    }

    public LogoutPage clickOnLogoffButton() {
        logoffIcon.click();
        log.info("Clicked logoff icon");
        return new LogoutPage();
    }

    public boolean assertLogoffButtonIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(logoffIcon);

    }

    public void verifyAllSideWidgetElementsInAccountPageLinks() {
        List<WebElement> links = sideWidgetAccountDashboardLinks;
        for (int i = 0; i < links.size(); i = i++) {
            String listOfElements = links.get(i).getText();
            log.info("Size Of Side Widget List => " + links.size());
            break;
        }

    }

    public void ClickOnLogOffLinkInSideWidgetList() {
        List<WebElement> ListSideWidget = WebDrv.getInstance().getWebDriver().findElements(By.cssSelector(".side_account_list"));
        for (int i = 0; i < ListSideWidget.size(); i = i++) {
            String listName = ListSideWidget.get(i).getText();
            if (listName.contains("Logoff")) {
                WebDrv.getInstance().getWebDriver().findElements(By.linkText("Logoff")).get(i).click();
                break;
            }

        }

    }

    public String getAccountText() {
        return myAccountTxt.getText();

    }

    public String userProfileFirstName() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(userProfileFirstName);

    }

    public String getWelcomeMessageText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(welcomeBackMessage);

    }

    public boolean isUserProfileWelcomeMessageDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(welcomeBackMessage);

    }

    public String accountDashboardMessage() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(accountDashboardTxt);

    }

    public boolean isAccountDashboardDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(accountDashboardTxt);

    }

    public void clickOnLogoutButton() {
        logout.click();
        log.info("User Logged Out Successfully");

    }

    public EditProfilePage clickOnEditAccountDetailsLink() throws IOException, InterruptedException {
        Thread.sleep(3000);
        editAccountDetailsLink.click();
        return new EditProfilePage();

    }

    public String getAccountProfileSuccessfulUpdateMessage() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(successYourAccountSuccessfullyUpdatedTxt);

    }

    public boolean assertMyAccountPageIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(myAccountTxt);

    }

    public String getManageAddressBook() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(dashTileHeaders.get(0)).trim();

    }

    public String getOrderHistory() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(dashTileHeaders.get(1)).trim();

    }

    public String getDownloads() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(dashTileHeaders.get(2)).trim();

    }

    public String getTransactionHistory() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(dashTileHeaders.get(3)).trim();
    }

    public int getSideWidgetIconCount() {
        return sideWidgetIcons.size();

    }

    public boolean isLoginSuccessful() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(userProfileFirstName);
    }

}