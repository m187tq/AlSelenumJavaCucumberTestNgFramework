package com.automationteststore.helperutilities;

import com.automationteststore.helperutilities.Strings.StringSplitter;
import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.By;

import java.util.List;

public class InvoiceOrderNumberExtractor {

    public static String extractInvoiceOrderNumberFromInvoiceOrderPageUrl() {
        String currentUrl = WebDrv.getInstance().getWebDriver().getCurrentUrl();
        List<String> urlSegments = StringSplitter.splitStringIntoAnArrayWithADelimiterAndReturnIt("/", currentUrl);
        String invoiceOrderNumber = "";

        if (urlSegments.size() >= 4) {
            invoiceOrderNumber = urlSegments.get(4);
        }

        return invoiceOrderNumber;
    }

    public static String extractInvoiceOrderNumberFromInvoiceOrderPageText() {
        return WebDrv.getInstance().getWebDriver().findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > section:nth-child(1) > p:nth-child(3)")).getText();
    }

}
