package com.automationteststore.tableutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableUtils {

    public static List<String> convertDatesIntoTableFormat(Map<String, String> expectedFieldDataFromPage) {
        String startMonth = expectedFieldDataFromPage.get("Start Month");
        String startYear = expectedFieldDataFromPage.get("Start Year");
        String finishMonth = expectedFieldDataFromPage.get("Finish Month");
        String finishYear = expectedFieldDataFromPage.get("Finish Year");
        String startDateInTableFormat = startMonth + "/" + startYear;
        String finishDateInTableFormat = finishMonth + "/" + finishYear;
        String dateInTableFormat = startDateInTableFormat + " - " + finishDateInTableFormat;

        List<String> fieldData = new ArrayList<String>(expectedFieldDataFromPage.values());

        fieldData.remove(fieldData.indexOf(startMonth));
        fieldData.remove(fieldData.indexOf(startYear));
        fieldData.remove(fieldData.indexOf(finishMonth));
        fieldData.remove(fieldData.indexOf(finishYear));
        fieldData.add(dateInTableFormat);
        fieldData.add("Edit or");

        return fieldData;
    }

}
