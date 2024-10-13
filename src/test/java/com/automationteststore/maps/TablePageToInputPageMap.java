package com.automationteststore.maps;

import java.util.HashMap;
import java.util.Map;

public class TablePageToInputPageMap {

    private static Map<String, String> map;

    static {
        map = new HashMap<>();
        map.put("Academic qualifications", "Add academic qualification");
        map.put("Professional and vocational training table", "Professional and vocational training");
        map.put("Job history table", "Job details");
    }

    public static String getPageInputNameFromItsRelatedTablePageName(String tablePageName) {
        String pageName = map.get(tablePageName);
        return pageName;
    }

}
