package com.automationteststore.pages;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShippingReturnsPage extends Page{
    private final static Logger log = LogManager.getLogger(ShippingReturnsPage.class);
    Page page;
    // Example: Shipping Form Fields
    @FindBy(css = "input#shipping_firstname")
    private WebElement shippingFirstName;

    @FindBy(css = "input#shipping_lastname")
    private WebElement shippingLastName;

    @FindBy(css = "input#shipping_address")
    private WebElement shippingAddress;

    @FindBy(css = "button#save_shipping")
    private WebElement saveShippingButton;


    /*=====================================
     * Getter Methods
     *=====================================*/

    public WebElement getShippingFirstName() {
        return shippingFirstName;
    }

    public WebElement getShippingLastName() {
        return shippingLastName;
    }

    public WebElement getShippingAddress() {
        return shippingAddress;
    }

    public WebElement getSaveShippingButton() {
        return saveShippingButton;
    }

    /*=====================================
     * Verification Methods
     *=====================================*/

    public boolean isShippingFirstNameDisplayed() {
        return isDisplayed(shippingFirstName);
    }

    public boolean isShippingLastNameDisplayed() {
        return isDisplayed(shippingLastName);
    }

    public boolean isShippingAddressDisplayed() {
        return isDisplayed(shippingAddress);
    }

    public boolean isSaveShippingButtonDisplayed() {
        return isDisplayed(saveShippingButton);
    }

    public boolean isSaveShippingButtonEnabled() {
        return isEnabled(saveShippingButton);
    }

    /*=====================================
     * Action Methods
     *=====================================*/

    public void enterShippingDetails(String firstName, String lastName, String address) {
        sendKeys(shippingFirstName, firstName);
        sendKeys(shippingLastName, lastName);
        sendKeys(shippingAddress, address);
    }

    public CheckoutConfirmationPage clickSaveShippingButton() {
        click(saveShippingButton);
        return new CheckoutConfirmationPage();
    }
}
