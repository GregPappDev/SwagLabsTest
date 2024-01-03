package services;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BrowsePage;

import java.util.List;


public class BrowseService {

    final private BrowsePage _browsePage;
    final private WebDriverWait _wait;
    final private Logger _logger;

    public BrowseService(WebDriver driver, WebDriverWait wait, Logger logger) {
        _browsePage = new BrowsePage(driver);
        _wait = wait;
        _logger = logger;
    }

    public void AddFirstItemToCart(){
        if(_browsePage.getAllAddToCartButtons().size() > 0){
            _browsePage.getAllAddToCartButtons().get(0).click();
        }
        else {
            _logger.info("There are no 'Add to Cart' buttons on the page");
        }
    }

    public int AddAllItemsToCart(){
        List<WebElement> allAddToCartButtons = _browsePage.getAllAddToCartButtons();
        int counter = 0;

        if(allAddToCartButtons.isEmpty()){
            _logger.info("There are no 'Add to Cart' buttons on the page");
            return counter;
        }

        for (WebElement button : allAddToCartButtons) {
            button.click();
            counter++;
        }
        return counter;
    }


}
