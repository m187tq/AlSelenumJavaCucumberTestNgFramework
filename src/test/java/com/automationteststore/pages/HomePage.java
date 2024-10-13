package com.automationteststore.pages;

import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class HomePage extends Page{

    private final static Logger log = LogManager.getLogger(HomePage.class);
    private final String linkSubMenu = "//a[text()='%s']";
    private final String linkSubMenu2 = "//div[text()='%s']/parent::a[text()='%s']";
    Page page;
    By addToCart = By.cssSelector("[title='Add to Cart']");
    @FindBy(css = ".prdocutname")
    private List<WebElement> productNames;
    @FindBy(css = ".welcome_msg h4")
    private WebElement welcome_msg;
    @FindBy(xpath = "///h2[@innertext='Fast shipping']")
    private WebElement fastShipping;
    @FindBy(xpath = "///h2[@innertext='Easy Payments']")
    private WebElement easyPayments;
    @FindBy(xpath = "///h2[@innertext='Shipping Options']")
    private WebElement shippingOptions;
    @FindBy(xpath = "///h2[@innertext='Large Variety']")
    private WebElement largeVariety;
    @FindBy(xpath = "span.maintext")
    private List<WebElement> headingLists;
    @FindBy(css = ".col-md-3.col-sm-6.col-xs-12")
    private List<WebElement> allProductsList;
    @FindBy(css = ".info_links_footer > li:nth-of-type(5) > a")
    private WebElement contactusLink;
    private String url = "https://automationteststore.com";

    // Locators
    private By categoryMenu = By.cssSelector(".nav .dropdown");
    private By logo = By.cssSelector(".logo a");


    // Methods
    public void navigateToTheHomePage() {
        WebDrv.getInstance().getWebDriver().get("https://automationteststore.com/");

    }

    public void addProductToTheCart(String productName) {
        boolean productFound = false;
        for (WebElement product : productNames) {
            if (product.getText().trim().equalsIgnoreCase(productName)) {
                product.click();
                productFound = true;
                break;
            }
        }
        if (!productFound) {
            throw new RuntimeException("Product '" + productName + "' not found on the homepage.");
        }
    }

    public String getFastShipping() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(fastShipping);
    }

    public String getEasyPayments() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(easyPayments);

    }

    public String getShippingOptions() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(shippingOptions);

    }

    public String getLargeVarietyLargeVariety() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(largeVariety);

    }

    public List<WebElement> getHeadingLists() {
        return headingLists;

    }

    public List<WebElement> getProductList() {
        return allProductsList;

    }

    public void addProductsToCart(String productName) {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
    }

    public WebElement getProductByName(String productName) {
        return allProductsList
                .stream()
                .parallel()
                .filter(product -> product.findElement(By.cssSelector("a.prdocutname")).getText().contains(productName))
                .findFirst()
                .orElse(null);

    }

    public HomePage addProductToCart(String productName) {
        List<WebElement> products = WebDrv.getInstance().getWebDriver().findElements(By.cssSelector("div.col-md-3.col-sm-6.col-xs-12"));
        WebElement prod = products
                .stream()
                .parallel()
                .filter(product -> product.findElement(By.cssSelector("a")).getText().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
        assert prod != null;
        prod.findElement(By.cssSelector("a.productcart")).click();
        log.info("Product " + productName + " added to cart");
        return this;
    }


    public String getWelcomeMessage() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(welcome_msg);

    }

    public boolean assertWelcomeMessage() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(welcome_msg);

    }

    public void navigateToHomePage() {
        if (WebDrv.getInstance().getWebDriver() == null) {
            page = PageFactory.initElements(WebDrv.getInstance().openBrowser("", null), Page.class);
            page.navigateTo(GlobalVarsHelper.getHomePageUrl());
        } else {
            WebDrv.getInstance().getWebDriver().manage().deleteAllCookies();
            page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
            page.navigateTo(GlobalVarsHelper.getHomePageUrl());
        }
    }

    public void navigateToRegistrationPage() {
        if (WebDrv.getInstance().getWebDriver() == null) {
            page = PageFactory.initElements(WebDrv.getInstance().openBrowser("", null), Page.class);
            page.navigateTo(GlobalVarsHelper.getAccountCreatePageUrl());
        } else {
            WebDrv.getInstance().getWebDriver().manage().deleteAllCookies();
            page = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), Page.class);
            page.navigateTo(GlobalVarsHelper.getAccountCreatePageUrl());
        }
    }

    public void selectCategory(String categoryName) {
        WebElement category = WebDrv.getInstance().getWebDriver().findElement(By.linkText(categoryName));
        category.click();
    }

    public void selectProduct(String productName) {
        WebElement product = WebDrv.getInstance().getWebDriver().findElement(By.linkText(productName));
        product.click();
    }

    public void navigateBackToHomePage() {
        page.clickOnLogoImage();

    }


}
