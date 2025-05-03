import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CartTest extends BaseTest {

    @Test
    public void checkLoginPurchase() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("[type=text]")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("[type=password]")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[id=login-button]")).click();
        String productPageTitle = driver.findElement(By.cssSelector(".title")).getText();
        softAssert.assertEquals(productPageTitle, "Products", "Логин не выполнен");
        String productName = driver.findElement(By.xpath("//a[@id='item_1_title_link']" +
                "/child::*")).getText();
        String ProductPrice = driver.findElement(By.xpath("//a[@id='item_1_title_link']" +
                "/following::div[@class='inventory_item_price'][1]")).getText();
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-bolt-t-shirt")).click();
        String isProductAdded = driver.findElement(By.cssSelector(".shopping_cart_badge")).getText();
        softAssert.assertEquals(isProductAdded, "1", "Товар не добавлен");
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        String cartPageTitle = driver.findElement(By.cssSelector(".title")).getText();
        softAssert.assertEquals(cartPageTitle, "Your Cart", "Корзина не открылась");
        String cartProductName = driver.findElement(By.xpath("//a[@id='item_1_title_link']" +
                "/child::*")).getText();
        String cartProductPrice = driver.findElement(By.xpath("//a[@id='item_1_title_link']" +
                "/following::div[@class='inventory_item_price'][1]")).getText();
        softAssert.assertEquals(productName, cartProductName);
        softAssert.assertEquals(ProductPrice, cartProductPrice);
        softAssert.assertAll();
    }
}