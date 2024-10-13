package com.automationteststore.pages;

import com.automationteststore.helperutilities.assertion.VerificationHelper;
import com.automationteststore.webdriverutilities.WebDrv;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ShoppingCartPage {
    private final Logger log = LogManager.getLogger(ShoppingCartPage.class);
    @FindBy(xpath = "//*[contains(text(),'Your shopping cart is empty')]")
    private WebElement emptyShoppingCartMsg;
    @FindBy(xpath = "//input[@id='estimate_postcode']")
    private WebElement estimatePostcodeBox;
    @FindBy(css = "h1.basePage-title.mb-3.h3") // MyAccount BasePage heading
    private WebElement headerTxt;
    @FindBy(css = ".alert.alert-danger.alert-dismissible")
    private WebElement errorAlertForDesiredQuantityOrNotInStock;
    @FindBy(xpath = "//td[2]")
    private List<WebElement> productNames;
    @FindBy(xpath = "//*[@id='cart']/div/div[1]/table/tbody/tr")
    private List<WebElement> products;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/form/div/div[1]/table/tbody/tr/td[2]")
    private List<WebElement> productList;
    @FindBy(xpath = "//*[@id='cart']/div/div/table/tbody/tr/td[1]")
    private List<WebElement> productImages;
    @FindBy(xpath = "//*[@id='cart']/div/div/table/tbody/tr/td[3]")
    private List<WebElement> productModels;
    @FindBy(css = "div.input-group.input-group-sm")
    private List<WebElement> quantityBox;
    @FindBy(css = "#cart_update")
    private WebElement updateButton;

    @FindBy(css = "i.fa.fa-trash-o.fa-fw")
    private List<WebElement> removeItems;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/form/div/div[1]/table/tbody/tr/td[1]")
    private List<WebElement> product_Images;

    @FindBy(xpath = "//h1[@class='product-title']")
    private WebElement productTitle;

    @FindBy(xpath = "//p[@class='price']")
    private WebElement productPrice;

    // Example: Edit Cart Button
    @FindBy(css = "a.edit-cart") // Adjust selector based on actual HTML
    private WebElement editCartButton;

    // Example: Continue Shopping Button
    @FindBy(css = "button.continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/form/div/div[1]/table/tbody/tr/td[2]")
    private List<WebElement> product_Names;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/form/div/div[1]/table/tbody/tr/td[3]")
    private List<WebElement> product_Models;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/form/div/div[1]/table/tbody/tr/td[4]")
    private List<WebElement> product_Quantities;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/form/div/div[1]/table/tbody/tr/td[5]")
    private List<WebElement> product_Prices;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/form/div/div[1]/table/tbody/tr/td[6]")
    private List<WebElement> product_TotalPrices;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/form/div/div[1]/table/tbody/tr/td[7]")
    private List<WebElement> product_RemoveButtons;
    @FindBy(xpath = "/html/body/div/div[2]/div/div/div/form/div/div[1]/table/tbody/tr/td")
    private List<WebElement> allTds_Tr;
    @FindBy(xpath = "//*[@id='cart']/div/div[1]/table/tbody/tr[2]/td[6]")
    private List<WebElement> totalPrices;
    @FindBy(xpath = "//table/tbody/tr/td[6]")
    private List<WebElement> unitPrices;
    @FindBy(xpath = "//div[@innertext='Your shopping cart is empty!']")
    private WebElement cart_empty;
    @FindBy(xpath = "//input[@id='coupon_coupon']")
    private WebElement couponBox;
    @FindBy(css = "#apply_coupon_btn")
    private WebElement applyCouponBtn;
    @FindBy(css = "#estimate_country")
    private WebElement estimateCountryDropdown;
    @FindBy(css = "#estimate_country_zones")
    private WebElement countryZonesDropdown;
    @FindBy(xpath = "//tbody/tr[2]/td[1]/div[1]/form[1]/div[2]/div[1]/span[1]/button[1]")
    private WebElement estimateCalculatorBtn;
    @FindBy(xpath = "//select[@id='shippings']")
    private WebElement FlatRateShipmentsDropdown;
    @FindBy(xpath = "//*[@id='cart']/div/div[1]/table/tbody/tr[2]/td[7]/a/i")
    private WebElement removeItemBtn;
    @FindBy(id = "cart_quantity50")
    private WebElement quantity;
    @FindBy(id = "cart_update")
    private WebElement updateBtn;
    @FindBy(css = ".mb20.pull-right > a[title='Checkout']")
    private WebElement checkoutBtn;

    By addToCartButton = By.id("add_to_cart_button");
    By cartItemCount = By.cssSelector(".cart_total");
    // Locators
    private By cartIcon = By.cssSelector(".cart_total a");
    private By cart_Items = By.cssSelector(".cart_product .product-name a");
    private By quantityInput = By.cssSelector(".cart_quantity input");
    private By removeButton = By.cssSelector(".cart_quantity_delete");
    private By unitPrice = By.cssSelector(".cart_unit .price");
    private By totalPrice = By.cssSelector(".cart_total .price");
    private By grandTotal = By.cssSelector(".cart_totals .price");
    private By updateCartButton = By.cssSelector("button[name='update_cart']");

    @FindBy(xpath = "//h1[text()='ACCOUNT LOGIN']")
    private WebElement accountLoginHeader;

    @FindBy(xpath = "//nav//ol/li[last()]")
    private WebElement breadcrumb;

/*    @FindBy(xpath = "//a[text()='Edit Cart']")
    private WebElement editCartButton;*/

    @FindBy(xpath = "//button[text()='Remove']")
    private List<WebElement> removeButtons;

    @FindBy(xpath = "//input[@type='number']")
    private List<WebElement> quantityFields;

    @FindBy(xpath = "//h1[text()='YOUR ORDER HAS BEEN PROCESSED!']")
    private WebElement orderSuccessMessage;

    @FindBy(xpath = "//button[text()='Continue']")
    private WebElement continueButton;
    // Locators
    @FindBy(css = "a[href*='checkout/cart']")
    private WebElement cartLink;

    @FindBy(css = ".cart-info tbody tr")
    private List<WebElement> cartItems;

    private static String getProductModelNumbers(WebElement p) {
        return p.findElement(By.xpath("following-sibling::td[1]")).getText();
    }

    public static String getProductQuantity() {
        return WebDrv.getInstance().getWebDriver().findElement(By.xpath("//*[@id='cart']/div/div[1]/table/tbody/tr[2]/td[3]")).getText();
    }

    public void EnterCouponNumberOnCouponBox(String CouponNumber) {
        couponBox.sendKeys(CouponNumber);

    }

    public void clickOnApplyCouponBtn() {
        applyCouponBtn.click();

    }

/*    public void selectEstimateCountryDropdown(String countryName) {
        new DropDownHelper(WebDrv.getInstance().getWebDriver()).selectUsingVisibleText(estimateCountryDropdown, countryName);
    }

    public void selectCountryZonesDropdown(String countryZonesName) {
        new DropDownHelper(WebDrv.getInstance().getWebDriver()).selectUsingVisibleText(countryZonesDropdown, countryZonesName);


    }*/

    public void enterOnEstimatePostcodeBox(String postCode) {
        estimatePostcodeBox.sendKeys(postCode);

    }

    public void clickOnEstimateCalculatorBtn() {
        estimateCalculatorBtn.click();

    }

/*    public void selectFlatRateShipmentsDropdown(String flatRateShipment) {
        new DropDownHelper(WebDrv.getInstance().getWebDriver()).selectUsingVisibleText(FlatRateShipmentsDropdown, flatRateShipment);
    }*/

    public void updateQuantityOfProductInTheCart(String productName, int qty) throws InterruptedException {
        List<WebElement> productList = WebDrv.getInstance().getWebDriver().findElements(By.xpath("/html/body/div/div[2]/div/div/div/form/div/div[1]/table/tbody/tr/td[2]"));
        int size = productList.size();
        for (int i = 0; i < size; i++) {
            if (productList.get(i).getText().equalsIgnoreCase(productName)) {
                productList.get(i).findElement(By.xpath("following-sibling::td[3]//div//input")).clear();
                productList.get(i).findElement(By.xpath("following-sibling::td[3]//div//input")).sendKeys(String.valueOf(qty));
                break;
            }
        }

    }

    public Boolean VerifyOrderDisplay(String productName) {
        Boolean match = product_Names.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
        return match;

    }


/*    public String getModelOfProductInTheCart(String productName) {
        String productModel = null;
        List<WebElement> productList = WebDrv.getInstance().getWebDriver().findElements(By.xpath("/html/body/div/div[2]/div/div/div/form/div/div[1]/table/tbody/tr/td[2]"));
        for (WebElement element : productList) {
            if (element.getText().contains(productName)) {
                productModel = element.findElement(By.xpath("following-sibling::td[1]")).getText().trim();

            }
        }
        return productModel;
    }*/

    public String assertProductNameAndModelOfProductInTheCart(String productName) {
        String productModel = null;
        List<WebElement> productList = WebDrv.getInstance().getWebDriver().findElements(By.xpath("/html/body/div/div[2]/div/div/div/form/div/div[1]/table/tbody/tr/td[2]"));
        int size = productList.size();
        for (int i = 0; i < size; i++) {
            if (productList.get(i).getText().equalsIgnoreCase(productName)) {
                productList.get(i).findElement(By.xpath("following-sibling::td[5]")).click();
                break;
            }
        }
        return productModel;

    }

    public String getUnitPriceOfProductInTheCart(String productName) {
        String unitPerPrice = null;
        List<WebElement> productList = WebDrv.getInstance().getWebDriver().findElements(By.xpath("/html/body/div/div[2]/div/div/div/form/div/div[1]/table/tbody/tr/td[2]"));
        int size = productList.size();
        for (int i = 0; i < size; i++) {
            if (productList.get(i).getText().trim().equalsIgnoreCase(productName.trim())) {
                unitPerPrice = productList.get(i).findElement(By.xpath("following-sibling::td[2]")).getText();
                break;
            }
        }
        return unitPerPrice;
    }

    public String getTotalPriceOfProductInTheCart(String productName) {
        String totalPrice = null;
        List<WebElement> productList = WebDrv.getInstance().getWebDriver().findElements(By.xpath("/html/body/div/div[2]/div/div/div/form/div/div[1]/table/tbody/tr/td[2]"));
        int size = productList.size();
        for (WebElement element : productList) {
            if (element.getText().equalsIgnoreCase(productName)) {
                totalPrice = element.findElement(By.xpath("following-sibling::td[4]")).getText();
                break;
            }
        }
        return totalPrice;
    }

    public String getNameOfProductsInTheCart(String productName) {
        List<WebElement> productList = WebDrv.getInstance().getWebDriver().findElements(By.xpath("/html/body/div/div[2]/div/div/div/form/div/div[1]/table/tbody/tr/td[2]"));
        return productList
                .stream()
                .parallel()
                .map(WebElement::getText)
                .filter(text -> text.equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
    }

    public void removeProductFromTheCart(String productName) {
        List<WebElement> products =
                productList.stream()
                        .parallel()
                        .filter(p -> p.getText().equalsIgnoreCase(productName))
                        .collect(Collectors.toList());
        if (products.size() > 0) {
            products.stream().parallel().findFirst().ifPresent(product -> product.findElement(By.xpath("following-sibling::td[5]")).click());
        }
    }

    public void clickOnUpdateButton() {
        updateBtn.click();

    }

    public LoginPage clickOnCheckoutButton() {
        checkoutBtn.click();
        return new LoginPage();
    }

    public void inputItemQuantity(int number) {
        quantity.clear();
        quantity.sendKeys(String.valueOf(number));

    }

    public String getUpdateButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(updateBtn);

    }

    public String getCheckoutButtonText() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(checkoutBtn);

    }

    public boolean assertEmptyShoppingCartMessage() {
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).isDisplayed(emptyShoppingCartMsg);
    }

    public List<String> getQuantityOfProductInTheCart(String productName) {
        return product_Names
                .stream()
                .parallel()
                .filter(s -> s.getText().contains(productName))
                .map(s -> getProductQuantity()).collect(Collectors.toList());
        //.forEach(a->System.out.println(a));
    }

    public String getProductName(String prodName) {
        WebElement name = WebDrv.getInstance().getWebDriver().findElement(By.xpath("//td[@class='align_left']//a[contains(text(),'" + prodName + "')]"));
        return new VerificationHelper(WebDrv.getInstance().getWebDriver()).getText(name);

    }

    public String getProductTotalPrice(String prodName) {
        WebElement prod = productList.stream()
                .parallel()
                .filter(p -> p.getText().equalsIgnoreCase(prodName))
                .findFirst()
                .orElse(null);
        return prod.findElement(By.xpath("following-sibling::td[2]")).getText().trim();
    }

//    public void getProductModelNumberInTheCart(String productName) {
//        List<String> productModel = product_Names
//                .stream()
//                .parallel()
//                .filter(s -> s.getText().contains(productName))
//                .map(ShoppingCartPage::getProductModelNumber).toList();
//        productModel.forEach(System.out::println);
//    }
//
//
//
//    private static String getProductModelNumber(WebElement s) {
//        return s.findElement(By.xpath("following-sibling::td[2]")).getText();
//    }
// Methods to verify elements
public String getProductTitle() {
    return productTitle.getText();
}

    public String getProductPrice() {
        return productPrice.getText();
    }

    // Add methods to interact with the page as needed

    // Method to navigate to cart
    public void goToCart() {
        cartLink.click();
    }

    // Method to verify product in cart
    public boolean isProductInTheCart(String productName) {
        for (WebElement item : cartItems) {
            String name = item.findElement(org.openqa.selenium.By.xpath(".//td[@class='name']/a")).getText();
            if (name.equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    // Method to remove a product by name
    public void removeProduct(String productName) {
        for (WebElement item : cartItems) {
            String name = item.findElement(org.openqa.selenium.By.xpath(".//td[@class='name']/a")).getText();
            if (name.equalsIgnoreCase(productName)) {
                WebElement removeButton = item.findElement(org.openqa.selenium.By.xpath(".//button[contains(@class, 'remove')]"));
                removeButton.click();
                break;
            }
        }
    }

    // Methods to update quantity
    public void updateTheProductQuantity(String productName, int quantity) {
        for (WebElement item : cartItems) {
            String name = item.findElement(org.openqa.selenium.By.xpath(".//td[@class='name']/a")).getText();
            if (name.equalsIgnoreCase(productName)) {
                WebElement quantityField = item.findElement(org.openqa.selenium.By.xpath(".//input[@type='text' and contains(@name, 'quantity')]"));
                quantityField.clear();
                quantityField.sendKeys(String.valueOf(quantity));
                WebElement updateButton = item.findElement(org.openqa.selenium.By.xpath(".//button[contains(@class, 'update')]"));
                updateButton.click();
                break;
            }
        }
    }

    // Method to get total price
    public String getTotalPrice() {
        WebElement total = WebDrv.getInstance().getWebDriver().findElement(org.openqa.selenium.By.xpath("//div[@class='cart-total']//span[@class='price']"));
        return total.getText();
    }

    // Method to proceed to checkout
    public void proceedToCheckout() {
        WebElement checkoutButton = WebDrv.getInstance().getWebDriver().findElement(org.openqa.selenium.By.xpath("//a[@href='https://automationteststore.com/index.php?rt=checkout/checkout']"));
        checkoutButton.click();
    }

    public ProductCategoryPage clickOnProductName(String productName) {


        return null;
    }

    public void clickEditCart() {
        editCartButton.click();
    }

    public void removeItemFromCart(String itemName) {
        for (WebElement product : removeButtons) {
            WebElement productNameElement = product.findElement(By.xpath("./../../div[1]/a"));
            String productName = productNameElement.getText().trim();
            if (productName.equalsIgnoreCase(itemName)) {
                product.click();
                break;
            }
        }
    }

    public void updateProductQuantity(Map<String, Integer> products) {
        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            String productName = entry.getKey();
            Integer quantity = entry.getValue();
            for (WebElement product : quantityFields) {
                WebElement productNameElement = product.findElement(By.xpath("./../../div[1]/a"));
                String currentProductName = productNameElement.getText().trim();
                if (currentProductName.equalsIgnoreCase(productName)) {
                    product.clear();
                    product.sendKeys(quantity.toString());
                    break;
                }
            }
        }
    }

    public void clickUpdateButton() {
        updateButton.click();
    }

    public boolean isProductNameDisplayed(String productName) {
        List<WebElement> products = WebDrv.getInstance().getWebDriver().findElements(By.xpath("//div[@class='product-name']/a"));
        for (WebElement product : products) {
            if (product.getText().trim().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public void clickCheckout() {
        WebDrv.getInstance().getWebDriver().findElement(By.xpath("//button[text()='Checkout']")).click();
    }

    public void clickConfirmOrder() {
        WebDrv.getInstance().getWebDriver().findElement(By.xpath("//button[text()='Confirm Order']")).click();
    }


    public String getOrderSuccessMessage() {
        return orderSuccessMessage.getText();

    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public boolean isProductInCart(String productName) {
        for (WebElement item : cartItems) {
            if (item.getText().contains(productName)) {
                return true;
            }
        }
        return false;
    }

    // Actions
    public void addItemToCart() {
        WebDrv.getInstance().getWebDriver().findElement(addToCartButton).click();

    }

    public int getItemCount() {
        String itemCountText = WebDrv.getInstance().getWebDriver().findElement(cartItemCount).getText();
        return Integer.parseInt(itemCountText);
    }

    public void removeItemFromCart() {
        WebDrv.getInstance().getWebDriver().findElement(By.cssSelector(".remove_button")).click();
    }

    public void navigateToCart() {
        WebDrv.getInstance().getWebDriver().findElement(cartIcon).click();
    }

    public boolean verifyProductInCart(String productName, int quantity) {
        List<WebElement> products = WebDrv.getInstance().getWebDriver().findElements(cart_Items);
        for (WebElement product : products) {
            if (product.getText().trim().equals(productName)) {
                WebElement qtyInput = product.findElement(By.xpath("./ancestor::tr//input[@type='text']"));
                int actualQty = Integer.parseInt(qtyInput.getAttribute("value").trim());
                return actualQty == quantity;
            }
        }
        return false;
    }


    public void removeProductFromCart(String productName) {
        List<WebElement> products = WebDrv.getInstance().getWebDriver().findElements(cart_Items);
        for (WebElement product : products) {
            if (product.getText().trim().equals(productName)) {
                WebElement removeBtn = product.findElement(By.xpath("./ancestor::tr//a[@class='cart_quantity_delete']"));
                removeBtn.click();
                break;
            }
        }
    }

    public void updateProductQuantity(String productName, int newQuantity) {
        List<WebElement> products = WebDrv.getInstance().getWebDriver().findElements(cart_Items);
        for (WebElement product : products) {
            if (product.getText().trim().equals(productName)) {
                WebElement qtyInput = product.findElement(By.xpath("./ancestor::tr//input[@type='text']"));
                qtyInput.clear();
                qtyInput.sendKeys(String.valueOf(newQuantity));
                // Click update button if necessary
                WebElement updateButton = WebDrv.getInstance().getWebDriver().findElement(updateCartButton);
                updateButton.click();
                break;
            }
        }
    }

    public double getUnitPrice(String productName) {
        List<WebElement> products = WebDrv.getInstance().getWebDriver().findElements(cart_Items);
        for (WebElement product : products) {
            if (product.getText().trim().equals(productName)) {
                WebElement unitPriceElement = product.findElement(By.xpath("./ancestor::tr//span[@class='price']"));
                return parsePrice(unitPriceElement.getText().trim());
            }
        }
        return 0.0;
    }

    public double getTotalPrice(String productName) {
        List<WebElement> products = WebDrv.getInstance().getWebDriver().findElements(cart_Items);
        for (WebElement product : products) {
            if (product.getText().trim().equals(productName)) {
                WebElement totalPriceElement = product.findElement(By.xpath("./ancestor::tr//span[@class='price']"));
                return parsePrice(totalPriceElement.getText().trim());
            }
        }
        return 0.0;
    }

    public boolean verifyTotalPriceEqualsUnitPrice() {
        List<WebElement> products = WebDrv.getInstance().getWebDriver().findElements(cart_Items);
        for (WebElement product : products) {
            WebElement unitPriceElement = product.findElement(By.xpath("./ancestor::tr//span[@class='price']"));
            double unitPrice = parsePrice(unitPriceElement.getText().trim());
            WebElement totalPriceElement = product.findElement(By.xpath("./ancestor::tr//span[@class='price']"));
            double totalPrice = parsePrice(totalPriceElement.getText().trim());
            if (unitPrice != totalPrice) {
                return false;
            }
        }
        return true;
    }

    public double getGrandTotal() {
        WebElement grandTotalElement = WebDrv.getInstance().getWebDriver().findElement(grandTotal);
        return parsePrice(grandTotalElement.getText().trim());
    }

    public double calculateSumOfProductTotals() {
        double sum = 0.0;
        List<WebElement> products = WebDrv.getInstance().getWebDriver().findElements(cart_Items);
        for (WebElement product : products) {
            WebElement totalPriceElement = product.findElement(By.xpath("./ancestor::tr//span[@class='price']"));
            sum += parsePrice(totalPriceElement.getText().trim());
        }
        return sum;
    }

    private double parsePrice(String priceText) {
        return Double.parseDouble(priceText.replace("$", "").replace(",", ""));
    }

}
