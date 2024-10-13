package com.automationteststore.utils;

import com.automationteststore.testData.javaFiles.GlobalVars;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class JsonReaderUtils {
    public Properties p;
    WebDriver driver;
    Properties prop;

    public WebDriver initializeDriver() throws IOException {

        // loading properties file
        FileReader file = new FileReader(GlobalVars.GLOBAL_PROPERTIES);
        p = new Properties();
        p.load(file);
        // properties class

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(GlobalVars.GLOBAL_PROPERTIES);
        prop.load(fis);
        return null;

    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath),
                StandardCharsets.UTF_8);

        //String to HashMap- Jackson Databind

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;

        //{map, map}

    }

}
