import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import services.BrowseService;
import services.LogInService;

import java.time.Duration;

public class BrowseTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static BrowseService browseService;
    private static LogInService loginService;
    @BeforeAll
    public static void setUp() {
        System.setProperty("web-driver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        Duration waitTimeout = Duration.ofSeconds(7);

        //loginService = new LogInService(driver);
        browseService = new BrowseService(driver, waitTimeout);
        //loginService.performLogin(username, password);
    }

    @AfterAll
    public static void baseTearDown() {
        driver.close();
    }

    @Test
    public void orderByNameAscending(){

        Assert.isTrue(browseService.orderByNameAscending(),"asd");
    }

    @Test
    public void orderByNameDescending(){

        Assert.isTrue(browseService.orderByNameDescending(),"asd");
    }

    @Test
    public void orderByPriceAscending(){

        Assert.isTrue(browseService.orderByPriceAscending(),"asd");
    }

    @Test
    public void orderByPriceDescending(){

        Assert.isTrue(browseService.orderByPriceDescending(),"asd");
    }
}

