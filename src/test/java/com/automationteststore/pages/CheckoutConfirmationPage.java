package com.automationteststore.pages;

import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;


public class CheckoutConfirmationPage extends Page{

    private final Logger log = LogManager.getLogger(CheckoutConfirmationPage.class);

    // Example: Edit Cart Button
    @FindBy(css = "a.edit-cart") // Adjust selector based on actual HTML
    private WebElement editCartButton;

    // Example: Continue Shopping Button
    @FindBy(css = "button.continue-shopping")
    private WebElement continueShoppingButton;
    @FindBy(css = "p > a > b")
    public WebElement returnPolicyLink;
    Page page;
    @FindBy(css = "h2[class='heading2'] span")
    private WebElement orderSummaryText;
    @FindBy(xpath = "/html/body/div/div[2]/div/div[1]/div/h1/span[1]")
    private WebElement pageHeading;
    @FindBy(css = ".align_right.valign_top")
    private List<WebElement> orderSummaryUnitPriceList;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div[1]/div/div[2]/table[3]/tbody/tr/td[2]/a")
    private List<WebElement> itemsInYourCartList;
    @FindBy(linkText = "Items in your cart")
    private WebElement itemsInYourCartTxt;
    @FindBy(xpath = "//td[@class='align_left valign_top']//a[contains(text(),'Total Moisture Facial Cream')]")
    private WebElement orderSummaryProductName;
    @FindBy(css = ".bold.extra.totalamout")
    private WebElement totalPriceLabel;
    @FindBy(css = "[class='bold totalamout']")
    private WebElement totalAmt;
    @FindBy(linkText = "Sub-Total:")
    private WebElement subTotal;
    @FindBy(css = ".align_left.valign_top")
    private WebElement orderSummaryNoOfItems;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div[2]/div[1]/table[2]/tbody/tr[2]/td[2]/span")
    private WebElement flatShippingRate;
    @FindBy(partialLinkText = "tota")
    private WebElement total;
    @FindBy(partialLinkText = "Edit Pay")
    private WebElement editPaymentBtn;
    @FindBy(partialLinkText = "Edit Ship")
    private WebElement editShippingBtn;
    @FindBy(partialLinkText = "Edit Ship")
    private WebElement editShippingButton;
    @FindBy(partialLinkText = "Edit Car")
    private WebElement editCartBtn;
    @FindBy(partialLinkText = "Edit Coup")
    private WebElement editCouponBtn;
    @FindBy(id = "back")
    private WebElement backArrowBtn;
    @FindBy(css = "[title='Confirm Order']")
    private WebElement confirmOrderButton;
    @FindBy(xpath = "//h4[normalize-space()='Payment']")
    private WebElement paymentHeader;
    @FindBy(xpath = "//h4[normalize-space()='Shipping']")
    private WebElement shippingHeader;
    @FindBy(xpath = "//body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/table[1]/tbody[1]/tr[1]/td[1]")
    private WebElement qtyProductWithStockLocations;
    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]")
    private List<WebElement> ShippingPaymentItemsInYourCartText;
    @FindBy(xpath = "/html/body/div/header/div[2]/div/div[2]/ul/li")
    private WebElement currencyDropdownBtn;
    //=========================================================
    @FindBy(css = "button#checkout_btn")
    private WebElement confirmOrder_button;
    @FindBy(css = "button.btn.btn-orange.pull-right.lock-on-click")
    private WebElement confirm_Order_button;
    @FindBy(xpath = "/html/body/div/header/div[2]/div/div[3]/ul/li/a")
    private WebElement itemsCartIcon;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div[1]/div/h1/span[1]")
    private WebElement checkoutConfirmationHeadingTxt;
    @FindBy(css = "//*[@id='maincontainer']/div/div[1]/div/div[2]/p")
    private WebElement clickingConfirmOrderReturnPolicyText;
    @FindBy(xpath = "//b[contains(text(),'Return Policy')]")
    private WebElement returnPolicyTxtLink;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div[1]/div/div[2]/table[1]/tbody/tr/td[4]/a")
    private WebElement editShipmentIcon;
    @FindBy(xpath = "//h4[contains(text(),'Shipping')]")
    private WebElement shippingTxt;
    @FindBy(css = "//h4[contains(text(),'Payment')]")
    private WebElement paymentTxt;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div[1]/div/div[2]/table[2]/tbody/tr/td[4]/a[1]")
    private WebElement editPaymentIcon;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div[1]/div/div[2]/table[2]/tbody/tr/td[4]/a[2]")
    private WebElement editCouponIcon;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div[1]/div/div[2]/h4[3]/a")
    private WebElement editCartIcon;
    @FindBy(xpath = "/html/body/div/div[2]/div/div[1]/div/div[2]/table[3]/tbody/tr/td[4]")
    private List<WebElement> items_In_Your_Cart_List;
    @FindBy(xpath = "/html//a[@id='back']")
    private WebElement backArrowButton;
    @FindBy(css = "span.bold.totalamout")
    private List<WebElement> totalPriceAndAmountTxt;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div[1]/div/div[2]/div/div[1]/table/tbody/tr[4]/td[1]/span")
    private WebElement totalPriceTxt;
    @FindBy(xpath = "//td[@class='align_left']//a[contains(text(),'Absolue Eye Precious Cells')]")
    private WebElement tPrice;
    @FindBy(xpath = "//*[@id='maincontainer']/div/div[1]/div/div[2]/div/div[1]/table/tbody/tr[4]/td[2]/span")
    private WebElement amountTxt;

    // 1. Return Policy Link
    @FindBy(css = "div.contentpanel p a[href*='content_id=3']")
    private WebElement return_PolicyLink;

    // 2. Edit Shipping Button
    @FindBy(css = "table.confirm_shippment_options a.btn[href*='shipping&mode=edit']")
    private WebElement Button_editShipping;

    // 3. Edit Payment Button
    @FindBy(css = "table.confirm_payment_options a.btn[href*='payment&mode=edit']:first-of-type")
    private WebElement editPaymentButton;

    // 4. Edit Coupon Button
    @FindBy(css = "table.confirm_payment_options a.btn[href*='payment&mode=edit']:last-of-type")
    private WebElement editCouponButton;

    // 5. Edit Cart Link
    @FindBy(css = "h4.heading4 a[href*='checkout/cart']")
    private WebElement editCartLink;

    // 6. Confirm Order Button
    @FindBy(css = "button#checkout_btn")
    private WebElement button_confirmOrder;

    // 7. Back Button
    @FindBy(css = "a#back")
    private WebElement backButton;

    // 8. Sub-Total Text
    @FindBy(css = "div.confirm_total table.table-striped tr:nth-child(1) td:nth-child(2) span.bold")
    private WebElement subTotalText;

    // 9. Total Amount Text
    @FindBy(css = "div.confirm_total table.table-striped tr.totalamout td span.bold")
    private WebElement totalAmountText;


    public String getOrderSummaryText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(orderSummaryText);

    }

    public String getPageHeadingText() {
        new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait)).until(ExpectedConditions.visibilityOf(pageHeading));
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(pageHeading);
    }

    public WebElement getQtyProductWithStockLocations() {
        return qtyProductWithStockLocations;

    }

    public List<WebElement> getShippingPaymentItemsInYourCartText() {
        return ShippingPaymentItemsInYourCartText;

    }

    public WebElement getCurrencyDropdownBtn() {
        return currencyDropdownBtn;


    }

    public String getConfirmOrderButtonTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(confirm_Order_button);

    }

    public WebElement getItemsCartIcon() {
        return itemsCartIcon;

    }


    public WebElement getClickingConfirmOrderReturnPolicyText() {
        return clickingConfirmOrderReturnPolicyText;

    }

    public WebElement getReturnPolicyTextLink() {
        return returnPolicyTxtLink;

    }

    public WebElement getEditShipmentIcon() {
        return editShipmentIcon;

    }

    public WebElement getShippingText() {
        return shippingTxt;

    }

    public WebElement getPaymentText() {
        return paymentTxt;

    }

    public WebElement getEditPaymentIcon() {
        return editPaymentIcon;

    }

    public WebElement getEditCouponIcon() {
        return editCouponIcon;

    }

    public String getItemsInYourCartText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(itemsInYourCartTxt);

    }

    public WebElement getEditCartIcon() {
        return editCartIcon;

    }


    public long getItems_In_Your_Cart_List() {
        return items_In_Your_Cart_List.size();

    }

    public WebElement getBackArrowButton() {
        return backArrowBtn;

    }

    public List<WebElement> getTotalPriceAndAmountText() {
        return totalPriceAndAmountTxt;

    }

    public WebElement getTotalPriceText() {
        return totalPriceTxt;

    }

    public WebElement getMountText() {
        return amountTxt;

    }

    //===============================================================

    public String getPaymentHeaderText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(paymentHeader);

    }

    public String getShippingHeaderText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(paymentHeader);

    }

    public String getOrderSummaryNoOfItemsText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(orderSummaryNoOfItems);

    }

    public List<WebElement> getUnitPriceList() {
        return orderSummaryUnitPriceList;

    }

    public String getReturnPolicyLinkText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(returnPolicyLink);

    }

    public ReturnPolicyPage clickOnReturnPolicyLink() {
        returnPolicyLink.click();
        return new ReturnPolicyPage();
    }

    public List<WebElement> getItemsInYourCartList() {
        return itemsInYourCartList;

    }

    public String getTotalPriceLabelText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(totalPriceLabel);

    }

    public String getTheTotalAmountText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(totalAmt);

    }

    public String getTheSubTotalText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(subTotal);

    }

    public String getFlatShippingRateText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(flatShippingRate);

    }

    public String getTotalText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(total);

    }

    public CheckoutEditPaymentPage clickOnEditPaymentButton() {
        editPaymentBtn.click();
        return new CheckoutEditPaymentPage();
    }

    public String getEditPaymentButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(editPaymentBtn);

    }

    public CheckoutShippingModeEditPage clickOnEditShippingButton() throws IOException {
        editShippingBtn.click();
        return new CheckoutShippingModeEditPage();
    }

    public String getEditShippingButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(editShippingButton);

    }

    public ShoppingCartPage clickOnEditCartButton() throws IOException {
        editCartBtn.click();
        return new ShoppingCartPage();
    }

    public String getEditCartButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(editCartBtn);

    }

    public CheckoutEditCouponPage clickOnEditCouponButton() throws IOException {
        this.page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        this.page.waitAndClick(editCouponBtn);
        return new CheckoutEditCouponPage();
    }

    public String getEditCouponButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(editCouponBtn);

    }

    public CheckoutSuccessPage tapConfirmOrderButton() throws IOException {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        page.waitAndClick(confirmOrderButton);
        return new CheckoutSuccessPage();
    }

    public String getCheckoutConfirmationHeadingText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(checkoutConfirmationHeadingTxt);

    }

    public CheckoutCartPage clickOnBackArrowButton() throws IOException {
        backArrowButton.click();
        return new CheckoutCartPage();
    }

    public String getBackArrowButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(backArrowButton);

    }

    public String getAmountText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(amountTxt);

    }

    public CheckoutSuccessPage clickOnConfirmOrderButton() throws Exception {
        //new JavaScriptHelper(WebDrv.getInstance().getWebDriver()).scrollIntoViewAndClick(confirm_Order_button);
        confirm_Order_button.click();
        return new CheckoutSuccessPage();
    }

    public String getConfirmOrderButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(confirmOrder_button);

    }

    //===================================================================

    public WebElement getReturnPolicyLink() {
        return returnPolicyLink;
    }

    public WebElement getEditShippingButton() {
        return editShippingButton;
    }

    public WebElement getEditPaymentButton() {
        return editPaymentButton;
    }

    public WebElement getEditCouponButton() {
        return editCouponButton;
    }

    public WebElement getEditCartLink() {
        return editCartLink;
    }

    public WebElement getConfirmOrderButton() {
        return confirmOrderButton;
    }

    public WebElement getBackButton() {
        return backButton;
    }

    public WebElement getSubTotalText() {
        return subTotalText;
    }

    public WebElement getTotalAmountText() {
        return totalAmountText;
    }

    /*=====================================
     * Verification Methods
     *=====================================*/

    public boolean isReturnPolicyLinkDisplayed() {
        return isDisplayed(returnPolicyLink);
    }

    public boolean isReturnPolicyLinkEnabled() {
        return isEnabled(returnPolicyLink);
    }

    public boolean isEditShippingButtonDisplayed() {
        return isDisplayed(editShippingButton);
    }

    public boolean isEditShippingButtonEnabled() {
        return isEnabled(editShippingButton);
    }

    public boolean isEditPaymentButtonDisplayed() {
        return isDisplayed(editPaymentButton);
    }

    public boolean isEditPaymentButtonEnabled() {
        return isEnabled(editPaymentButton);
    }

    public boolean isEditCouponButtonDisplayed() {
        return isDisplayed(editCouponButton);
    }

    public boolean isEditCouponButtonEnabled() {
        return isEnabled(editCouponButton);
    }

    public boolean isEditCartLinkDisplayed() {
        return isDisplayed(editCartLink);
    }

    public boolean isEditCartLinkEnabled() {
        return isEnabled(editCartLink);
    }

    public boolean isConfirmOrderButtonDisplayed() {
        return isDisplayed(confirmOrderButton);
    }

    public boolean isConfirmOrderButtonEnabled() {
        return isEnabled(confirmOrderButton);
    }

    public boolean isBackButtonDisplayed() {
        return isDisplayed(backButton);
    }

    public boolean isBackButtonEnabled() {
        return isEnabled(backButton);
    }

    public boolean isSubTotalTextDisplayed() {
        return isDisplayed(subTotalText);
    }

    public boolean isTotalAmountTextDisplayed() {
        return isDisplayed(totalAmountText);
    }

    /*=====================================
     * Action Methods
     *=====================================*/

    public ReturnPolicyPage clickReturnPolicyLink() {
        click(returnPolicyLink);
        return new ReturnPolicyPage();
    }

    public ShippingReturnsPage clickEditShippingButton() {
        click(editShippingButton);
        return new ShippingReturnsPage();
    }

    public PaymentModeEditPage clickEditPaymentButton() {
        click(editPaymentButton);
        return new PaymentModeEditPage();
    }

    public CouponPage clickEditCouponButton() {
        click(editCouponButton);
        return new CouponPage();
    }

    public CheckoutCartPage clickEditCartLink() {
        click(editCartLink);
        return new CheckoutCartPage();
    }

    public CheckoutSuccessPage clickConfirmOrderButton() {
        click(confirmOrderButton);
        return new CheckoutSuccessPage();
    }

    public PaymentModeEditPage clickBackButton() {
        click(backButton);
        return new PaymentModeEditPage();
    }

    /*=====================================
     * Utility Methods
     *=====================================*/

    /**
     * Get Sub-Total Value.
     *
     * @return Sub-Total as String.
     */
    public String getSubTotalValue() {
        return getText(subTotalText);
    }

    /**
     * Get Total Amount Value.
     *
     * @return Total Amount as String.
     */
    public String getTotalAmountValue() {
        return getText(totalAmountText);
    }

    /**
     * Verify Total Amount matches expected value.
     *
     * @param expectedTotal The expected total amount.
     * @return True if matches, else false.
     */
    public boolean verifyTotalAmount(String expectedTotal) {
        String actualTotal = getTotalAmountValue();
        return actualTotal.equals(expectedTotal);
    }

}
