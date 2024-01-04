import baseTests.BaseTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import services.BrowseService;
import services.CartService;
import services.CheckoutService;
import services.LogInService;
import testCases.LoginTestCases;

import java.time.Duration;

public class CheckoutTest extends BaseTest {

    private WebDriver driver;
    private CartService cartService;
    private LogInService loginService;

    private CheckoutService checkoutService;

    private WebDriverWait wait;
    private static Logger logger = LogManager.getLogger(CartTest.class);


    @BeforeEach
    public void init(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        this.loginService = new LogInService(driver,wait);
        loginService.performLogin("standard_user", "secret_sauce");

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/checkout_datas.csv", numLinesToSkip = 1)
    public void checkoutWithoutFillingForm() throws InterruptedException {
        logger.info("Perform checkout without filling form");
        test.log(Status.INFO, "Perform checkout without filling form");


//        Thread.sleep(3000);
        cartService = new CartService(driver, wait, logger);
        checkoutService = new CheckoutService(driver, wait, logger);
        cartService.clickCheckoutButton();
        checkoutService.checkoutWithoutFillingForm();
    }
}
