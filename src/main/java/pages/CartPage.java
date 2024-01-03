package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class CartPage {
    static String CART_PAGE_URL = "https://www.saucedemo.com/cart.html";

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "cart_item")
    private WebElement cartItem;

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement backPackCartItem;

    @FindBy(id = "remove-sauce-labs-bike-light")
    private WebElement sauceLabsBikeLightCartItem;

    @FindBy(id = "remove-sauce-labs-bolt-t-shirt")
    private WebElement sauceLabsBoltTShirtCartItem;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;
}
