package com.automationteststore.webdriverutilities;

import com.automationteststore.testData.javaFiles.GlobalVars;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;
import java.util.HashMap;

public class WebDrv {
    private static WebDrv instance = null;
    private WebDriver webDriver;

    private WebDrv() {
    }

    public static WebDrv getInstance() {
        if (instance == null) {
            instance = new WebDrv();
        }
        return instance;
    }

    public static void setInstance() {
        instance = null;
    }

    public WebDriver openBrowser(String url, DesiredCapabilities caps) {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("behavior", "allow");
        chromePrefs.put("download.prompt_for_download", false);
//        chromePrefs.put("download.default_directory", AdminTestData.APPLICATIONS_DOWNLOAD_FILEPATH);
//        System.out.println("Download path set to: " + AdminTestData.APPLICATIONS_DOWNLOAD_FILEPATH);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--whitelisted-ips");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--test-type");
        //chromeOptions.addArguments("--headless");
        String osName = System.getProperty("os.name");
        if (osName.equalsIgnoreCase("Linux")) {
            System.setProperty("webWebDrv.getInstance().getWebDriver().chrome.driver", "chromedriver/linux/chromedriverlinux");
        } else {
            webDriver = new ChromeDriver();
            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(GlobalVars.IMPLICIT_TIMEOUT));
        }

        return webDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;

    }
}