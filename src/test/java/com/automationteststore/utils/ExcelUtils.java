package com.automationteststore.utils;

// src/main/java/com/automationteststore/utils/ExcelUtils.java

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.Iterator;

public class ExcelUtils {
    public static Object[][] getTestData(String filePath, String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            Object[][] data = new Object[rowCount - 1][colCount];
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // Skip header
            int i = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    switch (cell.getCellType()) {
                        case STRING:
                            data[i][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[i][j] = String.valueOf((long) cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            data[i][j] = cell.getBooleanCellValue();
                            break;
                        default:
                            data[i][j] = "";
                    }
                }
                i++;
            }
            workbook.close();
            fis.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

