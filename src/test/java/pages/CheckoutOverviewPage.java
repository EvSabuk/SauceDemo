package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CheckoutOverviewPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-test = title]");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutOverviewPage open() {
        log.info("Opening 'Overview' page");
        driver.get(BASE_URL + "fillingForm-step-two.html");
        return this;
    }

    @Override
    public CheckoutOverviewPage isPageOpened() {
        log.info("The 'Overview' page was opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    public String getTitle() {
        log.info("Get title for the 'Overview' page");
        return driver.findElement(TITLE).getText();
    }
}