package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.base.BaseTest;
import com.automationteststore.pages.*;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class NavigationSteps extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    ShoppingCartPage shoppingCartPage;
    Page page;
    NavigationMenuPage navigationMenuPage;

    @Given("I hover over Home and click on sub menu category dropdown {string}")
    public void i_hover_over_makeup_and_click_on_sub_menu_category_dropdown(String subMenuCategoryItem) throws InterruptedException {
        navigationMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), NavigationMenuPage.class);
        navigationMenuPage.mouseOverHome();
        navigationMenuPage.clickItemFromHomeSubMenuCategoryDropdownList(subMenuCategoryItem);
    }

    @Given("I hover over {string} and click on {string} from the sub menu dropdown")
    public void i_hover_over_Apparel_And_accessories_and_click_on_sub_menu_category_dropdown(String categoryMenuItem, String subMenuCategoryItem) {
        navigationMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), NavigationMenuPage.class);
        Assert.assertEquals(navigationMenuPage.getApparelAccessoriesMenuText(), categoryMenuItem);
        navigationMenuPage.mouseOverApparelAccessories();
        navigationMenuPage.clickItemFromApparelAndAccessoriesSubMenuCategoryDropdownList(subMenuCategoryItem);
    }

    @Given("I hovered over {string} and click on {string} from the sub menu dropdown")
    public void i_hover_over_makeup_menu_and_click_on_sub_menu_category_dropdown(String categoryMenuItem, String subMenuCategoryItem) {
        navigationMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), NavigationMenuPage.class);
        Assert.assertEquals(navigationMenuPage.getMakeupMenuText(), categoryMenuItem);
        navigationMenuPage.clickMakeupMenu();
        navigationMenuPage.clickItemFromMakeupSubMenuCategoryDropdownList(subMenuCategoryItem);
    }

    @Given("I hover over SKINCARE category menu and click on {string} from the sub menu dropdown")
    public void i_hover_over_skincare_menu_and_click_on_sub_menu_category_dropdown(String subMenuCategoryItem) {
        navigationMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), NavigationMenuPage.class);
        navigationMenuPage.mouseOverSkincare();
        navigationMenuPage.clickItemFromSkincareSubMenuCategoryDropdownList(subMenuCategoryItem);
    }


    @Given("I hover over {string} and click on {string} from the sub-menu dropdown")
    public void i_hover_over_menu_and_click_on_sub_menu_category_dropdown(String categoryMenuItem, String subMenuCategoryItem) throws InterruptedException {
        navigationMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), NavigationMenuPage.class);
        navigationMenuPage.clickCategory(categoryMenuItem);
        navigationMenuPage.clickSubCategory(subMenuCategoryItem);;
    }

    @Given("I hover over {string}")
    public void i_hover_over(String categoryMenuItem) throws InterruptedException {
        navigationMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), NavigationMenuPage.class);
        navigationMenuPage.clickCategory(categoryMenuItem);;
    }

    @Given("click on {string} from the sub-menu dropdown")
    public void click_on_from_the_sub_menu_dropdown(String subMenuCategoryItem) throws InterruptedException {
        navigationMenuPage = PageFactory.initElements(WebDrv.getInstance().getWebDriver(), NavigationMenuPage.class);
        navigationMenuPage.clickSubCategory(subMenuCategoryItem);;
    }

}

