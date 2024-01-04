package services;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BrowsePage;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BrowsePage;
import pages.CartPage;
import utilities.ElementChecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrowseService {

   
    final private BrowsePage browsePage;
    final private CartPage cartPage;
    final private ElementChecker elementChecker;
    final private LogInService logInService;
    final private WebDriverWait wait;
    final private Logger logger;

    public BrowseService(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        this.browsePage = new BrowsePage(driver);
        this.cartPage = new CartPage(driver);
        this.elementChecker = new ElementChecker(driver);
        this.logInService = new LogInService(driver, wait);
        this.logger = logger;
    }
  
    public void AddFirstItemToCart(){
        if(browsePage.getAllAddToCartButtons().size() > 0){
            browsePage.getAllAddToCartButtons().get(0).click();
        }
        else {
            logger.info("There are no 'Add to Cart' buttons on the page");
        }
    }

    public int AddAllItemsToCart(){
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


    public void clickOptionByText(String optionText) {
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[text()='" + optionText + "']")));
        option.click();
    }

    public boolean orderByNameAscending() {

        clickOptionByText("Name (A to Z)");

        List<String> productNamesSorted = new ArrayList<>();
        List<WebElement> productNamesOnPage = browsePage.getProductNames();
        browsePage.getProductNames().stream().map(WebElement::getText).forEach(productNamesSorted::add);
        Collections.sort(productNamesSorted);

        boolean sortedCorrectly = true;
        for (int i = 0; i < productNamesOnPage.size(); i++) {
            if (!productNamesOnPage.get(i).getText().equals(productNamesSorted.get(i))) {
                sortedCorrectly = false;
            }
        }

        return sortedCorrectly;
    }

    public boolean orderByNameDescending(String username) {

        clickOptionByText("Name (Z to A)");

        List<String> productNamesSorted = new ArrayList<>();
        List<WebElement> productNamesOnPage = browsePage.getProductNames();
        browsePage.getProductNames().stream().map(WebElement::getText).forEach(productNamesSorted::add);
        productNamesSorted.sort(Collections.reverseOrder());

        boolean sortedCorrectly = true;
        for (int i = 0; i < productNamesOnPage.size(); i++) {
            if (!productNamesOnPage.get(i).getText().equals(productNamesSorted.get(i)) && !username.equals("problem_user")) {
                sortedCorrectly = false;
            }
        }

        return sortedCorrectly;
    }

    public boolean orderByPriceAscending(String username) {

        clickOptionByText("Price (low to high)");

        List<String> productPricesAsString = new ArrayList<>();
        List<WebElement> productPricesOnPage = browsePage.getProductPrices();
        productPricesOnPage.stream().map(WebElement::getText).forEach(productPricesAsString::add);

        boolean sortedCorrectly = true;
        for (int i = 0; i < productPricesOnPage.size(); i++) {
            if (!productPricesOnPage.get(i).getText().equals("$" + sortByPrice(productPricesAsString, "regular").get(i).toString()) && !username.equals("problem_user")) {
                sortedCorrectly = false;
            }
        }

        return sortedCorrectly;
    }

    public boolean orderByPriceDescending(String username) {

        clickOptionByText("Price (high to low)");

        List<String> productPricesAsString = new ArrayList<>();
        List<WebElement> productPricesOnPage = browsePage.getProductPrices();
        productPricesOnPage.stream().map(WebElement::getText).forEach(productPricesAsString::add);

        boolean sortedCorrectly = true;
        for (int i = 0; i < productPricesOnPage.size(); i++) {

            if (!productPricesOnPage.get(i).getText().equals("$" + sortByPrice(productPricesAsString, "reverse").get(i).toString()) && !username.equals("problem_user")) {
                sortedCorrectly = false;
            }
        }

        return sortedCorrectly;
    }

    public boolean removeItemFromCart(String username) {

        if (!elementChecker.isElementPresent(browsePage.getSauceLabsBackpackAddToCart())) {
            browsePage.getSauceLabsBackpackRemoveFromCart().click();
        }

        browsePage.getSauceLabsBackpackAddToCart().click();
        browsePage.getCartButton().click();
        cartPage.getContinueShoppingButton().click();
        browsePage.getSauceLabsBackpackRemoveFromCart().click();

        return !username.equals("problem_user") ? elementChecker.isElementPresent(browsePage.getSauceLabsBackpackAddToCart()) : !elementChecker.isElementPresent(browsePage.getSauceLabsBackpackAddToCart());
    }

    private List<Double> sortByPrice(List<String> productPricesAsString, String order) {
        List<Double> productPricesAsNumbers = new ArrayList<>();

        for (String s : productPricesAsString) {
            productPricesAsNumbers.add(Double.parseDouble(s.substring(1)));
        }

        if (order.equals("reverse")) {
            productPricesAsNumbers.sort(Collections.reverseOrder());
        } else if (order.equals("regular")) {
            Collections.sort(productPricesAsNumbers);
        }

        return productPricesAsNumbers;
    }

}
