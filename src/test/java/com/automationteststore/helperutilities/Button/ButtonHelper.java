/**
 * rsr
 * <p>
 * Aug 6, 2016
 */
package com.automationteststore.helperutilities.Button;

import com.automationteststore.helperutilities.logger.LoggerHelper;
import com.automationteststore.interfaces.IwebComponent;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ButtonHelper implements IwebComponent {

    private WebDriver driver;
    private Logger log = LoggerHelper.getLogger(ButtonHelper.class);

    public ButtonHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By locator) {
        click(WebDrv.getInstance().getWebDriver().findElement(locator));
        log.info(locator);
    }

    public void click(WebElement element) {
        element.click();
        log.info(element);
    }
}
