package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutOverviewPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-test = title]");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutOverviewPage open() {
        driver.get(BASE_URL + "fillingForm-step-two.html");
        return this;
    }

    @Override
    public CheckoutOverviewPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
}