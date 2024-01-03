package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class CheckOutPage {

    public CheckOutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindAll({
            @FindBy(id = "checkout")
    })
    private List<WebElement> checkOutButton;
}
