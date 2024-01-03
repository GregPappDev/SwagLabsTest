package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LogInPage;

public class LoginActions {
    private LogInPage logInPage;
    private WebDriverWait wait;

    public LoginActions(WebDriver driver, WebDriverWait wait) {
        this.logInPage = new LogInPage(driver);
        this.wait = wait;
    }

    public void enterUsername(String usernameOrEmail){
        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(logInPage.getUsernameField()));
        usernameField.sendKeys(usernameOrEmail);
    }

    public void enterPassword(String password){
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(logInPage.getPasswordField()));
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(logInPage.getLoginButton()));
        loginButton.click();
    }

    public String getErrorMessage(){
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOf(logInPage.getLoginErrorMessage()));
        return errorMessageElement.getText();
    }
}
