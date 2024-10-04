package PageObject;

import TestFramework.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;

public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.ID, using = "onetrust-accept-btn-handler")
    public WebElement cookieConsent;

    @FindBy(how = How.CSS, using = ".companySearchTagInput__input")
    public WebElement searchBar;

    @FindBy(how = How.CSS, using = ".companySearchTagInput__button--search svg")
    public WebElement searchButton;

    @FindBy(how = How.CSS, using = ".companySearchTagInput__tagWrapper")
    public WebElement searchText;

    @FindBy(how = How.XPATH, using = "//table//td[contains(@class,'companyName')]")
    public List<WebElement> searchResults;

    @FindBy(how = How.CSS, using = ".companyResults__empty")
    public WebElement noResultsMessage;

    // *************************** Methods ***************************

    public void acceptCookies() {
        waitForElementTobeClickable(cookieConsent);
        cookieConsent.click();
        waitForElementToDisappear(cookieConsent);
    }

    public void doSearch(String text) {
        waitForElementTobeClickable(searchBar);
        searchBar.sendKeys(text);
        searchButton.click();
    }

    public boolean isTheElementPresent(List<WebElement> listOfElements, int rangeOfElements, String targetText) {
        boolean isPresent = false;
        if (listOfElements.size() == 0) {
            System.out.println("\nThere are no results to evaluate, list size is zero.");
            return false;
        } else {
            System.out.println("\n" + listOfElements.size() + "items were found.");

            for (int i = 0; i < rangeOfElements; i++) {
                String resultText = listOfElements.get(i).getText();
                if (resultText.trim().equals(targetText)) {
                    System.out.println(targetText + " is among the first " + rangeOfElements + " results.");
                    System.out.println(resultText + " is equal to " + targetText);
                    isPresent = true;
                    break;
                }
            }

            if (!isPresent) {
                System.out.println(targetText + " is not among the first " + rangeOfElements + " results.");
            }
            return isPresent;
        }
    }

    public void waitUntilResultIsLoaded() {
        waitForElementTobeVisible(searchText);
        waitForAttributeContains(searchBar, "class", "companySearchTagInput__input--dirty");

        if (!isResultsEmpty()) {
            waitForVisibilityOfAllElements(searchResults);
        } else {
            System.out.println("No results found!");
        }
    }

    public boolean isResultsEmpty() {
        try {
            return noResultsMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
