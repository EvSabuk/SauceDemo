import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{


    @Test
    public void checkSuccessLogin() {
    driver.get("https://www.saucedemo.com/");
    driver.findElement(By.id("user-name")).sendKeys("standard-user");
    driver.findElement(By.id("password")).sendKeys("secret-sauce");
    driver.findElement(By.id("login-button")).click();
    String title = driver.findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals(title, "Products", "Логин не выполнен");

    }

    @Test
    public void checkLoginWithEmptyPassword() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.xpath
                ("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(errorMessage, "Epic sadface: Username is required");
    }

    @Test
    public void checkLoginWithInvalidCredentials() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("1");
        driver.findElement(By.id("password")).sendKeys("1");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.xpath
                ("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(errorMessage,
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void checkLoginWithSpaces() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("               ");
        driver.findElement(By.id("password")).sendKeys("            ");
        driver.findElement(By.id("login-button")).click();
        String errorMessage = driver.findElement(By.xpath
                ("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(errorMessage,
                "Epic sadface: Username and password do not match any user in this service");
    }


}

