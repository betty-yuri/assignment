package com.selenium.assignment.test;

import com.relevantcodes.extentreports.ExtentReports;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;


public class SeleniumTest {
    public static String reportLocation = "C:\\\\assignment\\";
    public static RemoteWebDriver driver;
    SeleniumConfig config;

    public static String PAGE_URL= "https://my.symphony.com";

    private static final String browser = "browser";
    private static final String choix = System.getProperty(browser);
    public ExtentReports report = ExtentReports.get(SeleniumTest.class);


    @Before
    public void setup() throws Exception {
        System.out.println("navigator: " + choix);
        config=new SeleniumConfig();
        driver=config.navigator(choix);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(PAGE_URL);
        report.init(reportLocation + SeleniumTest.class.getSimpleName()+".html", false);
    }

    @AfterClass
    public static void close()throws Exception {
        driver.quit();
    }
}