package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SidebarMenu;
import services.LogInService;
import utilities.ElementChecker;

public class LoginTestCases {
    private LogInService logInService;
    private SidebarMenu sidebarMenu;
    private ElementChecker elementChecker;

    public LoginTestCases(WebDriver driver, WebDriverWait wait){
        this.logInService = new LogInService(driver,wait);
        this.sidebarMenu = new SidebarMenu(driver);
        this.elementChecker = new ElementChecker(driver);
    }

    public boolean Successful_Login(String username, String password){
        logInService.performLogin(username, password);
        return elementChecker.isElementPresent(sidebarMenu.getMenuButton());
    }
}
