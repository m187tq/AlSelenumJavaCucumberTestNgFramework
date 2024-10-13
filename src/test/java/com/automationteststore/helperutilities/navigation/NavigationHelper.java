package com.automationteststore.helperutilities.navigation;

import com.automationteststore.helperutilities.logger.LoggerHelper;
import com.automationteststore.interfaces.IwebComponent;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.net.URL;


public class NavigationHelper implements IwebComponent {

    private WebDriver driver;
    private Logger log = LoggerHelper.getLogger(NavigationHelper.class);

    public NavigationHelper(WebDriver driver) {
        this.driver = driver;
        log.debug("NavigationHelper : " + WebDrv.getInstance().getWebDriver().hashCode());
    }

    public void navigateTo(String url) {
        log.info(url);
        WebDrv.getInstance().getWebDriver().get(url);
    }

    public void naviagteTo(URL url) {
        log.info(url.getPath());
        WebDrv.getInstance().getWebDriver().get(url.getPath());
    }

    public String getTitle() {
        String title = WebDrv.getInstance().getWebDriver().getTitle();
        log.info(title);
        return WebDrv.getInstance().getWebDriver().getTitle();
    }

    public String getCurrentUrl() {
        String url = WebDrv.getInstance().getWebDriver().getCurrentUrl();
        log.info(url);
        return WebDrv.getInstance().getWebDriver().getCurrentUrl();
    }


}
