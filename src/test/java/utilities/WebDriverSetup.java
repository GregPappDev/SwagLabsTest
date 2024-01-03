package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;

public class WebDriverSetup {
    private static WebDriver driver;
    private static Properties properties;

    public static WebDriver getDriver(){
        properties = PropertyLoader.loadProperties();
        if (driver == null){
            System.setProperty(properties.getProperty("webDriver"), properties.getProperty("webDriverPath"));
            driver = new ChromeDriver();
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
