package com.automationteststore.helperutilities.assertion;

import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class VerificationHelper {

    private final Logger log = LogManager.getLogger(VerificationHelper.class);
    private final WebDriver driver;

    public VerificationHelper(WebDriver driver) {
        this.driver = driver;

    }

    public boolean isDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            log.info("Element is Displayed: " + element);
            return true;
        } catch (Exception e) {
            log.error("Element is not Displayed..");
            return false;
        }
    }

    public boolean assertItemDisplayedFromList(List<WebElement> listElements, String item) {
        return listElements.stream().parallel().anyMatch(s -> s.getText().equalsIgnoreCase(item));
    }

    public boolean isSelected(WebElement element) {
        try {
            System.out.println("The element is Selected :: " + "<<" + element + ">>");
            return true;
        } catch (Exception e) {
            log.error("Element is not Selected: ", e.getCause());
            return false;
        }
    }

    public boolean isDisplayedAndEnabled(WebElement element) {
        try {
            element.isDisplayed();
            element.isEnabled();
            log.info("Element is displayed and enabled :: " + element.getText());
            return true;
        } catch (Exception e) {
            log.error("Element is not displayed and enabled");
            return false;
        }
    }

    public boolean isEnabled(WebElement element) {
        try {
            element.isEnabled();
            log.info("Element is enabled :: " + element.getText());
            return true;
        } catch (Exception e) {
            log.error("Element is not enabled :: ", e.getCause());
            return false;
        }
    }

    public boolean isNotDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            log.info("element is displayed :: " + element.getText());
            return false;
        } catch (Exception e) {
            log.error("element is not displayed :: ");
            return true;
        }
    }

    public String readValueFromElement(WebElement element) {
        if (null == element) {
            log.info("WebElement is null..");
            return null;
        }
        boolean status = isDisplayed(element);
        if (status) {
            log.info("Element text is :: " + element.getText());
            return element.getText();
        } else {
            return null;
        }
    }


    public String getText(WebElement element) {
        if (null == element) {
            log.info("WebElement is null..");
            return null;
        }
        boolean status = isDisplayed(element);
        if (status) {
            log.info("Element text is displayed :: " + element);
            return element.getText().trim();
        } else {
            return null;
        }
    }

    public String getText(List<WebElement> elements) {
        if (null == elements) {
            System.out.println("WebElement is null..");
            return null;
        }
        boolean status = isDisplayed((WebElement) elements);
        if (status) {
            System.out.println("Element text is displayed:: " + elements);
            return ((WebElement) elements).getText().trim();
        } else {
            return null;
        }
    }

    public String getAttributeValue(WebElement element) {
        if (null == element) {
            log.info("WebElement is null..");
            return null;
        }
        boolean status = isDisplayed(element);
        if (status) {
            log.info("Element text is displayed :: " + element);
            return element.getAttribute("Value").trim();
        } else {
            return null;
        }
    }

    public String getTitle() {
        log.info("current page title is: " + WebDrv.getInstance().getWebDriver().getTitle());
        return WebDrv.getInstance().getWebDriver().getTitle();
    }

    public String getCurrentPageTitle() {
        log.info("current page title is :: " + WebDrv.getInstance().getWebDriver().getTitle());
        return WebDrv.getInstance().getWebDriver().getTitle();
    }

    public String getCurrentPageUrl() {
        log.info("current page url is :: " + WebDrv.getInstance().getWebDriver().getCurrentUrl());
        return WebDrv.getInstance().getWebDriver().getCurrentUrl();
    }

    public boolean isElementVisible(WebElement element) {
        return element.isDisplayed();

    }
}
