package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class BrowsePage {

    static String BROWSE_PAGE_URL = "https://www.saucedemo.com/inventory.html";

    public BrowsePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement sauceLabsBackpackAddToCart;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement sauceLabsBikeLightAddToCart;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    private WebElement sauceLabsBoltTShirtAddToCart;

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement sauceLabsBackpackRemoveFromCart;

    @FindBy(id = "remove-sauce-labs-bike-light")
    private WebElement sauceLabsBikeLightRemoveFromCart;

    @FindBy(id = "remove-sauce-labs-bolt-t-shirt")
    private WebElement sauceLabsBoltTShirtRemoveFromCart;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenuButton;

    @FindBy(id = "shopping_cart_container")
    private WebElement cartButton;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(id = "logout_sidebar_link")
    private WebElement sidebarLogoutButton;

    @FindBy(id = "reset_sidebar_link")
    private WebElement sidebarResetAppButton;

    @FindAll({
            @FindBy(className = "inventory_item_name")
    })
    private List<WebElement> ProductNames;

    @FindAll({
            @FindBy(className = "inventory_item_price")
    })
    private List<WebElement> ProductPrices;

    @FindAll({
            @FindBy(xpath = "//*[contains(text(), 'Add to cart')]")
    })
    private List<WebElement> allAddToCartButtons;

}
