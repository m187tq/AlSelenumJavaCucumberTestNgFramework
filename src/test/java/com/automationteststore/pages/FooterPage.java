package com.automationteststore.pages;

import com.automationteststore.helperutilities.javaScript.JavaScriptHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class FooterPage {
    private final static Logger log = LogManager.getLogger(FooterPage.class);
    Page page;
    @FindBy(css = ".footer-info-heading")
    private List<WebElement> footerLinks;
    @FindBy(xpath = "//h3[contains(text(),'Navigate')]")
    private WebElement navigate;
    @FindBy(xpath = "//h3[contains(text(),'Categories')]")
    private WebElement categories;
    @FindBy(xpath = "//h3[contains(text(),'Popular Brands')]")
    private WebElement popularBrands;
    @FindBy(xpath = "//h3[contains(text(),'Info')]")
    private WebElement info;
    @FindBy(xpath = "//h3[contains(text(),'Subscribe to our newsletter')]")
    private WebElement subscribeNewsletter;
    @FindBy(css = "#nl_email")
    private WebElement subscribeEmailBox;
    @FindBy(xpath = "/html/body/footer/div[1]/section/article[5]/form/fieldset/div/div/input[2]")
    private WebElement subscribeBtn;
    @FindBy(css = ".info_links_footer > li:nth-of-type(5) > a")
    private WebElement contactusLink;

    public ContactPage clickOnContactusLink() throws IOException {
        new JavaScriptHelper(WebDrv.getInstance().getWebDriver()).scrollIntoViewAndClick(contactusLink);
        return new ContactPage();
    }

    public void clickOnFooterTextName(String textName) {
        List<WebElement> textNames = footerLinks;
        Iterator<WebElement> itr = textNames.iterator();
        while (itr.hasNext()) {
            WebElement c = itr.next();
            String text = c.getText().trim();
            if (text.equals(textName)) {
                c.click();
                break;
            }
        }
    }

}
