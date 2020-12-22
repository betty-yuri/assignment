package com.selenium.assignment.test.signUp;

/**
 * The purpose of this test is verify the error message for existing account
 * @author: byurivilca;
 */

import com.relevantcodes.extentreports.LogStatus;
import com.selenium.assignment.page.signIn.SignInPage;
import com.selenium.assignment.page.signUp.SignUpPage;
import com.selenium.assignment.test.SeleniumTest;
import org.junit.Assert;
import org.junit.Test;


public class SignUpExistingAccountTest extends SeleniumTest {

    protected SignUpPage signUp;
    protected SignInPage signIn;

    /*Page 1*/
    protected static String DATA_FIRST_NAME = "Home";
    protected static String DATA_LAST_NAME = "Assignment";
    protected static String DATE_EMAIL = "bettyyurivilca@gmail.com";
    protected static String DATA_PASSWORD = "Mdp@123456";

    /*Error message*/
    protected static String ERROR_MESSAGE = "This email address is already in use. Please try signing in";


      @Test
     public void actionSignUp() throws Exception {
        report.startTest(SignUpExistingAccountTest.class.getSimpleName(),
                ": The purpose of this test is verify the error message for existing accound");
        signUp = new SignUpPage(driver);

        report.log(LogStatus.PASS, "Select the link \"Sign up\"");
        signIn = new SignInPage(driver);
        signIn.signUp(driver);

        report.log(LogStatus.PASS, "Enter\n" +
                " • existing\n" +
                " • account\n" +
                " • bettyyurivilca@gmail.com\n" +
                " • Mdp@123456\n" +
                "Select the button \"Next\"");
        signUp.actionSignUp(DATA_FIRST_NAME,DATA_LAST_NAME,DATE_EMAIL,DATA_PASSWORD,driver);

        Assert.assertTrue(signUp.getMessage().replaceAll("\\s+","").
                contains(ERROR_MESSAGE.replaceAll("\\s+","")));
        report.log(LogStatus.PASS, "The error message is displayed ");
    }



}