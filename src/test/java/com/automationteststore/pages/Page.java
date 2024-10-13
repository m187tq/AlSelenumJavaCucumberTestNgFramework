package com.automationteststore.pages;

import com.automationteststore.enums.WaitStrategy;
import com.automationteststore.factories.ExplicitWaitFactory;
import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.ScrollPage;
import com.automationteststore.helperutilities.WebElementOrderChecker;
import com.automationteststore.helperutilities.WebElementOrderCheckerImpl;
import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.helperutilities.logger.LoggerHelper;
import com.automationteststore.testData.javaFiles.GlobalVars;
import com.automationteststore.webdriverutilities.WebDrv;
import com.google.common.util.concurrent.Uninterruptibles;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Page {
    protected WebDriverWait wait;

    final static Logger log = LoggerHelper.getLogger(Page.class);
    private static String PAGE_URL = "https://automationteststore.com/";
    @FindBy(css = ".col-md-3.col-sm-6.col-xs-12")
    public List<WebElement> allProductsList;
    By addToCart = By.cssSelector("[title='Add to Cart']");
    By searchBox = By.id("keyword");
    By addToCartButton = By.cssSelector("[title='Add to Cart']");
    By productTitle = By.cssSelector("h2[class='heading2'] span");
    By button_continue = By.cssSelector("button[title='Continue']");
    By product_names = By.cssSelector("a.prdocutname");
    By errorMsg = By.xpath("//span[normalize-space()='First Name must be between 1 and 32 characters!']");
    @FindBy(how = How.CSS, using = ".maintext")
    private WebElement heading;
    @FindBy(css = "a.prdocutname")
    private WebElement productName;
    @FindBy(xpath = "/html/body/div/div[1]/div[1]/section/nav/ul/li[4]/div/ul[1]/li/a")
    private List<WebElement> items;
    @FindBy(xpath = "//*[@id='categorymenu']/nav/ul/li/a")
    private List<WebElement> categories;
    @FindBy(css = ".subnav ul li")
    private List<WebElement> categorymenuList;
    @FindBy(xpath = "/html/body/div/div/div/section/nav/ul/li/div/ul/li")
    private List<WebElement> subCategoriesDropdownList;
    @FindBy(css = "button[title='Continue']")
    private WebElement continueButton;
    @FindBy(how = How.CSS, using = "h2[class='heading2'] span")
    private WebElement subHeading;
    @FindBy(css = ".//*")
    private List<WebElement> allPageElements;
    @FindBy(css = "div.header-logo")
    private WebElement logo;

    @FindBy(css = "a")
    private WebElement aTag;
    @FindBy(css = "//*[contains(text(),'$')]")
    private List<WebElement> dollarSigns;
    @FindBy(css = "button[title='Login']")
    private WebElement loginButton;
    @FindBy(xpath = "/html/body/div/div[1]/div[2]/section/ul/li/a")
    private List<WebElement> allPageBreadCrumbList;
    @FindBy(xpath = "div.alert.alert-error.alert-danger")
    private WebElement errorMessage;
    @FindBy(xpath = "/html/body/div/div[1]/div[2]/section/ul/li/a")
    private List<WebElement> breadCrumbList;

    public Page() {

    }

    // Generic method to send keys
    public static void sendKeysAction(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    // Generic method to click
    public static void clickAction(WebElement element) {
        element.click();
    }

    // Generic method to select from dropdown
    public static void selectFromDropdown(WebElement element, String visibleText) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    // Generic method to tick radio button
    public static void selectRadioButton(List<WebElement> radioButtons, String value) {
        radioButtons.stream()
                .filter(radio -> radio.getAttribute("value").equalsIgnoreCase(value))
                .findFirst()
                .ifPresent(WebElement::click);
        log.info("Radio button selected successfully");
    }

    // Generic method to check checkbox
    public static void checkCheckbox(WebElement checkbox) {
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public static boolean isActivePage() {

        return WebDrv.getInstance().getWebDriver().getCurrentUrl().contains(PAGE_URL);

    }

    public static void selectFromDropDown(Consumer<Select> consumer, WebElement element) {
        consumer.accept(new Select(element));
        log.info("Dropdown selected successfully");
    }

    public static void actionMethods(Consumer<Actions> consumer) {
        consumer.accept(new Actions(WebDrv.getInstance().getWebDriver()));
        log.info("Action performed successfully");

    }

    public static void webdriverWaitMethods(Consumer<WebDriverWait> consumer) {
        consumer.accept(new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVars.EXPLICIT_TIMEOUT)));
        log.info("WebdriverWait methods performed successfully");

    }

    public static void waitForWebElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.EXPLICITWAIT));
        wait.until(ExpectedConditions.visibilityOf(findBy));

    }

    public void assertElementDoesNotExist(By locator) {
        try {
            getElement(locator);
            Assert.assertTrue(false, "This element should not exist.");
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public void assertEmployersNameIsDisplayedInTheAddedUsersConfirmationPanel(String value) {
        Assert.assertTrue(
                heading.getText().contains(value));
    }

    public void assertHeadingIsDisplayed() {
        Assert.assertTrue(heading.isDisplayed());

    }

    public void assertOnErrorMessagesForFieldsFromTable(List<Map<String, String>> datatable) {
        for (Map<String, String> data : datatable) {
            WebElement fieldInputTextBox = WebDrv.getInstance().getWebDriver().findElement(By.id(data.get("Field")));
            fieldInputTextBox.clear();
            fieldInputTextBox.sendKeys(data.get("invalidValue"));
            //saveAndContinueButton.click();
            WebElement errorTextElement = WebDrv.getInstance().getWebDriver()
                    .findElement(By.id(data.get("ErrorField")));
            Assert.assertEquals(data.get("ErrorMessage"), errorTextElement.getText());

        }

    }

    public void assertOnHeadingText(String headingText) {
        String newHeading = null;
        if (headingText.contains("Applications in progress")) {
            newHeading = WebDrv.getInstance().getWebDriver().findElement(By.id("applications_in_progress")).getText()
                    .trim();
        } else {
            WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.EXPLICITWAIT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("heading")));
            newHeading = WebDrv.getInstance().getWebDriver().findElement(By.id("heading")).getText().trim();
        }
        Assert.assertTrue(newHeading.contains(headingText.trim()));
    }

    public void assertOnSubHeadingTextMsg(String subHeadingText) {
        WebElement subHeading = WebDrv.getInstance().getWebDriver().findElement(By.id("error-summary-title"));
        Assert.assertEquals(subHeadingText, subHeading.getText());
    }

    public void assertErrorMessage(String errorData, String errorText) {
        String getErrorText = WebDrv.getInstance().getWebDriver().findElement(By.xpath("//span[normalize-space()='" + errorData + "']")).getText();
        Assert.assertEquals(errorText, getErrorText);
    }

    public void assertContinueButtonIsEnabled() {
        Assert.assertTrue(continueButton.isEnabled(), "Save and Continue Button not enabled");
    }

    public void assertWebElementNotDisplayedInCurrentPage(By locator) {
        try {
            WebDrv.getInstance().getWebDriver().findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            Assert.assertTrue(e.getMessage().contains("no such element: Unable to locate element:"));
        }
    }


    public void tapOnContinueButton() {
        WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait));
        wait.until(ExpectedConditions.visibilityOfElementLocated(button_continue));
        ScrollPage.scrollToViewContinue(WebDrv.getInstance().getWebDriver());
        continueButton.click();
    }

    public void clickOnContinueButton() {
        click(button_continue, WaitStrategy.PRESENCE);
    }

    public List<String> getArrayListOfStringWithCommaSeparated(String fieldNames) {
        List<String> errorIds = new ArrayList<>();
        if (fieldNames.contains(",")) {
            errorIds = Arrays.asList(fieldNames.split(","));
        } else {
            errorIds.add(fieldNames);
        }
        return errorIds;
    }

    private List<String> getIdListOfExpectedChildElements(List<String> elementIds) {
        List<String> ids = new ArrayList<>();
        for (String elementId : elementIds) {
            ids.add(elementId);
        }
        ids.remove(0);
        return ids;
    }

    public void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = wd -> "complete".equals(((JavascriptExecutor) wd).executeScript("return document.readyState"));
        WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait));
        wait.until(pageLoadCondition);
        WebDrv.getInstance().getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalVarsHelper.DEFAULT_IMPLICIT_TIMEOUT));
    }

    public void assertPageElementsAreDisplayed(List<String> elementIds) {
        List<String> ids = getIdListOfExpectedChildElements(elementIds);
        List<WebElement> actualElements = getAllElements();
        for (String id : ids) {
            Assert.assertTrue(actualElements.stream().anyMatch(e -> e.getAttribute("id").equals(id)));
        }
    }

    public List<WebElement> getAllElements() {
        return allPageElements;

    }

    public void getRectHeightAndWidthOfAnElement(WebElement element) {
        Point p = element.getLocation();
        log.info(p.getX());
        log.info(p.getY());
        Rectangle r = element.getRect();
        log.info(r.getX());
        log.info(r.getY());
    }

    public boolean checkForTitle(WebDriver driver, String title) {
        log.info(title);
        if (title == null || title.isEmpty())
            throw new IllegalArgumentException(title);
        return WebDrv.getInstance().getWebDriver().getTitle().trim().contains(title);
    }

    public boolean assertLogoIsDisplayed() {
        log.info("Logo is displayed: " + logo.isDisplayed());
        return logo.isDisplayed();

    }

    public String getLogoText() {
        log.info("Logo Text is: " + logo.getText());
        return logo.getText();

    }

    public void clickLogoImage() {
        logo.click();
        log.info("Clicking on the logo");

    }

    // ********* Input Label names ********* //

    public String getThisPageTitle() {
        log.info("Title is: " + WebDrv.getInstance().getWebDriver().getTitle());
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getCurrentPageTitle();
    }

    public String getThisPageUrl() {
        log.info("Page Url is: " + WebDrv.getInstance().getWebDriver().getCurrentUrl());
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getCurrentPageUrl();
    }

    public String getThisPageHeader() {
        log.info("Page Header :: " + heading.getText());
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(heading);
    }

    public boolean assertCurrentPageBreadCrumbMenuIsDisplayedCorrectly(String MenuName) throws InterruptedException {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(GlobalVarsHelper.EXPLICITWAIT));
        return breadCrumbList.stream().parallel().anyMatch(s -> s.getText().trim().contains(MenuName));
    }

    public void assertNoRadioButtonSelected(List<WebElement> elements) {
        Assert.assertTrue(elements.get(1).isSelected());

    }

    public void assertOnElementIsDisplayed(WebElement element) {
        if (WebDrv.getInstance().getWebDriver().getCurrentUrl().contains("/index.php?rt")) {
            Assert.assertTrue(element.isDisplayed());
        } else {

            Assert.assertTrue(element.isDisplayed());
        }
    }

    public void assertOnElementIsNotDisplayed(List<WebElement> elements) {
        Assert.assertEquals(elements.size(), 0);
    }

    public void assertOnOrderOfWebElementsByListOrder(List<String> elementIds) {
        String idOfParentElement = elementIds.get(0);
        WebElement parentWebElement = WebDrv.getInstance().getWebDriver().findElement(By.id(idOfParentElement));
        List<WebElement> childWebElements = parentWebElement.findElements(By.xpath(".//*"));
        WebElementOrderChecker webElementOrderChecker = (WebElementOrderChecker) new WebElementOrderCheckerImpl(childWebElements);
        List<String> childIds = getIdListOfExpectedChildElements(elementIds);
    }

    public void checkPageIsReady(int loopCount) throws Exception {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait));
        wait.until(pageLoadCondition);
    }

    public void assertOnSubHeadingText(String subHeadingText) {
        WebElement subHeading = WebDrv.getInstance().getWebDriver().findElement(By.id("sub_heading"));
        Assert.assertEquals(subHeadingText, subHeading.getText());
    }

    public void assertOnSubHeadingT(String subHeadingText) {
        WebElement subHeading = WebDrv.getInstance().getWebDriver().findElement(By.cssSelector(".subtext"));
        Assert.assertEquals(subHeadingText, subHeading.getText());
    }

    public void assertRejectedCookiesMessageIsNotVisible() {
        Assert.assertEquals(0, WebDrv.getInstance().getWebDriver().findElements(By.id("rejected-cookies-message")).size());
    }

    public void assertTextShouldNotBeDisplayedInHeadingText(String headingText) {
        WebElement newHeading = WebDrv.getInstance().getWebDriver().findElement(By.id("heading"));
        Assert.assertNotEquals(headingText, newHeading.getText());
    }

    public WebElement getElement(By locator) {
        return WebDrv.getInstance().getWebDriver().findElement(locator);

    }

    public void navigateTo(String url) {
        WebDrv.getInstance().getWebDriver().navigate().to(url);
        WebDrv.getInstance().getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalVarsHelper.getImplicitWait()));
        WebDrv.getInstance().getWebDriver().manage().deleteAllCookies();
        WebDrv.getInstance().getWebDriver().manage().window().maximize();
        log.info("Navigated to :: " + url);
    }

    public void goToUrl(String url) {
        WebDrv.getInstance().getWebDriver().navigate().to(url);
        WebDrv.getInstance().getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalVarsHelper.getImplicitWait()));
    }

    public void waitForElementToDisappear(By findBy) {
        WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.getExplicitWait()));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
        log.info("Waiting Element to disappear....");
    }

    public void waitForElementToAppearBy(By findBy) {
        WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.EXPLICITWAIT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

    }

    protected void sendKeys(By by, String value, WaitStrategy waitstrategy, String elementname) {
        WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
        element.sendKeys(value);
        log.info(value + " is entered successfully in " + elementname);
    }

    public void waitAndSendKeys(WebElement findBy, String keysToSend) {
        findBy.clear();
        WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.getExplicitWait()));
        wait.until(ExpectedConditions.visibilityOf(findBy)).sendKeys(keysToSend);
        log.info("Waited and Successfully sent keys to :: " + keysToSend);
    }

    protected void click(By by, WaitStrategy waitstrategy, String elementname) {
        WebElement element = ExplicitWaitFactory.performExplicitWait(waitstrategy, by);
        element.click();
        log.info(elementname + " is clicked");

    }

    public void waitAndClick(WebElement findBy) {
        log.info("Waiting Element to be clicked....");
        WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.getExplicitWait()));
        log.info("Successfully clicked ");
        wait.until(ExpectedConditions.elementToBeClickable(findBy)).click();
        log.info("Successfully clicked ");

    }

    public void SwitchWindowToChild() {
        Set<String> s1 = WebDrv.getInstance().getWebDriver().getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        String parentWindow = i1.next();
        String childWindow = i1.next();
        WebDrv.getInstance().getWebDriver().switchTo().window(childWindow);
        log.info("Switched to child window");
    }

    public void clickAnyElementMatchingText(List<WebElement> elements, Predicate<WebElement> predicate) {
        WebElement element = elements
                .stream()
                .parallel()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Element not present"));
        element.click();
        log.info("Clicked on an element matching the text");
    }

    public void iHoverOverCategoryNameAndClickMenuItem(List<WebElement> elements, Predicate<WebElement> predicate, Predicate<WebElement> predicate1) throws InterruptedException {
        new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVars.EXPLICIT_TIMEOUT))
                .until(e -> elements.size() > 6);
        elements
                .stream()
                .parallel()
                .filter(predicate)
                .map(ele -> new Actions(WebDrv.getInstance().getWebDriver()).moveToElement(ele))
                .findFirst()
                .ifPresent(Actions::perform);
        Thread.sleep(5000);
        log.info("Hovered over a category item ");
        new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVars.EXPLICIT_TIMEOUT))
                .until(e -> subCategoriesDropdownList.size() > 39);
        Thread.sleep(5000);
        subCategoriesDropdownList.stream()
                .filter(predicate1)
                .findFirst()
                .ifPresent(WebElement::click);
        log.info("Clicked on an item from the dropdown");

    }

    public ProductCategoryPage iHoverOverProductCategoryMenuAndClickAMenuItem(String categoryName, String menuItem) {
        new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVars.EXPLICIT_TIMEOUT))
                .until(e -> categorymenuList.size() > 6);
        categorymenuList.stream().map(WebElement::getText).filter(text -> text.equalsIgnoreCase(categoryName)).forEach(System.out::println);

        categorymenuList
                .stream()
                .parallel()
                .filter(s -> s.getText().contains(categoryName))
                .map(ele -> new Actions(WebDrv.getInstance().getWebDriver()).moveToElement(ele))
                .findFirst()
                .ifPresent(Actions::perform);
        log.info("Hovered over a category item ");
        new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(60))
                .until(e -> subCategoriesDropdownList.size() > 39);
        Object catList = subCategoriesDropdownList.stream().filter(e -> e.getText().equalsIgnoreCase(menuItem)).collect(Collectors.toList());
        System.out.println(catList);
        subCategoriesDropdownList.stream()
                .filter(s -> s.getText().contains(menuItem))
                .findFirst()
                .ifPresent(WebElement::click);
        log.info("Clicked on an item from the dropdown");
        return new ProductCategoryPage();
    }

    public boolean doesThePageHaveValidationErrors() {
        try {
            WebElement errorSummaryElement = getElement(
                    By.cssSelector("ul[class='list']"));
            List<WebElement> errorSummaryChildElements = errorSummaryElement.findElements(By.xpath(".//*"));
            List<WebElement> errorFieldElements = WebDrv.getInstance().getWebDriver()
                    .findElements(By.className("error-message"));
        } catch (NoSuchElementException e) {
            return false;
        }

        return true;
    }

    public void clickAnyMatchingLinkText(List<WebElement> elements, String linkText) {
        WebElement ele = elements.stream()
                .parallel()
                .filter(s -> s.getText().equalsIgnoreCase(linkText))
                .findFirst()
                .orElse(null);
        if (ele != null) {
            ele.click();
            log.info("Clicked on the link");
        }
    }

    public void clickAnyMatchingElementByText(List<WebElement> elements, String text) {
        elements
                .stream()
                .parallel()
                .filter(s -> s.isDisplayed() && s.getText().equalsIgnoreCase(text))
                .findFirst()
                .ifPresent(WebElement::click);
        log.info("Clicked on the element");
    }

    public List<String> getAnyMatchingElement_Text_Attribute_TagName(List<WebElement> elements, Function<WebElement, String> function) {
        return elements
                .stream()
                .parallel()
                .filter(WebElement::isDisplayed)
                .filter(s -> s.isDisplayed() && s.isEnabled())
                .map(function)
                .collect(Collectors.toList());
    }

    public boolean assertAnyLinkFromListOfElements(List<WebElement> listOfElements, String linkText) {
        return listOfElements
                .stream()
                .parallel()
                .anyMatch(s -> s.getText().equalsIgnoreCase(linkText));
    }

    public String getThisPageHeaderText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(heading);
    }

    public String getPageSubHeaderText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(subHeading);
    }

    public WebElement getPageBreadcrumbMenuList(String breadCrumbMenuName) {
        for (int i = 0; i < allPageBreadCrumbList.size(); i++) {
            if (allPageBreadCrumbList.get(i).getText().contains(breadCrumbMenuName)) {
                return allPageBreadCrumbList.get(i);

            }
        }
        return null;
    }

    public List<WebElement> getDollarSigns() {
        return dollarSigns;

    }

    public ArrayList<Integer> getPriceMassagedData(Iterator<WebElement> itr) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        while (itr.hasNext()) {
            String p = itr.next().getText();
            if (p.contains("$")) {
                String actualData = p.substring(1);
                //log.info(actualData);
                double p1 = Double.parseDouble(actualData);
                int productPrice = (int) (p1);
                array.add(productPrice);
            }
        }
        return array;
    }

    public boolean verifyArrayHasAscendingData(ArrayList<Integer> array) {
        for (int i = 0; i < array.size() - 1; i++) {
            // this condition will check all next price should be smaller than previous one.
            // next price can be grater and equal
            if (array.get(i) <= array.get(i + 1)) {
                log.info("obj.get(i).." + array.get(i));
                log.info("obj.get(i+1).." + array.get(i + 1));
            } else {
                log.info("price filter is not working");
                return false;
            }
        }
        return true;
    }

    public void moveToElementAndClick(WebElement element, long durationInSeconds) {
        WebElement webElement = waitForVisibilityOfElement(element, durationInSeconds);
        Actions actions = new Actions(WebDrv.getInstance().getWebDriver());
        actions.moveToElement(webElement).click().build().perform();
    }

    public void moveToElement(WebElement element) {
        WebElement webElement = waitForVisibilityOfElement(element, GlobalVarsHelper.explicitWait);
        Actions actions = new Actions(WebDrv.getInstance().getWebDriver());
        actions.moveToElement(webElement).build().perform();
        log.info("Moved to element: " + element.getText());
    }

    public WebElement waitForVisibilityOfElement(WebElement element, long durationInSeconds) {
        WebElement webElement = null;
        try {
            WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(durationInSeconds));
            webElement = wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return webElement;
    }

    public void waitForElementInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait));
        wait.until(ExpectedConditions.invisibilityOf(element));
        log.info("Element " + element.getText() + " is now invisible");
    }

    public void clickAnItemFromListByText(List<WebElement> elements, String itemText) {
        WebElement element = elements
                .stream()
                .parallel()
                .filter(e -> e.getText().equalsIgnoreCase(itemText))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Element with text" + itemText + " not present"));
        element.click();
        log.info("Clicked on item: " + itemText);
    }

    public void clickAnElementMatchingText(List<WebElement> elements, String itemText) {
        WebElement element = elements
                .stream()
                .parallel()
                .filter(e -> e.getText().equalsIgnoreCase(itemText))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Element with text" + itemText + " not present"));
        element.click();
        log.info("Clicked on item: " + itemText);
    }

    public HomePage addProductToCart(String productName) {
        List<WebElement> products = allProductsList;
        for (WebElement product : products)
            if (product.findElement(By.cssSelector("a.prdocutname")).getText().equalsIgnoreCase(productName)) {
                log.info(product.findElement(By.cssSelector("a.prdocutname")).getText());
                product.findElement(addToCart).click();
                break;
            }
        return new HomePage();

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
                .filter(product -> product.findElement(By.cssSelector("a.prdocutname")).getText().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);

    }

    public ProductIdPage addProductToBasket(String productName) {
        for (int i = 0; i < allProductsList.size(); i++) {
            //if (allProductsList.get(i).findElement(By.cssSelector("a.prdocutname")).getText().equalsIgnoreCase(productName)) {
            if (allProductsList.get(i).findElement(product_names).getText().equalsIgnoreCase(productName)) {

                log.info(allProductsList.get(i).findElement(By.cssSelector("a.prdocutname")).getText());
                allProductsList.get(i).findElement(addToCart).click();
                break;
            }

        }

        return new ProductIdPage();
    }

    public void selectDropDownByIndexOrTextOrValue(Object option, WebElement element) {
        Select select = new Select(element);
        if (option instanceof Integer) {
            select.selectByIndex((int) option);
            log.info("element is selected from dropdown :: " + option);
        } else if (option instanceof String) {
            select.selectByVisibleText((String) option);
            log.info("element is selected from dropdown :: " + option);
        } else if (option instanceof WebElement) {
            select.selectByVisibleText(((WebElement) option).getText());
            log.info("element is selected from dropdown :: " + option);
        } else {
            log.error("Invalid input for dropdown selection");
        }
    }

    public void selectFromTheDropdown(Consumer<Select> consumer, WebElement element) {
        consumer.accept(new Select(element));

    }

    public void click(By xpath, WaitStrategy clickable) {
        WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.EXPLICIT_TIMEOUT));
        WebElement element = null;
        switch (clickable) {
            case CLICKABLE:
                element = wait.until(ExpectedConditions.elementToBeClickable(xpath));
                break;
            case PRESENCE:
                element = wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
                break;
            case VISIBLE:
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
                break;
            default:
                break;
        }
        element.click();
        log.info("Clicked on element: " + element.getText());

    }

    public void sendKeys(By locator, String cityName, WaitStrategy presence) {
        WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait));
        WebElement element = null;
        switch (presence) {
            case PRESENCE:
                element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
                break;
            case VISIBLE:
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                break;

            default:
                break;
        }
        assert element != null;
        element.sendKeys(cityName);
    }

    public void clickOnLoginButton() {
        loginButton.click();
        log.info("Clicked on login button");

    }

    //=====================================================================

    public void waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        log.info("Element: " + element.getText() + " is now visible");
    }

    public void clickOn(WebElement element) {
        waitForElementToBeVisible(element);
        element.click();
        log.info("Clicked on element: " + element.getText());

    }

    public void sendKeysTo(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.sendKeys(text);
        log.info("Sent: " + text + " to element: " + element.getText());

    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) WebDrv.getInstance().getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        log.info("Scrolled to element: " + element.getText());

    }

    public void select(WebElement element, String option) {
        Select select = new Select(element);
        select.selectByVisibleText(option);
        log.info("Selected: " + option + " from dropdown");
    }

    public void click(By locator) {
        WebElement element = WebDrv.getInstance().getWebDriver().findElement(locator);
        click(element);
    }

    public void mouseOverElement(WebDriver driver, WebElement element) {
        boolean flag = false;
        try {
            new Actions(driver).moveToElement(element).build().perform();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (flag) {
                log.info(" MouserOver Action is performed on: " + element.getText());
            } else {
                log.info("MouseOver action is not performed on");
            }
        }
    }
    //=====================================================================

    public void rightClick(WebElement element) {
        Actions action = new Actions(WebDrv.getInstance().getWebDriver());
        action.contextClick(element).build().perform();
    }

    public void doubleClick(WebElement element) {
        Actions action = new Actions(WebDrv.getInstance().getWebDriver());
        action.doubleClick(element).build().perform();
    }

    public void rightClickAndWait(WebElement element, long durationInSeconds) {
        Actions action = new Actions(WebDrv.getInstance().getWebDriver());
        action.contextClick(element).build().perform();
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(durationInSeconds));
        action.doubleClick(element).build().perform();
    }

    public void moveToElementAndWait(WebElement element, long durationInSeconds) {
        Actions action = new Actions(WebDrv.getInstance().getWebDriver());
        action.moveToElement(element).build().perform();
    }

    public void moveToElementAndClick(WebElement element) {
        Actions action = new Actions(WebDrv.getInstance().getWebDriver());
        action.moveToElement(element).click().build().perform();
    }


    public void scrollToElementAndWait(WebElement element, long durationInSeconds, int xOffset, int yOffset) {
        ((JavascriptExecutor) WebDrv.getInstance().getWebDriver()).executeScript("arguments[0].scrollIntoView(true); window.scrollBy(" + xOffset + "," + yOffset + ");", element);
    }

    public void scrollToElementAndClick(WebElement element) {
        Actions action = new Actions(WebDrv.getInstance().getWebDriver());
        action.moveToElement(element).click().build().perform();
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(GlobalVars.explicitWait));
        action.moveToElement(element).click().build().perform();
    }

    public void dragAndDrop(WebElement sourceElement, WebElement targetElement) {
        Actions action = new Actions(WebDrv.getInstance().getWebDriver());
        action.dragAndDrop(sourceElement, targetElement).build().perform();
        log.info("Drag and drop performed from '" + sourceElement.toString() + "' to '" + targetElement.toString() + "'");
    }

    public void dragAndDropAndWait(WebElement sourceElement, WebElement targetElement, long durationInSeconds) {
        Actions action = new Actions(WebDrv.getInstance().getWebDriver());
        action.dragAndDrop(sourceElement, targetElement).build().perform();
    }

    public void selectOptionInDropdown(WebElement element, String dropDownOption) {
        Select select = new Select(element);
        select.selectByVisibleText(dropDownOption);
        log.info("Selected option '" + dropDownOption + "' from dropdown.");
    }

    private List<String> getDesiredValue(List<WebElement> elements, Function<WebElement, String> function) {
        return elements.stream().parallel().map(function).collect(Collectors.toList());

    }

    public HomePage clickOnLogoImage() {
        logo.click();
        log.info("Click on logo image");
        return new HomePage();
    }

    protected void waitForVisibility(WebElement element) {
        new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait))
                .until((s)->isDisplayed(element));
        log.info("Element " + element.toString() + " is visible.");
    }

    protected void click(WebElement element) {
        new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait))
                .until((s)->isDisplayed(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected void sendKeys(WebElement element, String text) {
        new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(GlobalVarsHelper.explicitWait))
                .until((s)->isDisplayed(element));
        element.clear();
        element.sendKeys(text);
        log.info("Text '" + text + "' is sent to element " + element.toString() + ".");
    }

    protected String getText(WebElement element) {
        waitForVisibility(element);
        log.info("Text of element " + element.toString() + " is: " + element.getText());
        return element.getText();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            waitForVisibility(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    protected boolean isEnabled(WebElement element) {
        try {
            waitForVisibility(element);
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
}
