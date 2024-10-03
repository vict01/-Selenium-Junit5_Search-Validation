package TestFramework;

import org.junit.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class BaseTest {
    public String urlApp = "https://www.statista.com/companies/search";
    private WebDriver driver; // Elimina 'static'

    public WebDriver getDriver() {
        return driver;
    }

    @Before
    public void beforeTest() { // Elimina 'static'
        try {
            WebDriverManager.chromedriver().forceDownload().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

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

    protected void closeBrowser() { // Elimina 'static'
        if (null != driver) {
            driver.close();
            driver.quit();
        }
    }

    protected void maximiseWindow() { // Elimina 'static'
        driver.manage().window().maximize();
    }
}
