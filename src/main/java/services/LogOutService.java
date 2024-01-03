package services;

import actions.LogoutActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogOutService {
    private LogoutActions logoutActions;
    public LogOutService(WebDriver driver, WebDriverWait wait){
        this.logoutActions = new LogoutActions(driver, wait);
    }

    public void performLogout(){
        logoutActions.clickMenuButton();
        logoutActions.clickLogoutButton();
    }
}
