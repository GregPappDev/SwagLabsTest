package services;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BrowsePage;

import java.util.List;


public class BrowseService {

    final private BrowsePage browsePage;
    final private LogInService logInService;
    final private WebDriverWait wait;
    final private Logger logger;

    public BrowseService(WebDriver driver, WebDriverWait wait, Logger logger) {
        this.browsePage = new BrowsePage(driver);
        this.wait = wait;
        this.logInService = new LogInService(driver, wait);
        this.logger = logger;
    }

    public void AddFirstItemToCart(String userName, String password){
        logInService.performLogin(userName, password);
        if(browsePage.getAllAddToCartButtons().size() > 0){
            browsePage.getAllAddToCartButtons().get(0).click();
        }
        else {
            logger.info("There are no 'Add to Cart' buttons on the page");
        }
    }

    public int AddAllItemsToCart(String userName, String password){
        logInService.performLogin(userName, password);

        List<WebElement> allAddToCartButtons = browsePage.getAllAddToCartButtons();
        int counter = 0;

        if(allAddToCartButtons.isEmpty()){
            logger.info("There are no 'Add to Cart' buttons on the page");
            return counter;
        }

        for (WebElement button : allAddToCartButtons) {
            button.click();
            counter++;
        }
        return counter;
    }

}
