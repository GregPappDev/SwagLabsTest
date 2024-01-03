package services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BrowsePage;

import java.util.logging.Logger;

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
}
