/**
 * @author rahul.rathore
 * <p>
 * 07-Aug-2016
 */
package com.automationteststore.helperutilities.HyperLink;

import com.automationteststore.helperutilities.logger.LoggerHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LinkHelper {

    private WebDriver driver;
    private Logger log = LoggerHelper.getLogger(LinkHelper.class);


    public String getHyperLink(WebElement element) {
        String link = element.getAttribute("hreg");
        log.info("Element : " + element + " Value : " + link);
        return link;
    }
}
