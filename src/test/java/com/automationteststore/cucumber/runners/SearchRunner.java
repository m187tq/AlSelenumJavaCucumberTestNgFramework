package com.automationteststore.cucumber.runners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        monochrome = true,
        features = {"src/test/java/com/automationteststore/cucumber/features/Search/"},
        glue = {"com.automationteststore.cucumber.stepDefinitions"},
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json",
                "html:cucumberReport/cucumber.html", "html:target/cucumber-reports.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        tags = "@Search_CompleteOrder",
        dryRun = false)

public class SearchRunner extends AbstractTestNGCucumberTests {
    private static ExtentReports extent;
    private static ExtentTest test;

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();

    }

    @BeforeSuite
    public void setup() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterSuite
    public void tearDown() {
        extent.flush();
    }

}
