import actions.LoginActions;
import baseTests.BaseTest;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import services.LogInService;
import services.LogOutService;
import testCases.LoginTestCases;

public class LogInTest extends BaseTest {
    private LogInService logInService;
    private LoginActions loginActions;
    private LogOutService logOutService;
    private LoginTestCases loginTestCases;
    @BeforeEach
    public void initComponents(){

        this.logInService = new LogInService(driver,wait);
        this.loginActions = new LoginActions(driver,wait);
        this.loginTestCases = new LoginTestCases(driver, wait);
        this.logOutService = new LogOutService(driver, wait);
    }

    @AfterEach
    public void tearDown(){
    }

    @Order(2)
    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginData.csv", numLinesToSkip = 1)
    public void login_With_Valid_Username_And_Password(String username, String password){
        logger.info("Perform a valid login with username and password.");
        test.log(Status.INFO,"Perform a valid login with username and password.");

        boolean loginSuccessful = loginTestCases.Successful_Login(username, password);
        Assertions.assertTrue(loginSuccessful, "Login was not Successful");

        logOutService.performLogout();
    }

    @Order(1)
    @Test
    public void login_Without_Credentials(){
        logger.info("Perform invalid login without credentials");
        test.log(Status.INFO, "Perform invalid login without credentials");
        logInService.performLoginWithoutCredentials();

        String errorMessage = loginActions.getErrorMessage();
        Assertions.assertEquals("Epic sadface: Username is required", errorMessage, "Incorrect error message");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginWithoutUsername.csv", numLinesToSkip = 1)
    public void login_Without_Username(String password){
        logger.info("Perform login without username");
        test.log(Status.INFO, "Perform login without username");
        logInService.performLoginWithoutUsername(password);

        String errorMessage = loginActions.getErrorMessage();
        Assertions.assertEquals("Epic sadface: Username is required", errorMessage, "Incorrect error message");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testData/loginWithout_Password.csv", numLinesToSkip = 1)
    public void login_Without_Password(String username){
        logger.info("Perform login without password");
        test.log(Status.INFO, "Perform login without password");
        logInService.performLoginWithoutPassword(username);

        String errorMessage = loginActions.getErrorMessage();
        Assertions.assertEquals("Epic sadface: Password is required", errorMessage, "Incorrect error message");
    }
}
