package com.automationteststore.ai;

import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


public class LoginTest {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDrv.getInstance().getWebDriver().manage().window().maximize();
        WebDrv.getInstance().getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

        WebDrv.getInstance().getWebDriver().get("https://automationteststore.com/index.php?rt=account/login");
        WebElement usernameField = WebDrv.getInstance().getWebDriver().findElement(By.id("loginFrm_loginname"));
        WebElement passwordField = WebDrv.getInstance().getWebDriver().findElement(By.id("loginFrm_password"));
        WebElement loginButton = WebDrv.getInstance().getWebDriver().findElement(By.cssSelector("button[title='Login']"));
        usernameField.sendKeys("webdriverio2");
        passwordField.sendKeys("webdriverio2");
        loginButton.click();
        String currentUrl = WebDrv.getInstance().getWebDriver().getCurrentUrl();
        if (currentUrl.contains("account/account")) {
            System.out.println("Login successful! URL change verified.");
        } else {
            System.out.println("Login might have failed. URL did not change as expected.");
        }

        WebDrv.getInstance().getWebDriver().quit();

    }

}

