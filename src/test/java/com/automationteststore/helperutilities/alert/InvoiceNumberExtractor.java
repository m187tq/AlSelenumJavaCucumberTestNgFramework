package com.automationteststore.helperutilities.alert;

import com.automationteststore.helperutilities.Strings.StringSplitter;
import com.automationteststore.webdriverutilities.WebDrv;

import java.util.List;

public class InvoiceNumberExtractor {

    public static String extractInvoiceNumberFromCurrentPageUrl() {
        String currentUrl = WebDrv.getInstance().getWebDriver().getCurrentUrl();
        List<String> urlSegments = StringSplitter.splitStringIntoAnArrayWithADelimiterAndReturnIt("/", currentUrl);
        String invoiceNumber = "";

        if (urlSegments.size() >= 4) {
            invoiceNumber = urlSegments.get(4);
        }

        return invoiceNumber;
    }

}
