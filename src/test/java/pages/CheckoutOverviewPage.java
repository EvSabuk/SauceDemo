package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-test = title]");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "fillingForm-step-two.html");
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
}
