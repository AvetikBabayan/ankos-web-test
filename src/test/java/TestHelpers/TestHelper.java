package TestHelpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class TestHelper extends BaseTest{

    public static boolean isClickable(WebDriver driver, WebElement webel) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(webel));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // useful methods

    public static void clickElement(By by) {
        waitUntilShows(by);
        driver.findElement(by).click();
    }

    public static void clickElement(WebElement element) {
        waitUntilShows(element);
        element.click();
    }

    public static void waitUntilShows(By by) {
        try {
            longWait.until(ExpectedConditions.elementToBeClickable(by));
            assertTrue(true);
        } catch (TimeoutException e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }

    public static void checkUnitToBeDisplayed(By by) {
        try {
            WebElement el =driver.findElement(by);
            assertTrue(el.isDisplayed());
        } catch (TimeoutException e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }

    public static void waitUntilShows(WebElement element) {
        try {
            longWait.until(ExpectedConditions.elementToBeClickable(element));
            assertTrue(true);
        } catch (TimeoutException e) {
            fail(e.toString());
        }
    }

    public static void assertEqual(String m1, WebElement element) {
        waitUntilShows(element);
        assertEquals(m1.toLowerCase(), element.getText().toLowerCase());
    }

    public static void assertEqual(WebElement element, String m1) {
        waitUntilShows(element);
        assertEquals(m1.toLowerCase(), element.getText().toLowerCase());
    }

    public static String errorXpath(String errorMsg){
        return "//mat-error[text()='"+errorMsg+"']";
    }

    public static String amiSpeachXpath(String speachMsg){
        return "//p[text()='"+speachMsg+"']";
    }

    public static String amiSpeachPartXpath(String speachMsg){
        return "//p[contains(text(),'"+speachMsg+"')]";
    }

    public static String amiPageXpath(String speachMsg){
        return "//h1[text()='"+speachMsg+"']";
    }

    public static boolean isElementNotDisplayed(By by) {
        try {
            ExpectedCondition condition = new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(final WebDriver webDriver) {
                    WebElement element = webDriver.findElement(by);
                    return !element.isDisplayed();
                }
            };
            longWait.until(condition);
        } catch (Exception ex) {
            // if it gets here it is because the element is still displayed after timeoutInSeconds
            // insert code most suitable for you
        }
        return true;
    }

    public static void elementNotExists(String byXpath, String errMsg){
        if(driver.findElements(By.xpath(byXpath)).size()!=0){
            assertTrue(false,errMsg);
        }
    }


    public static void skipSetup(){
        WebElement skip;
        int i = 1;
        do{
            try{
                Thread.sleep(5000);
            }catch (InterruptedException ex){

            }
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='SKIP']")));
            skip = driver.findElement(By.xpath("//span[text()='SKIP']"));
            skip.click();
            i++;
        }while (i < 2);
    }

    public static void closeOtherWindows(){
        Set<String> handls = driver.getWindowHandles();
        if(handls.size() > 1){
            System.out.print("NUMBER OF WINDOWS ===== " + handls.size());
            for(int i=1; i <=handls.size()-1;  i++) {
                driver.switchTo().window(handls.toArray()[i].toString()).close();
            }
            driver.switchTo().window(handls.toArray()[0].toString());
        }
    }

    public static void waitForPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        longWait.until(pageLoadCondition);
    }

    public static void logout(){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(String.format("window.localStorage.clear();"));
        driver.navigate().refresh();
    }
}
