package tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

    public void openCheckoutPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addProduct("Sauce Labs Backpack");
        productsPage.addToCart();
        checkoutYourInformationPage.open();
    }

    @Test
    public void checkSuccessCheckout() {
        openCheckoutPage();
        checkoutYourInformationPage.checkout("Evgeny", "Chrome", "6000");
        assertEquals(checkoutOverviewPage.getTitle(),
                "Checkout: Overview",
                "Пользовательские данные не верны");
    }

    @Test
    public void checkCheckoutEmptyFields() {
        openCheckoutPage();
        checkoutYourInformationPage.checkout("", "", "");
        assertEquals(checkoutYourInformationPage.getErrorMessage(),
                "Error: First Name is required",
                "Валидационное сообщение не найдено");
    }

    @Test
    public void checkCheckoutEmptyLastName() {
        openCheckoutPage();
        checkoutYourInformationPage.checkout("Evgeny", "", "6000");
        assertEquals(checkoutYourInformationPage.getErrorMessage(),
                "Error: Last Name is required",
                "Валидационное сообщение не найдено");
    }

    @Test
    public void checkCheckoutEmptyFirstName() {
        openCheckoutPage();
        checkoutYourInformationPage.checkout("", "Chrome", "6000");
        assertEquals(checkoutYourInformationPage.getErrorMessage(),
                "Error: First Name is required",
                "Валидационное сообщение не найдено");
    }

    @Test
    public void checkCheckoutEmptyPostalCode() {
        openCheckoutPage();
        checkoutYourInformationPage.checkout("Evgeny", "Chrome", "");
        assertEquals(checkoutYourInformationPage.getErrorMessage(),
                "Error: Postal Code is required",
                "Валидационное сообщение не найдено");
    }

    @Test
    public void checkCancelCheckout() {
        openCheckoutPage();
        checkoutYourInformationPage.cancel();
        assertEquals(cartPage.getTitle(),
                "Your Cart",
                "Возвращение на предыдущую страницу не выполнено");
    }
}
