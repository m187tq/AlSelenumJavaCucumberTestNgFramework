package com.automationteststore.tests.Order;


import com.automationteststore.pages.ShoppingCartPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CartTests {

    @Test
    public void testAddItemToCart() {
        ShoppingCartPage shoppingCartPage  = new ShoppingCartPage();
        shoppingCartPage.addItemToCart();

        int itemCount = shoppingCartPage.getItemCount();
        Assert.assertEquals(itemCount, 1, "Item was not added to the cart!");
    }

    @Test
    public void testRemoveItemFromCart() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        shoppingCartPage.addItemToCart();
        shoppingCartPage.removeItemFromCart();

        int itemCount = shoppingCartPage.getItemCount();
        Assert.assertEquals(itemCount, 0, "Item was not removed from the cart!");
    }
}

