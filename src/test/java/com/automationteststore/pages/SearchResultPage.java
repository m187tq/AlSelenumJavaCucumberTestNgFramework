package com.automationteststore.pages;

import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.helperutilities.javaScript.JavaScriptHelper;
import com.automationteststore.helperutilities.select.DropDownHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class SearchResultPage {

    WebDriver driver;
    private final static Logger log = LogManager.getLogger(SearchResultPage.class);
    Page page;
    By addToCart = By.cssSelector("a[title='Add to Cart']");

    @FindBy(css = ".col-md-3.col-sm-6.col-xs-12")
    private List<WebElement> allProductsList;
    @FindBy(css = "[title='Add to Cart']")
    private List<WebElement> addTo_Cart;
    @FindBy(css = ".col-md-3.col-sm-6.col-xs-12")
    private List<WebElement> searchResultFound;
    @FindBy(css = "/html/body/div/div[2]/div/div/div/div/div[3]/div/div[1]/div/a")
    private List<WebElement> productTitleList;
    @FindBy(id = "keyword")
    private WebElement searchBox;
    @FindBy(css = "#keyword")
    private WebElement searchCriteriaAttributeValue;
    @FindBy(xpath = "//select[@id='category_id']")
    private WebElement allCategoriesDropdown;
    @FindBy(id = "/html/body/div/div[2]/div/div/div/div/div[1]/fieldset/div[2]/div/select/option")
    private List<WebElement> allSubCategoriesDropdownList;
    @FindBy(css = "label[for='description']")
    private WebElement searchProductDescriptionsRadioButton;
    @FindBy(css = "label[for='model']")
    private WebElement searchProductModelRadioButton;
    @FindBy(css = "label[for='price']")
    private WebElement searchProductPriceRadioButton;
    @FindBy(css = "#search_button")
    private WebElement searchButton;
    @FindBy(css = "label[for='viewed']")
    private WebElement searchProductViewedRadioButton;
    @FindBy(xpath = "//h4[normalize-space()='Search Criteria']")
    private WebElement SearchCriteriaText;
    @FindBy(xpath = "//h4[normalize-space()='Products meeting the search criteria']")
    private WebElement productsMeetingSearchCriteriaText;
    @FindBy(css = "label[for='model']")
    private WebElement searchProductModelRadioButtonLabel;
    @FindBy(xpath = "//div[contains(text(),'There is no product that matches the search criter')]")
    private WebElement noSearchResultFoundText;
    @FindBy(css = "option[value='date_modified-ASC']")
    private WebElement dateOldNew;
    @FindBy(css = ".fa.fa-th-list.icon-white")
    private WebElement listIconWhiteIcon;
    @FindBy(css = ".fa.fa-th")
    private WebElement listGridIcon;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/div/div[2]/form/select/option")
    private List<WebElement> sortByDropdownList;
    @FindBy(id = "sort")
    private WebElement sortByDropdown;
    @FindBy(css = "select#limit")
    private WebElement perPageDropdown;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/div/div[5]/form/select/option")
    private List<WebElement> perPageDropdownList;
    @FindBy(xpath = "i.fa.fa-cart-plus.fa-fw")
    private List<WebElement> addToCartButtonList;
    @FindBy(xpath = "(//a[@title='Add to Cart'])")
    private WebElement addToCartButton;
    @FindBy(css = "span.nostock")
    private List<WebElement> outOfStockButtons;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/div/div/div/div/div/div/div")
    private List<WebElement> onePriceList;
    @FindBy(xpath = "//body/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[5]/form[1]")
    private WebElement perPageSearchResultCount;


    public void clickPerPageDropdown() {
        perPageDropdown.click();

    }

    public List<WebElement> perPageDropdownList() {
        return perPageDropdownList;

    }

    public void addToCart() {
        addToCartButton.click();
        log.info("Product added to cart successfully");
    }

    public boolean isProductExist(String productName) {
        boolean flag = false;
        for (WebElement product : searchResultFound) {
            if (product.getAttribute("title").equals(productName)) {
                flag = true;
                break;
            }
        }

        return flag;

    }

    public void selectProduct(String productName) {
        for (WebElement product : searchResultFound) {
            if (product.getAttribute("title").equals(productName)) {
                product.click();
            }
        }

    }

    public void waitForTheSearchResult() {
        await().atMost(15, MINUTES)
                .pollDelay(5, SECONDS)
                .pollInterval(5, SECONDS)
                .until(() -> {
                    searchButton.click();
                    return searchResultFound.get(0).getText().equalsIgnoreCase("1");
                });
    }

    public WebElement getSearchBox() {
        return searchBox;

    }

    public WebElement getAllCategoriesDropdown() {
        return allCategoriesDropdown;

    }

    public List<WebElement> getAllSubCategoriesDropdownList() {
        return allSubCategoriesDropdownList;

    }

    public void clickSearchProductDescriptionsRadioButton() {
        searchProductDescriptionsRadioButton.click();

    }

    public String getSearchProductDescriptionsRadioButtonLabel() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(searchProductDescriptionsRadioButton);

    }

    public void clickSearchProductModelRadioButton() {
        searchProductModelRadioButton.click();

    }


    public void clickSearchProductPriceRadioButton() {
        searchProductPriceRadioButton.click();

    }

    public String getSearchProductPriceRadioButton() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(searchProductPriceRadioButton);

    }

    public String getSearchButton() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(searchButton);

    }

    public void clickSearchButton() {
        searchButton.click();

    }

    public void clickSearchProductViewedRadioButton() {
        searchProductViewedRadioButton.click();

    }

    public String getSearchProductViewedRadioButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(searchProductViewedRadioButton);

    }

    public String getSearchCriteriaText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(SearchCriteriaText);
    }

    public String getSearchCriteriaAttributeValue() {
        return searchCriteriaAttributeValue.getAttribute("value");

    }

    public boolean assertSearchCriteriaTextIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(SearchCriteriaText);

    }


    public boolean assertProductsMeetingSearchCriteriaTextIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(productsMeetingSearchCriteriaText);

    }

    public String getProductsMeetingSearchCriteriaText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(productsMeetingSearchCriteriaText);

    }

    public boolean assertSearchProductModelRadioButtonLabel() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(searchProductModelRadioButtonLabel);

    }

    public String getSearchProductModelRadioButtonLabel() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(searchProductModelRadioButtonLabel);

    }


    public void clickListIconWhiteIcon() {
        listIconWhiteIcon.click();

    }

    public boolean assertListIconWhiteIconIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(listIconWhiteIcon);

    }

    public boolean assertListGridIconIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(listGridIcon);

    }

    public void clickListGridIcon() {
        listGridIcon.click();
        log.info("List grid icon clicked successfully");
    }

    public List<WebElement> getSortByDropdownList() {
        return sortByDropdownList;

    }

    public String getSortByDropdownDateOldNewText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(dateOldNew);
    }

    public boolean assertSortByDropdownItemIsSelected() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isSelected(dateOldNew);
    }

    public boolean assertSortByDropdownDefaultItemIsSelected(String dateOldNewText) {
        new DropDownHelper(WebDrv.getInstance().getWebDriver()).selectUsingVisibleText(sortByDropdown, dateOldNewText);
        return true;
    }

    public boolean assertSortByDropdownIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(sortByDropdown);

    }

    public void clickSortByDropdownButton() throws InterruptedException {
        driver = WebDrv.getInstance().getWebDriver();
        new JavaScriptHelper(driver).scrollToElementAndClick(sortByDropdown);
        log.info("Sort by dropdown button clicked successfully");
    }

    public boolean assertPerPageDropdownIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(perPageDropdown);

    }

    public WebElement getPerPageDropdown() {
        return perPageDropdown;

    }

    public List<WebElement> getPerPageDropdownList() {
        return perPageDropdownList;

    }

    public List<WebElement> getAddToCartButtonList() {
        return addToCartButtonList;

    }

    public boolean assertAddToCartButtonIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(addToCartButton);

    }

    public void clickAddToCartButton() {
        addToCartButton.click();

    }

    public List<WebElement> getOutOfStockButtons() {
        return outOfStockButtons;

    }

    public List<WebElement> getOnePriceList() {
        return onePriceList;

    }

    public void assertProductName(String productTitle) {
        productTitleList.stream().parallel().forEach(product -> {
            if (product.isDisplayed() && product.getText().equals(productTitle)) {
                log.info("Product Title: " + productTitle);
            }
        });

    }


    public void assertProductTitle() {
        productTitleList.stream().parallel().forEach(product -> {
            if (product.isDisplayed()) {
                log.info("Product Title: " + product.getText());
            }
        });
    }

    public String getNoSearchResultFoundText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(noSearchResultFoundText);

    }

    public boolean getSearchResultProductFound(String itemName) {
        boolean item = productTitleList
                .stream()
                .parallel()
                .anyMatch(s -> s.getText().contains(itemName));
        return item;
    }

    public long getSearchResultProductFoundCount() {
        return productTitleList.stream().parallel().filter(WebElement::isDisplayed).count();

    }

    public boolean assertNoSearchResultFoundTextIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(noSearchResultFoundText);
    }


    public int getProductResultCount() {
        return searchResultFound.size();

    }

    public void selectSortByDropdownItems(String sortBy) {
        new DropDownHelper(WebDrv.getInstance().getWebDriver()).selectUsingVisibleText(sortByDropdown, sortBy);

    }

    public void selectItemsFromAllCategories() {
        allCategoriesDropdown.click();
        new DropDownHelper(WebDrv.getInstance().getWebDriver()).selectUsingVisibleText(sortByDropdown, "All Categories");
    }

    public void selectSearchCriteriaAllCategoriesByText(String categoryText) {
        new DropDownHelper(WebDrv.getInstance().getWebDriver()).selectUsingVisibleText(allCategoriesDropdown, categoryText);
    }

    public String getSearchResultProductFoundCountInPagination() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(perPageSearchResultCount);
    }

    public void selectPerPageDropdownItems(String perPage) {
        new DropDownHelper(WebDrv.getInstance().getWebDriver()).selectUsingVisibleText(perPageDropdown, perPage);
    }

    public boolean getSortByDropdownItems(String itemSelector) {
        return getSortByDropdownList()
                .stream()
                .parallel()
                .anyMatch(s -> s.getText().contains(itemSelector));
    }

    public List<WebElement> getProductList() {
        return allProductsList;

    }

    public WebElement getProductByName(String productName) {
        return getProductList()
                .stream()
                .parallel()
                .filter(product -> product.findElement(By.cssSelector("a.prdocutname")).getText().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
    }

    public void addProductToCart(String productName) {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
    }
}


