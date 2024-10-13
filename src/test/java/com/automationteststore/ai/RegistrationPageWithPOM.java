package com.automationteststore.ai;

import com.automationteststore.pages.RegistrationPage;
import com.automationteststore.webdriverutilities.WebDrv;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;


public class RegistrationPageWithPOM {
    private RegistrationPage registrationPage;

    @Test
    public void validRegistration(String firstName, String lastName, String email, String telephone, String fax, String company, String address1, String address2, String city, String region, String zip, String country, String loginName, String password, String subscribe) {
        this.registrationPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), RegistrationPage.class);
        this.registrationPage.navigateToRegistrationPage();
        this.registrationPage.enterPersonalDetails(firstName, lastName, email, telephone, fax);
        this.registrationPage.enterAddressDetails(company, address1, address2, city, region, zip, country);
        this.registrationPage.enterLoginDetails(loginName, password);
        this.registrationPage.tickOnSubscribeAsYes();
        this.registrationPage.agreeToPrivacyPolicy();
        this.registrationPage.clickContinue();
    }
}
