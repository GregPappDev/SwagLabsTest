import baseTests.BaseTest;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import testCases.LogoutTestCases;

public class LogOutTest extends BaseTest {

    private LogoutTestCases logoutTestCases;

    @BeforeEach
    public void initComponents(){
        this.logoutTestCases = new LogoutTestCases(driver, wait);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginData.csv", numLinesToSkip = 1)
    public void successfulLogout(String username, String password){
        logger.info("Perform logout with logged in user.");
        test.log(Status.INFO,"Perform logout with logged in user.");

        boolean successfulLogout = logoutTestCases.performLogout(username, password);
        Assertions.assertTrue(successfulLogout, "Logout was not successful");
    }
}
