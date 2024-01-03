package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SidebarMenu;
import services.LogInService;
import services.LogOutService;
import utilities.ElementChecker;

public class LoginTestCases {
    private LogInService logInService;
    private LogOutService logOutService;
    private SidebarMenu sidebarMenu;
    private ElementChecker elementChecker;

    public LoginTestCases(WebDriver driver, WebDriverWait wait){
        this.logInService = new LogInService(driver,wait);
        this.logOutService = new LogOutService(driver,wait);
        this.sidebarMenu = new SidebarMenu(driver);
        this.elementChecker = new ElementChecker(driver);
    }

    public boolean successfulLogin(String username, String password){
        logInService.performLogin(username, password);
        boolean loginSuccessful = elementChecker.isElementPresent(sidebarMenu.getMenuButton());
        logOutService.performLogout();
        return loginSuccessful;
    }

    public boolean loginWithoutCredentials(){
        logInService.performLoginWithoutCredentials();
        return logInService.getErrorMessage().equals("Epic sadface: Username is required");
    }

    public boolean loginWithoutUsername(String password){
        logInService.performLoginWithoutUsername(password);
        return logInService.getErrorMessage().equals("Epic sadface: Username is required");
    }

    public boolean loginWithoutPassword(String username){
        logInService.performLoginWithoutPassword(username);
        return logInService.getErrorMessage().equals("Epic sadface: Password is required");
    }

    public boolean loginWithWrongCredentials(String username, String password){
        logInService.performLogin(username, password);
        return logInService.getErrorMessage()
                .equals("Epic sadface: Username and password do not match any user in this service");
    }

    public boolean loginWithWrongUsername(String username, String password){
        logInService.performLogin(username,password);
        return logInService.getErrorMessage()
                .equals("Epic sadface: Username and password do not match any user in this service");
    }

    public boolean loginWithWrongPassword(String username, String password){
        logInService.performLogin(username,password);
        return logInService.getErrorMessage()
                .equals("Epic sadface: Username and password do not match any user in this service");
    }
}
