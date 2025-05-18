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
        checkoutYourInformationPage.fillingForm("Evgeny", "Chrome", "6000");
        assertEquals(checkoutOverviewPage.getTitle(),
                "Checkout: Overview",
                "Пользовательские данные не верны");
    }

    @Test
    public void checkCheckoutEmptyFields() {
        openCheckoutPage();
        checkoutYourInformationPage.fillingForm("", "", "");
        assertEquals(checkoutYourInformationPage.getErrorMessage(),
                "Error: First Name is required",
                "Валидационное сообщение не найдено");
    }

    @Test
    public void checkCheckoutEmptyLastName() {
        openCheckoutPage();
        checkoutYourInformationPage.fillingForm("Evgeny", "", "6000");
        assertEquals(checkoutYourInformationPage.getErrorMessage(),
                "Error: Last Name is required",
                "Валидационное сообщение не найдено");
    }

    @Test
    public void checkCheckoutEmptyFirstName() {
        openCheckoutPage();
        checkoutYourInformationPage.fillingForm("", "Chrome", "6000");
        assertEquals(checkoutYourInformationPage.getErrorMessage(),
                "Error: First Name is required",
                "Валидационное сообщение не найдено");
    }

    @Test
    public void checkCheckoutEmptyPostalCode() {
        openCheckoutPage();
        checkoutYourInformationPage.fillingForm("Evgeny", "Chrome", "");
        assertEquals(checkoutYourInformationPage.getErrorMessage(),
                "Error: Postal Code is required",
                "Валидационное сообщение не найдено");
    }

    @Test
    public void checkCancelCheckout() {
        openCheckoutPage();
        checkoutYourInformationPage.clickCancelButton();
        assertEquals(cartPage.getTitle(),
                "Your Cart",
                "Возвращение на предыдущую страницу не выполнено");
    }
}
