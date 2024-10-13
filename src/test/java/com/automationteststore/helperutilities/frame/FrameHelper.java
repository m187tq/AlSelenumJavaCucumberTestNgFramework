package com.automationteststore.helperutilities.frame;

import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FrameHelper {

    WebDriver driver;
    private Logger log = LogManager.getLogger(this.getClass());

    public FrameHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToFrame(int frameIndex) {
        WebDrv.getInstance().getWebDriver().switchTo().frame(frameIndex);
        log.info("switched to :" + frameIndex + " frame");

    }

    public void switchToFrame(String frameName) {
        WebDrv.getInstance().getWebDriver().switchTo().frame(frameName);
        log.info("switched to :" + frameName + " frame");

    }

    public void switchToFrame(WebElement element) {
        WebDrv.getInstance().getWebDriver().switchTo().frame(element);
        log.info("switched to frame " + element.toString());
    }
}
