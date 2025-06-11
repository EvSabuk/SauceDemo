package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Проверка входа в систему с корректными данными")
    @Epic("Авторизация")
    @Feature("Страница логина")
    @Story("Позитивный логин")
    public void checkSuccessLogin() {
        loginPage.open()
                .isPageOpened()
                .login(user, password);
        assertEquals(productsPage.getTitle(),
                "Products",
                "Логин не выполнен");
    }

    @Test(testName = "Проверка входа в систему без пароля")
    @Description("Проверка входа в систему с коррекными пользовательскими данными")
    @Step("Ожидается открытие вкладки Products")
    public void checkLoginWithEmailOnly() {
        loginPage.open()
                .isPageOpened()
                .login(user, "");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "SO BAD");
    }

    @Test(testName = "Проверка входа в систему с невалидными данными")
    @Description("Проверка отображения валидации при попытке входа в систему с не сущесутвующим пользователем ")
    @Step("Ожидается появление валидационного сообщения")
    public void checkLoginWithInvalidCredentials() {
        loginPage.open()
                .isPageOpened()
                .login("1", "1");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "SO BAD");
    }

    @Test(retryAnalyzer = Retry.class)
    @Description("Проверка отображения валидации при попытке входа в систему с пустыми полями ")
    @Step("Ожидается появление валидационного сообщения")
    public void checkLoginWithEmptyValues() {
        loginPage.open()
                .isPageOpened()
                .login("", "");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "SO BAD");
    }
}