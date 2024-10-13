package com.automationteststore.pages;

import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductCategoryPage {
    private final static Logger log = LogManager.getLogger(ProductCategoryPage.class);
    By addToCart = By.cssSelector("[title='Add to Cart']");
    @FindBy(css = ".col-md-3.col-sm-6.col-xs-12")
    private List<WebElement> totalPriceTxt;
    @FindBy(css = ".pricenew")
    private List<WebElement> price_new;
    @FindBy(css = ".priceold")
    private WebElement price_old;
    @FindBy(css = "select#sort")
    private List<WebElement> sortBy;
    @FindBy(css = ".col-md-3.col-sm-6.col-xs-12")
    private List<WebElement> allProductsList;
    @FindBy(css = "[title='Add to Cart']")
    private List<WebElement> addTo_Cart;
    @FindBy(css = "a.prdocutname")
    private List<WebElement> productNames;
    @FindBy(css = "[class='col-md-3 col-sm-6 col-xs-12']")
    private List<WebElement> productsList;
    @FindBy(xpath = "/html/body/div/div/div/div/div/div/div/div/div[1]/div/a")
    private List<WebElement> productnamesList;
    @FindBy(css = "[title='Add to Cart']")
    private List<WebElement> add2CartList;
    @FindBy(css = "i.fa.fa-phone.fa-fw")
    private WebElement call2orderList;
    @FindBy(css = "[class='nostock']")
    private List<WebElement> noStockList;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/div/div/div/div/div/div/div")
    private List<WebElement> onePriceList;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/div/div[1]/form/select/option")
    private List<WebElement> sortByDropdownList;
    @FindBy(id = "sort")
    private WebElement sortByDropdown;
    @FindBy(css = "select#limit")
    private WebElement perPageDropdown;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/div/div[5]/form/select/option")
    private List<WebElement> perPageDropdownList;
    @FindBy(css = ".fa.fa-th-list.icon-white")
    private WebElement listIconWhiteIcon;
    @FindBy(css = ".fa.fa-th")
    private WebElement listGridIcon;
    @FindBy(css = ".flex-control-nav.flex-control-paging")
    private WebElement testimonyPaging;

    public List<WebElement> allProductsList() {
        return productsList;

    }

    public void clickOnFirstItemOnProductsList() {
        if (productsList.size() > 0) {
            productsList.get(1).click();
        }
    }

    public List<WebElement> productNamesList() {
        return productnamesList;

    }

    public List<WebElement> add2CartList() {
        return add2CartList;

    }


    public List<WebElement> noStockList() {
        return noStockList;

    }

    public List<WebElement> onePriceList() {
        return onePriceList;

    }

    public List<WebElement> sortByDropdownList() {
        return sortByDropdownList;

    }

    public void clickOnSortByDropdown() {
        sortByDropdown.click();

    }

    public void clickPerPageDropdown() {
        perPageDropdown.click();

    }

    public List<WebElement> perPageDropdownList() {
        return perPageDropdownList;

    }

    public void mouseOverOnProduct(int number) {
//         /html/body/div/div[1]/div[1]/section/nav/ul/li[4]/a
        String fPart = "/html/body/div/div[1]/div[1]/section/nav/ul/li[";
        String sPart = "]/a";
        Actions action = new Actions(WebDrv.getInstance().getWebDriver());
        System.out.println("doing mouse over on: " + number + "..product");
        action.moveToElement(WebDrv.getInstance().getWebDriver().findElement(By.xpath(fPart + number + sPart))).build().perform();
    }

    public List<WebElement> getProductList() {
        return allProductsList;

    }

    public WebElement getProductByName(String productName) {
        return getProductList()
                .stream()
                .filter(product -> product.findElement(By.cssSelector("a.prdocutname")).getText().equals(productName))
                .findFirst()
                .orElse(null);
    }

    public void addProductToCart(String productName) {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
    }


    public List<WebElement> getTotalPriceTxt() {
        return totalPriceTxt;

    }

    public List<WebElement> getPrice_new() {
        return price_new;

    }

    public WebElement getPrice_old() {
        return price_old;

    }

    public List<WebElement> getSortBy() {
        return sortBy;

    }

    public List<WebElement> productsList() {
        return allProductsList;

    }

    public List<WebElement> getProductNames() {
        return productNames;

    }

    public List<WebElement> getAddTo_Cart() {
        return addTo_Cart;
    }

    public void clickOnAddToCart() {
        System.out.println("clicking on add to cart");
        WebDrv.getInstance().getWebDriver().findElement(addToCart).click();
    }

    public WebElement getListIconWhiteIcon() {
        return listIconWhiteIcon;

    }

    public WebElement getListGridIcon() {
        return listGridIcon;

    }

    public WebElement getTestimonyPaging() {
        return testimonyPaging;

    }

    public WebElement getCall2orderList() {
        return call2orderList;

    }


}
