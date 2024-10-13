package com.automationteststore.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReturnPolicyPage extends Page{
    private final static Logger log = LogManager.getLogger(ReturnPolicyPage.class);
    Page page;

    // Example: Close Button for Modal
    @FindBy(css = "button.close-modal") // Adjust selector based on actual HTML
    private WebElement closeModalButton;

    // Example: Return Policy Text
    @FindBy(css = "div.return-policy-content") // Adjust selector based on actual HTML
    private WebElement returnPolicyContent;

    /*=====================================
     * Getter Methods
     *=====================================*/

    public WebElement getCloseModalButton() {
        return closeModalButton;
    }

    public WebElement getReturnPolicyContent() {
        return returnPolicyContent;
    }

    /*=====================================
     * Verification Methods
     *=====================================*/

    public boolean isCloseModalButtonDisplayed() {
        return isDisplayed(closeModalButton);
    }

    public boolean isReturnPolicyContentDisplayed() {
        return isDisplayed(returnPolicyContent);
    }

    /*=====================================
     * Action Methods
     *=====================================*/

    public CheckoutConfirmationPage closeReturnPolicyModal() {
        click(closeModalButton);
        return new CheckoutConfirmationPage();
    }

    /*=====================================
     * Utility Methods
     *=====================================*/

    /**
     * Get Return Policy Text.
     *
     * @return Return Policy as String.
     */
    public String getReturnPolicyText() {
        return getText(returnPolicyContent);
    }
}