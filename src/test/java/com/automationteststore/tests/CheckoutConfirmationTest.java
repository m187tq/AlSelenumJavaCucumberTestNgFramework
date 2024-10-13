package com.automationteststore.tests;

import com.automationteststore.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for Checkout Confirmation Page.
 */
public class CheckoutConfirmationTest extends Page {

    @Test(priority = 1)
    public void testCheckoutConfirmationPageElements() {
        // Initialize CheckoutConfirmationPage
        CheckoutConfirmationPage checkoutPage = new CheckoutConfirmationPage();

        // Verify Return Policy Link
        Assert.assertTrue(checkoutPage.isReturnPolicyLinkDisplayed(), "Return Policy link is not displayed.");
        Assert.assertTrue(checkoutPage.isReturnPolicyLinkEnabled(), "Return Policy link is not enabled.");

        // Verify Edit Shipping Button
        Assert.assertTrue(checkoutPage.isEditShippingButtonDisplayed(), "Edit Shipping button is not displayed.");
        Assert.assertTrue(checkoutPage.isEditShippingButtonEnabled(), "Edit Shipping button is not enabled.");

        // Verify Edit Payment Button
        Assert.assertTrue(checkoutPage.isEditPaymentButtonDisplayed(), "Edit Payment button is not displayed.");
        Assert.assertTrue(checkoutPage.isEditPaymentButtonEnabled(), "Edit Payment button is not enabled.");

        // Verify Edit Coupon Button
        Assert.assertTrue(checkoutPage.isEditCouponButtonDisplayed(), "Edit Coupon button is not displayed.");
        Assert.assertTrue(checkoutPage.isEditCouponButtonEnabled(), "Edit Coupon button is not enabled.");

        // Verify Edit Cart Link
        Assert.assertTrue(checkoutPage.isEditCartLinkDisplayed(), "Edit Cart link is not displayed.");
        Assert.assertTrue(checkoutPage.isEditCartLinkEnabled(), "Edit Cart link is not enabled.");

        // Verify Confirm Order Button
        Assert.assertTrue(checkoutPage.isConfirmOrderButtonDisplayed(), "Confirm Order button is not displayed.");
        Assert.assertTrue(checkoutPage.isConfirmOrderButtonEnabled(), "Confirm Order button is not enabled.");

        // Verify Back Button
        Assert.assertTrue(checkoutPage.isBackButtonDisplayed(), "Back button is not displayed.");
        Assert.assertTrue(checkoutPage.isBackButtonEnabled(), "Back button is not enabled.");

        // Verify Sub-Total and Total Amount
        Assert.assertTrue(checkoutPage.isSubTotalTextDisplayed(), "Sub-Total text is not displayed.");
        Assert.assertTrue(checkoutPage.isTotalAmountTextDisplayed(), "Total Amount text is not displayed.");

        // Optionally, verify the values
        String expectedSubTotal = "$311.70";
        String expectedTotal = "$313.70";

        Assert.assertEquals(checkoutPage.getSubTotalValue(), expectedSubTotal, "Sub-Total value mismatch.");
        Assert.assertEquals(checkoutPage.getTotalAmountValue(), expectedTotal, "Total Amount value mismatch.");
    }

    @Test(priority = 2)
    public void testReturnPolicyLink() {
        // Initialize CheckoutConfirmationPage
        CheckoutConfirmationPage checkoutPage = new CheckoutConfirmationPage();

        // Click on Return Policy Link
        ReturnPolicyPage returnPolicyPage = checkoutPage.clickReturnPolicyLink();

        // Verify Return Policy Page elements
        Assert.assertTrue(returnPolicyPage.isCloseModalButtonDisplayed(), "Close Modal button is not displayed.");
        Assert.assertTrue(returnPolicyPage.isReturnPolicyContentDisplayed(), "Return Policy content is not displayed.");

        // Optionally, verify content
        String returnPolicyText = returnPolicyPage.getReturnPolicyText();
        Assert.assertTrue(returnPolicyText.contains("Return Policy"), "Return Policy text is incorrect.");

        // Close the modal and return to Checkout Confirmation Page
        CheckoutConfirmationPage returnedCheckoutPage = returnPolicyPage.closeReturnPolicyModal();

        // Optionally, verify that we're back on CheckoutConfirmationPage
        Assert.assertTrue(returnedCheckoutPage.isConfirmOrderButtonDisplayed(), "Not navigated back to Checkout Confirmation Page.");
    }

    @Test(priority = 3)
    public void testEditShipping() {
        // Initialize CheckoutConfirmationPage
        CheckoutConfirmationPage checkoutPage = new CheckoutConfirmationPage();

        // Click Edit Shipping Button
        ShippingReturnsPage shippingPage = checkoutPage.clickEditShippingButton();

        // Verify Shipping Page elements
        Assert.assertTrue(shippingPage.isShippingFirstNameDisplayed(), "Shipping First Name field is not displayed.");
        Assert.assertTrue(shippingPage.isShippingLastNameDisplayed(), "Shipping Last Name field is not displayed.");
        Assert.assertTrue(shippingPage.isShippingAddressDisplayed(), "Shipping Address field is not displayed.");
        Assert.assertTrue(shippingPage.isSaveShippingButtonDisplayed(), "Save Shipping button is not displayed.");
        Assert.assertTrue(shippingPage.isSaveShippingButtonEnabled(), "Save Shipping button is not enabled.");

        // Update Shipping Details
        shippingPage.enterShippingDetails("John", "Doe", "123 New Street, City, Country");

        // Save Shipping Details
        CheckoutConfirmationPage updatedCheckoutPage = shippingPage.clickSaveShippingButton();

        // Optionally, verify updated details on CheckoutConfirmationPage
        // This depends on how the application displays updated shipping info
    }

    @Test(priority = 4)
    public void testEditPayment() {
        // Initialize CheckoutConfirmationPage
        CheckoutConfirmationPage checkoutPage = new CheckoutConfirmationPage();

        // Click Edit Payment Button
        PaymentModeEditPage paymentPage = checkoutPage.clickEditPaymentButton();

        // Verify Payment Page elements
        Assert.assertTrue(paymentPage.isPaymentCODRadioDisplayed(), "Payment COD radio button is not displayed.");
        Assert.assertTrue(paymentPage.isPaymentCreditCardRadioDisplayed(), "Payment Credit Card radio button is not displayed.");
        Assert.assertTrue(paymentPage.isCouponCodeInputDisplayed(), "Coupon Code input is not displayed.");
        Assert.assertTrue(paymentPage.isApplyCouponButtonDisplayed(), "Apply Coupon button is not displayed.");
        Assert.assertTrue(paymentPage.isSavePaymentButtonDisplayed(), "Save Payment button is not displayed.");
        Assert.assertTrue(paymentPage.isSavePaymentButtonEnabled(), "Save Payment button is not enabled.");

        // Select Payment Method
        paymentPage.selectPaymentMethod("Cash On Delivery");

        // Optionally, apply a coupon
        paymentPage.enterCouponCode("DISCOUNT10");
        paymentPage.clickApplyCouponButton();

        // Save Payment Details
        CheckoutConfirmationPage updatedCheckoutPage = paymentPage.clickSavePaymentButton();

        // Optionally, verify updated payment info on CheckoutConfirmationPage
    }

    @Test(priority = 5)
    public void testConfirmOrder() {
        // Initialize CheckoutConfirmationPage
        CheckoutConfirmationPage checkoutPage = new CheckoutConfirmationPage();

        // Click Confirm Order Button
        CheckoutSuccessPage successPage = checkoutPage.clickConfirmOrderButton();

        // Verify Success Page elements
        Assert.assertTrue(successPage.isSuccessMessageDisplayed(), "Success message is not displayed.");
        Assert.assertTrue(successPage.isOrderNumberDisplayed(), "Order Number is not displayed.");

        // Optionally, verify the success message content
        String successMessage = successPage.getSuccessMessageText();
        Assert.assertTrue(successMessage.contains("Thank you for your order"), "Success message text is incorrect.");

        // Optionally, verify the Order Number format
        String orderNumber = successPage.getOrderNumberText();
        Assert.assertTrue(orderNumber.matches("ORD\\d{6}"), "Order Number format is incorrect.");
    }

    @Test(priority = 6)
    public void testBackButton() {
        // Initialize CheckoutConfirmationPage
        CheckoutConfirmationPage checkoutPage = new CheckoutConfirmationPage();

        // Click Back Button
        PaymentModeEditPage paymentPage = checkoutPage.clickBackButton();

        // Verify that we are back on the Payment Page
        Assert.assertTrue(paymentPage.isPaymentCODRadioDisplayed(), "Not navigated back to Payment Page.");
    }
}

