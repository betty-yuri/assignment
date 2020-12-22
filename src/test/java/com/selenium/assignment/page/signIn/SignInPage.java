package com.selenium.assignment.page.signIn;
/**
 * Page SignIn for assignment
 * @author byurivilca;
 **/

import com.selenium.assignment.page.resetPassword.ResetPasswordPage;
import com.selenium.assignment.page.signUp.SignUpPage;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    RemoteWebDriver driver;

    public SignInPage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    //SignIn Page Objects
    protected static By LOC_INPUT_EMAIL = By.id("signin-email");
    protected static By LOC_INPUT_PASSWORD = By.id("signin-password");
    protected static By LOC_BT_SUBMIT = By.cssSelector("#signin-form > button");

    // Forgot link Object
    public final static By LOC_LINK_FORGOT_PASSWORD = By.cssSelector("#signin-form  div:last-child a");

    //SignUp Link Object
    public final static By LOC_LINK_SIGNUP =
            By.cssSelector("#authentication > div > div > div.footer-container > div > a");

    //Error message Object
    protected final static By LOC_ERROR_MESSAGE = By.cssSelector("span.message");

    /**
     * Login in the application
     * @param driver
     * @param email
     * @param password
     */
    public void actionSignIn (RemoteWebDriver driver, String email, String password) throws InterruptedException {
        driver.findElement(LOC_INPUT_EMAIL).sendKeys(email);
        driver.findElement(LOC_INPUT_PASSWORD).sendKeys(password);
        driver.findElement(LOC_BT_SUBMIT).click();
        Thread.sleep(1000);
    }

    public String getErrorMessage(){
        new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(LOC_ERROR_MESSAGE));
        return driver.findElement(SignInPage.LOC_ERROR_MESSAGE).getText();
    }

    /**
     * Link to sign up
     * @param driver
     */
    public SignUpPage signUp (RemoteWebDriver driver) throws Exception{
        driver.findElement(LOC_LINK_SIGNUP).click();
        Thread.sleep(1000);
        return new SignUpPage(driver);
    }

    /**
     * Link to reset password
     * @param driver
     */
    public ResetPasswordPage resetPassword (RemoteWebDriver driver) throws Exception{
        driver.findElement(LOC_LINK_FORGOT_PASSWORD).click();
        Thread.sleep(1000);
        return new ResetPasswordPage(driver);
    }
}
