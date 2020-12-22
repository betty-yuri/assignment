package com.selenium.assignment.page.resetPassword;

/**
 * @author byurivilca
 */

import com.selenium.assignment.utils.PhoneMessage;
import com.selenium.assignment.utils.TwoCaptchaService;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;


public class ResetPasswordPage {

    private final RemoteWebDriver driver;

    //Reset Password Page Objects
    public static final By LOC_EMAIL = By.cssSelector("#recover-form > input");
    public static final By LOC_CAPTCHA= By.cssSelector("#recaptcha-anchor > div");
    public static final By LOC_BT_RESET= By.cssSelector("div.recover-form__submit button");
    protected final static By LOC_ERROR_MESSAGE = By.cssSelector("span.message");

    // Captcha
    public static final By LOC_IFRAME = By.cssSelector("iframe");
    public String apiKey = "3a417dea126daebfc2ad357ae0f97a21";
    public String googleKey = "6LftVv8SAAAAAFxweJaHvwLPBlZm1ZIetZ2Otnok&amp";
    public String pageUrl = "https://my.symphony.com/#forgot-password";
    TwoCaptchaService service = new TwoCaptchaService(apiKey, googleKey, pageUrl);

    //Phone code Page Objetcs
    public static final By LOC_AUTH_CODE= By.cssSelector("#authcode-form input");
    public static final By LOC_BT_AUTH_CODE= By.cssSelector("#authcode-form button");

    //New password Page Objects
    public static final By LOC_PASSPHRASE= By.cssSelector("#passphrase-form input");
    public static final By LOC_BT_PASSPHRASE= By.cssSelector("#passphrase-form button");

    public ResetPasswordPage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public String getMessage(){
        new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(LOC_ERROR_MESSAGE));
        return driver.findElement(ResetPasswordPage.LOC_ERROR_MESSAGE).getText();
    }

    /**
     * getToken for captcha
     */
    public void getToken() throws Exception{
        service = new TwoCaptchaService(apiKey, googleKey, pageUrl);
        driver.switchTo().frame(driver.findElement(LOC_IFRAME));
        new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(LOC_CAPTCHA));

        try {
            driver.switchTo().defaultContent();
            String responseToken = service.solveCaptcha();
            System.out.println("The response token is: " + responseToken);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById(\"g-recaptcha-response\").innerHTML=\"" + responseToken + "\";");
            js.executeScript("document.getElementsByName(\"recover-submit\")[0].classList.remove('disabled');");
            js.executeScript("___grecaptcha_cfg.clients[0].V.V.callback(\"" + responseToken + "\");");
            Thread.sleep(2000);
            clickSubmitButton();
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Click button reset password
     */
    private void clickSubmitButton() throws InterruptedException, IOException {
        try {
            driver.findElement(LOC_BT_RESET).click();
        } catch (Exception e) {
            throw new IOException("Captcha Solver: No Submit button found.");
        }
        Thread.sleep(3000);
    }

    /**
     * Launch the action reset password
     * @param email
     * @param driver
     */
    public ResetPasswordPage actionResetPassword (String email,RemoteWebDriver driver) throws Exception{
        driver.findElement(LOC_EMAIL).sendKeys(email);
        getToken();
        return new ResetPasswordPage(driver);
    }

    /**
     * Launch the action reset password without email
     * @param driver
     */
    public void actionReset (RemoteWebDriver driver) throws Exception{
        new WebDriverWait(driver, 25).until(ExpectedConditions.
                presenceOfElementLocated(LOC_BT_RESET));
        driver.findElement(LOC_BT_RESET).click();
        Thread.sleep(1000);
    }

    /**
     * Send sms to mobile number
     * @param phoneNumber
     * @param driver
     */
    public void actionSendSMS (String phoneNumber, RemoteWebDriver driver) throws Exception{
        PhoneMessage code = new PhoneMessage();
        driver.findElement(LOC_AUTH_CODE).sendKeys(code.retrieveCode());
        new WebDriverWait(driver, 25).until(ExpectedConditions.
                presenceOfElementLocated(LOC_BT_AUTH_CODE));
        driver.findElement(LOC_BT_AUTH_CODE).click();
        Thread.sleep(1000);
    }

    /**
     * Create new password
     * @param password
     * @param driver
     */
    public void actionCreatePassword (String password, RemoteWebDriver driver) throws Exception{
        driver.findElement(LOC_PASSPHRASE).sendKeys(password);
        new WebDriverWait(driver, 25).until(ExpectedConditions.
                presenceOfElementLocated(LOC_BT_PASSPHRASE));
        driver.findElement(LOC_BT_PASSPHRASE).click();
        Thread.sleep(1000);
    }

}