import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    public void testAddSingleProductToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-bike-light")));
        cartButton.click();

        WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));

        String badgeCount = cartBadge.getText();

        Assert.assertEquals(badgeCount, "1", "Cart count should be 1 after adding one product");
    }

    @Test
    public void testAddMultipleProductsToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-bike-light")));
        cartButton.click();
        WebElement cartButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-bolt-t-shirt")));
        cartButton2.click();
        WebElement cartButton3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-onesie")));
        cartButton3.click();

        WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));

        String badgeCount = cartBadge.getText();

        Assert.assertEquals(badgeCount, "3", "Cart count should be 3 after adding three products");
    }

    @Test
    public void testRemoveProductFromCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-bike-light")));
        cartButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));

        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("remove-sauce-labs-bike-light")));
        removeButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("shopping_cart_badge")));

        List<WebElement> cartBadges = driver.findElements(By.className("shopping_cart_badge"));
        Assert.assertTrue(cartBadges.isEmpty(), "The cart badge should not be visible after removing the last product.");

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
