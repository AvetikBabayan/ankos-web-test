package RegistrationFunctionalTests;

import TestHelpers.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import pages.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrationSuccessTest extends BaseTest {
    private static MainPage mainPage;
    private static RegistrationPage registrationPage;

    private static String timeStamp =  new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    private static String firstName = "FirstNameTest_"+timeStamp;
    private static String lastName = "LastNameTest_"+timeStamp;
    private static String userName = "email"+timeStamp+"@test.com";
    private static String password = "Qwer1234!@#$";

    @BeforeClass
    public void initialSetUp(){

    }

    @BeforeMethod
    public void setUpTest(){
        mainPage = PageFactory.initElements(driver, MainPage.class);
    }

    @AfterClass(alwaysRun = true)
    public void finalTearDown(){

    }

    @AfterMethod
    public void tearDown(){
    }

    @Test
    public void registrationTest(){
        mainPage.startRegistration.click();
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        wait.until(ExpectedConditions.visibilityOf(registrationPage.registration));

        registrationPage.firstNameInp.sendKeys( firstName);
        registrationPage.lastNameInp.sendKeys(lastName);
        registrationPage.usernameInp.sendKeys( userName);
        registrationPage.passwordInp.sendKeys( password);
        registrationPage.joinNowBtn.click();

        wait.until(ExpectedConditions.visibilityOf(registrationPage.registrationDone));
        assertTrue(registrationPage.registrationDone.getText().contains("Tell us more about you"));
    }


}
