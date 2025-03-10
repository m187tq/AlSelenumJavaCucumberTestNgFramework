package com.automationteststore.utils;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class ExcelUtil {

    static Sheet wrksheet;
    static Workbook wrkbook = null;
    static Hashtable dict = new Hashtable();

    //Create a Constructor
    public ExcelUtil(String ExcelSheetPath) throws BiffException, IOException {
        //Initialize
        wrkbook = Workbook.getWorkbook(new File(ExcelSheetPath));
        //For Demo purpose the excelFiles sheet name is hardcoded, but not recommended :)
        wrksheet = wrkbook.getSheet("Sheet1");

        //Call the Column Dictionary to store column Names
        ColumnDictionary();
    }

    //Returns the Number of Rows
    public static int RowCount() {
        return wrksheet.getRows();
    }

    //Returns the Cell value by taking row and Column values as argument
    private static String ReadCell(int column, int row) {
        return wrksheet.getCell(column, row).getContents();

    }

    public static String ReadCell(String columnName, int rowNumber) {
        return ReadCell(GetCell(columnName, 1), rowNumber);

    }

    //Create Column Dictionary to hold all the Column Names
    private static void ColumnDictionary() {
        //Iterate through all the columns in the Excel sheet and store the value in Hashtable
        for (int col = 0; col < wrksheet.getColumns(); col++) {
            dict.put(ReadCell(col, 0), col);
        }
    }

    //Read Column Names
    public static int GetCell(String colName, int i) {
        try {
            int value;
            value = (Integer) dict.get(colName);
            return value;
        } catch (NullPointerException e) {
            return (0);

        }
    }


}
