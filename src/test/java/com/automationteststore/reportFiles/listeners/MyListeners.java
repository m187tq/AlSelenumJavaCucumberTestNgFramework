package com.automationteststore.reportFiles.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class MyListeners implements ITestListener {

    ExtentReports extentReport;
    ExtentTest extentTest;
    String testName;

    @Override
    public void onStart(ITestContext context) {
        extentReport = ExtentReporter.generateExtentReport();

    }

    @Override
    public void onTestStart(ITestResult result) {
        testName = result.getName();
        extentTest = extentReport.createTest(testName);
        extentTest.log(Status.INFO, testName + " started executing");

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, testName + " got successfully executed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        /*String destinationScreenshotPath =new TestBase().captureScreenshot(driver, result.getName());
        extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.FAIL, testName + " got failed");*/

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.SKIP, result.getName() + " got skipped");

    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();

    }

}