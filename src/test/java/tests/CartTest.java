package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class CartTest extends BaseTest {

    @Test(testName = "Проверка покупки любого товара", priority = 1)
    @Epic("Корзина")
    @Feature("Добавление товара")
    @Story("Отображение товара в корзине")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Евгений Сабук")
    @Description("Проврека добавления товара в корзину")
    @Flaky
    @Link(name = "документация", url = "saucedemo.com")
    @TmsLink("TMS_T10")
    @Issue("Bug_Jira")
    public void checkProductPurchase() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addProduct("Sauce Labs Backpack")
                .addProduct("Sauce Labs Fleece Jacket")
                .addToCart()
                .isPageOpened();
        assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"), "bad");
        assertEquals(cartPage.getProductFromCart(0), "Sauce Labs Backpack", "BAD");
        assertTrue(cartPage.getProductsName().contains("Sauce Labs Backpack"));
        assertEquals(cartPage.getProductPrice("Sauce Labs Backpack"), 29.99);
    }

    @Test(testName = "Проверка пустой корзины", priority = 2, groups = {"slow"})
    @Description("Проверяет стандартное отображение страницы корзины без товаров.")
    @Step("Проверка, что по стандарту страница пуста. Ожидается 0 товаров")
    public void checkCartEmptyState() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened();
        cartPage.open();
        assertTrue(cartPage.checkEmptyState(), "Корзина не пуста");
    }

    @Test(testName = "Проверка удаления продукта", priority = 2, groups = {"slow"})
    @Description("Проверка того, что товар корректно удаляется из корзины")
    @Step("Проверка удаления товара. Ожидается 0 товаров")
    public void checkRemoveProduct() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addProduct("Sauce Labs Backpack")
                .addToCart()
                .open()
                .removeProduct("Sauce Labs Backpack")
                .isPageOpened();
        assertTrue(cartPage.checkEmptyState(), "Корзина не пуста");
    }

    @Test(testName = "Проверка работы кнопки 'Continue'", priority = 1)
    @Description("Проверка того, что кнопка continue возвращает пользователя на предыдущую страницу")
    @Step("Проверка, что после перенаправления открывается страница Products")
    public void checkContinueSoppingButton() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened();
        cartPage.open()
                .isPageOpened()
                .continueShopping()
                .isPageOpened();
        assertEquals(productsPage.getTitle(),
                "Products",
                "Возвращение на предыдущую страницу не выполнено");
    }
}