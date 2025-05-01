import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;


public class LocatorTest extends BaseTest{

    @Test
    public void checkLocator() {
    driver.get("https://www.saucedemo.com/");
    driver.findElement(By.id("user-name"));
    driver.findElement(By.name("user-name"));
    driver.findElement(By.className("error-message-container"));
    driver.findElement(By.tagName("div"));
    }

}
