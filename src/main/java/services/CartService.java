package services;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.CheckOutPage;
import pages.CheckOutStepTwoPage;

import java.util.List;

public class CartService {
    final private CartPage cartPage;
    final private CheckOutPage checkOutPage;
    final private CheckOutStepTwoPage checkOutStepTwoPage;
    final private WebDriverWait wait;
    final private Logger logger;

    public CartService(WebDriver driver, WebDriverWait wait, Logger logger) {
        this.cartPage = new CartPage(driver);
        this.checkOutPage = new CheckOutPage(driver);
        this.checkOutStepTwoPage = new CheckOutStepTwoPage(driver);
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

    public void ClickContinueShopping(){
        cartPage.getContinueShoppingButton().click();
    }

    public void CompleteCheckoutWithCustomerInfo(String firstName, String lastName, String postalCode){
        StartCheckOutProcess();
        FillInCustomerInformation(firstName, lastName, postalCode);
        SubmitCustomerInformation();
    }

    private void StartCheckOutProcess(){
        List<WebElement> checkOutButton = checkOutPage.getCheckOutButton();
        if(checkOutButton.isEmpty()){
            logger.info("There is no 'Checkout' button on the page");
            return;
        }
        checkOutButton.get(0).click();
    }

    private void FillInCustomerInformation(String firstName, String lastName, String postalCode){
        checkOutStepTwoPage.getFirstName().sendKeys(firstName);
        checkOutStepTwoPage.getLastName().sendKeys(lastName);
        checkOutStepTwoPage.getPostalCode().sendKeys(postalCode);
    }

    private void SubmitCustomerInformation(){
        checkOutStepTwoPage.getContinueButton().click();
    }
}
