package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutYourInformationPage extends BasePage {

    private static final By FIRST_NAME_FIELD = By.id("first-name"),
            LAST_NAME_FIELD = By.id("last-name"),
            POSTAL_CODE_FIELD = By.id("postal-code"),
            CONTINUE_BUTTON = By.id("continue"),
            CANCEL_BUTTON = By.id("cancel"),
            ERROR_MESSAGE = By.xpath("//h3[@data-test='error']"),
            TITLE = By.cssSelector("[data-test = title]");

    public CheckoutYourInformationPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutYourInformationPage open() {
        driver.get(BASE_URL + "checkout-step-one.html");
        return this;
    }

    @Override
    public CheckoutYourInformationPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public CartPage clickCancelButton() {
        driver.findElement(CANCEL_BUTTON).click();
        return new CartPage(driver);
    }

    public CheckoutOverviewPage fillingForm(String first_name, String last_name, String postal_code) {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(first_name);
        driver.findElement(LAST_NAME_FIELD).sendKeys(last_name);
        driver.findElement(POSTAL_CODE_FIELD).sendKeys(postal_code);
        driver.findElement(CONTINUE_BUTTON).click();
        return new CheckoutOverviewPage(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
}