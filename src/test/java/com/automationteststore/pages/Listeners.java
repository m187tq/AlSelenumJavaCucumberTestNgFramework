package com.automationteststore.pages;

import com.automationteststore.base.BaseTest;
import com.automationteststore.listeners.ExtentReporterNG;
import com.automationteststore.webdriverutilities.WebDrv;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    Page page;
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);//unique thread id(ErrorValidationTest)->test
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        extentTest.get().log(Status.PASS, "Test Passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        // TODO Auto-generated method stub
        extentTest.get().fail(result.getThrowable());//

        try {
            //driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        String filePath = null;
        try {
            // Capture screenshot and attach to report.
            page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
            filePath = new BaseTest().getScreenshot(result.getMethod().getMethodName(), WebDrv.getInstance().getWebDriver());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        extent.flush();

    }


}
