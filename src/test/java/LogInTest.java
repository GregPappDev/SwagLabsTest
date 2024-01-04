import baseTests.BaseTest;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import testCases.LoginTestCases;

public class LogInTest extends BaseTest {
    private LoginTestCases loginTestCases;
    @BeforeEach
    public void initComponents(){

        this.loginTestCases = new LoginTestCases(driver, wait);
    }

    @Order(2)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginData.csv", numLinesToSkip = 1)
    public void login_With_Valid_Username_And_Password(String username, String password){
        logger.info("Perform a valid login with username and password.");
        test.log(Status.INFO,"Perform a valid login with username and password.");

        boolean loginSuccessful = loginTestCases.successfulLogin(username, password);
        Assertions.assertTrue(loginSuccessful, "Login was not successful");
    }

    @Order(1)
    @Test
    public void login_Without_Credentials(){
        logger.info("Perform invalid login without credentials");
        test.log(Status.INFO, "Perform invalid login without credentials");

        boolean failedLogin = loginTestCases.loginWithoutCredentials();
        Assertions.assertTrue(failedLogin, "Incorrect error message");
    }

    @Order(3)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginWithoutUsername.csv", numLinesToSkip = 1)
    public void login_Without_Username(String password){
        logger.info("Perform login without username");
        test.log(Status.INFO, "Perform login without username");

        boolean loginFailedWithoutUsername = loginTestCases.loginWithoutUsername(password);
        Assertions.assertTrue(loginFailedWithoutUsername, "Invalid error message");
    }

    @Order(4)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginWithout_Password.csv", numLinesToSkip = 1)
    public void login_Without_Password(String username){
        logger.info("Perform login without password");
        test.log(Status.INFO, "Perform login without password");

        boolean loginFailedWithoutPassword = loginTestCases.loginWithoutUsername(username);
        Assertions.assertTrue(loginFailedWithoutPassword, "Invalid error message");
    }
}
