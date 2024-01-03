package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SidebarMenu;

public class LogoutActions {

    private SidebarMenu sidebarMenu;
    private WebDriverWait wait;

    public LogoutActions(WebDriver driver, WebDriverWait wait){
        this.sidebarMenu = new SidebarMenu(driver);
        this.wait = wait;
    }

    public void clickMenuButton(){
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(sidebarMenu.getMenuButton()));
        menuButton.click();
    }

    public void clickLogoutButton(){
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(sidebarMenu.getLogout()));
        logoutButton.click();
    }
}
