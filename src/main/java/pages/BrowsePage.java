package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@Getter
public class BrowsePage {

    public BrowsePage(WebDriver driver){
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
    private Select sortDropdown;

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

}
