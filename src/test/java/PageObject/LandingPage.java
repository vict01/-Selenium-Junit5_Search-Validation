package PageObject;

import TestFramework.BasePage;
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

    @FindBy(how = How.XPATH, using = "//table//td[contains(@class,'companyName')]")
    public List<WebElement> searchResults;

    // *************************** Methods definition ***************************

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

    public boolean isTheElementPresent(List<WebElement> listOfElements, int RangeOfElements, String targetText) {
        boolean isPresent = false;
        if (listOfElements.size() == 0) {
            System.out.println("\nThere's no result to evaluate, size of the list is zero");
            return false;
        } else {
            System.out.println("\nWere found " + listOfElements.size() + " elements");

            for (int i = 0; i < RangeOfElements; i++) {
                String resultText = listOfElements.get(i).getText();
                if (resultText.trim().equals(targetText)) {
                    System.out.println(targetText + " is within the top " + RangeOfElements + " results");
                    System.out.println(resultText + " is equal to " + targetText);
                    isPresent = true;
                    break;
                }
            }

            if (!isPresent) {
                System.out.println(targetText + " is not within the top " + RangeOfElements + " results");
            }
            return isPresent;
        }
    }

}
