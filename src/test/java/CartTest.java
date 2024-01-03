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
import utilities.WebDriverSetup;

import java.time.Duration;

import static utilities.WebDriverSetup.properties;

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
    public void AddOneItemToCartSuccessfully(){

        browseService.AddFirstItemToCart("standard_user", "secret_sauce");
        driver.get("https://www.saucedemo.com/cart.html");
        int numberOfItemsInCart = cartService.numberOfItemsInCart();
        Assertions.assertEquals(1, numberOfItemsInCart);
    }

    @Order(2)
    @Test
    public void AddAllItemsToCartSuccessfully(){
        //driver.get("https://www.saucedemo.com/");
        int numberOfItemsAdded = browseService.AddAllItemsToCart("standard_user", "secret_sauce");
        driver.get("https://www.saucedemo.com/cart.html");
        int numberOfItemsInCart = cartService.numberOfItemsInCart();
        Assertions.assertEquals(numberOfItemsAdded, numberOfItemsInCart);
    }

    @Order(3)
    @Test
    public void RemoveItemFromCartSuccessfully(){
        //driver.get("https://www.saucedemo.com/");
        browseService.AddFirstItemToCart("standard_user", "secret_sauce");
        driver.get("https://www.saucedemo.com/cart.html");
        cartService.removeItemFromCart();
        int numberOfItemsInCart = cartService.numberOfItemsInCart();
        Assertions.assertEquals(0, numberOfItemsInCart);
    }



}
