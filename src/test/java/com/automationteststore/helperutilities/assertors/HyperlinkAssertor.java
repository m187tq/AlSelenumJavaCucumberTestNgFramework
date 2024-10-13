package com.automationteststore.helperutilities.assertors;

import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;


public class HyperlinkAssertor {

    static WebDriver driver;

    public static void assertNewTabIsOpenedWithExpectedPage(String expectedPageName) {
        List<String> browserTabs = new ArrayList<String>(WebDrv.getInstance().getWebDriver().getWindowHandles());
        // switch to new tab
        WebDrv.getInstance().getWebDriver().switchTo().window(browserTabs.get(1));
        // check is it correct page opened or not (e.g. check page's title)
        Assert.assertEquals("The opened page is not what was expected", expectedPageName,
                WebDrv.getInstance().getWebDriver().findElement(By.id("heading")).getText());
        // then close tab and get back
        WebDrv.getInstance().getWebDriver().close();
        WebDrv.getInstance().getWebDriver().switchTo().window(browserTabs.get(0));
    }

    public static void assertNewTabOpenedWithExpectedTitle(String pageTitle) {
        List<String> browserTabs = new ArrayList<String>(WebDrv.getInstance().getWebDriver().getWindowHandles());
        // switch to new tab
        WebDrv.getInstance().getWebDriver().switchTo().window(browserTabs.get(1));
        // check is it correct page opened or not (check page's title)
        Assert.assertEquals("The opened page title did not match", pageTitle,
                WebDrv.getInstance().getWebDriver().getTitle());
        // then close tab and get back
        WebDrv.getInstance().getWebDriver().close();
        WebDrv.getInstance().getWebDriver().switchTo().window(browserTabs.get(0));
    }
}
