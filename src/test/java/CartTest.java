import baseTests.BaseTest;
import baseTests.CartBaseTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import services.BrowseService;
import services.CartService;
import services.LogInService;


public class CartTest extends CartBaseTest {

    private BrowseService browseService;
    private LogInService logInService;
    private CartService cartService;
    private static  Logger logger = LogManager.getLogger(CartTest.class);

    @BeforeEach
    public void init(){
        browseService = new BrowseService(driver, wait, logger);
        cartService = new CartService(driver, wait, logger);
        logInService = new LogInService(driver, wait);
        logInService.performLogin(testProperties.getProperty("CORRECT_USER"), testProperties.getProperty("CORRECT_PASSWORD"));
    }

    @Order(1)
    @Test
    @DisplayName("Add one item to cart")
    public void AddOneItemToCartSuccessfully(){
        // Arrange

        // Act
        browseService.AddFirstItemToCart();

        //Assert
        driver.get(testProperties.getProperty("CART_URL"));
        int numberOfItemsInCart = cartService.numberOfItemsInCart();
        Assertions.assertEquals(1, numberOfItemsInCart);
    }

    @Order(2)
    @Test
    @DisplayName("Add all items on page to cart")
    public void AddAllItemsToCartSuccessfully(){
        // Arrange
        int numberOfItemsAdded = browseService.AddAllItemsToCart();
        driver.get(testProperties.getProperty("CART_URL"));

        // Act
        int numberOfItemsInCart = cartService.numberOfItemsInCart();

        // Assert
        Assertions.assertEquals(numberOfItemsAdded, numberOfItemsInCart);
    }

    @Order(3)
    @Test
    @DisplayName("Remove item from cart")
    public void RemoveItemFromCartSuccessfully(){
        // Arrange
        browseService.AddFirstItemToCart();
        driver.get(testProperties.getProperty("CART_URL"));

        // Act
        cartService.removeItemFromCart();

        // Assert
        int numberOfItemsInCart = cartService.numberOfItemsInCart();
        Assertions.assertEquals(0, numberOfItemsInCart);
    }

    @Order(4)
    @Test
    @DisplayName("Navigate to Product Page after clicking 'Continue Shopping'")
    public void NavigateToProductsPage_AfterClickingContinueShopping(){
        // Arrange
        driver.get(testProperties.getProperty("CART_URL"));

        // Act
        cartService.ClickContinueShopping();

        // Assert
        String pageTitle = driver.findElement(By.className("title")).getText();
        Assertions.assertEquals("Products", pageTitle);
    }

}
