package com.automationteststore.helperutilities.assertors;

import com.automationteststore.helperutilities.WebElementFinderUtils;
import com.automationteststore.helperutilities.WebElementOrderChecker;
import com.automationteststore.helperutilities.WebElementOrderCheckerImpl;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class WebElementOrderingAssertor {
    private String idOfParentWebElement;

    public WebElementOrderingAssertor(String idOfParentWebElement) {
        this.idOfParentWebElement = idOfParentWebElement;
    }

    public WebElementOrderingAssertor() {
        // TODO Auto-generated constructor stub
    }

    private void assertOnWebElementOrder(List<WebElement> childWebElementsInActualOrder,
                                         List<String> expectedOrderOfElementIds) {
        WebElementOrderChecker webElementOrderChecker = new WebElementOrderCheckerImpl(childWebElementsInActualOrder);
        Assert.assertTrue(webElementOrderChecker.areWebElementsOrderedLikeSpecifiedListByElementId(
                getModifiableIdListOfExpectedChildElements(expectedOrderOfElementIds)), "Web Elements are not in the expected order");
    }


    private List<String> getModifiableIdListOfExpectedChildElements(List<String> elementIds) {
        List<String> ids = new ArrayList<>();
        for (String elementId : elementIds) {
            ids.add(elementId);
        }
        return ids;
    }


    public void assertOnOrderOfWebElementsByListOrder(List<String> expectedOrderOfElementIds, WebElement parentElement) {
        List<WebElement> childWebElementsInActualOrder = WebElementFinderUtils
                .findAllChildElementsOfParentElementInActualOrder(parentElement);
        assertOnWebElementOrder(childWebElementsInActualOrder, expectedOrderOfElementIds);
    }
}
