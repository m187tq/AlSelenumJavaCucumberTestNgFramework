package com.automationteststore.pages;

import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;


public class AsideWidgetPage {
    final static Logger log = LogManager.getLogger(AsideWidgetPage.class);
    Page page;
    NotificationPage notificationPage;
    LogoutPage logoutPage;

    private WebDriver driver;
    @FindBy(xpath = "/html/body/div/div[2]/div/div[2]/div[1]/div/ul/li/a")
    private List<WebElement> asideWidgetLinks;

    public AsideWidgetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getAsideWidgetLinks() {
        return new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait)).until(ExpectedConditions.visibilityOfAllElements(asideWidgetLinks));
    }

    public void tapOnAsideWidgetLinkByName(String linkName) throws IOException {
        WebDrv.getInstance().getWebDriver().findElements(By.xpath("//a[contains(text(),'" + linkName + "')]"));
    }

    public void clickSideWidgetLinkByName(String linkName) {
        asideWidgetLinks.stream()
                .parallel()
                .filter(s -> s.getText().contains(linkName))
                .findFirst()
                .ifPresent(WebElement::click);
        log.info("Taped on " + linkName + " link");
    }

    public AccountPage tapMyAccountLink() throws IOException {
        clickSideWidgetLinkByName(" My Account");
        return new AccountPage();
    }

    public EditProfilePage tapEditAccountLink() throws IOException {
        clickSideWidgetLinkByName(" Edit Account");
        return new EditProfilePage();
    }

    public RegistrationPage tapOnRegisterLink() throws IOException {
        clickSideWidgetLinkByName("Register");
        return new RegistrationPage();
    }

    public ForgottenPasswordPage tapOnPasswordLink() {
        clickSideWidgetLinkByName(" Password");
        log.info("clicked Forgotten Password link");
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        log.info("current basePage url :: " + page.getThisPageUrl());
        log.info("current basePage title:: " + page.getThisPageTitle());
        return new ForgottenPasswordPage();
    }

    public AddressPage tapOnAddressBookLink() {
        clickSideWidgetLinkByName("Address Book");
        log.info("clicked Address Book link");
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        log.info("current basePage url :: " + page.getThisPageUrl());
        log.info("current basePage title:: " + page.getThisPageTitle());
        return new AddressPage();
    }

    public WishListPage tapOnWishListLink() throws IOException {
        clickSideWidgetLinkByName("Wish List");
        log.info("clicked Wish List link");
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        log.info("current basePage url :: " + page.getThisPageUrl());
        log.info("current basePage title:: " + page.getThisPageTitle());
        return new WishListPage();
    }

    public OrderHistoryPage tapOnOrderHistoryLink() throws IOException {
        clickSideWidgetLinkByName("Order History");
        log.info("clicked Order History link");
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        log.info("current basePage url :: " + page.getThisPageUrl());
        log.info("current basePage title:: " + page.getThisPageTitle());
        return new OrderHistoryPage();
    }

    public DownloadsPage tapOnDownloadLink() {
        clickSideWidgetLinkByName(" Download");
        log.info("clicked Order Download link");
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        return new DownloadsPage();
    }

    public NotificationPage tapOnNewsletterLink() throws IOException {
        clickSideWidgetLinkByName(" Newsletter");
        log.info("clicked Newsletter link");
        notificationPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), NotificationPage.class);
        return notificationPage;
    }

    public TransactionsPage tapOnTransactionsLink() throws IOException {
        clickSideWidgetLinkByName(" Transactions");
        log.info("clicked Transactions link");
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        log.info("current basePage url :: " + page.getThisPageUrl());
        log.info("current basePage title:: " + page.getThisPageTitle());
        return new TransactionsPage();
    }

    public LogoutPage tapLogoffLink() throws IOException {
        logoutPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), LogoutPage.class);
        clickSideWidgetLinkByName("  Logoff");
        return logoutPage;
    }

    public List<String> getAsideWidgetList() {
        List<String> asideWidgetList =
                asideWidgetLinks.stream()
                        .filter(WebElement::isDisplayed)
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
        log.info("Aside Widget List: " + asideWidgetList);
        return asideWidgetList;
    }

    public int getCountAsideWidgetList() {
        List<String> asideWidgetList =
                asideWidgetLinks.stream()
                        .filter(WebElement::isDisplayed)
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
        return asideWidgetList.size();
    }

    public boolean assertAsideWidgetItemsCountAndFirstAndLastIndex(int labelsCount, int firstIndex, int lastIndex) {
        List<WebElement> asideWidgetList = asideWidgetLinks;
        int size = asideWidgetList.size();
        for (int i = 0; i < size; i++) {
            if (asideWidgetList.get(i).getText().equals(String.valueOf(labelsCount))) {
                if (i == firstIndex) {
                    log.info("First Index: " + firstIndex);
                }
                if (i == lastIndex) {
                    log.info("Last Index: " + lastIndex);
                }

            }

        }

        return true;

    }

}

