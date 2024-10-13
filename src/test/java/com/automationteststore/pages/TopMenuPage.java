package com.automationteststore.pages;

import com.automationteststore.enums.WaitStrategy;
import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.helperutilities.javaScript.JavaScriptHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import com.google.common.util.concurrent.Uninterruptibles;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;


public class TopMenuPage {

    private final Logger log = LogManager.getLogger(TopMenuPage.class);
    Page page;
    By addToCart = By.cssSelector("i.fa.fa-cart-plus.fa-fw");
    By login_option = By.xpath("//a[contains(text(),'Login')]");
    @FindBy(xpath = "//a[normalize-space()='Login or register']")
    WebElement loginOrRegisterLink;
    @FindBy(linkText = "Login or register")
    WebElement link_LoginOrRegister;
    @FindBy(xpath = "/html/body/div/div[1]/div[2]/section/ul/li/a")
    private List<WebElement> allPageBreadCrumbList;
    @FindBy(xpath = "/html/body/div/header/div[1]/div/div[2]/div/div/div/ul/li/a")
    private List<WebElement> topMenuList;
    @FindBy(xpath = "/html/body/div/header/div[1]/div/div[2]/div/div/div/ul/li/a")
    private WebElement menuItemsLink;
    @FindBy(xpath = "(//span[text()='Specials'])[1]")
    private WebElement specialsLink;
    @FindBy(xpath = "//*[@id='main_menu_top']/li[2]/a/span")
    private WebElement accountLink;
    @FindBy(xpath = "//span[normalize-space()='3']")
    private WebElement itemCount;
    @FindBy(xpath = "//ul[@id='main_menu_top']//span[@class='menu_text']")
    private List<WebElement> menuList;
    @FindBy(xpath = "(//span[text()='Login'])[1]")
    private WebElement loginOption;
    @FindBy(xpath = "//*[@id='main_menu_top']/li[2]/ul/li[2]/a/span")
    private WebElement checkYourOrderOption;
    @FindBy(xpath = "/html/body/div/header/div[1]/div/div[2]/div/div[3]/div/ul/li[2]/ul/li")
    private List<WebElement> accountOptionList;
    @FindBy(xpath = "//a[contains(text(),'My Account')]")
    private WebElement account_link;
    @FindBy(css = "ul[class='nav topcart pull-left'] li[class='dropdown hover']")
    private WebElement itemsLink;
    @FindBy(css = "span.cart_total")
    private WebElement cartTotalAmount;
    @FindBy(xpath = "//span[normalize-space()='1']")
    private WebElement noOfItemCount;
    @FindBy(xpath = "/html/body/div/header/div[2]/div/div[3]/ul/li/ul/li/div[2]/div/a")
    private List<WebElement> itemsCartDropdownButtonsList;
    @FindBy(css = "a[title='View Cart']")
    private WebElement viewCartIcon;
    @FindBy(css = ".fa.fa-money.fa-fw")
    private WebElement checkoutIcon;
    @FindBy(xpath = "(//span[text()='Logout'])[1]")
    private WebElement logoutOption;
    @FindBy(xpath = "//div[contains(text(),'Welcome back')]")
    private WebElement welcomeBackText;
    @FindBy(xpath = "//body[1]/div[1]/header[1]/div[2]/div[1]/div[2]/ul[1]/li[1]/a[1]/span[1]")
    private WebElement currencyDropdown;
    @FindBy(xpath = "/html/body/div/header/div[2]/div/div[3]/ul/li")
    private WebElement itemsCartDropdown;
    @FindBy(css = "span.cart_block_total")
    private List<WebElement> itemsCartTotalLabelAndPriceList;
    @FindBy(xpath = "//header/div[1]/div[1]/div[1]/a[1]/img[1]")
    private WebElement accountIconImage;
    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/ul[1]/li[1]/a[1]")
    private WebElement accountStatusTxt;
    @FindBy(xpath = "/html/body/div/div[1]/div[2]/section/ul/li/a")
    private List<WebElement> breadcrumbsList;
    @FindBy(xpath = "//ul[@id='customer_menu_top']//div[@class='menu_text']")
    private WebElement userProfileDropdown;
    @FindBy(xpath = "/html/body/div/header/div[1]/div/div[2]/div/div[2]/div/ul/li/ul/li/a")
    private List<WebElement> userProfileDropdownList;
    @FindBy(css = "div[class='menu_text']")
    private WebElement WelcomeMessage;
    @FindBy(xpath = "/html/body/div/header/div/div/div/ul/li/ul/li/div/div/table/tbody/tr/td[2]")
    private List<WebElement> itemsProductnames;


    public By getAddToCart() {
        return addToCart;

    }

    public List<WebElement> getTopMenuList() {
        return topMenuList;

    }

    public boolean getTopMenuItemsList(String menuName) {
        new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait)).until((s) -> topMenuList.size() == 5);
        return topMenuList.stream().parallel().anyMatch(s -> s.getText().contains(menuName));

    }

    public void assertMenuItemExists(String name) {
        List<WebElement> menuName = menuItemsLink.findElements(By.tagName("li"));
        for (WebElement li : menuName) {
            if (li.getText().contains(name)) {
                Assert.assertEquals(li.getText(), name);
                System.out.println(li.getText());
            }
        }
    }

    public TopMenuPage navigateToHomePage() {
        if (WebDrv.getInstance().getWebDriver() == null) {
            page = PageFactory.initElements(WebDrv.getInstance().openBrowser("", null), Page.class);
            page.navigateTo(GlobalVarsHelper.getHomePageUrl());
        } else {
            WebDrv.getInstance().getWebDriver().manage().deleteAllCookies();
            page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
            page.navigateTo(GlobalVarsHelper.getHomePageUrl());
        }
        return new TopMenuPage();
    }

    public void clickOnLoginOrRegister() {
        loginOrRegisterLink.click();
        log.info("Click on Login or Register");
    }

    public LoginPage clickOnLoginOrRegisterLink() throws InterruptedException {
        loginOrRegisterLink.click();
        log.info("Click on Login or Register");
        return new LoginPage();
    }

    public LoginPage navigateToLoginPage() {
        loginOrRegisterLink.click();
        log.info("Click on Login or Register");
        return new LoginPage();
    }

    public boolean assertLoginOrRegisterLinkIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(loginOrRegisterLink);
    }

    public String getLoginOrRegisterLink() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(loginOrRegisterLink);

    }

    public CheckoutCartPage clickOnViewCartLink() {
        viewCartIcon.click();
        log.info("Clicked on Cart Link");
        return new CheckoutCartPage();
    }

    public LoginPage clickCheckoutIcon() {
        checkoutIcon.click();
        log.info("Clicked on Checkout icon");
        return new LoginPage();
    }

    public CheckoutConfirmationPage clickOnCheckoutButton() {
        checkoutIcon.click();
        log.info("Clicked on Checkout icon");
        return new CheckoutConfirmationPage();
    }

    public boolean assertProductsAreDisplayed() {
        return itemsProductnames.stream().parallel().filter(WebElement::isDisplayed).count() == itemsProductnames.size();

    }

    public boolean assertProductNamesAreDisplayed() {
        boolean status = false;
        int size = itemsProductnames.size();
        for (WebElement itemsProductname : itemsProductnames) {
            if (new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(itemsProductname)) {
                status = true;
                break;
            }
        }
        return status;
    }


    public RegistrationPage navigateToRegisterPage() {
        WebDrv.getInstance().getWebDriver().get("https://automationteststore.com/index.php?rt=account/create");
        return new RegistrationPage();
    }

    public WebElement getItemsLink() {
        return itemsLink;

    }

    public boolean assertItemsLinkIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(itemsLink);

    }

    public WebElement getItemCount() {
        return itemCount;

    }

    public String getItemCountText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(itemCount);

    }

    public boolean assertItemCountTextIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(itemCount);

    }

    public void mouseOverItemLink() {
        Actions action = new Actions(WebDrv.getInstance().getWebDriver());
        action.moveToElement(itemsLink).build().perform();
        action.moveToElement(checkoutIcon).click().build().perform();

    }

    public ShoppingCartPage mouseOverAndClickOnItemsLink() {
        new JavaScriptHelper(WebDrv.getInstance().getWebDriver()).scrollToElementAndClick(itemsLink);
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        return new ShoppingCartPage();
    }


    public CheckoutCartPage mouseOverItemsCartLinkAndClickCheckoutIcon() {
        Actions action = new Actions(WebDrv.getInstance().getWebDriver());
        action.moveToElement(itemsLink).build().perform();
        checkoutIcon.click();
        log.info("Clicked on Checkout icon");
        return new CheckoutCartPage();
    }

    public ShoppingCartPage mouseOverItemsCartLinkAndClickViewCartIcon() {
        Actions action = new Actions(WebDrv.getInstance().getWebDriver());
        action.moveToElement(itemsLink).build().perform();
        viewCartIcon.click();
        log.info("Clicked on View Cart icon");
        return new ShoppingCartPage();

    }

    public String getCartTotalAmountText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(cartTotalAmount);

    }

    public boolean assertCartTotalAmountIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(cartTotalAmount);

    }

    public String getCartTotalQuantityText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(noOfItemCount);

    }

    public boolean assertNoOfItemCountIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(noOfItemCount);

    }


    public List<WebElement> getItemsCartDropdownButtonsList() {
        return itemsCartDropdownButtonsList;

    }

    public LoginPage clickCheckoutCartDropdownIconButton() throws IOException {
        itemsCartDropdownButtonsList.get(1).click();
        log.info("Clicked on cart dropdown icon");
        return new LoginPage();

    }

    public ShoppingCartPage clickViewCartDropdownIconButton() {
        viewCartIcon.click();
        log.info("Clicked on view cart dropdown icon");
        return new ShoppingCartPage();
    }

    public boolean assertTopMenuItemsListArePresent(String menuItem) {
        new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait)).until(d -> topMenuList.size() > 3);
        try {
            for (int i = 0; i < topMenuList.size(); i++) {
                if (topMenuList.get(i).isDisplayed()) {
                    log.info("Index " + i + " :: " + "Element is Present: " + topMenuList.get(i).getText());
                }
            }
        } catch (Exception e) {
            log.error("Fail: Element is Not Present");
        }
        return topMenuList.stream().anyMatch(s -> s.isDisplayed() && s.getText().contains(menuItem));
    }

    public WebElement getLogoutOption() {
        return logoutOption;

    }

    public String getWelcomeBackText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(welcomeBackText);

    }
    public boolean isWelcomeBackMessageDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(welcomeBackText);

    }

    public WebElement getCurrencyDropdown() {
        return currencyDropdown;

    }

    public WebElement getItemsCartDropdown() {
        return itemsCartDropdown;

    }

    public List<WebElement> getItemsCartTotalLabelAndPriceList() {
        return itemsCartTotalLabelAndPriceList;

    }

    public WebElement getAccountIconImage() {
        return accountIconImage;

    }

    public WebElement getAccountStatusTxt() {
        return accountStatusTxt;

    }

    public List<WebElement> getBreadcrumbsList() {
        return breadcrumbsList;

    }

    public WebElement getUserProfileDropdown() {
        return userProfileDropdown;

    }

    public List<WebElement> getUserProfileDropdownList() {
        return userProfileDropdownList;

    }

    public List<WebElement> getItemsProductnames() {
        return itemsProductnames;

    }

    public void hoverOverAccountLink() {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        page.moveToElement(accountLink);
        log.info("Hovered over Account link");

    }


    public String getLoginButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(loginOption);
    }

    public String getCheckYourOrderButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(checkYourOrderOption);
    }

    public LoginPage tapOnLoginOption() {
        page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
        page.click(login_option, WaitStrategy.CLICKABLE);
        return new LoginPage();
    }

    public String getWelcomeMessage() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(WelcomeMessage);
    }


    public void goToHomePage() {
        WebDrv.getInstance().getWebDriver().get(GlobalVarsHelper.getHomePageUrl());

    }

    public void openUserProfile() {
        userProfileDropdown.click();
        log.info("Clicked on User Profile Dropdown");
    }

    public String getUserProfileWelcomeMessage() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(userProfileDropdown);
    }

    public boolean isUserProfileWelcomeMessageDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(userProfileDropdown);
    }


}