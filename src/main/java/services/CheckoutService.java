package services;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.CheckOutPage;

public class CheckoutService {
    private final CheckOutPage checkOutPage;
    private final WebDriverWait wait;
    private final Logger logger;

    public CheckoutService(WebDriver driver, WebDriverWait wait, Logger logger) {
        this.checkOutPage = new CheckOutPage(driver);
        this.wait = wait;
        this.logger = logger;
    }

    private void enterFirstName(String firstName) {
        checkOutPage.getFirstNameField().sendKeys(firstName);
    }

    private void enterLastName(String lastName) {
        checkOutPage.getLastNameField().sendKeys(lastName);
    }

    private void enterPostalCode(String postalCode) {
        checkOutPage.getPostalCodeField().sendKeys(postalCode);
    }

    private void clickContinueButton() {
        checkOutPage.getContinueButton().click();
    }

    private void clickCancelButton() {
        checkOutPage.getCancelButton().click();
    }

    public void checkoutWithoutFillingForm(){
        clickContinueButton();
    }

    public void cancelCheckout(){
        clickCancelButton();
    }
}
