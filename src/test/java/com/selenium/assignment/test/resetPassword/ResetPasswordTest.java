package com.selenium.assignment.test.resetPassword;

import com.relevantcodes.extentreports.LogStatus;
import com.selenium.assignment.page.resetPassword.ResetPasswordPage;
import com.selenium.assignment.page.signIn.SignInPage;
import com.selenium.assignment.page.signUp.SignUpPage;
import com.selenium.assignment.test.SeleniumTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  The purpose of this test is reset password
 * @author byurivilca;
 */
public class ResetPasswordTest extends SeleniumTest {

    protected SignInPage signIn;
    protected SignUpPage signUp;
    protected ResetPasswordPage resetPassword;
    protected static String DATA_EMAIL = "email8@1eov1bgh.mailosaur.io";
    protected static String DATA_PHONE = "12256382438";
    protected static String DATA_PASSWORD = "Mdp@123456";
    protected static String MSG_SUCCES = "Your password was successfully changed";

    @Test
     public void TestLaunchPassword() throws Exception {
        report.startTest(ResetPasswordTest.class.getSimpleName(),
                "The purpose of this test is reset password");
        signIn = new SignInPage(driver);
        signUp = new SignUpPage(driver);

        report.log(LogStatus.INFO, "Select the link -Forgot your password? ");
        resetPassword = signIn.resetPassword(driver);

        report.log(LogStatus.INFO, "Enter\n" +
                " • email8@1eov1bgh.mailosaur.io\n" +
                " • select checkbox captcha\n "+
                "Select the button Recover Password");
        resetPassword.actionResetPassword (DATA_EMAIL, driver);

        report.log(LogStatus.INFO, "Add mobile number 12256382438\n" +
                "Select the button Retrieve code");
        resetPassword.actionSendSMS(DATA_PHONE,driver);

        report.log(LogStatus.INFO, "Add the code\n" +
                "Retrieve email");
        signUp.actionRetrieveEmail(DATA_EMAIL,driver);

        report.log(LogStatus.INFO, "Retrieve the email password and click under the link");
        resetPassword.actionCreatePassword(DATA_PASSWORD,driver);

        Assert.assertTrue(resetPassword.getMessage().contains(MSG_SUCCES));
        report.log(LogStatus.PASS, resetPassword.getMessage());
    }


}