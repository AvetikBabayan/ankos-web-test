package TestHelpers;

import com.idma.helpers.MainHelpers;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import static TestHelpers.TestHelper.waitUntilShows;

public abstract class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static WebDriverWait longWait;

    private static long waitTime = 20;
    private static long longWaitTime = 40;

    @BeforeTest
    public static void beforeTest() {
        MainHelpers.InitialSetUp();
    }

    @BeforeClass(alwaysRun = true)
    public void startDriver(){
        if (MainHelpers.browser.equals(MainHelpers.chromeBr)) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--start-fullscreen"); //--kiosk no header

            driver = new ChromeDriver(options);

            driver.get(MainHelpers.getURL());

        } else if (MainHelpers.browser.equals(MainHelpers.ffBr)) {
            FirefoxOptions options = new FirefoxOptions();
            FirefoxProfile ffprofile = new FirefoxProfile();
            ffprofile.setPreference("dom.webnotifications.enabled", false);
            DesiredCapabilities dc = DesiredCapabilities.firefox();
            dc.setCapability(FirefoxDriver.PROFILE, ffprofile);
            options.merge(dc);
            driver = new FirefoxDriver(options);
            driver.manage().window().fullscreen();

            driver.get(MainHelpers.getURL());
        }

        longWait = new WebDriverWait(driver, longWaitTime);
        wait = new WebDriverWait(driver, waitTime);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(TestConstants.START_REGISTRATION_BTN)));

    }


    @AfterTest(alwaysRun = true)
    public static void afterTest() {
        if(!driver.toString().contains("(null)")) {
            driver.quit();
        }
    }

    @AfterClass(alwaysRun = true)
    public void goToLoginPage() {
        driver.quit();
    }


}
