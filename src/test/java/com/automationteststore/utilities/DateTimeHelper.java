package com.automationteststore.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author bsingh5
 */
public class DateTimeHelper {

    public static String getCurrentDateTime() {

        DateFormat dateFormat = new SimpleDateFormat("_yyyy-MM-dd_HH-mm-ss");
        Calendar cal = Calendar.getInstance();
        String time = "" + dateFormat.format(cal.getTime());
        return time;
    }

    public static String getCurrentDate() {
        return getCurrentDateTime().substring(0, 11);
    }

}
