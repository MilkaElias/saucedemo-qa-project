import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class CheckoutTest {
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


    @Test(description = "BUG TEST: Empty cart should not allow checkout - will FAIL until bug is fixed")
    public void testEmptyCartCannotCheckout(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link")));
        cartButton.click();

        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        Assert.assertTrue(cartItems.isEmpty(), "Cart should be empty for this test");

        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutButton.click();

        try {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("h3[data-test='error']")));
            Assert.assertEquals(errorMessage.getText(), "Error: Your cart is empty.",
                    "Expected error message not displayed.");

            // If we get here, the bug is fixed!
            System.out.println("BUG FIXED: Proper error message shown for empty cart");

        } catch (TimeoutException e) {
            String currentUrl = driver.getCurrentUrl();

            if (currentUrl.contains("checkout-step-one")) {
                // BUG CONFIRMED: We were redirected to checkout form with empty cart
                Assert.fail("BUG EXISTS: Empty cart was allowed to proceed to checkout form. " +
                        "Expected: Error message 'Your cart is empty'. " +
                        "Actual: Redirected to " + currentUrl);
            } else {
                Assert.fail("UNEXPECTED: Neither error message nor checkout redirect occurred. URL: " + currentUrl);
            }
        }
    }

    @Test
    public void testPositiveCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-sauce-labs-backpack")));
        addToCartButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));

        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.className("shopping_cart_link")));
        cartButton.click();

        WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("cart_item")));
        Assert.assertTrue(cartItem.isDisplayed(), "Product should be in cart");

        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutButton.click();

        driver.findElement(By.id("first-name")).sendKeys("Maya");
        driver.findElement(By.id("last-name")).sendKeys("John");
        driver.findElement(By.id("postal-code")).sendKeys("12345");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueButton.click();

        WebElement checkoutOverview = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("checkout_summary_container")));
        Assert.assertTrue(checkoutOverview.isDisplayed(), "Should be on checkout overview page");

        WebElement finishButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        finishButton.click();

        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        Assert.assertEquals(confirmationMessage.getText(), "Thank you for your order!",
                "Order should be completed successfully");
    }

    @Test
    public void testNegativeCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-cart-sauce-labs-backpack")));
        addToCartButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));

        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.className("shopping_cart_link")));
        cartButton.click();

        WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("cart_item")));
        Assert.assertTrue(cartItem.isDisplayed(), "Product should be in cart");

        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutButton.click();

        driver.findElement(By.id("first-name")).sendKeys("");
        driver.findElement(By.id("last-name")).sendKeys("John");
        driver.findElement(By.id("postal-code")).sendKeys("12345");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
        Assert.assertEquals(errorMessage.getText(), "Error: First Name is required", "Incorrect error message displayed.");

        WebElement checkoutForm = driver.findElement(By.className("checkout_info"));
        Assert.assertTrue(checkoutForm.isDisplayed(), "Should remain on the checkout form page.");



    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
