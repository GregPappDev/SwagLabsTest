package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LogInPage;
import services.LogInService;
import services.LogOutService;
import utilities.ElementChecker;

public class LogoutTestCases {

    private LogOutService logOutService;
    private LogInService logInService;
    private LogInPage logInPage;
    private ElementChecker elementChecker;

    public LogoutTestCases(WebDriver driver, WebDriverWait wait){
        this.logOutService = new LogOutService(driver,wait);
        this.logInService = new LogInService(driver,wait);
        this.logInPage = new LogInPage(driver);
        this.elementChecker = new ElementChecker(driver);
    }

    public boolean performLogout(String username, String password){
        logInService.performLogin(username, password);
        logOutService.performLogout();
        return elementChecker.isElementPresent(logInPage.getLoginButton());
    }
}
