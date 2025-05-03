import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorTest extends BaseTest {

    @Test
    public void checkLocator() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name"));
        driver.findElement(By.name("user-name"));
        driver.findElement(By.className("error-message-container"));
        driver.findElement(By.tagName("div"));
        logIn();
        driver.findElement(By.linkText("Sauce Labs Backpack"));
        driver.findElement(By.partialLinkText("Bolt"));
        driver.findElement(By.xpath("//button[@type='button']"));
        driver.findElement(By.xpath("//div[text()='29.99']"));
        driver.findElement(By.xpath("//button[contains(@data-test,'cart')]"));
        driver.findElement(By.xpath("//button[@type='button']" +
                "//ancestor::div[@id='menu_button_container']"));
        driver.findElement(By.xpath("//div[@id='header_container']" +
                "/descendant::div[@id='menu_button_container']"));
        driver.findElement(By.xpath("//div[@id='header_container']" +
                "/following::div[@class='inventory_item_price']"));
        driver.findElement(By.xpath("//button[@type='button']/parent::*"));
        driver.findElement(By.xpath("//div[@class='inventory_item_img']" +
                "/preceding::div[@class='inventory_item_price']"));
        driver.findElement(By.xpath("//a[@id='item_4_title_link' and @data-test='item-4-title-link']"));
        driver.findElement(By.cssSelector(".pricebar"));
        driver.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
        driver.findElement(By.cssSelector(".pricebar .inventory_item_price"));
        driver.findElement(By.cssSelector("#inventory_container"));
        driver.findElement(By.cssSelector("button"));
        driver.findElement(By.cssSelector("img.inventory_item_img"));
        driver.findElement(By.cssSelector("[data-test=inventory-item-price]"));
        driver.findElement(By.cssSelector("[class~='btn_small']"));
        driver.findElement(By.cssSelector("[data-test|='inventory']"));
        driver.findElement(By.cssSelector("[data-test^='inv']"));
        driver.findElement(By.cssSelector("[data-test$='price']"));
        driver.findElement(By.cssSelector("[src*='backpack']"));
    }
}