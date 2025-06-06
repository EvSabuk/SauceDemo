package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

    public void openCheckoutPage() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addProduct("Sauce Labs Backpack")
                .addToCart()
                .isPageOpened();
        checkoutYourInformationPage.open()
                .isPageOpened();
    }

    @Test(testName = "Проверка ввода корректных пользовательских данных для покупки товара")
    @Description("Проверка покупки товара с коррекными пользовательскими данными")
    @Step("Ожидается открытие вкладки Overview")
    public void checkSuccessCheckout() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addProduct("Sauce Labs Backpack")
                .addToCart()
                .isPageOpened();
        checkoutYourInformationPage.open()
                .isPageOpened()
                .fillingForm("Evgeny", "Chrome", "6000")
                .isPageOpened();
        assertEquals(checkoutOverviewPage.getTitle(),
                "Checkout: Overview",
                "Пользовательские данные не верны");
    }

    @DataProvider
    public Object[][] checkoutData() {
        return new Object[][]{
                {"", "", "", "Error: First Name is required"},
                {"Evgeny", "", "6000", "Error: Last Name is required"},
                {"", "Chrome", "6000", "Error: First Name is required"},
                {"Evgeny", "Chrome", "", "Error: Postal Code is required"}
        };
    }

    @Test(dataProvider = "checkoutData")
    public void fillingFormFromParameters(String first_name, String last_name,
                                          String postal_code, String error_message) {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addProduct("Sauce Labs Backpack")
                .addToCart()
                .isPageOpened();
        checkoutYourInformationPage.open()
                .isPageOpened()
                .fillingForm(first_name, last_name, postal_code)
                .isPageOpened();
        assertEquals(checkoutYourInformationPage.getErrorMessage(),
                error_message,
                "Валидационное сообщение не найдено");
    }

    @Test(testName = "Проверка отправки формы с пустыми полями", dependsOnMethods = {"checkSuccessCheckout"})
    @Description("Проверка отображения валидации при попытке сохранения формы с пустыми данными")
    @Step("Ожидается появление валидационного сообщения")
    public void checkCheckoutEmptyFields() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addProduct("Sauce Labs Backpack")
                .addToCart()
                .isPageOpened();
        checkoutYourInformationPage.open()
                .isPageOpened()
                .fillingForm("", "", "")
                .isPageOpened();
        assertEquals(checkoutYourInformationPage.getErrorMessage(),
                "Error: First Name is required",
                "Валидационное сообщение не найдено");
    }

    @Test(testName = "Проверка отправки поля без фамилии", dependsOnMethods = {"checkSuccessCheckout"})
    @Description("Проверка отображения валидации при попытке сохранения формы без Last Name")
    @Step("Ожидается появление валидационного сообщения")
    public void checkCheckoutEmptyLastName() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addProduct("Sauce Labs Backpack")
                .addToCart()
                .isPageOpened();
        checkoutYourInformationPage.open()
                .isPageOpened()
                .fillingForm("Evgeny", "", "6000")
                .isPageOpened();
        assertEquals(checkoutYourInformationPage.getErrorMessage(),
                "Error: Last Name is required",
                "Валидационное сообщение не найдено");
    }

    @Test(testName = "Проверка отправки формы без имени", dependsOnMethods = {"checkSuccessCheckout"})
    @Description("Проверка отображения валидации при попытке сохранения формы без First Name")
    @Step("Ожидается появление валидационного сообщения")
    public void checkCheckoutEmptyFirstName() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addProduct("Sauce Labs Backpack")
                .addToCart()
                .isPageOpened();
        checkoutYourInformationPage.open()
                .isPageOpened()
                .fillingForm("", "Chrome", "6000")
                .isPageOpened();
        assertEquals(checkoutYourInformationPage.getErrorMessage(),
                "Error: First Name is required",
                "Валидационное сообщение не найдено");
    }

    @Test(testName = "Проверка отправик формы без почтового индекса", dependsOnMethods = {"checkSuccessCheckout"})
    @Description("Проверка отображения валидации при попытке сохранения формы без Postal Code")
    @Step("Ожидается появление валидационного сообщения")
    public void checkCheckoutEmptyPostalCode() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addProduct("Sauce Labs Backpack")
                .addToCart()
                .isPageOpened();
        checkoutYourInformationPage.open()
                .isPageOpened()
                .fillingForm("Evgeny", "Chrome", "")
                .isPageOpened();
        assertEquals(checkoutYourInformationPage.getErrorMessage(),
                "Error: Postal Code is required",
                "Валидационное сообщение не найдено");
    }

    @Test(testName = "Проверка отмены формы воода пользовательских данных", dependsOnMethods = {"checkSuccessCheckout"})
    @Description("Проверка отображения Cart страницы после нажатия кнопки cancel")
    @Step("Ожидается отображение Cart страницы")
    public void checkCancelCheckout() {
        loginPage.open()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addProduct("Sauce Labs Backpack")
                .addToCart()
                .isPageOpened();
        checkoutYourInformationPage.open()
                .isPageOpened()
                .clickCancelButton()
                .isPageOpened();
        assertEquals(cartPage.getTitle(),
                "Your Cart",
                "Возвращение на предыдущую страницу не выполнено");
    }
}