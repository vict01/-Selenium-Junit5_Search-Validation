package TestBench;

import PageObject.LandingPage;
import TestFramework.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositiveTests extends BaseTest {

    String textToBeSearched = "statista";
    String expectedTextToBeFound = "Statista Pte. Ltd.";
    int amountOfElementsTobeEvaluated = 5;

    LandingPage methods;

    @Before
    public void prepare() {
        beforeTest();
        methods = new LandingPage(getDriver());
    }

    @Test
    @DisplayName("Happy Path | expected text is in the top 5")
    public void happyPath() {
        navigateToTheApp(urlApp);
        methods.acceptCookies();
        methods.doSearch(textToBeSearched);
        methods.waitUntilResultIsLoaded();
        boolean isPresent = methods.isTheElementPresent(methods.searchResults, amountOfElementsTobeEvaluated, expectedTextToBeFound);
        assertTrue(isPresent, expectedTextToBeFound + " is not within the top " + amountOfElementsTobeEvaluated + " results");
    }

    @Test
    @DisplayName("Positive test | expected text is in the top 4")
    public void expected_Search_Is_In_The_Top_Four() {
        expectedTextToBeFound = "Statista GmbH";
        amountOfElementsTobeEvaluated = 4;
        navigateToTheApp(urlApp);
        methods.acceptCookies();
        methods.doSearch(textToBeSearched);
        methods.waitUntilResultIsLoaded();
        boolean isPresent = methods.isTheElementPresent(methods.searchResults, amountOfElementsTobeEvaluated, expectedTextToBeFound);
        assertTrue(isPresent, expectedTextToBeFound + " is not within the top " + amountOfElementsTobeEvaluated + " results");
    }

}
