package com.automationteststore.helperutilities.wait;

import com.automationteststore.helperutilities.Generic.GenericHelper;
import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.javaScript.JavaScriptHelper;
import com.automationteststore.helperutilities.logger.LoggerHelper;
import com.automationteststore.interfaces.IconfigReader;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WaitHelper extends GenericHelper {

    WebDriver driver;
    IconfigReader reader;
    private Logger log = LoggerHelper.getLogger(WaitHelper.class);

    public WaitHelper(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(NoSuchFrameException.class);
        return wait;
    }

    public void WaitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds,
                                                     int pollingEveryInMiliSec) {
        log.info("Waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
        wait.until(ExpectedConditions.visibilityOf(element));
        log.info("Element is visible now");
    }

    public void WaitForElementClickable(WebElement element, int timeOutInSeconds) {
        log.info("Waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(element));
        log.info("Element is clickable now");
    }

    public boolean waitForElementNotPresent(WebElement element, long timeOutInSeconds) {
        log.info("Waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
        log.info("Element is invisible now");
        return status;
    }

    public void waitForFrameToBeAvailableAndSwitchToIt(WebElement element, long timeOutInSeconds) {
        log.info("Waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVarsHelper.getExplicitWait()));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
        log.info("Frame is available and switched");
    }

    private Wait<WebDriver> getfluentWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
        Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class);
        return fWait;
    }

    public WebElement waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec) {
        Wait<WebDriver> fwait = getfluentWait(timeOutInSeconds, pollingEveryInMiliSec);
        fwait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public void pageLoadTime() {
        log.info("Waiting for page to load for ...");
        WebDrv.getInstance().getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(GlobalVarsHelper.getExplicitWait()));
        log.info("page is loaded");
    }

    public void waitForElement(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalVarsHelper.getExplicitWait()))
                .until(ExpectedConditions.visibilityOf(element));
        log.info("Waited and element is now visible :: " + element.getText());
    }

    public void setImplicitWait(int implicitWait, TimeUnit seconds) {
        WebDrv.getInstance().getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalVarsHelper.getExplicitWait()));
    }

    public void setPageLoadTimeout(int pageLoadTimeOut) {
        WebDrv.getInstance().getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeOut));
    }

    public void setImplicitWait(long timeout, TimeUnit unit) {
        log.info(timeout);
        driver
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    public void waitForElementVisible(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
        log.info(locator);
        setImplicitWait(1, TimeUnit.SECONDS);
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
        wait.until(ExpectedConditions.visibilityOf(WebDrv.getInstance().getWebDriver().findElement(locator)));
        setImplicitWait(reader.getImplicitWait(), TimeUnit.SECONDS);
    }

    public void hardWait(int timeOutInMiliSec) throws InterruptedException {
        log.info(timeOutInMiliSec);
        Thread.sleep(timeOutInMiliSec);
    }

    public WebElement handleStaleElement(By locator, int retryCount, int delayInSeconds) throws InterruptedException {
        log.info(locator);
        WebElement element = null;

        while (retryCount >= 0) {
            try {
                element = WebDrv.getInstance().getWebDriver().findElement(locator);
                return element;
            } catch (StaleElementReferenceException e) {
                hardWait(delayInSeconds);
                retryCount--;
            }
        }
        throw new StaleElementReferenceException("Element cannot be recovered");
    }

    public void elementExits(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
        log.info(locator);
        setImplicitWait(1, TimeUnit.SECONDS);
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
        wait.until(elementLocatedBy(locator));
        setImplicitWait(reader.getImplicitWait(), TimeUnit.SECONDS);
    }

    public void elementExistAndVisible(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
        log.info(locator);
        setImplicitWait(1, TimeUnit.SECONDS);
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
        wait.until(elementLocatedBy(locator));
        new JavaScriptHelper(driver).scrollIntoView((WebElement) locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        setImplicitWait(reader.getImplicitWait(), TimeUnit.SECONDS);

    }

    public void waitForIframe(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
        log.info(locator);
        setImplicitWait(1, TimeUnit.SECONDS);
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
        WebDrv.getInstance().getWebDriver().switchTo().defaultContent();
        setImplicitWait(reader.getImplicitWait(), TimeUnit.SECONDS);
    }

    private Function<WebDriver, Boolean> elementLocatedBy(final By locator) {
        return new Function<WebDriver, Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                log.debug(locator);
                return WebDrv.getInstance().getWebDriver().findElements(locator).size() >= 1;
            }
        };
    }
}
