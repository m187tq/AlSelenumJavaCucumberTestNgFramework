package com.automationteststore.pages;

import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class CartSummaryPage {

    final static Logger log = LogManager.getLogger(CartSummaryPage.class);
    @FindBy(css = ".maintext")
    public WebElement yourShoppingCart;
    @FindBy(css = "i.fa.fa-trash-o.fa-fw")
    public List<WebElement> quantity_delete;
    @FindBy(xpath = "//*[contains(text(),'Your shopping cart is empty')]")
    public WebElement emptyShoppingCartMsg;
    @FindBy(css = "a[title='Continue']")
    public WebElement continueBtn;
    private WebDriver driver;

    public CartSummaryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnContinueButton() throws IOException {
        continueBtn.click();
    }

    public boolean assertProduct(String prod) {
        System.out.println("selecting product.." + prod);
        WebElement product = WebDrv.getInstance().getWebDriver().findElement(By.xpath("//*[contains(text(),'" + prod + "')]"));
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(product);
    }

    public void deleteProductFromShoppingCart() throws InterruptedException {
        // Delete all items from cart
        System.out.println("Deleting all products from cart..");
        List<WebElement> deletes = quantity_delete;
        Iterator<WebElement> itr = deletes.iterator();
        while (itr.hasNext()) {
            itr.next().click();
            Thread.sleep(2000);
        }
    }

    public boolean assertEmptyShoppingCartMessageIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(emptyShoppingCartMsg);
    }

}
