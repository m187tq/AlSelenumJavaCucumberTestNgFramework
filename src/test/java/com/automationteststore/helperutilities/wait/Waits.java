package com.automationteststore.helperutilities.wait;

import com.automationteststore.testData.javaFiles.GlobalVars;
import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.BooleanSupplier;


public class Waits {
    static int timeinSec = 30;


    public static void setImplicitWait() {
        WebDrv.getInstance().getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalVars.getImplicitWait()));
    }


    public static void waitUntilElementLocated(WebElement element) {
        WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVars.getImplicitWait()));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitUntilElementToClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVars.getImplicitWait()));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static synchronized Object execJavascript(String script, Object... args) {
        JavascriptExecutor scriptExe = ((JavascriptExecutor) WebDrv.getInstance().getWebDriver());
        return scriptExe.executeScript(script, args);
    }

    public static synchronized Object tryJavascript(String script, Object... args) {
        try {
            return execJavascript(script, args);
        } catch (Exception ignore) {
            return "";
        }
    }

    public static boolean isPageLoaded() {
        String state = (String) tryJavascript("return document.readyState;");
        return state.matches("complete|loaded|interactive");
    }

    public static boolean isJQueryDone() {
        Object jsResponse = tryJavascript("return jQuery.active;");
        if (jsResponse instanceof Long) {
            return ((Long) jsResponse) == 0;
        } else if (jsResponse instanceof String) {
            String response = (String) jsResponse;
            return (response.startsWith("{\"hCode\"") || response.isEmpty());
        } else {
            return true;
        }
    }

    public static boolean isAngularDone() {
        Object jsResponse = tryJavascript("return window.getAllAngularTestabilities().filter(x=>!x.isStable()).length;");
        if (jsResponse instanceof Long) {
            return ((Long) jsResponse) == 0;
        } else if (jsResponse instanceof String) {
            String response = (String) jsResponse;
            return response.isEmpty();
        } else {
            return true;
        }
    }

    public static void waitUntil(BooleanSupplier condition, int seconds) {
        new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(seconds)).until((WebDriver driver) -> condition.getAsBoolean());
    }

    public static void waitUntil(BooleanSupplier condition) {
        waitUntil(condition, timeinSec);

    }


}
