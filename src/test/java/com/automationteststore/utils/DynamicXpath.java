package com.automationteststore.utils;

/*
 * It will be used to handle dynamic xpaths at run time
 */
public class DynamicXpath {

    public static String get(String xpath, String data) {

        String rawxpath = xpath.replaceAll("%replaceable%", data);
        return rawxpath;

    }
}
