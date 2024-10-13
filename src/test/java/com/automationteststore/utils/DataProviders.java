package com.automationteststore.utils;

// src/main/java/com/automationteststore/utils/DataProviders.java

import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "jsonPositiveData")
    public static Object[][] jsonPositiveData() {
        String filePath = "src/main/resources/testdata/registrationData.json";
        return JsonUtils.getTestData(filePath, "positive");
    }

    @DataProvider(name = "jsonNegativeData")
    public static Object[][] jsonNegativeData() {
        String filePath = "src/main/resources/testdata/registrationData.json";
        return JsonUtils.getTestData(filePath, "negative");
    }

    @DataProvider(name = "excelPositiveData")
    public static Object[][] excelPositiveData() {
        String filePath = "src/test/java/com/automationteststore/testData/excelFiles/RegistrationData.xlsx";
        return ExcelUtils.getTestData(filePath, "registration");
    }

    @DataProvider(name = "excelNegativeData")
    public static Object[][] excelNegativeData() {
        String filePath = "src/test/java/com/automationteststore/testData/excelFiles/RegistrationData.xlsx";
        return ExcelUtils.getTestData(filePath, "registration");
    }

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

    @DataProvider(name = "productData")
    public Object[][] getProductData() {
        return new Object[][] {
                {"Apparel & accessories", "T-shirts", "Product1"},
                {"Makeup", "Lipstick", "Product2"},
                // Add more test data as needed
        };
    }

    // Similarly, add more DataProviders for different test cases

}

