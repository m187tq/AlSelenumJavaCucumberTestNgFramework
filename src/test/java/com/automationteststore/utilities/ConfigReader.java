package com.automationteststore.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    public Properties initializeProperties() {

        Properties prop = new Properties();
        File proFile = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\com.automationteststore.config\\com.automationteststore.config.properties");
        FileInputStream fis;
        try {
            fis = new FileInputStream(proFile);
            prop.load(fis);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return prop;

    }
}
