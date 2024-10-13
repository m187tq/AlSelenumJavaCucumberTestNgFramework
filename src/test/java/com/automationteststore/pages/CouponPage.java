package com.automationteststore.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CouponPage extends Page {
    private final Logger log = LogManager.getLogger(CouponPage.class);

    // Example: Coupon Code Input
    @FindBy(css = "input#coupon_code")
    private WebElement couponCodeInput;

    @FindBy(css = "button#apply_coupon")
    private WebElement applyCouponButton;



    /*=====================================
     * Getter Methods
     *=====================================*/

    public WebElement getCouponCodeInput() {
        return couponCodeInput;
    }

    public WebElement getApplyCouponButton() {
        return applyCouponButton;
    }

    /*=====================================
     * Verification Methods
     *=====================================*/

    public boolean isCouponCodeInputDisplayed() {
        return isDisplayed(couponCodeInput);
    }

    public boolean isApplyCouponButtonDisplayed() {
        return isDisplayed(applyCouponButton);
    }

    /*=====================================
     * Action Methods
     *=====================================*/

    public void enterCouponCode(String couponCode) {
        sendKeys(couponCodeInput, couponCode);
    }

    public CheckoutConfirmationPage clickApplyCouponButton() {
        click(applyCouponButton);
        return new CheckoutConfirmationPage();
    }

}
