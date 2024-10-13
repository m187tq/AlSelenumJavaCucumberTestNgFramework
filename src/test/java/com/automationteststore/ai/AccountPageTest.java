package com.automationteststore.ai;

import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class AccountPageTest {

    public static void main(String[] args) {
        // ... (WebDriver setup and navigation to the page)

        WebDriver driver = new ChromeDriver();

        // Navigate to the login page
        WebDrv.getInstance().getWebDriver().get("https://automationteststore.com/index.php?rt=account/login");
        WebDrv.getInstance().getWebDriver().manage().window().maximize();
        WebDrv.getInstance().getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));


        // Locate elements using IDs (best practice if available)
        WebElement usernameField = WebDrv.getInstance().getWebDriver().findElement(By.id("loginFrm_loginname"));
        WebElement passwordField = WebDrv.getInstance().getWebDriver().findElement(By.id("loginFrm_password"));
        WebElement loginButton = WebDrv.getInstance().getWebDriver().findElement(By.cssSelector("button[title='Login']"));

        // Enter login credentials
        usernameField.sendKeys("webdriverio2");
        passwordField.sendKeys("webdriverio2");

        // Click the login button
        loginButton.click();

    }


}