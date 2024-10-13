package com.automationteststore.utils;

import com.automationteststore.testData.javaFiles.GlobalVars;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

    public static String get(String propertyname) {

        String returnproperty = null;
        Properties property = new Properties();
        try {
            FileInputStream file = new FileInputStream(GlobalVars.GLOBAL_PROPERTIES);
            property.load(file);
            returnproperty = property.getProperty(propertyname);
            if (returnproperty == null) {
                throw new Exception("Property named " + propertyname + "not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnproperty;

    }

    public Properties loadPropertiesFile(String pathname) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(pathname);
        prop.load(fis);
        return prop;
    }

}
