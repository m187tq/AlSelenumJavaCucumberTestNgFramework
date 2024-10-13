package com.automationteststore.pages;

import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentModeEditPage extends Page{
    private final static Logger log = LogManager.getLogger(PaymentModeEditPage.class);
    Page page;
    @FindBy(css = ".paymentProviderHeader-name")
    private WebElement testGatewayText;
    @FindBy(css = "#ccNumber")
    private WebElement ccNumberBox;

    // Example: Payment Method Radio Buttons
    @FindBy(css = "input#payment_cod") // Cash On Delivery
    private WebElement paymentCODRadio;

    @FindBy(css = "input#payment_creditcard")
    private WebElement paymentCreditCardRadio;

    // Example: Coupon Code Input
    @FindBy(css = "input#coupon_code")
    private WebElement couponCodeInput;

    @FindBy(css = "button#apply_coupon")
    private WebElement applyCouponButton;

    // Example: Save Payment Method Button
    @FindBy(css = "button#save_payment")
    private WebElement savePaymentButton;


    /*=====================================
     * Getter Methods
     *=====================================*/

    public WebElement getPaymentCODRadio() {
        return paymentCODRadio;
    }

    public WebElement getPaymentCreditCardRadio() {
        return paymentCreditCardRadio;
    }

    public WebElement getCouponCodeInput() {
        return couponCodeInput;
    }

    public WebElement getApplyCouponButton() {
        return applyCouponButton;
    }

    public WebElement getSavePaymentButton() {
        return savePaymentButton;
    }

    /*=====================================
     * Verification Methods
     *=====================================*/

    public boolean isPaymentCODRadioDisplayed() {
        return isDisplayed(paymentCODRadio);
    }

    public boolean isPaymentCreditCardRadioDisplayed() {
        return isDisplayed(paymentCreditCardRadio);
    }

    public boolean isCouponCodeInputDisplayed() {
        return isDisplayed(couponCodeInput);
    }

    public boolean isApplyCouponButtonDisplayed() {
        return isDisplayed(applyCouponButton);
    }

    public boolean isSavePaymentButtonDisplayed() {
        return isDisplayed(savePaymentButton);
    }

    public boolean isSavePaymentButtonEnabled() {
        return isEnabled(savePaymentButton);
    }

    /*=====================================
     * Action Methods
     *=====================================*/

    public void selectPaymentMethod(String method) {
        if (method.equalsIgnoreCase("Cash On Delivery")) {
            click(paymentCODRadio);
        } else if (method.equalsIgnoreCase("Credit Card")) {
            click(paymentCreditCardRadio);
        }
    }

    public void enterCouponCode(String couponCode) {
        sendKeys(couponCodeInput, couponCode);
    }

    public void clickApplyCouponButton() {
        click(applyCouponButton);
    }

    public CheckoutConfirmationPage clickSavePaymentButton() {
        click(savePaymentButton);
        return new CheckoutConfirmationPage();
    }

    public String getTestGatewayText() {
        log.info("Getting text:: " + testGatewayText.getText());
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(testGatewayText);

    }

    public String getCcNumberBox() {
        return ccNumberBox.getText();

    }
}
