package services;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;

public class CartService {
    final private CartPage cartPage;
    final private WebDriverWait wait;
    final private Logger logger;

    public CartService(WebDriver driver, WebDriverWait wait, Logger logger) {
        this.cartPage = new CartPage(driver);
        this.wait = wait;
        this.logger = logger;
    }

    public int numberOfItemsInCart(){
        return cartPage.getListOfCartItems().size();
    }
}
