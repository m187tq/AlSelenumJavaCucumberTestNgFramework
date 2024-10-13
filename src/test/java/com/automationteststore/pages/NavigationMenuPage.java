package com.automationteststore.pages;

import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NavigationMenuPage {
    private final static Logger log = LogManager.getLogger(NavigationMenuPage.class);
    Page page;
    LoginPage loginPage;
    HomePage homePage;
    ProductCategoryPage productCategoryPage;
    ProductIdPage productIDPage;

    @FindBy(xpath = "//*[@id='categorymenu']/nav/ul/li/a")
    private List<WebElement> categories;
    @FindBy(xpath = "//li//div//ul//li//a")
    private List<WebElement> subCategoriesMenuList;

    @FindBy(xpath = "//*[@id='categorymenu']/nav/ul/li/a")
    private List<WebElement> categories_list;

    @FindBy(xpath = "//li//div//ul//li//a")
    private List<WebElement> subCategories_List;

    @FindBy(css = ".active.menu_home")
    private WebElement homeMenu;
    @FindBy(xpath = "//*[@id='categorymenu']/nav/ul/li[2]/a")
    private WebElement apparelAccessoriesMenu;


    @FindBy(xpath = "//*[@id='categorymenu']/nav/ul/li[3]/a")
    private WebElement makeupMenu;
    @FindBy(xpath = "//*[@id='categorymenu']/nav/ul/li[4]/a")
    private WebElement skincareMenu;
    @FindBy(xpath = "//*[@id='categorymenu']/nav/ul/li[5]/a")
    private WebElement fragranceMenu;
    @FindBy(xpath = "//*[@id='categorymenu']/nav/ul/li[6]/a")
    private WebElement menMenu;
    @FindBy(xpath = "//*[@id='categorymenu']/nav/ul/li[7]/a")
    private WebElement hairCareMenu;
    @FindBy(xpath = "//*[@id='categorymenu']/nav/ul/li[8]/a")
    private WebElement booksMenu;

    @FindBy(xpath = "/html/body/div/div[1]/div[1]/section/nav/ul/li[1]/div/ul[1]/li/a")
    private List<WebElement> homeDropdownList;
    @FindBy(xpath = "/html/body/div/div[1]/div[1]/section/nav/ul/li[2]/div/ul[1]/li/a")
    private List<WebElement> apparelAndAccessoriesDropdownList;
    @FindBy(xpath = "/html/body/div/div[1]/div[1]/section/nav/ul/li[3]/div/ul[1]/li/a")
    private List<WebElement> makeupDropdownList;
    @FindBy(xpath = "/html/body/div/div[1]/div[1]/section/nav/ul/li[4]/div/ul[1]/li/a")
    private List<WebElement> skincareDropdownList;
    @FindBy(xpath = "/html/body/div/div[1]/div[1]/section/nav/ul/li[5]/div/ul[1]/li/a")
    private List<WebElement> fragranceDropdownList;
    @FindBy(xpath = "/html/body/div/div[1]/div[1]/section/nav/ul/li[6]/div/ul[1]/li/a")
    private List<WebElement> menDropdownList;
    @FindBy(xpath = "/html/body/div/div[1]/div[1]/section/nav/ul/li[7]/div/ul[1]/li/a")
    private List<WebElement> hairCareDropdownList;
    @FindBy(xpath = "/html/body/div/div[1]/div[1]/section/nav/ul/li[8]/div/ul[1]/li/a")
    private List<WebElement> booksDropdownList;

    public List<WebElement> getCategories() {
        return categories;
    }

    public List<WebElement> getSubCategoriesMenuList() {
        return subCategoriesMenuList;
    }


    public List<WebElement> getHomeDropdownList() {
        return homeDropdownList;
    }

    public List<WebElement> getApparelAndAccessoriesDropdownList() {
        return apparelAndAccessoriesDropdownList;
    }

    public List<WebElement> getMakeupDropdownList() {
        return makeupDropdownList;
    }

    public List<WebElement> getSkincareDropdownList() {
        return skincareDropdownList;
    }

    public List<WebElement> getFragranceDropdownList() {
        return fragranceDropdownList;
    }

    public List<WebElement> getMenDropdownList() {
        return menDropdownList;
    }

    public List<WebElement> getHairCareDropdownList() {
        return hairCareDropdownList;
    }

    public List<WebElement> getBooksDropdownList() {
        return booksDropdownList;
    }

    public WebElement homeMenu() {
        return homeMenu;
    }

    public WebElement apparelAccessoriesMenu() {
        return apparelAccessoriesMenu;
    }

    public WebElement makeupMenu() {
        return makeupMenu;
    }

    public WebElement skincareMenu() {
        return skincareMenu;
    }

    public WebElement fragranceMenu() {
        return fragranceMenu;
    }

    public WebElement menMenu() {
        return menMenu;
    }

    public WebElement hairCareMenu() {
        return hairCareMenu;
    }

    public WebElement getBooks() {
        return booksMenu;
    }

    public void mouseOverMenu(List<WebElement> menuList) {
        for (int i = 0; i < menuList.size(); i++) {
            if (menuList.get(i).getText().equalsIgnoreCase(String.valueOf(menuList))) {
                new Actions(WebDrv.getInstance().getWebDriver()).moveToElement(menuList.get(i)).perform();
                log.info("Clicked on menu: " + menuList.get(i).getText());
                break;
            }
            log.info("Hovering over category menu: " + menuList.get(i).getText());
        }


    }

    public void mouseOverHome() {
        log.info("Hovering over Home menu");
        new Actions(WebDrv.getInstance().getWebDriver()).moveToElement(homeMenu).perform();

    }

    public void mouseOverApparelAccessories() {
        log.info("Hovering over Apparel & Accessories menu");
        new Actions(WebDrv.getInstance().getWebDriver()).moveToElement(apparelAccessoriesMenu).perform();
    }

    public void mouseOverMakeup() {
        log.info("Hovering over Makeup menu");
        new Actions(WebDrv.getInstance().getWebDriver()).moveToElement(makeupMenu).perform();
    }

    public void mouseOverSkincare() {
        log.info("Hovering over Skincare menu");
        new Actions(WebDrv.getInstance().getWebDriver()).moveToElement(skincareMenu).perform();
    }

    public void mouseOverFragrance() {
        log.info("Hovering over Fragrance menu");
        new Actions(WebDrv.getInstance().getWebDriver()).moveToElement(fragranceMenu).perform();

    }

    public void mouseOverMen() {
        log.info("Hovering over Men menu");
        new Actions(WebDrv.getInstance().getWebDriver()).moveToElement(menMenu).perform();
    }

    public void mouseOverHairCare() {
        log.info("Hovering over Hair Care menu");
        new Actions(WebDrv.getInstance().getWebDriver()).moveToElement(hairCareMenu).perform();
    }

    public void mouseOverBooks() {
        log.info("Hovering over Books menu");
        Actions actions = new Actions(WebDrv.getInstance().getWebDriver());
        actions.moveToElement(booksMenu).perform();

    }

    public String getHomeMenuText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(homeMenu);

    }

    public String getApparelAccessoriesMenuText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(apparelAccessoriesMenu);

    }

    public String getMakeupMenuText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(makeupMenu);

    }

    public String getSkincareMenuText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(skincareMenu);

    }

    public String getFragranceMenuText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(fragranceMenu);

    }

    public String getMenMenuText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(menMenu);

    }

    public String getHairCareMenuText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(hairCareMenu);

    }

    public String getBooksMenuText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(booksMenu);

    }

    //==============================================

    public ProductCategoryPage clickHomeMenu() {
        homeMenu.click();
        return new ProductCategoryPage();
    }

    public ProductCategoryPage clickApparelAccessoriesMenu() {
        apparelAccessoriesMenu.click();
        return new ProductCategoryPage();
    }

    public ProductCategoryPage clickMakeupMenu() {
        makeupMenu.click();
        return new ProductCategoryPage();
    }

    public ProductCategoryPage clickSkincareMenu() {
        skincareMenu.click();
        return new ProductCategoryPage();
    }

    public ProductCategoryPage clickFragranceMenu() {
        fragranceMenu.click();
        return new ProductCategoryPage();
    }

    public ProductCategoryPage clickMenMenu() {
        menMenu.click();
        return new ProductCategoryPage();
    }

    public ProductCategoryPage clickHairCareMenu() {
        hairCareMenu.click();
        return new ProductCategoryPage();
    }

    public ProductCategoryPage clickBooksMenu() {
        booksMenu.click();
        return new ProductCategoryPage();
    }

    public ProductCategoryPage clickItemFromHomeSubMenuCategoryDropdownList(String subMenuCategoryItem) {
        WebElement subItem = homeDropdownList.stream()
                .parallel().filter(s -> s.getText().contains(subMenuCategoryItem))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Element with text" + subMenuCategoryItem + "not present"));
        subItem.click();
        log.info("clicked on :: " + subMenuCategoryItem);
        return new ProductCategoryPage();
    }

    public ProductCategoryPage clickItemFromApparelAndAccessoriesSubMenuCategoryDropdownList(String subMenuCategoryItem) {
        apparelAndAccessoriesDropdownList
                .stream()
                .parallel().filter(s -> s.getText().trim().equalsIgnoreCase(subMenuCategoryItem))
                .findFirst()
                .ifPresent(WebElement::click);
        log.info("clicked on :: " + subMenuCategoryItem);
        return new ProductCategoryPage();
    }

    public ProductCategoryPage clickItemFromMakeupSubMenuCategoryDropdownList(String subMenuCategoryItem) {
        makeupDropdownList
                .stream()
                .parallel().filter(s -> s.getText().equalsIgnoreCase(subMenuCategoryItem))
                .findFirst()
                .ifPresent(WebElement::click);
        log.info("clicked on :: " + subMenuCategoryItem);
        return new ProductCategoryPage();
    }

    public ProductCategoryPage clickItemFromSkincareSubMenuCategoryDropdownList(String subMenuCategoryItem) {
        skincareDropdownList
                .stream()
                .parallel().filter(s -> s.getText().equalsIgnoreCase(subMenuCategoryItem))
                .findFirst()
                .ifPresent(WebElement::click);
        log.info("clicked on :: " + subMenuCategoryItem);
        return new ProductCategoryPage();
    }

    public ProductCategoryPage clickItemFromFragranceSubMenuCategoryDropdownList(String subMenuCategoryItem) {
        fragranceDropdownList
                .stream()
                .parallel().filter(s -> s.getText().equalsIgnoreCase(subMenuCategoryItem))
                .findFirst()
                .ifPresent(WebElement::click);
        log.info("clicked on :: " + subMenuCategoryItem);
        return new ProductCategoryPage();
    }

    public ProductCategoryPage clickItemFromMenSubMenuCategoryDropdownList(String subMenuCategoryItem) {
        menDropdownList
                .stream()
                .parallel().filter(s -> s.getText().equalsIgnoreCase(subMenuCategoryItem))
                .findFirst()
                .ifPresent(WebElement::click);
        log.info("clicked on :: " + subMenuCategoryItem);
        return new ProductCategoryPage();
    }

    public ProductCategoryPage clickItemFromHairCareSubMenuCategoryDropdownList(String subMenuCategoryItem) {
        hairCareDropdownList
                .stream()
                .parallel().filter(s -> s.getText().equalsIgnoreCase(subMenuCategoryItem))
                .findFirst()
                .ifPresent(WebElement::click);
        log.info("clicked on :: " + subMenuCategoryItem);
        return new ProductCategoryPage();
    }

    public ProductCategoryPage clickItemFromBooksSubMenuCategoryDropdownList(String subMenuCategoryItem) {
        booksDropdownList
                .stream()
                .parallel().filter(s -> s.getText().equalsIgnoreCase(subMenuCategoryItem))
                .findFirst()
                .ifPresent(WebElement::click);
        log.info("clicked on :: " + subMenuCategoryItem);
        return new ProductCategoryPage();
    }


    public void clickItemFromSubMenuCategoryDropdownList(String subMenuCategoryItem) {
        WebDriverWait wait = new WebDriverWait(WebDrv.getInstance().getWebDriver(), Duration.ofSeconds(10));
        WebElement subMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='" + subMenuCategoryItem + "']")));
        new Actions(WebDrv.getInstance().getWebDriver()).moveToElement(subMenu).click().perform();
        subCategoriesMenuList.stream().parallel().filter(s -> s.getText().equals(subMenuCategoryItem))
                .findFirst().ifPresent(WebElement::click);
        log.info("clicked on :: " + subMenuCategoryItem);

    }

    public WebElement getMenuItem(String menuItemName) {
        log.info("returned :: " + menuItemName);
        return WebDrv.getInstance().getWebDriver().findElement(By.xpath("//li[@class='current']//a[contains(text(),'" + menuItemName + "')]"));
    }
    public WebElement getSubMenuItem(String subMenuItemName) {
        log.info("returned :: " + subMenuItemName);
        return WebDrv.getInstance().getWebDriver().findElement(By.xpath("//li[@class='current']//a[contains(text(),'" + subMenuItemName + "')]"));
    }

    public void clickCategory(String categoryName) throws InterruptedException {
        for (WebElement category : categories) {
            if (category.getText().trim().equalsIgnoreCase(categoryName)) {
                category.click();
                log.info("clicked on :: " + categoryName);
                break;
            }
        }
    }

    public void clickSubCategory(String subCategoryName) throws InterruptedException {
        for (WebElement subCategory : subCategories_List) {
            if (subCategory.getText().trim().equalsIgnoreCase(subCategoryName)) {
                subCategory.click();
                log.info("clicked on :: " + subCategoryName);
                break;
            }
        }
    }
    // Methods to navigate to different categories
    public void navigateToCategory(String category) {
        switch (category.toLowerCase()) {
            case "home":
                homeMenu.click();
                break;
            case "apparel & accessories":
                apparelAccessoriesMenu.click();
                break;
            case "makeup":
                makeupMenu.click();
                break;
            case "skincare":
                skincareMenu.click();
                break;
            case "fragrance":
                fragranceMenu.click();
                break;
            case "men":
                menMenu.click();
                break;
            case "hair care":
                hairCareMenu.click();
                break;
            case "books":
                booksMenu.click();
                break;
            default:
                throw new IllegalArgumentException("Invalid category: " + category);
        }
    }
    // Method to navigate to a subcategory
    public void navigateToSubCategory(String subCategory) {
        switch (subCategory.toLowerCase()) {
            case "t-shirts":
                //tShirtsSubCategory.click();
                break;
            // Add other cases as needed
            default:
                throw new IllegalArgumentException("Invalid subcategory: " + subCategory);
        }
    }

    // Method to add product to cart by product name
    public void addProductToCart(String productName) {
        // XPath to locate the product based on name
        WebElement productAddButton = WebDrv.getInstance().getWebDriver().findElement(
                org.openqa.selenium.By.xpath("//a[text()='" + productName + "']/ancestor::div[@class='thumbnail']//button[contains(@class, 'button-cart')]")
        );
        productAddButton.click();
    }

    public void mouseOver(String data){
        log.info("doing mouse over on :"+data);
        Actions action = new Actions(WebDrv.getInstance().getWebDriver());
        action.moveToElement(WebDrv.getInstance().getWebDriver().findElement(By.xpath("//*[contains(text(),'"+data+"')]"))).build().perform();
    }

    public ProductCategoryPage clickOnMenu(WebElement element){
        log.info("clicking on : "+element.getText());
        element.click();
        return new ProductCategoryPage();
    }

    public ProductIdPage clickOnItem(String data){
        log.info("clicking on :"+data);
        WebDrv.getInstance().getWebDriver().findElement(By.xpath("//*[contains(text(),'"+data+"')]")).click();
        return new ProductIdPage();
    }


}

