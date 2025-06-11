package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CartPage extends BasePage {

    private static final By TITLE = By.cssSelector("[data-test = title]"),
            CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage open() {
        log.info("Opening 'Cart' page");
        driver.get(BASE_URL + "cart.html");
        return this;
    }

    @Override
    public CartPage isPageOpened() {
        log.info("The 'Cart' page was opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    public boolean isProductInCart(String product) {
        log.info("Product '{}' is shown in the cart.", product);
        return driver.findElement(By.xpath(String.format("//div[@class='cart_item']//*[text()='%s']",
                product))).isDisplayed();
    }

    public String getProductFromCart(int index) {
        log.info("Get product with index '{}'", index);
        return driver.findElements(By.cssSelector(".inventory_item_name")).get(index).getText();
    }

    public ArrayList<String> getProductsName() {
        log.info("Get list of all products");
        List<WebElement> allProductsElements = driver.findElements(By.cssSelector(".inventory_item_name"));
        ArrayList<String> names = new ArrayList<>();
        for (WebElement product : allProductsElements) {
            names.add(product.getText());
        }
        return names;
    }

    public double getProductPrice(String product) {
        log.info("Get price for the '{}' product", product);
        return Double.parseDouble(driver.findElement(
                        By.xpath(String.format(
                                "//*[text() = '%s']/ancestor::div[@class='cart_item']//" +
                                        "*[@class = 'inventory_item_price']", product)))
                .getText().replace("$", ""));
    }

    public String getTitle() {
        log.info("Get title for the 'Cart' page");
        return driver.findElement(TITLE).getText();
    }

    public ProductsPage continueShopping() {
        log.info("Click on the 'continue' button on the 'Cart' page");
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
        return new ProductsPage(driver);
    }

    public CartPage removeProduct(String product) {
        log.info("Click on the 'remove' button on the 'Cart' page for the '{}' product", product);
        driver.findElement(By.xpath(String.format("//div[@class='cart_item'][.//div[text() = '%s']]" +
                "//button[text()='Remove']", product))).click();
        return this;
    }

    public boolean checkEmptyState() {
        log.info("Checking that cart page is empty");
        return driver.findElements(By.xpath("//div[@class='cart_item']")).isEmpty();
    }
}