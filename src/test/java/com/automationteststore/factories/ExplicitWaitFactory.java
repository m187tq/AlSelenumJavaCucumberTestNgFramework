package com.automationteststore.factories;

import com.automationteststore.enums.WaitStrategy;
import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class ExplicitWaitFactory {

    private ExplicitWaitFactory() {

    }

    public static WebElement performExplicitWait(WaitStrategy waitstrategy, By by) {
        WebElement element = null;
        if (waitstrategy == WaitStrategy.CLICKABLE) {
            element = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait))
                    .until(ExpectedConditions.elementToBeClickable(by));
        } else if (waitstrategy == WaitStrategy.PRESENCE) {
            element = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        } else if (waitstrategy == WaitStrategy.HANDLE_STALE_ELEMENT) {
            element = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait)).until(d -> {
                System.out.println("Searching for the element...");
                d.navigate().refresh();
                return d.findElement(by);
            });
        } else if (waitstrategy == WaitStrategy.NONE) {
            element = WebDrv.getInstance().getWebDriver().findElement(by);
        } else if (waitstrategy == WaitStrategy.VISIBLE) {
            element = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
        } else if (waitstrategy == WaitStrategy.URL_CONTAINS) {
            new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait))
                    .until(ExpectedConditions.urlContains("www"));
        }

        return element;
    }

}
