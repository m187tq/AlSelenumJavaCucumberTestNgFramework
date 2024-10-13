package com.automationteststore.configReader;


import com.automationteststore.utilities.Settings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private final String propertyFilePath = "src/main/resources/config.properties";

    private static Properties properties;

    static {
        try {
            String path = "src/test/resources/config/config.properties";
            FileInputStream fis = new FileInputStream(path);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration properties file cannot be found.");
        }
    }

    public static String get_Property(String key) {
        return properties.getProperty(key);
    }

//===================================================================

    public ConfigReader() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(propertyFilePath);
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public void PopulateSettings() throws IOException {
        ConfigReader reader = new ConfigReader();
        reader.ReadProperty();
    }

    private void ReadProperty() throws IOException {
        //Create Property Object
        Properties p = new Properties();
        //Load the Property file available in same package
        p.load(getClass().getResourceAsStream("GlobalConfig.properties"));
        //Get AUTConnection String
        Settings.AUTConnectionString = p.getProperty("AUTConnectionString");
        //Get Reporting String
        Settings.ReportingConnectionString = p.getProperty("ReportingConnectionString");
        //Get LogPath
        Settings.LogPath = p.getProperty("LogPath");
        //Get DriverType
        Settings.DriverType = p.getProperty("DriverType");
        //GEt ExcelSheetPath
        Settings.ExcelSheetPath = p.getProperty("ExcelSheetPath");
        //Get AUT
        Settings.AUT = p.getProperty("AUT");
        //Browser Type
        //Settings.BrowserType = BrowserType.valueOf(p.getProperty("BrowserType"));
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }



}
