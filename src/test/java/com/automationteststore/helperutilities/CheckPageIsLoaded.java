package com.automationteststore.helperutilities;

import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CheckPageIsLoaded {
    public static void checkPageIsReady(int loopCount) throws Exception {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(10));
        wait.until(pageLoadCondition);
    }

}
