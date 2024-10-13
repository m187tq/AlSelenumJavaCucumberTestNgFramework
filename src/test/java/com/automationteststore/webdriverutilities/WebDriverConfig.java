package com.automationteststore.webdriverutilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;
import java.util.HashMap;

public class WebDriverConfig {

    private WebDriver webDriver;

    public WebDriverConfig(WebDriver webDriver) {
        this.webDriver = webDriver;

    }

    public static WebDriver configureAndOpenBrowser(String url, DesiredCapabilities caps, WebDriver webDriver) {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(createChromeOptions());
        return webDriver;
    }

    private static ChromeOptions createChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("prefs", createChromePreferences());
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--whitelisted-ips");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--headless");
        return chromeOptions;
    }

    private static HashMap<String, Object> createChromePreferences() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        return chromePrefs;
    }

    public WebDriverConfig maximiseWindow() {
        WebDrv.getInstance().getWebDriver().manage().window().maximize();
        return this;
    }

    public WebDriverConfig setPageLoadTimeout(Duration pageLoadTime) {
        WebDrv.getInstance().getWebDriver().manage().timeouts().pageLoadTimeout(pageLoadTime);
        return this;
    }

}
