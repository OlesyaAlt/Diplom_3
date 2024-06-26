import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import static constants.Url.URL;

public abstract class BaseCase {
    public static WebDriver webDriver;
    private static final String BROWSER = "chrome";
    @Before
    public void setup(){
        webDriver = WebDriverFactory.getWebDriver(BROWSER);
        webDriver.get(URL);
    }
    @After
    public void tearDown(){
        webDriver.quit();
    }
}
