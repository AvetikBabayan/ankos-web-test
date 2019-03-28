package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {

    private static final String firstNameTxt = "FirstName";
    private static final String lastNameTxt = "LastName";
    private static final String usernameTxt = "UserName";
    private static final String passwordTxt = "Password";
    private static final String joinNowTxt = "\n" +
            "                Join Now\n" +
            "              ";
    private static final String regDoneTxt= "\n"+
            "        Tell us more about you\n"+
            "      ";
    private static final String registrationTxt = "Get started with a free account";

    private static final String jointNowXPATH = "//button[text()='"+joinNowTxt+"']";
    private static final String registrationXPATH = "//b[text()='"+registrationTxt+"']";
    private static final String redDoneXPATH = "//h1[text()='"+regDoneTxt+"']";

    @FindBy(name = firstNameTxt)
    public WebElement firstNameInp;

    @FindBy(name = lastNameTxt)
    public WebElement lastNameInp;

    @FindBy(name = usernameTxt)
    public WebElement usernameInp;

    @FindBy(name = passwordTxt)
    public WebElement passwordInp;

    @FindBy(xpath = jointNowXPATH)
    public WebElement joinNowBtn;

    @FindBy(xpath = registrationXPATH)
    public WebElement registration;

    @FindBy(xpath = redDoneXPATH)
    public WebElement registrationDone;
}
