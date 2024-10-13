package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.base.BaseTest;
import com.automationteststore.pages.Page;
import com.automationteststore.pages.TopMenuPage;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TopMenuPageSteps extends BaseTest {
    Page page;

    @Then("I should see {string} and {string} links")
    public void iShouldSeeAndLinks(String login, String checkYourOrder) {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        Assert.assertTrue(topMenuPage.getLoginButtonText().equalsIgnoreCase(login), "expected [true] but found [false]");
        Assert.assertTrue(topMenuPage.getCheckYourOrderButtonText().equalsIgnoreCase(checkYourOrder));
    }

    @When("I hover over Account menu item link")
    public void hoverOverAccountLink() {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        topMenuPage.hoverOverAccountLink();
    }

    @And("I tap on Login options")
    public void iTapOnLoginOptions() {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        topMenuPage.tapOnLoginOption();
    }

}