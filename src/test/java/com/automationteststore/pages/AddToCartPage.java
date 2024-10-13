package com.automationteststore.pages;

import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddToCartPage {

    @FindBy(xpath = "//*[@id='quickSearch']/div/section/ul/li[2]")
    public WebElement addToCartByIndex;

    @FindBy(id = "quantity_wanted")
    private WebElement quantity;
    @FindBy(name = "group_1")
    private WebElement size;
    @FindBy(xpath = "//span[text()='Add to cart']")
    private WebElement addToCartBtn;
    @FindBy(xpath = "//*[@id='layer_cart']//h2/i")
    private WebElement addToCartMessage;
    @FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
    private WebElement proceedToCheckOutBtn;

    public WebElement getAddToCartByIndex() {
        return addToCartByIndex;
    }

    public WebElement getQuantity() {
        return quantity;
    }

    public WebElement getSize() {
        return size;
    }

    public void clickAddToCartBtn() {
        addToCartBtn.click();
    }

    public String getAddToCartMessage() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(addToCartMessage);
    }

    public void clickProceedToCheckOutBtn() {
        proceedToCheckOutBtn.click();
    }

}
