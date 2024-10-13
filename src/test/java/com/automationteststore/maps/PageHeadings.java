package com.automationteststore.maps;

import java.util.HashMap;
import java.util.Map;

public class PageHeadings {
    private static Map<String, String> pageHeadingMap;

    static {
        pageHeadingMap = new HashMap<>();
        pageHeadingMap.put("A place to practice your automation skills!", "Welcome to the Automation Test Store");
        pageHeadingMap.put("Account Login", " Account Login");
        pageHeadingMap.put("My Account", " My Account");

    }

    public static String getHeadingForPage(String pageName) {
        String pageHeading = pageHeadingMap.get(pageName);
        return pageHeading != null ? pageHeading : pageName;
    }

}
