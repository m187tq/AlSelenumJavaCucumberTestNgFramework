package com.automationteststore.helperutilities;

import com.automationteststore.exceptions.TableRowDoesNotExistException;
import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebElementFinderUtils {

    public static List<WebElement> findAllChildElementsOfParentElementInActualOrder(String idOfParentElement) {
        WebElement parentWebElement = WebDrv.getInstance().getWebDriver().findElement(By.id(idOfParentElement));
        List<WebElement> childWebElementsInActualOrder = parentWebElement.findElements(By.xpath(".//*"));
        return childWebElementsInActualOrder;
    }

    public static WebElement findTableRow(String idOfTableBodyElement, int rowNumber)
            throws TableRowDoesNotExistException {
        List<WebElement> rowsInActualOrder = findAllTableRows(idOfTableBodyElement);
        TableRowDoesNotExistException.checkIfTableRowExistsOrElseThrowException(
                "The specified row does not exist within the Table.", rowsInActualOrder, rowNumber);
        return !rowsInActualOrder.isEmpty() ? rowsInActualOrder.get(rowNumber) : null;
    }

    public static List<WebElement> findAllTableRows(String idOfTableBodyElement) {
        List<WebElement> childWebElementsInActualOrder = findAllChildElementsOfParentElementInActualOrder(
                idOfTableBodyElement);
        List<WebElement> rowsInActualOrder = new ArrayList<>();
        for (WebElement element : childWebElementsInActualOrder) {
            if (element.getTagName().equals("tr")) {
                rowsInActualOrder.add(element);
            }
        }

        return rowsInActualOrder;
    }

    public static List<WebElement> findAllChildElementsOfParentElementInActualOrder(WebElement parentWebElement) {
        List<WebElement> childWebElementsInActualOrder = parentWebElement.findElements(By.xpath(".//*"));
        String tagName = childWebElementsInActualOrder.get(childWebElementsInActualOrder.size() - 1).getText();
        return childWebElementsInActualOrder;
    }

}
