package com.automationteststore.testData.javaFiles;

public class GlobalVars {

    /**
     * Config Properties file
     **/

    public final static String SYSTEM_GET_PROPERTY = System.getProperty("user.dir");

    public final static String CONFIG_PROPERTIES_DIRECTORY = "properties\\com.automationteststore.config.properties";
    public final static String DATAPROVIDER_JSONPATH = System.getProperty("user.dir") + "//src//test//java//com.automationteststore.testData//data//RegistrationData.json";
    public final static String GLOBAL_PROPERTIES = System.getProperty("user.dir") + "//src//test//java/com//automationteststore//testData//propertiesFiles//GlobalData.properties";

    public final static String REGISTRATIONDATA_EXCEL_FILE_PATH = "src/test/java/com/automationteststore/testData/excelFiles/RegistrationData.xlsx";
    public final static String SHEET_NAME_REGISTRATION_DATA = "registrationData";
    public final static String SHEET_NAME_REGISTER_DATA = "registerData";
    public final static String SHEET_NAME_REGISTER = "register";


    public final static String LOGINDATA_EXCEL_FILE_PATH = "src/test/java/com/automationteststore/testData/excelFiles/LoginData.xlsx";
    public final static String SHEET_NAME_LOGIN_POSITIVE_DATA = "loginPositiveData";
    public final static String SHEET_NAME_LOGIN_NEGATIVE_DATA = "loginNegativeData";
    public final static String SHEET_NAME_LOGIN = "login";

    public final static String URL_HOMEPAGE = "https://automationteststore.com/";
    public final static String GOOGLE_HOMEPAGE_URL = "https://www.google.co.uk/";
    public final static String ACCOUNTCREATEPAGE_URL = "https://automationteststore.com/index.php?rt=account/create";

    public final static String LOGINPAGE_URL = "https://automationteststore.com/index.php?rt=account/login";

    public final static String ACCOUNTCREATEPAGE_TITLE = "Register Account";
    public final static String ACCOUNT_LOGIN_TXT = "ACCOUNT LOGIN";
    public final static String MYACCOUNT = "My Account";
    public final static String LOGIN = "Login";

    public final static String Browser = "Chrome";
    public final static String RETURNING_CUSTOMER = "Returning Customer";
    public final static String NEW_CUSTOMER = "New Customer";

    public final static String myAccountInformation = "My Account Information";
    public final static String REGISTER_JSON_PATH = "//src//test//java//com.automationteststore.testData.data//createAnAccount.json";

    public final static String WELCOME_BACK = "Welcome back";
    public final static String EMAIL_PASSWORD = "Manchester@1";
    public final static String RECOVERY_PASSWORD_LOGINNAME = "benazeta70";
    public final static String LOGIN_NAME = "webdriverio2";
    public final static String PASSWORD = "webdriverio2";
    public final static String accountSuccessfullyUpdated = "Success: Your account has been Success: Your account has been successfully updated..";

    public final static String wrong_password = "Password_Wrong";
    public final static String pass_word = "Password";

    public final static String CONTINUE_BUTTON = "Continue";

    public final static String HOMEPAGE_TITLE = "Your Store";
    public final static String ACCOUNT_LOGOUT_HEADER = "Account Logout";
    public final static String ACCOUNT_EDIT_URL_PATH = "account/edit";
    public final static String PRODUCTNAME_1 = "Skinsheen Bronzer Stick";
    public final static String Benefit_Bella_Bamba = "Benefit Bella Bamba";
    public final static String PRODUCTNAME_3 = "Absolue Eye Precious Cells";


    //=================================================================//
    public final static String LASTNAME = "John";
    public final static String FIRSTNAME = "Doe";
    public final static String EMAIL = "email0001@email.com";
    public final static String TELEPHONE = "0740500000000";
    public final static String FAX = "0740500000001";
    public final static String COMPANY = "ABC Ltd";
    public final static String ADDRESS1 = "123 Main St";
    public final static String ADDRESS2 = "LONDON SOUTH";
    public final static String CITY = "Manchester";
    public final static String STATE = "Lancashire";
    public final static String POSTCODE = "M13 0BS";
    public final static String COUNTRY = "United Kingdom";
    public final static String CONGRATULATION_SUCCESSFULLY_CREATED_MESSAGE = "Congratulations! Your new account has been successfully created!";
    public final static String REGION_STATE = "Lancashire";
    public final static String LOGINNAME = "loginname00001";
    public final static String PASS = "password00001";
    public final static String PASSWORD_CONFIRM = "password00001";
    public final static String wrongPassword = "WrongPassword00";

    public final static String INCORRECT_LOGIN_PASSWORD_PROVIDED = "Warning: No match for E-Mail Address and/or Password.";
    public final static String RECOVERY_PASSWORD_SUCCESS_MSG = "Success: Password reset link has been sent to your e-mail address";
    public final static String RECOVERY_LOGINNAME_SUCCESS_MSG = "Success: Your login name reminder has been sent to your e-mail address.";

    public final static String FORGET_YOUR_PASSWORD_LINK_TXT = "Forgot your password?";
    public final static String FORGET_YOUR_LOGINNAME_LINK_TXT = "Forgot your login?";
    public final static int explicitWait = 15;
    public final static int EXPLICITWAIT = 5;
    public final static int SHORT_IMPLICIT_WAIT_TIME = 5;
    public final static int THREE = 3;
    public final static int TWO = 2;
    public final static int ONE = 1;
    public final static int FIVE = 5;
    public final static int SIX = 6;
    public final static int expWait = 2;
    public final static int explicit_Wait = 2;
    public final static int IMPLICIT_TIMEOUT = 10;
    public final static int IMPLICIT_WAIT_TIMEOUT = 1;
    public final static int DEFAULT_EXPLICIT_TIMEOUT = 15;
    public final static int EXPLICIT_TIMEOUT = 30;
    public final static int POLLING_TIMEOUT = 250;
    public final static int PAGELOAD_TIMEOUT = 120;
    public final static int DEFAULT_IMPLICIT_TIMEOUT = 7;
    public static final int IMPLICIT_WAIT_TIME = 10;
    public static final int PAGE_LOAD_TIME = 15;
    public static final int EXPLICIT_WAIT_BASIC_TIME = 30;
    public final static int implicitWait = 10;
    // All characters validation
    public static final String ALL_CHARACTERS_VALIDATION = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz!#$%&'*+-/=?^_`\\:#£:@][{|}~;.,()";
    //PATHS Page
    public static final String JSON_DATA_TO_MAP = "//src//test//java//com//automationteststore//testData//jsonFiles//RegistrationData.json";
    public static final String JSON_DATA_TO_MAP_LOGIN_DATA = SYSTEM_GET_PROPERTY + "/src/test/java/com/automationteststore/testData/jsonFiles/LoginData.json";
    static String UPDATED_SUCCESSFULLY = "Success: Your account has been successfully updated";
    static String THANKS_FOR_SHOPPING_WITH_US = "Thank you for shopping with us!";

    public static String getMyAccount() {
        return MYACCOUNT;
    }

    public static String getLogin() {
        return LOGIN;
    }

    public static String getRecoveryPasswordAlertSuccessMsg() {
        return RECOVERY_PASSWORD_SUCCESS_MSG;
    }

    public static String getRecoveryLoginnameAlertSuccessMsg() {
        return RECOVERY_LOGINNAME_SUCCESS_MSG;
    }

    public static String getWarningIncorrectLoginPasswordMsg() {
        return INCORRECT_LOGIN_PASSWORD_PROVIDED;
    }

    public static String getForgotYourPasswordTxt() {
        return FORGET_YOUR_PASSWORD_LINK_TXT;
    }

    public static String getForgotYourLoginnameTxt() {
        return FORGET_YOUR_LOGINNAME_LINK_TXT;
    }

    public static String getSkinBronzerStick() {
        return PRODUCTNAME_1;
    }

    public static String getBenefitBellaBamba() {
        return Benefit_Bella_Bamba;
    }

    public static String getAbsolueEyePreciousCells() {
        return PRODUCTNAME_3;
    }

    public static String getConfigPropertiesDirectory() {
        return CONFIG_PROPERTIES_DIRECTORY;
    }

    public static String getDataProviderJsonPath() {
        return DATAPROVIDER_JSONPATH;
    }

    public static String getHomePageTitle() {
        return HOMEPAGE_TITLE;
    }

    public static String getRegisterJsonPath() {
        return REGISTER_JSON_PATH;
    }

    public static String getHomePageUrl() {
        return URL_HOMEPAGE;
    }

    public static String getAccountCreatePageUrl() {
        return ACCOUNTCREATEPAGE_URL;
    }

    public static String getLoginPageUrl() {
        return LOGINPAGE_URL;
    }

    public static String getAccountCreatePageTitle() {
        return ACCOUNTCREATEPAGE_TITLE;
    }

    public static String getGoogleHomepageUrl() {
        return GOOGLE_HOMEPAGE_URL;
    }

    public static String getRecoveryPassword_Username() {
        return RECOVERY_PASSWORD_LOGINNAME;
    }

    public static String getWrongPassword() {
        return wrong_password;
    }

    public static String getContinueBtnText() {
        return CONTINUE_BUTTON;
    }

    public static String getReturningCustomer() {
        return RETURNING_CUSTOMER;
    }

    public static String getAccountLogin() {
        return ACCOUNT_LOGIN_TXT;
    }

    public static String getNewCustomer() {
        return NEW_CUSTOMER;
    }

    public static String getThanksForShoppingWithUs() {
        return THANKS_FOR_SHOPPING_WITH_US;
    }

    public static int getExplicitWait() {
        return explicitWait;
    }

    public static int explicitWait() {
        return expWait;
    }

    public static int getExplicit_Wait() {
        return explicit_Wait;
    }

    public static String getPass() {
        return pass_word;
    }

    public static String getWrongPass() {
        return wrong_password;
    }

    public static int getImplicitWait() {
        return implicitWait;
    }

    public static int getPollinTimeout() {
        return POLLING_TIMEOUT;
    }

    public static int getDefaultExplicitTimeout() {
        return DEFAULT_EXPLICIT_TIMEOUT;
    }

    public static int getMaxExplicitTimeout() {
        return EXPLICIT_TIMEOUT;
    }

    public static int getPageLoadTimeout() {
        return PAGELOAD_TIMEOUT;
    }

    public static int getDefaultImplicitTimeout() {
        return DEFAULT_IMPLICIT_TIMEOUT;
    }

    public static String getUpdatedSuccessfully() {
        return UPDATED_SUCCESSFULLY;
    }

    public static String getAccountLogoutHeaderTxt() {
        return ACCOUNT_LOGOUT_HEADER;
    }

    public static String getJsonDataToMapPath() {
        return JSON_DATA_TO_MAP;
    }

    public static String getEmailPassword() {
        return EMAIL_PASSWORD;
    }

    public static String getRecoveryLoginnameLastName() {
        return RECOVERY_PASSWORD_LOGINNAME;
    }
}

