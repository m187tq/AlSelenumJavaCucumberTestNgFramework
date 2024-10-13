package com.automationteststore.pages.general;

import com.automationteststore.pages.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class ApplicationFlowPageImpl extends Page implements ApplicationFlowPage {
    @FindBy(how = How.CSS, using = ".maintext")
    protected WebElement heading;
    @FindBy(how = How.CSS, using = ".maintext + p strong")
    protected WebElement mainText;
    @FindBy(how = How.CSS, using = ".maintext + p")
    protected WebElement subheading;
    @FindBy(css = "button[title='Continue']")
    private WebElement continueButton;


    @Override
    public void assertElementIsDisplayed(String expectedElementName, boolean isExpectedToBeDisplayed) {
        if (expectedElementName.equalsIgnoreCase("Continue") && isExpectedToBeDisplayed) {
            Assert.assertTrue(continueButton.isDisplayed(), expectedElementName + " is not being displayed");
        }
    }


}
