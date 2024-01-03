import dev.failsafe.internal.util.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import services.BrowseService;
import services.CartService;

import java.time.Duration;

public class CartTest {

    private WebDriver driver;
    private BrowseService browseService;
    private CartService cartService;
    private WebDriverWait wait;
    private static  Logger logger = LogManager.getLogger(CartTest.class);

    @BeforeEach
    public void init(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        browseService = new BrowseService(driver, wait, logger);
        cartService = new CartService(driver, wait, logger);
    }

    @Test
    public void AddOneItemToCartSuccessfully(){
        driver.get("https://www.saucedemo.com/");
        browseService.AddFirstItemToCart("standard_user", "secret_sauce");
        driver.get("https://www.saucedemo.com/cart.html");
        int numberOfItemsInCart = cartService.numberOfItemsInCart();
        Assertions.assertEquals(1, numberOfItemsInCart);
    }


    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
