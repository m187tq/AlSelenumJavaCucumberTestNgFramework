package com.automationteststore.base;

import com.automationteststore.pages.HomePage;
import com.automationteststore.pages.TopMenuPage;
import com.automationteststore.webdriverutilities.WebDrv;
import net.minidev.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class BaseTest {
    public TopMenuPage topMenuPage;
    public HomePage homePage;


    public Properties readPropertiesData(String filePath) throws IOException {
        FileReader reader = new FileReader(filePath);
        Properties properties = new Properties();
        properties.load(reader);
        return properties;

    }

    public JSONObject readJSONData(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), JSONObject.class);
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
            return null;
        }
    }


    public List<Map<String, String>> readExcelData(String filePath, String sheetName) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            Row headerRow = sheet.getRow(0); // Assuming the first row contains headers

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Map<String, String> rowData = new HashMap<>();

                for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                    Cell headerCell = headerRow.getCell(j);
                    Cell cell = row.getCell(j);
                    String headerValue = headerCell.getStringCellValue();
                    String cellValue = cell != null ? cell.toString() : ""; // Handle null cells
                    rowData.put(headerValue, cellValue);
                }

                data.add(rowData);
            }
        }

        return data;
    }

    public String getScreenshot(String testCaseName, WebDriver webDriver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) WebDrv.getInstance().getWebDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

    }

    @BeforeMethod(alwaysRun = true)
    public TopMenuPage launchApplication() {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        topMenuPage.navigateToHomePage();
        return topMenuPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (WebDrv.getInstance().getWebDriver() != null) {
            System.out.println("After method teardown");
            WebDrv.getInstance().getWebDriver().quit();
        }
    }


    public void getApplicationUrl(String url){
        WebDrv.getInstance().getWebDriver().get(url);

    }

}
