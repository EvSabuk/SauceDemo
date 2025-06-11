package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage {

    private static final By USER_NAME_FIELD = By.id("user-name"),
            PASSWORD_FIELD = By.id("password"),
            LOGIN_BUTTON = By.id("login-button"),
            ERROR_MESSAGE = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы Login Page")
    public LoginPage open() {
        log.info("Opening 'Login' page");
        driver.get(BASE_URL);
        return this;
    }

    @Override
    public LoginPage isPageOpened() {
        log.info("The 'Login' page was opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    @Step("Вход в систему с имненем пользователя: {user} и паролем {password}")
    public ProductsPage login(String user, String password) {
        log.info("Login with credential: {}, {}", user, password);
        driver.findElement(USER_NAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }

    public String getErrorMessage() {
        log.info("Checking validation message on the 'Login' tab");
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}