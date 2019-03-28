package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainPage extends BasePage {

    // xpaths
    public static final String startRegistrationXPath = "//a[contains(text()," +
            "'Get started with free account')]";

    @FindBy(xpath = startRegistrationXPath)
    public WebElement startRegistration;
}
