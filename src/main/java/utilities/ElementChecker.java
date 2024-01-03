package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementChecker {
    private WebDriver driver;

    public ElementChecker(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(By by){
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementPresent(WebElement element){
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException | NullPointerException e) {
            return false;
        }
    }
}
