package com.automationteststore.helperutilities;

import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ScrollPage {

    @FindBy(css = "button[title='Continue']")
    private static WebElement continueButton;
    @FindBy(css = "button[title='Login']")
    private static WebElement loginButton;

    public static void scrollToView(WebElement elementName) throws Exception {
        ((JavascriptExecutor) WebDrv.getInstance().getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", elementName);
        Thread.sleep(500);
    }

    public static void scrollToViewAndClickContinue() {
        try {
            scrollToView(continueButton);
            continueButton.click();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void scrollToViewAndClickLoginButton() {
        try {
            scrollToView(loginButton);
            loginButton.click();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void scrollToViewContinue(WebDriver driver) {
        try {
            scrollToView(WebDrv.getInstance().getWebDriver().findElement(By.cssSelector("button[title='Continue']")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void scrollToLoginButton(WebDriver driver) {
        try {
            scrollToView(WebDrv.getInstance().getWebDriver().findElement(By.cssSelector("button[title='Login']")));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void scrollToViewElement(WebDriver driver, String elementID) {
        try {
            scrollToView(WebDrv.getInstance().getWebDriver().findElement(By.id(elementID)));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void scrollToBottomOfThePage() {
        JavascriptExecutor js = (JavascriptExecutor) WebDrv.getInstance().getWebDriver();
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
    }

}
