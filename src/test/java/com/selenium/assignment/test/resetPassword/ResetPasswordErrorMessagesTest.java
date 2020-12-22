package com.selenium.assignment.test.resetPassword;

import com.relevantcodes.extentreports.LogStatus;
import com.selenium.assignment.page.resetPassword.ResetPasswordPage;
import com.selenium.assignment.page.signIn.SignInPage;
import com.selenium.assignment.test.SeleniumTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * The purpose of this test is verify that error message are displayed in the reset password page
 * @author byurivilca;
 */
public class ResetPasswordErrorMessagesTest extends SeleniumTest {

    protected SignInPage signIn;
    protected ResetPasswordPage resetPassword;
    protected static String MSG_ERROR = "An e-mail address is required";

    @Test
     public void TestLaunchPassword() throws Exception {
        report.startTest(ResetPasswordErrorMessagesTest.class.getSimpleName(),
                " The purpose of this test is verify that error message are displayed in the " +
                        "reset password page\n");

        signIn = new SignInPage(driver);

        report.log(LogStatus.INFO, "Select the link -Forgot your password? ");
        resetPassword = signIn.resetPassword(driver);

        report.log(LogStatus.INFO, "Select the button Recover Password");
        resetPassword.actionReset (driver);

        report.log(LogStatus.PASS, resetPassword.getMessage());
        Assert.assertTrue(resetPassword.getMessage().contains(MSG_ERROR));
    }


}