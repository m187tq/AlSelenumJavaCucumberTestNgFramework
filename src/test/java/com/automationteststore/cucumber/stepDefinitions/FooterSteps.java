package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.helperutilities.NavigateToNewTab;
import com.automationteststore.pages.Page;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;

public class FooterSteps {
    Page page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);

    @When("^I click the Terms and Conditions link in the bottom footer$")
    public void i_click_the_Terms_and_Conditions_link_in_the_bottom_footer() {
        //page.clickTermsAndConditionsLink();
    }

    @When("^the Terms and Conditions are displayed in new tab$")
    public void the_Terms_and_Conditions_are_displayed_in_new_tab() throws Throwable {
        NavigateToNewTab.navigateToNewTab();
    }

    @When("^I click the privacy policy link in the bottom footer$")
    public void i_click_the_privacy_policy_link_in_the_bottom_footer() {
        //page.clickPrivacyPolicyLink();
    }


    @When("^the Privacy Policy is displayed in new tab$")
    public void the_Privacy_Policy_is_displayed_in_new_tab() throws Throwable {
        NavigateToNewTab.navigateToNewTab();

    }

    @When("^I click the cookies link in the bottom footer$")
    public void i_click_the_cookies_link_in_the_bottom_footer() throws Throwable {
        //page.clickCookiesLink();
    }


    @When("^the cookies is displayed in new tab$")
    public void the_cookies_is_displayed_in_new_tab() throws Throwable {
        NavigateToNewTab.navigateToNewTab();
    }


    @And("^the link should be labelled essential cookies\"([^\"]*)\"$")
    public void the_link_should_be_labelled_essential_cookies(String linkText) throws Throwable {
        //page.assertEssentialCookiesLinkIsDisplayed();

    }

    @And("^the link should be labelled manage cookies\"([^\"]*)\"$")
    public void the_link_should_be_labelled_manage_cookies(String linkText) throws Throwable {
        //page.assertManageCookiesLinkIsDisplayed();
    }

    @And("^the link should be labelled privacy policy\"([^\"]*)\"$")
    public void the_link_should_be_labelled_privacy_policy(String linkText) throws Throwable {
        //page.assertPolicyPrivacyLinkIsDisplayed();

    }

    @And("^the button should be labelled use google analytics radio button\"([^\"]*)\"$")
    public void the_button_should_be_labelled_use_google_analytics_radio_button(String button) throws Throwable {
        //page.assertUseGoogleAnalyticsRadioButtonIsDisplayed();

    }

    @And("^the button should be labelled do not use google analytics radio button\"([^\"]*)\"$")
    public void the_button_should_be_labelled_do_not_use_google_analytics_radio_button(String button) throws Throwable {
        //page.assertDoNotUseGoogleAnalyticsRadioButtonIsDisplayed();
    }

    @And("^the button should be labelled save \"([^\"]*)\"$")
    public void the_button_should_be_labelled_save(String button) throws Throwable {
        //page.assertSaveRadioButtonIsDisplayed();
    }

    @Then("^the following cookies radio buttons should be present on the page$")
    public void following_cookies_radio_buttons_should_be_present_on_page(DataTable data) {
        //page.assertCookiesRadioButtons(data);
    }

    @Given("^I have previously selected Use Google Analytics cookies on customer cookie page$")
    public void i_have_previously_selected_use_google_analytics_cookies_on_candidate_cookie_page() {
        //page.clickOnCandidateUseGoogleAnalyticsCookiesButton();

    }

    @Then("^the customer cookie page needs to load with the previously selected cookie option of \"([^\"]*)\"$")
    public void the_candidate_cookie_page_needs_to_load_with_the_previously_selected_cookie_option_of(String preference) throws Throwable {
        //page.assertOnCorrectCandidateCookiesPreference(preference);
    }

    @Then("the section will expand showing the GA cookie details for customer")
    public void the_section_will_expand_showing_the_GA_cookie_details_candidate() {
        //page.assertCandidateExpandableLinkGoogleAnalyticsDetails();
    }

    @Then("the section will expand showing the essential cookie details")
    public void the_section_will_expand_showing_the_essential_cookie_details() {
        //page.assertExpandableLinkEssentialCookiesDetails();
    }

    @Then("the section will expand showing the essential cookie details for customer")
    public void the_section_will_expand_showing_the_essential_cookie_details_for_candidate() {
        //page.assertCandidateExpandableLinkEssentialCookiesDetails();
    }

    @Then("the find more about how to manage cookies will open on a new window")
    public void the_find_more_about_how_to_manage_cookie_will_open_on_a_new_window() throws Exception {
        //page.assertManageCookiesPage();
    }


}

