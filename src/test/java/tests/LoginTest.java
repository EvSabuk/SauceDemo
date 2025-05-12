package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {


    @Test
    public void checkSuccessLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(),
                "Products",
                "Логин не выполнен");
    }

    @Test
    public void checkLoginWithEmailOnly() {
        loginPage.open();
        loginPage.login("standard-user", "");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "SO BAD");
    }

    @Test
    public void checkLoginWithInvalidCredentials() {
        loginPage.open();
        loginPage.login("1", "1");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "SO BAD");
    }

    @Test
    public void checkLoginWithEmptyValues() {
        loginPage.open();
        loginPage.login("", "");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "SO BAD");
    }
}