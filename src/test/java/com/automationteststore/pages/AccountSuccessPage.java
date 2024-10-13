package com.automationteststore.pages;


import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.helperutilities.javaScript.JavaScriptHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;


public class AccountSuccessPage {
    private final Logger log = LogManager.getLogger(AccountSuccessPage.class);
    Page page;
    AsideWidgetPage asideWidgetPage;
    @FindBy(xpath = "//body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/h2[1]/span[1]")
    private WebElement myAccountText;
    @FindBy(css = ".btn.btn-default.mr10")
    private WebElement continueAccountSuccessBtn;
    @FindBy(css = ".maintext")
    private WebElement yourAccountHasBeenCreatedHeadingTxt;
    @FindBy(css = ".mb40")
    private WebElement congratulationsMsg;

    @FindBy(xpath = "//*[@id=\"maincontainer\"]/div/div/div/div/section/p[2]")
    private WebElement congratMsg;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div[1]/div/div/section/p[2]")
    private WebElement accountSuccessfullyCreatedMsg;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    private WebElement msgConfirmation;

    @FindBy(xpath = "//*[@id='content']/div/a")
    private WebElement continueBtn;

    @FindBy(css = "a.btn.btn-primary")
    private WebElement continueButton;

    public String getMyAccountText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(myAccountText);

    }

    public String getAccountHasBeenCreatedHeadingText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(yourAccountHasBeenCreatedHeadingTxt);

    }

    public AccountPage clickOnContinueAccountSuccessButton() {
        continueAccountSuccessBtn.click();
        log.info("Getting text:: " + continueAccountSuccessBtn.getText());
        return new AccountPage();

    }


    public String getContinueButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(continueButton);

    }

    public String getYourAccountHasBeenCreatedText() {
        new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(5))
                .until((s) -> yourAccountHasBeenCreatedHeadingTxt.isDisplayed());
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(yourAccountHasBeenCreatedHeadingTxt);

    }

    public String getYourAccountHasBeenCreatedHeadingText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(yourAccountHasBeenCreatedHeadingTxt);

    }

    public boolean assertYourAccountHasBeenCreatedHeadingTextDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(accountSuccessfullyCreatedMsg);

    }

    public boolean assertCongratulationsAccountSuccessfullyCreatedTextIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(congratulationsMsg);

    }

    public String getCongratulationsAccountSuccessfullyCreatedText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(congratMsg);

    }

    public String getAccountSuccessPageHeading() {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        return page.getThisPageHeaderText();

    }

    public boolean assertContinueButtonIsEnabledAndDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayedAndEnabled(continueButton);
    }

    public ShoppingCartPage clickContinueButton() {
        new JavaScriptHelper(WebDrv.getInstance().getWebDriver()).scrollToElementAndClick(continueButton);
        return new ShoppingCartPage();
    }

    public AccountPage clickContinueButtonInSuccessPage() {
        continueAccountSuccessBtn.click();
        return new AccountPage();
    }

    public LogoutPage clickLogOffButtonInAsIdeWidget() throws IOException {
        asideWidgetPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AsideWidgetPage.class);
        asideWidgetPage.tapLogoffLink();
        return new LogoutPage();
    }

    public WebElement getMsgConfirmation() {
        return msgConfirmation;

    }

    public WebElement getContinueButton() {
        return continueBtn;

    }


}
