package com.selenium.assignment.test.signIn;

/**
 * Description: Login in as invalid account to symphony site
 * @author byurivilca
 */
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.assignment.page.signIn.SignInPage;
import com.selenium.assignment.test.SeleniumTest;
import org.junit.Assert;
import org.junit.Test;

public class SignInInvalidAccountTest extends SeleniumTest {

    private SignInPage signIn;

    protected static String DATA_INPUT_EMAIL = "assign@email.com";
    protected static String DATA_INPUT_PASSWORD = "Mdp@123456";
    protected static String ERROR_MSG = "Invalid username or password";

    @Test
    public void scenario() throws Exception {
        signIn = new SignInPage(driver);
        report.startTest(SignInInvalidAccountTest.class.getSimpleName(),
                "Login in as invalid account to symphony site");

        report.log(LogStatus.INFO, "Sign in to account: "+DATA_INPUT_EMAIL + " "+DATA_INPUT_PASSWORD);
        signIn.actionSignIn(driver, DATA_INPUT_EMAIL,DATA_INPUT_PASSWORD);

        Assert.assertTrue(signIn.getErrorMessage().contains(ERROR_MSG));
        report.log(LogStatus.PASS, "La page login is displayed with the message "+signIn.getErrorMessage());
    }

}