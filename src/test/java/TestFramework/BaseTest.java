package TestFramework;

import org.junit.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class BaseTest {
    public String urlApp = "https://www.statista.com/companies/search";
    private WebDriver driver;
    public WebDriver getDriver() {
        return driver;
    }

    public void navigateToTheApp(String urlApp) {
        driver.get(urlApp);
    }

    protected void closeBrowser() {
        if (null != driver) {
            driver.close();
            driver.quit();
        }
    }

    protected void maximiseWindow() {
        driver.manage().window().maximize();
    }

    @Before
    public void beforeTest() {
        try {
            WebDriverManager.chromedriver().forceDownload().setup();
            driver = new ChromeDriver();

            maximiseWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert driver != null;
    }

    @After
    public void afterTest() {
        closeBrowser();
    }

}
