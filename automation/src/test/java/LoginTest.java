import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class LoginTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testSuccessfulLogin() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Login was not successful. The URL is incorrect.");
    }

    @Test
    public void testEmptyUsernameAndPassword() {
        driver.findElement(By.id("user-name")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();

        WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String expectedMessage = "Epic sadface: Username is required";

        Assert.assertEquals(errorMessage.getText(), expectedMessage, "The error message is incorrect.");
    }

    @Test
    public void testUnsuccessfulLogin() {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String expectedMessage = "Epic sadface: Sorry, this user has been locked out.";

        Assert.assertEquals(errorMessage.getText(), expectedMessage, "The error message is incorrect.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
