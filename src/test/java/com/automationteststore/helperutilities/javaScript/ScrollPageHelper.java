package com.automationteststore.helperutilities.javaScript;

import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollPageHelper {
    private WebDriver driver;
    private Logger log = LogManager.getLogger(ScrollPageHelper.class);

    public ScrollPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToView(WebElement elementName) throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementName);
        Thread.sleep(2000);
    }


    public void scrollToViewContinue(WebDriver driver) {
        try {
            scrollToView(WebDrv.getInstance().getWebDriver().findElement(By.id("continue")));
        } catch (Exception e) {
            log.info(e);
        }
    }

    public void scrollToViewElement(WebDriver driver, String elementID) {
        try {
            scrollToView(WebDrv.getInstance().getWebDriver().findElement(By.id(elementID)));
        } catch (Exception e) {
            log.info(e);
        }
    }

    public void scrollToBottomOfThePage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
    }

}
