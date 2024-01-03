package services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BrowsePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BrowseService {

    private WebDriverWait wait;
    final private BrowsePage browsePage;

    public BrowseService(WebDriver driver, Duration waitTimeout) {
        this.wait = new WebDriverWait(driver, waitTimeout);
        this.browsePage = new BrowsePage(driver);
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

    public boolean orderByNameDescending() {

        clickOptionByText("Name (Z to A)");

        List<String> productNamesSorted = new ArrayList<>();
        List<WebElement> productNamesOnPage = browsePage.getProductNames();
        browsePage.getProductNames().stream().map(WebElement::getText).forEach(productNamesSorted::add);
        productNamesSorted.sort(Collections.reverseOrder());

        boolean sortedCorrectly = true;
        for (int i = 0; i < productNamesOnPage.size(); i++) {
            if (!productNamesOnPage.get(i).getText().equals(productNamesSorted.get(i))) {
                sortedCorrectly = false;
            }
        }

        return sortedCorrectly;
    }

    public boolean orderByPriceAscending() {

        clickOptionByText("Price (low to high)");

        List<String> productPricesSorted = new ArrayList<>();
        List<WebElement> productNamesOnPage = browsePage.getProductNames();
        browsePage.getProductNames().stream().map(WebElement::getText).forEach(productPricesSorted::add);

        productPricesSorted.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return extractInt(o1) - extractInt(o2);
            }

            int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                // return 0 if no digits found
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        });

        boolean sortedCorrectly = true;
        for (int i = 0; i < productNamesOnPage.size(); i++) {
            if (!productNamesOnPage.get(i).getText().equals(productPricesSorted.get(i))) {
                sortedCorrectly = false;
            }
        }

        return sortedCorrectly;
    }

    public boolean orderByPriceDescending() {

        clickOptionByText("Price (high to low)");

        List<String> productPricesSorted = new ArrayList<>();
        List<WebElement> productPricesOnPage = browsePage.getProductPrices();
        productPricesOnPage.stream().map(WebElement::getText).forEach(productPricesSorted::add);
        List<Double> productPricesAsNumbers = new ArrayList<>();


        for (int i = 0; i < productPricesSorted.size(); i++) {
            productPricesAsNumbers.add(Double.parseDouble(productPricesSorted.get(i).substring(1)));
        }


        productPricesAsNumbers.sort(Collections.reverseOrder());

        boolean sortedCorrectly = true;
        for (int i = 0; i < productPricesOnPage.size(); i++) {

            if (!productPricesOnPage.get(i).getText().equals("$" + productPricesAsNumbers.get(i).toString())) {
                sortedCorrectly = false;
            }
        }

        return sortedCorrectly;
    }
}
