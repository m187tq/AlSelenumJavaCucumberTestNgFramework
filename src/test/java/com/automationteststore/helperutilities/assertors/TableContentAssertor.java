package com.automationteststore.helperutilities.assertors;

import com.automationteststore.exceptions.TableRowDoesNotExistException;
import com.automationteststore.webelementdata.TableData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class TableContentAssertor {

    public static void assertCorrectTableContents(TableData tableData) throws TableRowDoesNotExistException {
        assertTableHeadersAreInExpectedOrder(tableData);
        assertTableHeadersAreCorrectInExpectedContent(tableData);
        assertTableBodyContentIsInExpectedOrder(tableData);
        assertTableBodyContentAreCorrectInExpectedContent(tableData);
    }

    public static void assertEachRowHasAnElement(String expectedElementId, TableData tableData) {
        int idKey = 1;
        for (WebElement actualRow : tableData.getAllActualTableRows()) {
            if (actualRow.findElement(By.id(expectedElementId + Integer.toString(idKey))) == null) {
                Assert.assertTrue(false, "Found an actual element that does not contain the expected web element in the row");
            }
            idKey++;
        }
    }

    private static void assertTableBodyContentAreCorrectInExpectedContent(TableData tableData)
            throws TableRowDoesNotExistException {
        for (String actualContent : tableData.getActualTableBodyContentForRow(0)) {
            if (!tableData.getExpectedTableBodyContent().contains(actualContent)) {
                Assert.assertTrue(false, "Found an actual content called: " + actualContent + " that was not expected");
            }
        }
    }

    private static void assertTableBodyContentIsInExpectedOrder(TableData tableData)
            throws TableRowDoesNotExistException {
        WebElementOrderingAssertor elementOrderingAssertor = new WebElementOrderingAssertor();
        elementOrderingAssertor.assertOnOrderOfWebElementsByListOrder(tableData.getExpectedOrderOfTableBodyContentIds(),
                tableData.getTableBodyRowWebElement(0));
    }

    private static void assertTableHeadersAreCorrectInExpectedContent(TableData tableData) {
        for (String actualHeader : tableData.getActualHeaderTitles()) {
            if (!tableData.getExpectedHeaderTitles().contains(actualHeader)) {
                Assert.assertTrue(false, "Found an actual header called: " + actualHeader + " that was not expected");
            }
        }
    }

    private static void assertTableHeadersAreInExpectedOrder(TableData tableData) {
        WebElementOrderingAssertor elementOrderingAssertor = new WebElementOrderingAssertor(
                tableData.getTableHeadersRowId());
        //elementOrderingAssertor.assertOnOrderOfWebElementsByListOrder(tableData.getExpectedOrderOfHeaderIds());
    }

    public static void assertTableBodyDoesNotContainGivenContent(TableData tableData)
            throws TableRowDoesNotExistException {
        boolean hasGivenRowBeenDeleted = false;
        for (List<String> actualContentWithinARow : tableData.getActualTableBodyContentForAllRows()) {
            for (String actualContentInACell : actualContentWithinARow) {
                if (!tableData.getExpectedTableBodyContent().contains(actualContentInACell)) {
                    hasGivenRowBeenDeleted = true;
                    break;
                }
            }
        }

        Assert.assertTrue(hasGivenRowBeenDeleted, "Table contains content that it was not expected to contain");
    }

}
