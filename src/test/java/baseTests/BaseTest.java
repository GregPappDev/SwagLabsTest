package baseTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ExtentManager;
import utilities.PropertyLoader;
import utilities.WebDriverSetup;

import java.time.Duration;
import java.util.Properties;

@TestInstance(Lifecycle.PER_CLASS)
public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ExtentReports extent;
    protected ExtentTest test;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeAll
    public void baseSetUp() {
        Properties testProperties = PropertyLoader.loadProperties();
        String reportPath = testProperties.getProperty("reportPath");
        extent = ExtentManager.getInstance(reportPath);
        test = ExtentManager.createTest(getClass().getSimpleName());

        driver = WebDriverSetup.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(testProperties.getProperty("URL"));

        logger.info("Setting up the test");
        test.log(Status.INFO, "Setting up the test");
    }

    @AfterAll
    public void baseTearDown() {
        test.log(Status.INFO, "Web driver now closing");
        logger.info("Web driver now closing");

        WebDriverSetup.closeDriver();

        test.log(Status.INFO, "Web driver closed");
        logger.info("Web driver closed");

        ExtentManager.addTestToReport(extent, test);
    }
}
