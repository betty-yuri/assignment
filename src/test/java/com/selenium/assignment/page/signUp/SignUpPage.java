package com.selenium.assignment.page.signUp;
/**
 * Page SignUp for assignment
 * @author byurivilca;
 **/


import com.selenium.assignment.utils.Mail;
import com.selenium.assignment.utils.PhoneMessage;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;


public class SignUpPage {

    private final RemoteWebDriver driver;
    public static final By LOC_MSG= By.cssSelector("#sysMsg li");

    // Page 1 Objects
    public static final By LOC_FIRST = By.id("signup-first");
    public static final By LOC_LAST =  By.id("signup-last");
    public static final By LOC_EMAIL=  By.id("signup-email");
    public static final By LOC_PASSWORD= By.id("signup-password");
    public static final By LOC_BT_GREEN= By.cssSelector("button.button.green");

    //Page 2 Objects
    public static final By LOC_BT_SKIP= By.cssSelector("div.button.negative.skip");

    //Page 3 Objects
    public static final By LOC_PHONE_NUMBER= By.id("mobile-phone-number");
    public static final By LOC_BT_SEND_NUMBER= By.cssSelector("span.button.green.send-phone-number");

    //Page 4 Objects
    public static final By LOC_VERIFICATION_CODE= By.id("mobile-verification-code");
    public static final By LOC_BT_VERIFY_CODE= By.cssSelector("span.button.green.verify-code");


    public SignUpPage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public String getMessage(){
        new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(LOC_MSG));
        return driver.findElement(SignUpPage.LOC_MSG).getText();
    }

    /**
     * Sign up to first page
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param driver
     */
    public SignUpPage actionSignUp (String firstName, String lastName, String email, String password,
                                    RemoteWebDriver driver) throws Exception{
        driver.findElement(LOC_FIRST).sendKeys(firstName);
        driver.findElement(LOC_LAST).sendKeys(lastName);
        driver.findElement(LOC_EMAIL).sendKeys(email);
        driver.findElement(LOC_PASSWORD).sendKeys(password);
        new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(LOC_BT_GREEN));
        driver.findElement(LOC_BT_GREEN).click();
        Thread.sleep(1000);
        return new SignUpPage(driver);
    }

    /**
     * Send email with password
     * @param driver
     */
    public SignUpPage actionSendMail ( RemoteWebDriver driver) throws Exception{
        new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(LOC_BT_SKIP));
        driver.findElement(LOC_BT_SKIP).click();
        Thread.sleep(10000);
        return new SignUpPage(driver);
    }

    /**
     * Retrieve email
     * @param email
     * @param driver
     */
    public SignUpPage actionRetrieveEmail (String email, RemoteWebDriver driver) throws Exception{
        Thread.sleep(3000);
        Mail mail = new Mail();
        ArrayList<String> inbox = mail.checkInbox("Symphony");
        String urlActivation = mail.retrieveURL(inbox);
        driver.get(urlActivation);
        return new SignUpPage(driver);
    }

    /**
     * Send sms to mobile number
     * @param phoneNumber
     * @param driver
     */
    public SignUpPage actionSendSMS (String phoneNumber, RemoteWebDriver driver) throws Exception{
        driver.findElement(LOC_PHONE_NUMBER).sendKeys(phoneNumber);
        new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(LOC_BT_SEND_NUMBER));
        driver.findElement(LOC_BT_SEND_NUMBER).click();
        Thread.sleep(10000);
        return new SignUpPage(driver);
    }

    /**
     * Retrieve sms from mobile number
     * @param driver
     */
    public SignUpPage actionRetrieveCode (RemoteWebDriver driver) throws Exception{
        Thread.sleep(1000);
        PhoneMessage code = new PhoneMessage();
        driver.findElement(LOC_VERIFICATION_CODE).sendKeys(code.retrieveCode());
        new WebDriverWait(driver, 25).until(ExpectedConditions.
                presenceOfElementLocated(LOC_BT_VERIFY_CODE));
        driver.findElement(LOC_BT_VERIFY_CODE).click();
        Thread.sleep(1000);
        return new SignUpPage(driver);
    }

}