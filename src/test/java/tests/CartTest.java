package tests;


import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class CartTest extends BaseTest {

    @Test
    public void checkProductPurchase() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack");
        productsPage.addProduct("Sauce Labs Fleece Jacket");
        productsPage.addToCart();
        cartPage.open();
        assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"), "bad");
        assertEquals(cartPage.getProductFromCart(0), "Sauce Labs Backpack", "BAD");
        assertTrue(cartPage.getProductsName().contains("Sauce Labs Backpack"));
        assertEquals(cartPage.getProductPrice("Sauce Labs Backpack"), 29.99);
    }

    @Test
    public void checkCartEmptyState() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        cartPage.open();
        assertTrue(cartPage.checkEmptyState(), "Корзина не пуста");
    }

    @Test
    public void checkRemoveProduct() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack");
        productsPage.addToCart();
        cartPage.open();
        cartPage.removeProduct("Sauce Labs Backpack");
        assertTrue(cartPage.checkEmptyState(), "Корзина не пуста");
    }

    @Test
    public void checkContinueSoppingButton() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        cartPage.open();
        cartPage.continueShopping();
        assertEquals(productsPage.getTitle(),
                "Products",
                "Возвращение на предыдущую страницу не выполнено");
    }
}