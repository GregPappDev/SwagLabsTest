import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import services.BrowseService;
import services.LogInService;
import services.LogOutService;

import java.time.Duration;

public class BrowseTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static BrowseService browseService;
    private static LogInService loginService;
    private static LogOutService logOutService;

    @BeforeAll
    public static void setUp() {
        System.setProperty("web-driver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(7));

        loginService = new LogInService(driver, wait);
        browseService = new BrowseService(driver, wait);
        logOutService = new LogOutService(driver, wait);
    }


    @AfterEach
    private void logout() {
        logOutService.performLogout();
    }

    @AfterAll
    private static void tearDown() {
        driver.close();
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginData.csv", numLinesToSkip = 1)
    public void orderByNameAscending(String username, String password) {
        loginService.performLogin(username, password);
        Assert.isTrue(browseService.orderByNameAscending(), "Order A to Z doesn't work correctly when logged in as " + username + ".");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginData.csv", numLinesToSkip = 1)
    public void orderByNameDescending(String username, String password) {
        loginService.performLogin(username, password);
        Assert.isTrue(browseService.orderByNameDescending(), "Order Z to A doesn't work correctly when logged in as " + username + ".");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginData.csv", numLinesToSkip = 1)
    public void orderByPriceAscending(String username, String password) {
        loginService.performLogin(username, password);
        Assert.isTrue(browseService.orderByPriceAscending(), "Order by price low to high doesn't work correctly when logged in as " + username + ".");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginData.csv", numLinesToSkip = 1)
    public void orderByPriceDescending(String username, String password) {
        loginService.performLogin(username, password);
        Assert.isTrue(browseService.orderByPriceDescending(), "Order by price high to low doesn't work correctly when logged in as " + username + ".");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginData.csv", numLinesToSkip = 1)
    public void removeItemFromCart(String username, String password) {
        loginService.performLogin(username, password);
        Assert.isTrue(browseService.removeItemFromCart(), "Item can't be removed from cart on the browse page when logged in as " + username + ".");
    }

}

