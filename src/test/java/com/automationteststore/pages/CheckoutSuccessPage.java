package com.automationteststore.pages;

import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import com.google.common.util.concurrent.Uninterruptibles;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.time.Duration;


public class CheckoutSuccessPage extends Page{

    private final Logger log = LogManager.getLogger(CheckoutSuccessPage.class);
    HomePage homePage;
    Page page;

    private String orderSuccessPageUrl = "https://com/index.php?rt=checkout/success";

    // Example: Success Message
    @FindBy(css = "div.success-message")
    private WebElement successMessage;

    // Example: Order Number
    @FindBy(css = "span.order-number")
    private WebElement orderNumber;

    @FindBy(css = "span.maintext")
    private WebElement yourOrderHasBeenProcessedHeadingTxt;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div/div/div/section/p[2]")
    private WebElement OrderNumberText;
    @FindBy(partialLinkText = "Your order #")
    private WebElement OrderNumberTxt;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div/div/div/section/p[3]")
    private WebElement viewInvoiceText;
    @FindBy(xpath = "//p[contains(text(),'You can view your order details by going to the ')]")
    private WebElement youCanViewYourOrderDetailsText;
    @FindBy(xpath = "//p[contains(text(),'Thank you for shopping with us!')]")
    private WebElement thankYouForShoppingWithUsText;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div/div/div/section/p[3]")
    private WebElement invoicePageLink;
    @FindBy(css = "//*[@id='maincontainer']/div/div/div/div/section/p[3]/a")
    private WebElement invoicePage;
    @FindBy(css = ".maintext")
    private WebElement heading;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div/div/div/section/a")
    private WebElement continueCheckoutSuccessBtn;

    public String getHeading() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(heading);

    }

    public boolean assertYourOrderHasBeenProcessedTextIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(yourOrderHasBeenProcessedHeadingTxt);
    }

    public String getYourOrderHasBeenProcessedText() throws InterruptedException {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(yourOrderHasBeenProcessedHeadingTxt);
    }

    public String getTheOrderNumberText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(OrderNumberText);

    }

    public boolean assertOrderNumberTextIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(OrderNumberText);

    }

    public String getViewInvoiceText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(viewInvoiceText);

    }

    public String getThankYouForShoppingWithUsText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(thankYouForShoppingWithUsText);
    }

    public String assertThankYouForShoppingWithUsText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(thankYouForShoppingWithUsText);
    }

    public boolean assertThankYouForShoppingWithUsTextIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(thankYouForShoppingWithUsText);
    }


    public InvoiceOrderPage clickOnInvoicePageLink() throws IOException {
        invoicePageLink.click();
        return new InvoiceOrderPage();
    }

    public HomePage clickOnContinueCheckoutSuccessButton() {
        continueCheckoutSuccessBtn.click();
        return new HomePage();
    }

    public String getContinueButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(continueCheckoutSuccessBtn);

    }

    public String getYouCanViewYourOrderDetailsText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(youCanViewYourOrderDetailsText);
    }

    public String getInvoiceNumberText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(OrderNumberText);
    }

    public String getViewInvoiceLink() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(invoicePageLink);
    }

    public InvoiceOrderPage clickViewInvoiceLink() {
        invoicePageLink.click();
        return new InvoiceOrderPage();
    }

    /*=====================================
     * Getter Methods
     *=====================================*/

    public WebElement getSuccessMessage() {
        return successMessage;
    }

    public WebElement getOrderNumber() {
        return orderNumber;
    }

    /*=====================================
     * Verification Methods
     *=====================================*/

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    public boolean isOrderNumberDisplayed() {
        return isDisplayed(orderNumber);
    }

    /*=====================================
     * Utility Methods
     *=====================================*/

    /**
     * Get Success Message Text.
     *
     * @return Success message as String.
     */
    public String getSuccessMessageText() {
        return getText(successMessage);
    }

    /**
     * Get Order Number.
     *
     * @return Order number as String.
     */
    public String getOrderNumberText() {
        return getText(orderNumber);
    }
}
