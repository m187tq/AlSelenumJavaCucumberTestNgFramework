package com.automationteststore.utilities;


import com.automationteststore.utils.LogUtil;

import java.sql.Connection;

public class Settings {
    //For Application Backend
    public static Connection AUTConnection;
    //For EARS Reporting
    public static Connection ReportingConnection;
    //For Application backend connection string
    public static String AUTConnectionString;
    public static String ReportingConnectionString;
    //Log Path for framework
    public static String LogPath;
    //Driver Type for SQL Server connectivity
    public static String DriverType;
    public static String ExcelSheetPath;
    public static String AUT;
    public static LogUtil Logs;

}
