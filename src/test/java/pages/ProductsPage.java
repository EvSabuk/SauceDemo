package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class ProductsPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-test = title]");
    private static final By CART = By.cssSelector(".shopping_cart_link");
    private static final String ADD_TO_CART_PATTERN = "//*[text() = '%s']" +
            "/ancestor::div[@class = 'inventory_item']//button";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        log.info("Get title for the 'Products' page");
        return driver.findElement(TITLE).getText();
    }

    @Step("Добавление товара с именем: {product} в корзину")
    public ProductsPage addProduct(String product) {
        log.info("Click add product for the '{}'", product);
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return this;
    }

    @Step("Нажатие на кнопку корзины")
    public CartPage addToCart() {
        log.info("Opening Cart page by clicking 'Cart' button");
        driver.findElement(CART).click();
        return new CartPage(driver);
    }

    @Override
    public ProductsPage open() {
        log.info("Opening 'Products' page");
        return this;
    }

    @Override
    public ProductsPage isPageOpened() {
        log.info("The 'Products' page was opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }
}