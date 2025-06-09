package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-test = title]");
    private static final By CART = By.cssSelector(".shopping_cart_link");
    private static final String ADD_TO_CART_PATTERN = "//*[text() = '%s']" +
            "/ancestor::div[@class = 'inventory_item']//button";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Добавление товара с именем: {product} в корзину")
    public ProductsPage addProduct(String product) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return this;
    }

    @Step("Нажатие на кнопку корзины")
    public CartPage addToCart() {
        driver.findElement(CART).click();
        return new CartPage(driver);
    }

    @Override
    public ProductsPage open() {
        return this;
    }

    @Override
    public ProductsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }
}