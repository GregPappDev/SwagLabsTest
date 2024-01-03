package services;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;

import java.util.List;

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

    public void removeItemFromCart(){
        List<WebElement> itemsInCart = cartPage.getListOfRemoveButtons();
        if(itemsInCart.isEmpty()){
            logger.info("The cart is empty, not items could have been removed");
            return;
        }

        itemsInCart.get(0).click();
    }
}
