package services;

import actions.LoginActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInService {
    private LoginActions loginActions;

    public LogInService(WebDriver driver, WebDriverWait wait){this.loginActions = new LoginActions(driver,wait);}

    public void performLogin(String username, String password){
        loginActions.enterUsername(username);
        loginActions.enterPassword(password);
        loginActions.clickLoginButton();
    }

    public void performLoginWithoutCredentials(){loginActions.clickLoginButton();}

    public void performLoginWithoutUsername(String password){
        loginActions.enterPassword(password);
        loginActions.clickLoginButton();}

    public void performLoginWithoutPassword(String username){
        loginActions.enterUsername(username);
        loginActions.clickLoginButton();}

    public String getErrorMessage(){
        return loginActions.getErrorMessage();
    }
}
