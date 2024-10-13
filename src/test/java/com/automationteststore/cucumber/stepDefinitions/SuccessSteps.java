package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.helperutilities.assertion.AssertionHelper;
import com.automationteststore.pages.AccountPage;
import com.automationteststore.pages.Page;
import com.automationteststore.pages.AccountSuccessPage;
import com.automationteststore.pages.TopMenuPage;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.PageFactory;

public class SuccessSteps {
    TopMenuPage topMenuPage;
    AccountPage accountPage;
    Page page;
    AccountSuccessPage accountSuccessPage;


    @Then("I should see {string} and {string} message")
    public void i_should_see_and_message(String pageHeader, String registerValidationMessage) {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        AssertionHelper.updateTestStatus(page.getThisPageHeaderText().contains(pageHeader));
        accountSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AccountSuccessPage.class);
        AssertionHelper.updateTestStatus(accountSuccessPage.getCongratulationsAccountSuccessfullyCreatedText().contains(registerValidationMessage));

    }

    @Then("I should see {string} in Account success page")
    public void i_should_see_in_account_success_page(String accountCreatedSuccessfullyText) {
        accountSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AccountSuccessPage.class);
        AssertionHelper.updateTestStatus(accountSuccessPage.getYourAccountHasBeenCreatedHeadingText().contains(accountCreatedSuccessfullyText));

    }

    @Then("I can see in Account success page as follows:")
    public void i_can_see_in_account_success_page_as_follows(DataTable dataTable) {
        topMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), TopMenuPage.class);
        AssertionHelper.updateTestStatus(topMenuPage.getWelcomeBackText().contains(dataTable.cell(0, 0)));
        accountSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AccountSuccessPage.class);
        AssertionHelper.updateTestStatus(accountSuccessPage.getYourAccountHasBeenCreatedText().contains(dataTable.cell(1, 0)));
        AssertionHelper.updateTestStatus(accountSuccessPage.getCongratulationsAccountSuccessfullyCreatedText().contains(dataTable.cell(2, 0)));
        AssertionHelper.updateTestStatus(accountSuccessPage.getMyAccountText().contains(dataTable.cell(3, 0)));

    }


    @Then("I can see {string} Your new account has been successfully created!")
    public void i_can_see_your_new_account_has_been_successfully_created(String congratsMsg) {
        accountSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AccountSuccessPage.class);
        AssertionHelper.updateTestStatus(accountSuccessPage.getCongratulationsAccountSuccessfullyCreatedText().contains(congratsMsg));
    }


    @And("I click on continue button in success page")
    public void iClickOnContinueButtonInSuccessPage() {
        accountSuccessPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), AccountSuccessPage.class);
        accountPage = accountSuccessPage.clickContinueButtonInSuccessPage();

    }
}
