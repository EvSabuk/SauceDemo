package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
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
        log.info("Opening 'Checkout Your Information' page");
        driver.get(BASE_URL + "checkout-step-one.html");
        return this;
    }

    @Override
    public CheckoutYourInformationPage isPageOpened() {
        log.info("The 'Checkout Your Information' page was opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    public String getErrorMessage() {
        log.info("Checking validation message on the 'Checkout Your Information' tab");
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public CartPage clickCancelButton() {
        log.info("Click on the 'Cancel' button on the 'Checkout Your Information' page");
        driver.findElement(CANCEL_BUTTON).click();
        return new CartPage(driver);
    }

    public CheckoutOverviewPage fillingForm(String first_name, String last_name, String postal_code) {
        log.info("Filling form with next data: " +
                "First name '{}', " +
                "Last name '{}', " +
                "Postal code '{}' and click 'continue'", first_name, last_name, postal_code);
        driver.findElement(FIRST_NAME_FIELD).sendKeys(first_name);
        driver.findElement(LAST_NAME_FIELD).sendKeys(last_name);
        driver.findElement(POSTAL_CODE_FIELD).sendKeys(postal_code);
        driver.findElement(CONTINUE_BUTTON).click();
        return new CheckoutOverviewPage(driver);
    }

    public String getTitle() {
        log.info("Get title for the 'Checkout Your Information' page");
        return driver.findElement(TITLE).getText();
    }
}