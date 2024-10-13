package com.automationteststore.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OrderConfirmationPage {

    private final static Logger log = LogManager.getLogger(OrderConfirmationPage.class);
    Page page;

    @FindBy(id = "cart-print-link")
    private WebElement printIcon;
    @FindBy(xpath = "//h3[contains(text(),'Order Summary')]")
    private WebElement summaryOrder;
    @FindBy(xpath = "//h1[contains(text(),'Thank you')]")
    private WebElement thankYouTxt;
    @FindBy(xpath = "//*[@id='checkout-app']/div/div/div/section/p[1]")
    private WebElement yourOrderNumberTxt;
    @FindBy(xpath = "//span[contains(text(),'Your Orders')]")
    private WebElement yourOrdersLinkTxt;
    @FindBy(xpath = "//h3[contains(text(),'Order Summary')]")
    private WebElement orderSummaryTxt;
    @FindBy(css = ".cart-priceItem.optimizedCheckout-contentPrimary.cart-priceItem--total")
    private WebElement totalAmountTxt;
    @FindBy(xpath = "//h3[contains(text(),'Items')]")
    private WebElement totalItems;
    @FindBy(xpath = "//span[contains(text(),'Shipping')]")
    private WebElement shippingPrice;
    @FindBy(xpath = "//button[contains(text(),'Continue Shopping »')]")
    private WebElement continueShoppingBtn;
    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> productNames;

    public Boolean VerifyOrderDisplay(String productName) {
        return productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));

    }

    public WebElement getPrintIcon() {
        return printIcon;
    }

    public WebElement getSummaryOrder() {
        return summaryOrder;
    }

    public WebElement getThankYouTxt() {
        return thankYouTxt;
    }

    public WebElement getYourOrderNumberTxt() {
        return yourOrderNumberTxt;
    }

    public WebElement getYourOrdersLinkTxt() {
        return yourOrdersLinkTxt;
    }

    public WebElement getOrderSummaryTxt() {
        return orderSummaryTxt;
    }

    public WebElement getTotalAmountTxt() {
        return totalAmountTxt;
    }

    public WebElement getTotalItems() {
        return totalItems;
    }

    public WebElement getShippingPrice() {
        return shippingPrice;
    }

    public WebElement getContinueShoppingBtn() {
        return continueShoppingBtn;
    }

    public List<WebElement> getProductNames() {
        return productNames;
    }


}
