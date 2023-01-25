package TestFramework;

import org.junit.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class BaseTest {
    public String urlApp = "https://www.statista.com/companies/search";
    private static WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public static void beforeTest() {
        try {
            WebDriverManager.chromedriver().setup();
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

    protected static void closeBrowser() {
        if (null != driver) {
            driver.close();
            driver.quit();
        }
    }

    protected static void maximiseWindow() {
        driver.manage().window().maximize();
    }

}