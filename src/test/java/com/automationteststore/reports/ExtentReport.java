package com.automationteststore.reports;

import com.automationteststore.constants.FrameworkConstants;
import com.automationteststore.enums.CategoryType;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class ExtentReport {


    private static ExtentReports extent;

    /**
     * Private constructor to avoid external instantiation
     */
    private ExtentReport() {
    }

    /**
     * Set the initial configuration for the Extent Reports and decides the report generation path.
     *
     * @author Amuthan Sakthivel
     * Jan 22, 2021
     */
    public static void initReports() {
        if (Objects.isNull(extent)) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath());
            extent.attachReporter(spark);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("TMB Report");
            spark.config().setReportName("Youtube Training");
        }
    }

    /**
     * Flushing the reports ensures extent logs are reflected properly.
     * Opens the report in the default desktop browser.
     * Sets the ThreadLocal variable to default value
     *
     * @author Amuthan Sakthivel
     * Jan 22, 2021
     */
    public static void flushReports() {
        if (Objects.nonNull(extent)) {
            extent.flush();
        }
        ExtentManager.unload();
        try {
            Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createTest(String testcasename) {
        ExtentManager.setExtentTest(extent.createTest(testcasename));
    }

    public static void addAuthors(String[] authors) {
        for (String temp : authors) {
            ExtentManager.getExtentTest().assignAuthor(temp);
        }
    }

    public static void addCategories(CategoryType[] categories) {
        for (CategoryType temp : categories) {
            ExtentManager.getExtentTest().assignCategory(temp.toString());
        }
    }


}
