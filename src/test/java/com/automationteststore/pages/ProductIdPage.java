package com.automationteststore.pages;


import com.automationteststore.exceptions.ElementNotFoundException;
import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.helperutilities.javaScript.JavaScriptHelper;
import com.automationteststore.helperutilities.select.DropDownHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class ProductIdPage {

    private final static Logger log = LogManager.getLogger(ProductIdPage.class);
    Page page;
    ProductIdPage productIdPage;

    private String productIdPageUrl = "https://automationteststore.com/index.php?rt=product/product&product_id=";
    private WebDriverWait wait;

    // Add to Cart Button
    @FindBy(css = ".cart")
    private WebElement addToCartButton;
    @FindBy(xpath = "//div[@class='image']/img")
    private WebElement productImage;
    @FindBy(xpath = "//ul[@class='list-unstyled']/li")
    private List<WebElement> productFeatures;
    @FindBy(how = How.CSS, using = ".bgnone")
    private WebElement productName;
    @FindBy(how = How.CSS, using = ".productfilneprice")
    private WebElement productPrice;
    @FindBy(how = How.CSS, using = "#product_quantity")
    private WebElement quantityInput;
    @FindBy(how = How.CSS, using = "#option320")
    private WebElement sizeDropdown;
    @FindBy(xpath = "//h1[@class='product-title']")
    private WebElement productTitle;
    @FindBy(how = How.CSS, using = "h2[class='heading2'] span")
    private WebElement latestProductsSection;
    @FindBy(how = How.XPATH, using = "//div[@id='product']//img[@alt='Absolute Anti-Age Spot Replenishing Unifying TreatmentSPF 15']")
    private WebElement specificProductImage;
    @FindBy(how = How.CSS, using = ".total-price")
    private WebElement totalPrice;
    @FindBy(how = How.CSS, using = ".productprint.btn.btn-large")
    private WebElement printButton;
    @FindBy(how = How.CSS, using = "a[href='#description']")
    private WebElement descriptionSection;
    @FindBy(how = How.CSS, using = ".tab-content")
    private WebElement descriptionSectionContent;
    @FindBy(how = How.CSS, using = "a[href='#review']")
    private WebElement reviewsSection;
    @FindBy(how = How.CSS, using = "div[id='current_reviews'] div[class='content']")
    private WebElement reviewsContent;
    @FindBy(how = How.CSS, using = "div[id='review_title'] h4")
    private WebElement review_title;
    @FindBy(how = How.CSS, using = "[class='star-rating rater-0 star star-rating-applied star-rating-live']")
    private WebElement review_ratingSelect;
    @FindBy(how = How.CSS, using = "#name")
    private WebElement review_yourName;
    @FindBy(how = How.CSS, using = "#text")
    private WebElement review_comment;
    @FindBy(how = How.CSS, using = "#captcha")
    private WebElement captchaField;
    @FindBy(how = How.CSS, using = "#captcha_img")
    private WebElement captchaImage;
    @FindBy(how = How.CSS, using = "#review_submit")
    private WebElement review_submit;
    @FindBy(how = How.CSS, using = "a[href='#producttag']")
    private WebElement tagsSection;
    @FindBy(how = How.CSS, using = ".tab-content")
    private WebElement tagsContent;
    @FindBy(css = ".bgnone")
    private List<WebElement> productname;
    @FindBy(css = "[class='col-md-6 text-center'] div:nth-child(2) [src]")
    private List<WebElement> productEasyZoom;
    @FindBy(css = "[class='productname']")
    private List<WebElement> productnames;
    @FindBy(css = "div.productfilneprice")
    private WebElement unitprice;
    @FindBy(css = ".input-group-addon")
    private WebElement qtyLabel;
    @FindBy(css = "span.total-price")
    private WebElement total_Price;
    @FindBy(css = ".cart")
    private WebElement add2Cart;
    @FindBy(id = "option350")
    private WebElement colorDropdown;
    @FindBy(xpath = "//select[@id='option350']")
    private WebElement colorDrops;
    @FindBy(id = "option351")
    private WebElement size_Dropdown;
    @FindBy(id = "product_quantity")
    private WebElement product_quantity;
    @FindBy(xpath = "/html/body/div/div[2]/div/div[2]/div/div[1]/div/div[2]/div/div/div[2]/form/fieldset/div[1]/div/label")
    private List<WebElement> colorRadioButtonList;
    @FindBy(css = ".total-price-holder .control-label")
    private WebElement total_price_holder_label;
    @FindBy(css = ".productprint.btn.btn-large")
    private WebElement productPrint;
    @FindBy(css = ".wishlist_add.btn.btn-large")
    private WebElement addToWishlist;
    @FindBy(css = "a[href='#a[href='#description']']")
    private WebElement productDescription;
    @FindBy(css = "a[href='#review']")
    private WebElement productReview;
    @FindBy(css = "a[href='#relatedproducts']")
    private WebElement relatedProducts;
    @FindBy(css = ".btn.btn-large.productprint")
    private WebElement productprint;
    @FindBy(css = ".thumbnails.mainimage.smallimage")
    private WebElement thumbnailsImage;
    @FindBy(css = "div[class='tab-content'] li:nth-child(1)")
    private WebElement descriptionProductModel;
    @FindBy(css = ".productinfo li:nth-of-type(1)")
    private WebElement descriptionProductManufacturerLogo;
    @FindBy(css = "#option316")
    private WebElement sideDropdown;
    @FindBy(xpath = "/html/body/div/div[2]/div/div[2]/div/div[1]/div/div[2]/div/div/div[2]/form/fieldset/div[1]/div/select/option")
    private List<WebElement> sizeDropdownList;
    @FindBy(css = "div[id='review_title'] h4")
    private WebElement reviewTitle;
    @FindBy(css = ".control-label.col-md-3.pull-left")
    private WebElement reviewRatingHeader;
    @FindBy(css = "li.on")
    private List<WebElement> reviewRatingList;
    @FindBy(css = "#text")
    private WebElement reviewTextBox;
    @FindBy(css = ".control-label.col-md-3.pull-left")
    private WebElement reviewNoteWarning;
    @FindBy(css = "#captcha_img")
    private WebElement reviewNCaptchaImage;
    @FindBy(css = "captcha")
    private WebElement reviewCaptchaBox;
    @FindBy(css = "#review_submit")
    private WebElement reviewSubmitButton;

    public List<WebElement> getProductEasyZoom() {
        return productEasyZoom;

    }

    public List<WebElement> getProductnames() {
        return productnames;

    }

    public String getUnitpriceTxt() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(unitprice);
    }

    public WebElement product_quantity() {
        return product_quantity;

    }

    public void inputProduct_quantity(String qty) {
        product_quantity.sendKeys(qty);

    }

    public WebElement add2Cart() {
        return add2Cart;

    }

    public ShoppingCartPage clickOnAdd2Cart() {
        add2Cart.click();
        return new ShoppingCartPage();
    }

    public WebElement getTotalPrice() {
        return totalPrice;

    }

    public WebElement getAdd2Cart() {
        return add2Cart;

    }

    public WebElement getProduct_quantity() {
        return product_quantity;

    }

    public List<WebElement> getColorRadioButtonList() {
        return colorRadioButtonList;

    }

    public WebElement getTotal_price_holder_label() {
        return total_price_holder_label;

    }

    public WebElement getProductPrint() {
        return productPrint;

    }

    public WebElement getAddToWishlist() {
        return addToWishlist;

    }

    public WebElement getProductDescription() {
        return productDescription;

    }

    public WebElement getProductReview() {
        return productReview;

    }

    public WebElement getRelatedProducts() {
        return relatedProducts;

    }

    public WebElement getProductprint() {
        return productprint;

    }

    public WebElement getThumbnailsImage() {
        return thumbnailsImage;

    }

    public WebElement getDescriptionProductModel() {
        return descriptionProductModel;

    }

    public WebElement getDescriptionProductManufacturerLogo() {
        return descriptionProductManufacturerLogo;

    }

    public List<WebElement> getProductname() {
        return productname;

    }

    public WebElement getUnitprice() {
        return unitprice;

    }

    public WebElement getQtyLabel() {
        return qtyLabel;

    }

    public WebElement getSideDropdown() {
        return sideDropdown;

    }

    public List<WebElement> getSizeDropdownList() {
        return sizeDropdownList;

    }

    public WebElement getReviewTitle() {
        return reviewTitle;

    }

    public WebElement getReviewRatingHeader() {
        return reviewRatingHeader;

    }

    public WebElement getReviewTextBox() {
        return reviewTextBox;

    }

    public WebElement getReviewNoteWarning() {
        return reviewNoteWarning;

    }

    public WebElement getReviewCaptchaBox() {
        return reviewCaptchaBox;

    }

    public WebElement getReviewSubmitBtn() {
        return reviewSubmitButton;

    }

    public WebElement getReviewNCaptchaImage() {
        return reviewNCaptchaImage;

    }

    public void selectColor(String color) {
        new JavaScriptHelper(WebDrv.getInstance().getWebDriver()).scrollToElementAndClick(colorDrops);
        //new DropDownHelper(driver).selectUsingVisibleText(colorDrops, color.trim());
        new DropDownHelper(WebDrv.getInstance().getWebDriver()).selectUsingIndex(colorDrops, 1);

    }

    public void enterQuantity(String qty) {
        product_quantity.clear();
        log.info("Cleared the field ");
        product_quantity.sendKeys(qty);
        log.info("Entered keys to the field :: " +qty);

    }

    public ShoppingCartPage clickAdd2Cart() {
        add2Cart.click();
        log.info("clicked Add to Cart button :: " +add2Cart.getText());
        return new ShoppingCartPage();
    }

    public List<WebElement> getReviewRatingList() {
        return reviewRatingList;

    }

    //=====================Ai=================================

    public String getProductDescriptionText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(productDescription);
    }

    public String getProductPriceText() {
        log.info("Verifying product price...");
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(productPrice);

    }

    public void enterQuantity(int quantity) {
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
        log.info("Cleared and entered keys to the field :: " +quantity);
    }

    public void selectSize(String size) {
        Select select = new Select(sizeDropdown);
        select.selectByVisibleText(size);
        log.info("Selected size  :: " +size);
    }

    public CheckoutCartPage clickAddToCart() {
        addToCartButton.click();
        log.info("Clicked Add to Cart button");
        return new CheckoutCartPage();
    }

    public String getAddedToCartText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(addToCartButton);
    }
    public void verifyLatestProductsSectionIsDisplayed() {
        new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(latestProductsSection);
    }
    public boolean verifySpecificProductImageIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(specificProductImage);
    }

    public boolean verifyTotalPriceIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(totalPrice);
    }

    public void clickPrintButton() {
        printButton.click();
        log.info("Clicked Print button");
    }

    public boolean isPrintButtonDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(printButton);
    }

    public boolean verifyDescriptionSectionIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(descriptionSection);
    }

    public boolean verifyReviewsSectionIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(reviewsSection);

    }

    public boolean verifyTagsSectionIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(tagsSection);
    }

    public void assertProductName(String expectedName) {
        try {
            String actualName = productName.getText().trim();
            Assert.assertEquals(actualName, expectedName, "Product name does not match.");
        } catch (Exception e) {
            throw new ElementNotFoundException("Product name element not found or text does not match.");
        }
    }

    public String getProductNameText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(productName);

    }

    // Get product title
    public String getProductTitle() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(productTitle);


    }

    // Get product price
    public String getProductPrice() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(productPrice);

    }

    // Add product to cart
    public void addToCart() {
        addToCartButton.click();
        log.info("Clicked Add to Cart button");

    }

    // Navigate to cart
    public void goToCart() {
        addToCartButton.click();
        log.info("Navigated to cart");

    }

    // Verify all elements on the product page
    public boolean verifyAllElements() {
        try {
            return productTitle.isDisplayed() &&
                    productPrice.isDisplayed() &&
                    productImage.isDisplayed() &&
                    !productFeatures.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public String getProductIdUrlPath() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getCurrentPageUrl();
    }

    public boolean verifyPrintButtonIsDisplayed() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(printButton);
    }
}
