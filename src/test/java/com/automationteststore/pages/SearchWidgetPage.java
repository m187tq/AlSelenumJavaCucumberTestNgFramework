package com.automationteststore.pages;

import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.testData.javaFiles.GlobalVars;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;


public class SearchWidgetPage {
    private final static Logger log = LogManager.getLogger(SearchWidgetPage.class);
    Page page;
    @FindBy(id = "filter_keyword")
    private WebElement searchBox;
    @FindBy(css = ".fa.fa-search")
    private WebElement searchButton;
    @FindBy(css = ".search-category")
    private List<WebElement> searchCategoryList;

    @FindBy(css = "[placeholder='Search Keywords']")
    private WebElement searchWidget;

    @FindBy(css = "li.search-category")
    private List<WebElement> autoSuggestionDropdownList;

    public void enterSearchKeyword(String SearchKeyword) {
        searchBox.clear();
        searchBox.sendKeys(SearchKeyword);
        log.info("Entering search");
    }

    public void searchForProduct(String searchKeyword) {
        System.out.println("searching .." + searchKeyword);
        searchBox.sendKeys(searchKeyword);
        searchButton.click();
    }

    public SearchResultPage searchForProductWithKeywordAndCategory(String searchKeyword, String searchCategory) {
        System.out.println("searching .." + searchKeyword);
        searchBox.click();
        searchBox.sendKeys(searchKeyword);
        selectCategoryFromTheDropdown(searchCategory);
        searchButton.click();
        return new SearchResultPage();
    }

    private void selectCategoryFromTheDropdown(String searchCategory) {
        List<WebElement> dropdownList = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVars.explicitWait))
               .until(ExpectedConditions.visibilityOfAllElements(autoSuggestionDropdownList));

        for (WebElement dropdownItem : dropdownList) {
            if (dropdownItem.getText().equalsIgnoreCase(searchCategory)) {
                dropdownItem.click();
                break;
            }
        }
    }

    public SearchResultPage clickSearchButton() throws IOException {
        searchButton.click();
        log.info("Clicked search button");
        return new SearchResultPage();
    }
    public SearchResultPage navigateToSearchResultPage() {
        searchButton.click();
        log.info("Clicked search button");
        return new SearchResultPage();

    }

    public SearchResultPage searchWithValidSearchKeyword(String validProductSearchKeyword) {
        enterSearchKeyword(validProductSearchKeyword);
        searchButton.click();
        return new SearchResultPage();

    }

    public SearchResultPage SearchWithInvalidKeyword(String invalidKeyword) {
        enterSearchKeyword(invalidKeyword);
        searchButton.click();
        return new SearchResultPage();
    }

    public List<WebElement> getSearchCategoryList() {
        return new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfAllElements(searchCategoryList));
    }

    public void clickItemFromSearchCategoryList(String categoryName) {
        searchBox.click();
        WebElement categoryItem = getSearchCategoryList()
                .stream()
                .parallel()
                .filter(s -> s.getText().equalsIgnoreCase(categoryName))
                .findFirst()
                .orElse(null);
        assert categoryItem != null;
        categoryItem.click();
        log.info(categoryItem.getText());
    }

    public void assertAutoSuggestionDropdownListAreDisplayed() {
        new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait)).until(d -> autoSuggestionDropdownList.size() > 0);
        for (int i = 0; i < autoSuggestionDropdownList.size(); i++) {
            if (autoSuggestionDropdownList.get(i).isDisplayed()) {
                log.info("Index " + i + " :: " + "Element is Present: " + autoSuggestionDropdownList.get(i).getText());
            } else {
                log.error("Fail: Element is Not Present");
            }
        }


    }

    public boolean assertSearchBoxIsDisplayed() {
        return new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait)).until(d -> searchBox.isDisplayed());

    }



}


