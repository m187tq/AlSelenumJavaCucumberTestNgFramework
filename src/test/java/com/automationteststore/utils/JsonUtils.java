package com.automationteststore.utils;

import com.automationteststore.constants.FrameworkConstants;
import com.automationteststore.enums.ConfigProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JsonUtils {
    private static Map<String, String> CONFIGMAP;

    static {
        try {
            CONFIGMAP = new ObjectMapper().readValue(new File(FrameworkConstants.getJsonconfigfilepath()),
                    new TypeReference<HashMap<String, String>>() {
                    });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JsonUtils() {

    }

    public static String get(ConfigProperties key) throws Exception {
        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key.name().toLowerCase()))) {
            throw new Exception("Property name " + key + " is not found. Please check com.automationteststore.config.properties");
        }
        return CONFIGMAP.get(key.name().toLowerCase());
    }

    public static Object[][] getTestData(String filePath, String testCaseName) {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader(filePath);
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray testDataArray = (JSONArray) jsonObject.get(testCaseName);
            Object[][] data = new Object[testDataArray.size()][1];
            for (int i = 0; i < testDataArray.size(); i++) {
                data[i][0] = testDataArray.get(i);
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
