package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Properties;

public class WebDriverSetup {
    private static WebDriver driver;
    private static Properties properties;

    public static WebDriver getDriver(){
        properties = PropertyLoader.loadProperties();
        if (driver == null){
            System.setProperty(properties.getProperty("webDriver"), properties.getProperty("webDriverPath"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
