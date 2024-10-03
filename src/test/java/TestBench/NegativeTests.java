package TestBench;

import PageObject.LandingPage;
import TestFramework.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NegativeTests extends BaseTest {

    String textToBeSearched = "statista";
    String expectedTextToBeFound = "Statista GmbH";
    int amountOfElementsTobeEvaluated = 5;

    LandingPage methods;

    @Before
    public void prepare() {
        beforeTest();
        methods = new LandingPage(getDriver());
    }

    @Test
    @DisplayName("Negative test | expected text is not in the top 5")
    public void expected_Search_Is_Not_In_The_Top_Five() {
        textToBeSearched = "siemens";
        navigateToTheApp(urlApp);
        methods.acceptCookies();
        methods.doSearch(textToBeSearched);
        methods.waitUntilPageIsLoaded(3);
        boolean isPresent = methods.isTheElementPresent(methods.searchResults, amountOfElementsTobeEvaluated, expectedTextToBeFound);
        assertFalse(isPresent, expectedTextToBeFound + " is not within the top " + amountOfElementsTobeEvaluated + " results");
    }

    @Test
    @DisplayName("Negative test | No result found")
    public void no_Search_Result_Found() {
        textToBeSearched = "nothingToSearch";
        navigateToTheApp(urlApp);
        methods.acceptCookies();
        methods.doSearch(textToBeSearched);
        methods.waitUntilPageIsLoaded(3);
        boolean isPresent = methods.isTheElementPresent(methods.searchResults, amountOfElementsTobeEvaluated, expectedTextToBeFound);
        assertFalse(isPresent, expectedTextToBeFound + " is not within the top " + amountOfElementsTobeEvaluated + " results");
    }

}