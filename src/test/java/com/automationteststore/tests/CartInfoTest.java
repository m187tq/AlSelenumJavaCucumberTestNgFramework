package com.automationteststore.tests;

import com.automationteststore.pages.CheckoutCartPage;
import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.support.PageFactory;

public class CartInfoTest {
    static CheckoutCartPage checkoutCartPage;
    public static void main(String[] args) {
        checkoutCartPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), CheckoutCartPage.class);

        try {
            // Navigate to the target page
            WebDrv.getInstance().getWebDriver().get("https://automationteststore.com/index.php?rt=checkout/cart");
            

            // Initialize CartInfoPage
            //CheckoutCartPage cartInfo = new CheckoutCartPage();

            /* === Coupon Section === */
            if (checkoutCartPage.isCouponInputDisplayedCSS()) {
                checkoutCartPage.enterCouponCodeCSS("DISCOUNT2024");
                checkoutCartPage.clickApplyCouponCSS();
                System.out.println("Coupon applied successfully.");
            } else {
                System.out.println("Coupon input field is not displayed.");
            }

            /* === Estimate Shipping & Taxes Section === */
            checkoutCartPage.selectCountryCSS("United Kingdom");
            // Wait for State dropdown to load based on selected country
            Thread.sleep(2000); // Ideally, use better wait strategies
            checkoutCartPage.selectStateCSS("Lancashire");
            checkoutCartPage.enterPostcodeCSS("M18 7QT");
            checkoutCartPage.clickEstimateCSS();
            checkoutCartPage.selectShipmentCSS("Flat Shipping Rate - $2.00");

            /* === Cart Total Section === */
            if (checkoutCartPage.isTotalsTableDisplayedCSS()) {
                String subTotal = checkoutCartPage.getSubTotalCSS();
                String shipping = checkoutCartPage.getFlatShippingRateCSS();
                String total = checkoutCartPage.getTotalAmountCSS();
                System.out.println("Sub-Total: " + subTotal);
                System.out.println("Flat Shipping Rate: " + shipping);
                System.out.println("Total: " + total);
            } else {
                System.out.println("Totals table is not displayed.");
            }

            if (checkoutCartPage.isCheckoutBtnDisplayedCSS()) {
                checkoutCartPage.clickCheckoutCSS();
                System.out.println("Navigated to Checkout.");
            } else {
                System.out.println("Checkout button is not available.");
            }

            // Additional assertions can be added here to verify the outcomes

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser after the test
            WebDrv.getInstance().getWebDriver().quit();
        }
    }
}
