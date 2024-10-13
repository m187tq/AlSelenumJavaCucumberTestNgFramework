package com.automationteststore.helperutilities.resource;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ResourceHelper {
    private static final Logger log = LogManager.getLogger(ResourceHelper.class);

    public static String getResourcePath(String path) {
        String basePath = System.getProperty("user.dir");
        return basePath + "/" + path;
    }
}