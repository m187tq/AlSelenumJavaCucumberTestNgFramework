package com.automationteststore.tests.Login;

import com.automationteststore.testData.javaFiles.GlobalVars;
import com.automationteststore.utils.JsonDataReaderUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class LoginTest_Using_Json_Input {
    static WebDriver driver;


    @Test(dataProvider = "getData")
    public void jsonTest(HashMap<String, String> input) {
        // Set up WebDriver (assuming ChromeDriver)
        driver = new ChromeDriver();

        // Navigate to the login page
        driver.get("https://automationteststore.com/index.php?rt=account/login");
        try {
            // Read test data from JSON file

            String email = input.get("loginname");
            String password = input.get("password");
            String expectedResult = input.get("expectedResult"); // Assuming you have an expected result in JSON

            // Locate elements based on outerHTML (adjust selectors if needed)
            WebElement emailInput = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.id("loginFrm_loginname")));
            WebElement passwordInput = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.id("loginFrm_password")));
            WebElement loginButton = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[title='Login']")));

            // Input test data
            emailInput.clear();
            emailInput.sendKeys(email);
            passwordInput.clear();
            passwordInput.sendKeys(password);

            // Click login button
            loginButton.click();

            // Add assertions here to verify login success/failure based on expectedResult
            if (expectedResult.contains("Welcome back")) {
                // Check for presence of a welcome message or account dashboard element
                WebElement welcomeMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='menu_text']")));
                assert welcomeMessage.isDisplayed();
            } else {
                // Check for presence of an error message
                WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".alert.alert-error.alert-danger"))); // Adjust selector if needed
                assert errorMessage.isDisplayed();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        JsonDataReaderUtils jsonDataReaderUtils = new JsonDataReaderUtils();
        List<HashMap<String, String>> data = jsonDataReaderUtils.getJsonDataToMap(GlobalVars.JSON_DATA_TO_MAP_LOGIN_DATA);
        return new Object[][]{{data.get(0)}, {data.get(1)}, {data.get(2)}, {data.get(3)}, {data.get(4)}};

    }

}
