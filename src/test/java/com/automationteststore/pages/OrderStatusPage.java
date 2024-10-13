package com.automationteststore.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OrderStatusPage {

    private final static Logger log = LogManager.getLogger(OrderStatusPage.class);
    Page page;

    @FindBy(css = ".breadcrumbs")
    private List<WebElement> allMenuLinksText;
    @FindBy(css = ".navBar-section")
    private List<WebElement> navBarSection;
    @FindBy(xpath = "//span[contains(text(),'Home')]")
    private WebElement homeLinkTxt;
    @FindBy(xpath = "//span[contains(text(),'Your Account')]")
    private WebElement yourAccountLinkTxt;
    @FindBy(xpath = "//span[contains(text(),'Your Orders')]")
    private WebElement yourOrdersLinkTxt;
    @FindBy(xpath = "//a[contains(text(),'Order #')]")
    private WebElement OrderNoTxt;
    @FindBy(xpath = "//p[contains(text(),' products totaling $')]")
    private WebElement productsTotalingPriceTxt;
    @FindBy(css = ".account-product-detail")
    private WebElement productDetailDateTxt;
    @FindBy(xpath = "//h6[contains(text(),'Awaiting Fulfillment')]")
    private WebElement awaitingFulfillmentTxt;
    @FindBy(linkText = "Orders")
    private WebElement ordersMenuLinkTxt;
    @FindBy(linkText = "Returns")
    private WebElement returnsMenuLinkTxt;
    @FindBy(linkText = "Messages")
    private WebElement messagesMenuLinkTxt;
    @FindBy(linkText = "Addresses")
    private WebElement addressesMenuLinkTxt;
    @FindBy(linkText = "Wish Lists")
    private WebElement wishListsMenuLinkTxt;
    @FindBy(linkText = "Recently Viewed")
    private WebElement recentlyViewedLinkTxt;
    @FindBy(css = ".basePage-heading")
    private WebElement orderTitleTxt;
    @FindBy(css = ".alertBox-column.alertBox-message")
    private WebElement anyOrdersAlertBoxMessage;


    public WebElement getHomeLinkTxt() {
        return homeLinkTxt;
    }

    public WebElement getYourAccountLinkTxt() {
        return yourAccountLinkTxt;
    }

    public WebElement getYourOrdersLinkTxt() {
        return yourOrdersLinkTxt;
    }

    public WebElement getOrderNoTxt() {
        return OrderNoTxt;
    }

    public WebElement getProductsTotalingPriceTxt() {
        return productsTotalingPriceTxt;
    }

    public WebElement getProductDetailDateTxt() {
        return productDetailDateTxt;
    }

    public WebElement getAwaitingFulfillmentTxt() {
        return awaitingFulfillmentTxt;
    }

    public List<WebElement> getAllMenuLinksText() {
        return allMenuLinksText;
    }

    public WebElement getOrdersMenuLinkTxt() {
        return ordersMenuLinkTxt;
    }

    public WebElement getReturnsMenuLinkTxt() {
        return returnsMenuLinkTxt;
    }

    public WebElement getMessagesMenuLinkTxt() {
        return messagesMenuLinkTxt;
    }

    public WebElement getAddressesMenuLinkTxt() {
        return addressesMenuLinkTxt;
    }

    public WebElement getWishListsMenuLinkTxt() {
        return wishListsMenuLinkTxt;
    }

    public WebElement getRecentlyViewedLinkTxt() {
        return recentlyViewedLinkTxt;
    }

    public WebElement getOrderTitleTxt() {
        return orderTitleTxt;
    }

    public List<WebElement> getNavBarSection() {
        return navBarSection;
    }

    public WebElement getAnyOrdersAlertBoxMessage() {
        return anyOrdersAlertBoxMessage;
    }

}
