package com.automationteststore.pages;

import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CheckoutCartPage extends Page{
    private final Logger log = LogManager.getLogger(CheckoutCartPage.class);
    Page page;

    @FindBy(css = "a.edit-cart") // Adjust selector based on actual HTML
    private WebElement editCartButton;

    // Example: Continue Shopping Button
    @FindBy(css = "button.continue-shopping")
    private WebElement continueShoppingButton;
    @FindBy(xpath = "//input[@id='estimate_postcode']")
    private WebElement estimatePostcodeBox;
    @FindBy(xpath = "//*[@id='cart']/div/div[1]/table/tbody/tr")
    private List<WebElement> products;
    @FindBy(xpath = "//tr/td[2]")
    private List<WebElement> itemNames;
    @FindBy(xpath = "//tr/td[2]")
    private List<WebElement> productNames;
    @FindBy(xpath = "//div[@innertext='Your shopping cart is empty!']")
    private WebElement cart_empty;
    @FindBy(xpath = "//input[@id='coupon_coupon']")
    private WebElement couponBox;
    @FindBy(css = "#apply_coupon_btn")
    private WebElement applyCouponBtn;
    @FindBy(css = "#estimate_country")
    private WebElement estimateCountryDropdown;
    @FindBy(css = "#estimate_country_zones")
    private WebElement countryZonesDropdown;
    @FindBy(xpath = "//tbody/tr[2]/td[1]/div[1]/form[1]/div[2]/div[1]/span[1]/button[1]")
    private WebElement estimateCalculatorBtn;
    @FindBy(xpath = "//select[@id='shippings']")
    private WebElement flatRateShipmentsDropdown;
    @FindBy(xpath = "//*[@id='cart']/div/div[1]/table/tbody/tr[2]/td[7]/a/i")
    private WebElement removeItemBtn;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/form/div/div[1]/table/tbody/tr/td[5]/div/input")
    private WebElement quantity;
    @FindBy(id = "cart_update")
    private WebElement updateBtn;
    @FindBy(xpath = "//a[normalize-space()='Continue Shopping']")
    private WebElement continueShippingBtn;
    @FindBy(css = ".mb20.pull-right > a[title='Checkout']")
    private WebElement checkoutBtn;
    @FindBy(css = "a[title='Continue']")
    private WebElement ContinueBtn;
    @FindBy(xpath = "//h1[text()='Checkout']")
    private WebElement checkoutHeader;

    // Apply Coupon Code Input - CSS Locator
    @FindBy(css = "input#coupon_coupon")
    @CacheLookup
    private WebElement couponInputCSS;

    // Apply Coupon Code Input - XPath Locator
    @FindBy(xpath = "//input[@id='coupon_coupon']")
    @CacheLookup
    private WebElement couponInputXPath;

    // Apply Coupon Button - CSS Locator
    @FindBy(css = "button#apply_coupon_btn")
    @CacheLookup
    private WebElement applyCouponBtnCSS;

    // Apply Coupon Button - XPath Locator
    @FindBy(xpath = "//button[@id='apply_coupon_btn']")
    @CacheLookup
    private WebElement applyCouponBtnXPath;

    /* ================== Estimate Shipping & Taxes Section ================== */

    // Country Dropdown - CSS Locator
    @FindBy(css = "select#estimate_country")
    @CacheLookup
    private WebElement countryDropdownCSS;

    // Country Dropdown - XPath Locator
    @FindBy(xpath = "//select[@id='estimate_country']")
    @CacheLookup
    private WebElement countryDropdownXPath;

    // State/Zone Dropdown - CSS Locator
    @FindBy(css = "select#estimate_country_zones")
    @CacheLookup
    private WebElement stateDropdownCSS;

    // State/Zone Dropdown - XPath Locator
    @FindBy(xpath = "//select[@id='estimate_country_zones']")
    @CacheLookup
    private WebElement stateDropdownXPath;

    // Postcode Input - CSS Locator
    @FindBy(css = "input#estimate_postcode")
    @CacheLookup
    private WebElement postcodeInputCSS;

    // Postcode Input - XPath Locator
    @FindBy(xpath = "//input[@id='estimate_postcode']")
    @CacheLookup
    private WebElement postcodeInputXPath;

    // Estimate Button - CSS Locator
    @FindBy(css = "button[title='Estimate']")
    @CacheLookup
    private WebElement estimateBtnCSS;

    // Estimate Button - XPath Locator
    @FindBy(xpath = "//button[@title='Estimate']")
    @CacheLookup
    private WebElement estimateBtnXPath;

    // Shipments Dropdown - CSS Locator
    @FindBy(css = "select#shippings")
    @CacheLookup
    private WebElement shipmentsDropdownCSS;

    // Shipments Dropdown - XPath Locator
    @FindBy(xpath = "//select[@id='shippings']")
    @CacheLookup
    private WebElement shipmentsDropdownXPath;

    // Totals Table - CSS Locator
    @FindBy(css = "table#totals_table")
    @CacheLookup
    private WebElement totalsTableCSS;

    // Totals Table - XPath Locator
    @FindBy(xpath = "//table[@id='totals_table']")
    @CacheLookup
    private WebElement totalsTableXPath;

    // Continue Shopping Button - CSS Locator
    @FindBy(css = "a.btn.btn-default.mr10.mb10")
    @CacheLookup
    private WebElement continueShoppingBtnCSS;

    // Continue Shopping Button - XPath Locator
    @FindBy(xpath = "//a[contains(@class, 'btn-default') and contains(@class, 'mr10') and contains(@class, 'mb10')]")
    @CacheLookup
    private WebElement continueShoppingBtnXPath;

    // Checkout Button - CSS Locator
    @FindBy(css = "a#cart_checkout2.btn.btn-orange.pull-right")
    @CacheLookup
    private WebElement checkoutBtnCSS;

    // Checkout Button - XPath Locator
    @FindBy(xpath = "//a[@id='cart_checkout2']")
    @CacheLookup
    private WebElement checkoutBtnXPath;



    // Methods to verify elements
    public boolean isCheckoutPageDisplayed() {
        return checkoutHeader.isDisplayed();
    }

    // Add methods to interact with the checkout process

    private static double getUnitPrice(WebElement s) {
        String unitPz = s.findElement(By.xpath("following-sibling::td[2]")).getText().trim();
        String unitPz2 = unitPz.replace("$", "").replace(" ", "");
        double unitPz3 = Double.parseDouble(unitPz2);
        if (unitPz3 < 1000.0 || unitPz3 > 00.0) {
            System.out.println("UnitPrice: " + unitPz3);
            System.out.println("---------------------");
        }
        return unitPz3;


    }

    private static double getTotalPrice(WebElement s) {
        String totalPz = s.findElement(By.xpath("following-sibling::td[4]")).getText().trim();
        String totalPz2 = totalPz.replace("$", "").replace(" ", "");
        double totalPz3 = Double.parseDouble(totalPz2);
        if (totalPz3 < 1000.0 || totalPz3 > 00.0) {
            System.out.println("TotalPrice: " + totalPz3);
            System.out.println("-------------------------");
        }
        return totalPz3;
    }

    public void assertProductNameAndUnitPriceIsDisplayed(String productname) {
        List<Double> unitPrice = itemNames
                .stream()
                .parallel()
                .filter(s -> s.getText().contains(productname))
                .map(CheckoutCartPage::getUnitPrice).collect(Collectors.toList());
        unitPrice.forEach(System.out::println);

    }

    public void assertProductNameAndTotalPriceIsDisplayed(String productName) {
        List<Double> totalPrice = productNames
                .stream()
                .parallel()
                .filter(s -> s.getText().contains(productName))
                .map(s -> getTotalPrice(s)).collect(Collectors.toList());
        totalPrice.forEach(System.out::println);

    }

    public void increaseOrDecreaseQuantityOfItemsInTheCart(String productInCart, int qty) {
        // //tr/td[2]/following-sibling::td[3]/div/input
        for (int i = 1; i < itemNames.size(); i++) {
            if (itemNames.get(i).getText().contains(productInCart)) {
                WebElement element = itemNames.get(i).findElement(By.xpath("following-sibling::td[3]/div/input"));
                element.clear();
                element.sendKeys(String.valueOf(qty));
            }
        }

    }

    public void removeItemFromShippingCart(String productname) {
        // //tr/td[2]/following-sibling::td[5]/a
        for (int i = 1; i < itemNames.size(); i++) {
            if (itemNames.get(i).getText().contains(productname)) {
                WebElement element = itemNames.get(i).findElement(By.xpath("following-sibling::td[5]/a"));
                element.click();
            }
        }

    }


    //==============================================

    public void EnterCouponNumberOnCouponBox(String CouponNumber) {
        couponBox.sendKeys(CouponNumber);

    }

    public void clickOnApplyCouponBtn() {
        applyCouponBtn.click();

    }

    public void selectEstimateCountryDropdown(String countryName) {
        this.page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        this.page.selectDropDownByIndexOrTextOrValue(countryName, countryZonesDropdown);

    }

    public void selectCountryZonesDropdown(String countryZonesName) {
        this.page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        this.page.selectDropDownByIndexOrTextOrValue(countryZonesName, countryZonesDropdown);

    }

    public void enterOnEstimatePostcodeBox(String postCode) {
        estimatePostcodeBox.sendKeys(postCode);

    }

    public void clickOnEstimateCalculatorBtn() {
        estimateCalculatorBtn.click();

    }

    public void selectFlatRateShipmentsDropdown(String flatRateShipment) {
        this.page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        this.page.selectDropDownByIndexOrTextOrValue(flatRateShipment, flatRateShipmentsDropdown);
    }

    public WebElement removeIconBtn() {
        return removeItemBtn;

    }

    public void clickOnRemoveIconButton() {
        removeItemBtn.click();

    }

    public void clickOnUpdateButton() {
        updateBtn.click();

    }

    public HomePage clickOnContinueShippingButton() {
        continueShippingBtn.click();
        return new HomePage();
    }

    public LoginPage clickOnCheckoutButton() {
        checkoutBtn.click();
        return new LoginPage();
    }

    public boolean continueContinueShoppingButtonIdDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayedAndEnabled(ContinueBtn);

    }

    public HomePage tapContinueShoppingButton() throws IOException {
        ContinueBtn.click();
        return new HomePage();
    }

    public void tapContinueBtn() {
        ContinueBtn.click();

    }

    // Verify Coupon Input is displayed using CSS
    public boolean isCouponInputDisplayedCSS() {
        try {
            return couponInputCSS.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Verify Coupon Input is displayed using XPath
    public boolean isCouponInputDisplayedXPath() {
        try {
            return couponInputXPath.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Similarly, create verification methods for other elements...

    /* ================== Action Methods ================== */

    /* --- Coupon Section Actions --- */

    // Enter Coupon Code using CSS Locator
    public void enterCouponCodeCSS(String code) {
        if (isCouponInputDisplayedCSS()) {
            couponInputCSS.clear();
            couponInputCSS.sendKeys(code);
        }
    }

    // Enter Coupon Code using XPath Locator
    public void enterCouponCodeXPath(String code) {
        if (isCouponInputDisplayedXPath()) {
            couponInputXPath.clear();
            couponInputXPath.sendKeys(code);
        }
    }

    // Click Apply Coupon using CSS Locator
    public void clickApplyCouponCSS() {
        applyCouponBtnCSS.click();
    }

    // Click Apply Coupon using XPath Locator
    public void clickApplyCouponXPath() {
        applyCouponBtnXPath.click();
    }

    /* --- Estimate Shipping & Taxes Section Actions --- */

    // Select Country using CSS Locator
    public void selectCountryCSS(String countryVisibleText) {
        Select countrySelect = new Select(countryDropdownCSS);
        countrySelect.selectByVisibleText(countryVisibleText);
    }

    // Select Country using XPath Locator
    public void selectCountryXPath(String countryVisibleText) {
        Select countrySelect = new Select(countryDropdownXPath);
        countrySelect.selectByVisibleText(countryVisibleText);
    }

    // Select State/Zone using CSS Locator
    public void selectStateCSS(String stateVisibleText) {
        Select stateSelect = new Select(stateDropdownCSS);
        stateSelect.selectByVisibleText(stateVisibleText);
    }

    // Select State/Zone using XPath Locator
    public void selectStateXPath(String stateVisibleText) {
        Select stateSelect = new Select(stateDropdownXPath);
        stateSelect.selectByVisibleText(stateVisibleText);
    }

    // Enter Postcode using CSS Locator
    public void enterPostcodeCSS(String postcode) {
        postcodeInputCSS.clear();
        postcodeInputCSS.sendKeys(postcode);
    }

    // Enter Postcode using XPath Locator
    public void enterPostcodeXPath(String postcode) {
        postcodeInputXPath.clear();
        postcodeInputXPath.sendKeys(postcode);
    }

    // Click Estimate Button using CSS Locator
    public void clickEstimateCSS() {
        estimateBtnCSS.click();
    }

    // Click Estimate Button using XPath Locator
    public void clickEstimateXPath() {
        estimateBtnXPath.click();
    }

    // Select Shipment Option using CSS Locator
    public void selectShipmentCSS(String shipmentVisibleText) {
        Select shipmentSelect = new Select(shipmentsDropdownCSS);
        shipmentSelect.selectByVisibleText(shipmentVisibleText);
    }

    // Select Shipment Option using XPath Locator
    public void selectShipmentXPath(String shipmentVisibleText) {
        Select shipmentSelect = new Select(shipmentsDropdownXPath);
        shipmentSelect.selectByVisibleText(shipmentVisibleText);
    }

    /* ================== Additional Utility Methods ================== */

    // Example: Verify Apply Coupon Button is enabled
    public boolean isApplyCouponEnabledCSS() {
        try {
            return applyCouponBtnCSS.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isApplyCouponEnabledXPath() {
        try {
            return applyCouponBtnXPath.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    // Verify Totals Table is displayed using CSS
    public boolean isTotalsTableDisplayedCSS() {
        try {
            return totalsTableCSS.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Verify Totals Table is displayed using XPath
    public boolean isTotalsTableDisplayedXPath() {
        try {
            return totalsTableXPath.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Verify Continue Shopping Button is displayed using CSS
    public boolean isContinueShoppingBtnDisplayedCSS() {
        try {
            return continueShoppingBtnCSS.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Verify Continue Shopping Button is displayed using XPath
    public boolean isContinueShoppingBtnDisplayedXPath() {
        try {
            return continueShoppingBtnXPath.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Verify Checkout Button is displayed using CSS
    public boolean isCheckoutBtnDisplayedCSS() {
        try {
            return checkoutBtnCSS.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Verify Checkout Button is displayed using XPath
    public boolean isCheckoutBtnDisplayedXPath() {
        try {
            return checkoutBtnXPath.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /* ================== Cart Total Section Action Methods ================== */

    // Retrieve Sub-Total using CSS
    public String getSubTotalCSS() {
        try {
            WebElement subTotalElement = totalsTableCSS.findElement(By.xpath(".//tr[td/span[contains(text(), 'Sub-Total')]]/td[2]/span"));
            return subTotalElement.getText();
        } catch (Exception e) {
            return "";
        }
    }

    // Retrieve Sub-Total using XPath
    public String getSubTotalXPath() {
        try {
            WebElement subTotalElement = totalsTableXPath.findElement(By.xpath(".//tr[td/span[contains(text(), 'Sub-Total')]]/td[2]/span"));
            return subTotalElement.getText();
        } catch (Exception e) {
            return "";
        }
    }

    // Retrieve Flat Shipping Rate using CSS
    public String getFlatShippingRateCSS() {
        try {
            WebElement shippingElement = totalsTableCSS.findElement(By.xpath(".//tr[td/span[contains(text(), 'Flat Shipping Rate')]]/td[2]/span"));
            return shippingElement.getText();
        } catch (Exception e) {
            return "";
        }
    }

    // Retrieve Flat Shipping Rate using XPath
    public String getFlatShippingRateXPath() {
        try {
            WebElement shippingElement = totalsTableXPath.findElement(By.xpath(".//tr[td/span[contains(text(), 'Flat Shipping Rate')]]/td[2]/span"));
            return shippingElement.getText();
        } catch (Exception e) {
            return "";
        }
    }

    // Retrieve Total Amount using CSS
    public String getTotalAmountCSS() {
        try {
            WebElement totalElement = totalsTableCSS.findElement(By.xpath(".//tr[td/span[contains(@class, 'totalamout')]]/td[2]/span"));
            return totalElement.getText();
        } catch (Exception e) {
            return "";
        }
    }

    // Retrieve Total Amount using XPath
    public String getTotalAmountXPath() {
        try {
            WebElement totalElement = totalsTableXPath.findElement(By.xpath(".//tr[td/span[contains(@class, 'totalamout')]]/td[2]/span"));
            return totalElement.getText();
        } catch (Exception e) {
            return "";
        }
    }

    // Click Continue Shopping using CSS
    public void clickContinueShoppingCSS() {
        if (isContinueShoppingBtnDisplayedCSS()) {
            continueShoppingBtnCSS.click();
        }
    }

    // Click Continue Shopping using XPath
    public void clickContinueShoppingXPath() {
        if (isContinueShoppingBtnDisplayedXPath()) {
            continueShoppingBtnXPath.click();
        }
    }

    // Click Checkout Button using CSS
    public void clickCheckoutCSS() {
        if (isCheckoutBtnDisplayedCSS()) {
            checkoutBtnCSS.click();
        }
    }

    // Click Checkout Button using XPath
    public void clickCheckoutXPath() {
        if (isCheckoutBtnDisplayedXPath()) {
            checkoutBtnXPath.click();
        }
    }

    /*=====================================
     * Getter Methods
     *=====================================*/

    public WebElement getEditCartButton() {
        return editCartButton;
    }

    public WebElement getContinueShoppingButton() {
        return continueShoppingButton;
    }

    /*=====================================
     * Verification Methods
     *=====================================*/

    public boolean isEditCartButtonDisplayed() {
        return isDisplayed(editCartButton);
    }

    public boolean isContinueShoppingButtonDisplayed() {
        return isDisplayed(continueShoppingButton);
    }

    /*=====================================
     * Action Methods
     *=====================================*/

    public void clickEditCartButton() {
        click(editCartButton);
    }

    public CheckoutConfirmationPage clickContinueShoppingButton() {
        click(continueShoppingButton);
        return new CheckoutConfirmationPage();
    }

}