package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-test = title]"),
            CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage open() {
        driver.get(BASE_URL + "cart.html");
        return this;
    }

    @Override
    public CartPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    public boolean isProductInCart(String product) {
        return driver.findElement(By.xpath(String.format("//div[@class='cart_item']//*[text()='%s']",
                product))).isDisplayed();
    }

    public String getProductFromCart(int index) {
        return driver.findElements(By.cssSelector(".inventory_item_name")).get(index).getText();
    }

    public ArrayList<String> getProductsName() {
        List<WebElement> allProductsElements = driver.findElements(By.cssSelector(".inventory_item_name"));
        ArrayList<String> names = new ArrayList<>();
        for (WebElement product : allProductsElements) {
            names.add(product.getText());
        }
        return names;
    }

    public double getProductPrice(String product) {
        return Double.parseDouble(driver.findElement(
                        By.xpath(String.format(
                                "//*[text() = '%s']/ancestor::div[@class='cart_item']//" +
                                        "*[@class = 'inventory_item_price']", product)))
                .getText().replace("$", ""));
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public ProductsPage continueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
        return new ProductsPage(driver);
    }

    public CartPage removeProduct(String product) {
        driver.findElement(By.xpath(String.format("//div[@class='cart_item'][.//div[text() = '%s']]" +
                "//button[text()='Remove']", product))).click();
        return this;
    }

    public boolean checkEmptyState() {
        return driver.findElements(By.xpath("//div[@class='cart_item']")).isEmpty();
    }
}