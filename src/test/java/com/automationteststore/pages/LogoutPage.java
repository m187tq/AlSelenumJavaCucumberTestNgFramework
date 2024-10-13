package com.automationteststore.pages;

import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LogoutPage {
    private final static Logger log = LogManager.getLogger(LogoutPage.class);

    @FindBy(css = ".mb40 > a[title='Continue']")
    private WebElement logoutAccountContinueBtn;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div/div/div/section/a")
    private WebElement logoutAccountContinueButton;
    @FindBy(css = ".heading1")
    private WebElement accountLogoutTxt;

    public String getLogoutAccountContinueButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(logoutAccountContinueBtn);

    }

    public String getAccountLogoutText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(accountLogoutTxt);

    }

    public HomePage clickOnLogoutContinueButton() {
        new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(5))
                .until((s) -> logoutAccountContinueBtn.isDisplayed());
        logoutAccountContinueBtn.click();
        log.info("Clicked logout continue button");
        return new HomePage();
    }

    public String getLogoutContinueButtonTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(logoutAccountContinueButton);

    }

    public boolean assertContinueButtonIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(logoutAccountContinueButton);

    }

}
