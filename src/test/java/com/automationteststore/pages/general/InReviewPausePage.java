package com.automationteststore.pages.general;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;


/**
 * Created by sadheshviveganandan on 24/04/2019.
 */
public class InReviewPausePage {

    @FindBy(how = How.ID, using = "continue")
    private WebElement btn_Continue;

    public void assertContinueButtonIsDisplayed() {
        Assert.assertTrue(btn_Continue.isEnabled());
    }

    public void clickOnContinueButton() {
        btn_Continue.click();
    }
}
