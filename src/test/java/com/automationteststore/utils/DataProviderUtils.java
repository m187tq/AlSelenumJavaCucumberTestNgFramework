package com.automationteststore.utils;

import com.automationteststore.testData.javaFiles.GlobalVars;
import com.github.javafaker.Faker;
import net.minidev.json.JSONObject;
import org.testng.annotations.DataProvider;


public class DataProviderUtils {

    @DataProvider
    public static Object[][] dataProviderRegistrationData() {
        ReadExcelUtil excel = new ReadExcelUtil();
        String file = GlobalVars.REGISTRATIONDATA_EXCEL_FILE_PATH;
        return excel.Get_Excel_Data(file, GlobalVars.SHEET_NAME_REGISTRATION_DATA, 13);

    }

    @DataProvider
    public static Object[][] dataProviderRegisterData() {
        ReadExcelUtil excel = new ReadExcelUtil();
        //String file = "src/test/java/com/automationteststore/testData/excelFiles/RegistrationData.xlsx";
        String file = GlobalVars.REGISTRATIONDATA_EXCEL_FILE_PATH;
        return excel.Get_Excel_Data(file, GlobalVars.SHEET_NAME_REGISTER_DATA, 13);

    }

    @DataProvider
    public static Object[][] dataProviderLoginPositiveData() {
        ReadExcelUtil excel = new ReadExcelUtil();
        //String file = "src/test/java/com/automationteststore/testData/excelFiles/LoginData.xlsx";
        String file = GlobalVars.LOGINDATA_EXCEL_FILE_PATH;
        return excel.Get_Excel_Data(file, GlobalVars.SHEET_NAME_LOGIN_POSITIVE_DATA, 3);

    }

    @DataProvider
    public static Object[][] dataProviderLoginNegativeData() {
        ReadExcelUtil excel = new ReadExcelUtil();
        //String file = "src/test/java/com/automationteststore/testData/excelFiles/LoginData.xlsx";
        String file = GlobalVars.LOGINDATA_EXCEL_FILE_PATH;
        return excel.Get_Excel_Data(file, GlobalVars.SHEET_NAME_LOGIN_NEGATIVE_DATA, 3);

    }

    @DataProvider
    public static Object[][] dataProviderLoginData() {
        ReadExcelUtil excel = new ReadExcelUtil();
        //String file = "src/test/java/com/automationteststore/testData/excelFiles/LoginData.xlsx";
        String file = GlobalVars.LOGINDATA_EXCEL_FILE_PATH;
        return excel.Get_Excel_Data(file, "login", 3);

    }

    @DataProvider(name = "jsonPositiveData")
    public static Object[][] jsonPositiveData() {
        String filePath = "src/test/java/com/automationteststore/testData/jsonFiles/RegistrationData.json";
        return JsonUtils.getTestData(filePath, "positive");
    }

    @DataProvider(name = "jsonNegativeData")
    public static Object[][] jsonNegativeData() {
        String filePath = "src/test/java/com/automationteststore/testData/jsonFiles/RegistrationData.json";
        return JsonUtils.getTestData(filePath, "negative");
    }

/*    @DataProvider(name = "excelPositiveData")
    public static Object[][] excelPositiveData(){
        String filePath = "src/test/java/com/automationteststore/testData/excelFiles/RegistrationData.xlsx";
        return ExcelUtil.getTestData(filePath, "Positive");
    }

    @DataProvider(name = "excelNegativeData")
    public static Object[][] excelNegativeData(){
        String filePath = "src/test/java/com/automationteststore/testData/excelFiles/RegistrationData.xlsx";
        return ExcelUtil.getTestData(filePath, "Negative");
    }*/

    @DataProvider(name = "fakerData")
    public static Object[][] fakerData() {
        Faker faker = new Faker();
        Object[][] data = new Object[5][1]; // 5 sets of fake data
        for (int i = 0; i < 5; i++) {
            JSONObject obj = new JSONObject();
            obj.put("firstName", faker.name().firstName());
            obj.put("lastName", faker.name().lastName());
            obj.put("email", faker.internet().emailAddress());
            obj.put("address", faker.address().streetAddress());
            obj.put("city", faker.address().city());
            obj.put("state", faker.address().state());
            obj.put("postcode", faker.address().zipCode().substring(0, 5));
            obj.put("country", "United States");
            obj.put("telephone", faker.phoneNumber().subscriberNumber(10));
            obj.put("fax", faker.phoneNumber().subscriberNumber(10));
            obj.put("loginName", faker.name().username());
            obj.put("password", "Password123");
            obj.put("confirmPassword", "Password123");
            obj.put("coupon", "");
            obj.put("newsletterPreference", "Yes");
            data[i][0] = obj;
        }
        return data;
    }

}
