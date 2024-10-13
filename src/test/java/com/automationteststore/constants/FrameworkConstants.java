package com.automationteststore.constants;


import com.automationteststore.enums.ConfigProperties;
import com.automationteststore.utils.PropertyUtils;

public final class FrameworkConstants {

    public static final String URL = "https://automationteststore.com/";
    public static final String File_Path = "\\Users\\benaz\\IdeaProjects\\selenium-java-cucumber-testng\\src\\test\\java\\com.automationteststore.testData\\";
    public static final String File_Name = "ExcelData.xlsx";
    public final static String CONFIG_PROPERTIES_DIRECTORY = "properties\\com.automationteststore.config.properties";
    public static final String EXTENTCONFIGPATH = System.getProperty("user.dir") + "\\src\\test\\resources\\extentreport.xml";
    public static final String TESTDATASHEETNAME = "TestDataUtils";
    public final static String GECKO_DRIVER_DIRECTORY = System.getProperty("user.dir") + "drivers\\geckoWebDrv.getInstance().getWebDriver().exe";
    public final static String CHROME_DRIVER_DIRECTORY = System.getProperty("user.dir") + "drivers\\chromeWebDrv.getInstance().getWebDriver().exe";
    public final static String loginname = "datastudioplace";
    public final static String password = "Manchester1";
    public final static int explicitWait = 15;
    public final static int implicitWait = 15;
    private static final int EXPLICITWAIT = 10;
    private static final String RESOURCESPATH = System.getProperty("user.dir") + "/src/test/java/com/automationteststore";
    private static final String CONFIGFILEPATH = RESOURCESPATH + "/config/configfile/config.properties";
    private static final String JSONCONFIGFILEPATH = System.getProperty("user.dir") + "/resources/jsonFiles/config.json";
    //private static final String EXCELPATH = RESOURCESPATH + "/testData/excel/testData.xlsx";
    private static final String EXCELPATH = System.getProperty("user.dir") + "/resources/excel/testdata.xlsx";
    private static final String RUNMANGERSHEET = "RUNMANAGER";
    private static final String ITERATIONDATASHEET = "DATA";
    private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/extent-test-output/";
    public static String RUNMODE_COL = "Runmode";
    public static String DATA_SHEET = "TestDataUtils";
    public static String SUITE_SHEET = "Suite";
    public static String SUITENAME_COL = "SuiteName";
    public static String TESTCASE_SHEET = "TestCases";
    public static String TESTCASE_COL = "TestCases";
    public static String RUNMODE_YES = "Y";
    public static String RUNMODE_NO = "N";
    public static String SUITE_XL_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\Suite.xlsx";
    public static String SUITE1_XL_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\BankManagerSuite.xlsx";
    public static String SUITE2_XL_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\CustomerSuite.xlsx";
    private static String extentReportFilePath = "";

    private FrameworkConstants() {
    }

    public static String getExtentReportFilePath() {
        if (extentReportFilePath.isEmpty()) {
            extentReportFilePath = createReportPath();
        }
        return extentReportFilePath;
    }

    private static String createReportPath() {
        if (PropertyUtils.get(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("no")) {
            return EXTENTREPORTFOLDERPATH + System.currentTimeMillis() + "/index.html";
        } else {
            return EXTENTREPORTFOLDERPATH + "/index.html";
        }
    }


    public static String getExcelpath() {
        return EXCELPATH;

    }

    public static String getJsonconfigfilepath() {
        return JSONCONFIGFILEPATH;

    }

    public static int getExplicitwait() {
        return EXPLICITWAIT;

    }

    public static String getRunmangerDatasheet() {
        return RUNMANGERSHEET;

    }

    public static String getIterationDatasheet() {
        return ITERATIONDATASHEET;

    }

    public static String getConfigFilePath() {
        return CONFIGFILEPATH;

    }

}
