package com.automationteststore.webdriverutilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class WebDriver {
    private static WebDriver instance = null;
    private org.openqa.selenium.WebDriver webDriver;

    private WebDriver() {
    }

    public static WebDriver getInstance() {
        if (instance == null) {
            instance = new WebDriver();
        }
        return instance;
    }

    public static void getScreenshot(WebElement element) throws IOException {
        File srcScreenShot = element.getScreenshotAs(OutputType.FILE);
        FileHandler.copy(srcScreenShot, new File(System.getProperty("user.dir") + "//screenShots/screenShot.png"));
    }

    public WebDriver openBrowser(String url, DesiredCapabilities caps) throws MalformedURLException {
        webDriver = WebDriverConfig
                .configureAndOpenBrowser(url, caps,
                        webDriver);
        return (WebDriver) webDriver;
    }

    public WebDriver getWebDriver() {
        return (WebDriver) webDriver;

    }

}
