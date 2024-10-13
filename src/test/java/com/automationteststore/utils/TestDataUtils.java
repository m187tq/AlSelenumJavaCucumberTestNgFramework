package com.automationteststore.utils;

public class TestDataUtils {
    public static Object[][] getLoginData() {
        return new Object[][]{
                {"InvalidUser", "invalidPass", false},
                {"webdriverio2", "webdriverio2", true},
        };
    }

    public static Object[][] getRegistrationData() {
        return new Object[][]{
                {"validEmail@test.com", "validUser", "validPass", true},
                {"duplicateEmail@test.com", "validUser", "validPass", false},
        };
    }
}

