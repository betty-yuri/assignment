package com.selenium.assignment.test.signIn;

/**
 * Login in to symphony site
 * @autor byurivilca
 */


import com.relevantcodes.extentreports.LogStatus;
import com.selenium.assignment.page.signIn.SignInPage;
import com.selenium.assignment.test.SeleniumTest;
import org.junit.Test;

public class SignInTest extends SeleniumTest {

    private SignInPage signIn;

    protected static String DATA_INPUT_EMAIL = "bettyyurivilca@gmail.com";
    protected static String DATA_INPUT_PASSWORD = "Mdp@123456";


    @Test
    public void scenario() throws Exception {
        report.startTest(SignInTest.class.getSimpleName(), " Login in to symphony site");
        signIn = new SignInPage(driver);

        report.log(LogStatus.INFO, "Email:"+DATA_INPUT_EMAIL);
        signIn.actionSignIn(driver, DATA_INPUT_EMAIL,DATA_INPUT_PASSWORD);
        report.log(LogStatus.PASS, "Home page is available");
    }


}