/**
 * @author rahul.rathore
 * <p>
 * 06-Aug-2016
 */
package com.automationteststore.helperutilities.Generic;

import com.automationteststore.helperutilities.date.DateTimeHelper;
import com.automationteststore.helperutilities.logger.LoggerHelper;
import com.automationteststore.helperutilities.resource.ResourceHelper;
import com.automationteststore.interfaces.IwebComponent;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.File;
import java.io.IOException;

public class GenericHelper implements IwebComponent {

    private WebDriver driver;
    private Logger log = LoggerHelper.getLogger(GenericHelper.class);

    public GenericHelper(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(By locator) {
        log.info(locator);
        if (IsElementPresentQuick(locator))
            return WebDrv.getInstance().getWebDriver().findElement(locator);

        try {
            throw new NoSuchElementException("Element Not Found : " + locator);
        } catch (RuntimeException re) {
            log.error(re);
            throw re;
        }
    }

    /**
     * Check for element is present based on locator
     * If the element is present return the web element otherwise null
     *
     * @param locator
     * @return WebElement or null
     */

    public WebElement getElementWithNull(By locator) {
        log.info(locator);
        try {
            return WebDrv.getInstance().getWebDriver().findElement(locator);
        } catch (NoSuchElementException e) {
            // Ignore
        }
        return null;
    }

    public boolean IsElementPresentQuick(By locator) {
        boolean flag = WebDrv.getInstance().getWebDriver().findElements(locator).size() >= 1;
        log.info(flag);
        return flag;
    }

    public String takeScreenShot(String name) throws IOException {

        if (driver instanceof HtmlUnitDriver) {
            log.fatal("HtmlUnitDriver Cannot take the ScreenShot");
            return "";
        }

        File destDir = new File(ResourceHelper.getResourcePath("screenshots/")
                + DateTimeHelper.getCurrentDate());
        if (!destDir.exists())
            destDir.mkdir();

        File destPath = new File(destDir.getAbsolutePath()
                + System.getProperty("file.separator") + name + ".jpg");
        try {
            FileUtils
                    .copyFile(((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE), destPath);
        } catch (IOException e) {
            log.error(e);
            throw e;
        }
        log.info(destPath.getAbsolutePath());
        return destPath.getAbsolutePath();
    }

    public String takeScreenShot() {
        log.info("");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

}
