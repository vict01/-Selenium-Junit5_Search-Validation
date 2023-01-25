package TestBench;

import PageObject.LandingPage;
import TestFramework.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HappyPathTest extends BaseTest {

    String textToBeSearched = "statista";
    String expectedTextToBeFound = "Statista GmbH";
    int amountOfElementsTobeEvaluated = 5;

    LandingPage methods;

    @Before
    public void prepare() {
        beforeClass();
        methods = new LandingPage(getDriver());
    }

    @Test
    @DisplayName("Happy Path | expected text is in the top 5")
    public void happyPath() {
        methods.navigateToTheApp(urlApp);
        methods.acceptCookies();
        methods.doSearch(textToBeSearched);
        methods.waitUntilPageIsLoaded(3);
        boolean isPresent = methods.isTheElementPresent(methods.searchResults, amountOfElementsTobeEvaluated, expectedTextToBeFound);
        assertTrue(isPresent, expectedTextToBeFound + " is not within the top " + amountOfElementsTobeEvaluated + " results");
    }

    @Test
    @DisplayName("Negative test | expected text is not in the top 4")
    public void negativeTest() {
        expectedTextToBeFound = "Statista Inc";
        amountOfElementsTobeEvaluated = 4;
        methods.navigateToTheApp(urlApp);
        methods.acceptCookies();
        methods.doSearch(textToBeSearched);
        methods.waitUntilPageIsLoaded(3);
        boolean isPresent = methods.isTheElementPresent(methods.searchResults, amountOfElementsTobeEvaluated, expectedTextToBeFound);
        assertTrue(isPresent, expectedTextToBeFound + " is not within the top " + amountOfElementsTobeEvaluated + " results");
    }

    @Test
    @DisplayName("Negative test | expected text is not in the top 5")
    public void negativeTest2() {
        textToBeSearched = "siemens";
        methods.navigateToTheApp(urlApp);
        methods.acceptCookies();
        methods.doSearch(textToBeSearched);
        methods.waitUntilPageIsLoaded(3);
        boolean isPresent = methods.isTheElementPresent(methods.searchResults, amountOfElementsTobeEvaluated, expectedTextToBeFound);
        assertFalse(isPresent, expectedTextToBeFound + " is not within the top " + amountOfElementsTobeEvaluated + " results");
    }

    @Test
    @DisplayName("Negative test | No result found")
    public void negativeTest3() {
        textToBeSearched = "nothingToSearch";
        methods.navigateToTheApp(urlApp);
        methods.acceptCookies();
        methods.doSearch(textToBeSearched);
        methods.waitUntilPageIsLoaded(3);
        boolean isPresent = methods.isTheElementPresent(methods.searchResults, amountOfElementsTobeEvaluated, expectedTextToBeFound);
        assertFalse(isPresent, expectedTextToBeFound + " is not within the top " + amountOfElementsTobeEvaluated + " results");
    }

}
