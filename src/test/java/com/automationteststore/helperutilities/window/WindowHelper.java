package com.automationteststore.helperutilities.window;

import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class WindowHelper {

    private WebDriver driver;
    private Logger log = LogManager.getLogger(WindowHelper.class);

    public WindowHelper(WebDriver driver) {
        this.driver = driver;

    }

    public void checkPageIsReady(int loopCount) throws Exception {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVarsHelper.explicitWait));
        wait.until(pageLoadCondition);
    }

    public void switchToParentWindow() {
        log.info("switching to parent window...");
        WebDrv.getInstance().getWebDriver().switchTo().defaultContent();
    }

    public void switchToWindow(int index) {
        Set<String> windows = WebDrv.getInstance().getWebDriver().getWindowHandles();
        int i = 1;
        for (String window : windows) {
            if (i == index) {
                log.info("switched to : " + index + " window");
                WebDrv.getInstance().getWebDriver().switchTo().window(window);
            } else {
                i++;
            }
        }
    }

    public void closeAllTabsAndSwitchToMainWindow() {
        Set<String> windows = WebDrv.getInstance().getWebDriver().getWindowHandles();
        String mainWindow = WebDrv.getInstance().getWebDriver().getWindowHandle();

        for (String window : windows) {
            if (!window.equalsIgnoreCase(mainWindow)) {
                WebDrv.getInstance().getWebDriver().close();
            }
        }
        log.info("switched to main window");
        WebDrv.getInstance().getWebDriver().switchTo().window(mainWindow);
    }

    public void navigateBack() {
        log.info("navigating back");
        WebDrv.getInstance().getWebDriver().navigate().back();
    }

    public void navigateForward() {
        log.info("navigating forward");
        WebDrv.getInstance().getWebDriver().navigate().forward();
    }

    public void navigateToNewTab() throws Exception {
        String parent = WebDrv.getInstance().getWebDriver().getWindowHandle();
        Set<String> s1 = WebDrv.getInstance().getWebDriver().getWindowHandles();
        Iterator<String> I1 = s1.iterator();
        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                Thread.sleep(500);
                WebDrv.getInstance().getWebDriver().switchTo().window(child_window);

            }
        }
    }

    public void closeChildWindow() throws Exception {
        String parent = WebDrv.getInstance().getWebDriver().getWindowHandle();
        Set<String> s1 = WebDrv.getInstance().getWebDriver().getWindowHandles();
        Iterator<String> I1 = s1.iterator();
        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                Thread.sleep(500);
                WebDrv.getInstance().getWebDriver().switchTo().window(child_window).close();

            }
        }
        WebDrv.getInstance().getWebDriver().switchTo().window(parent);
    }
}
