package com.selenium.assignment.test.signUp;

/**
 * The purpose of this test is verify the sign up
 * @uthor: byurvilca;
 */

import com.relevantcodes.extentreports.LogStatus;
import com.selenium.assignment.page.signIn.SignInPage;
import com.selenium.assignment.page.signUp.SignUpPage;
import com.selenium.assignment.test.SeleniumTest;
import com.selenium.assignment.utils.RessourceUtil;
import org.junit.Test;


public class SignUpTest extends SeleniumTest {

    protected SignUpPage signUp;
    protected SignInPage signIn;
    protected static RessourceUtil random = new RessourceUtil();

    /*Page 1*/
    protected static String DATA_FIRST_NAME = "Home";
    protected static String DATA_LAST_NAME = "Assignment";
    protected static String DATA_EMAIL = "email"+random.calcRandom()+"@1eov1bgh.mailosaur.io";
    protected static String DATA_PASSWORD = "Mdp@123456";


    /*Page 3*/
    protected static String DATA_PHONE_NUMBER = "12256382438";


    @Test
    public void actionSignUp() throws Exception {
        report.startTest(SignUpTest.class.getSimpleName(),"The purpose of this test is verify the sign up");
        signUp = new SignUpPage(driver);
        signIn = new SignInPage(driver);

        report.log(LogStatus.INFO, "Select the link \"Sign up\" ");
        signIn.signUp(driver);

        report.log(LogStatus.INFO, "Enter\n" +
                " • Home\n" +
                " • Assignment\n" +
                " • email@1eov1bgh.mailosaur.io\n" +
                " • Mdp@123456\n" +
                "Select the button \"Next\"");
        signUp.actionSignUp(DATA_FIRST_NAME,DATA_LAST_NAME,DATA_EMAIL,DATA_PASSWORD,driver);

        report.log(LogStatus.INFO, "Click over skip button");
        signUp.actionSendMail(driver);

        report.log(LogStatus.INFO, "Retrieve the email password and click under the link");
        signUp.actionRetrieveEmail(DATA_EMAIL, driver);

        report.log(LogStatus.INFO, "Enter mobile number 12256382438\n" +
                "Click over \"Retrieve code\"\n");
        signUp.actionSendSMS(DATA_PHONE_NUMBER, driver);

        report.log(LogStatus.INFO, "Retrieve the code and enter\n" +
                "click over \"create account\" button");
        signUp.actionRetrieveCode(driver);
    }

}