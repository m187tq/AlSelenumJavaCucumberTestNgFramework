package com.automationteststore.helperutilities.alert;

import com.automationteststore.helperutilities.Strings.StringSplitter;
import com.automationteststore.webdriverutilities.WebDrv;

import java.util.List;


public class ProductIDExtractor {

    public static String extractProductIDFromCurrentPageUrl() {
        String currentUrl = WebDrv.getInstance().getWebDriver().getCurrentUrl();
        List<String> urlSegments = StringSplitter.splitStringIntoAnArrayWithADelimiterAndReturnIt("/", currentUrl);
        String ProductID = "";

        if (urlSegments.size() >= 4) {
            ProductID = urlSegments.get(4);
        }

        return ProductID;
    }

}
