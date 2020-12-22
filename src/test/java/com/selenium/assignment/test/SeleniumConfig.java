package com.selenium.assignment.test;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Selenium config in order select the browser
 * @author byurivilca;
 **/

public class SeleniumConfig {

    private DesiredCapabilities capabilities;
    private RemoteWebDriver driverInstance;
    public static String BROWSER;

    public SeleniumConfig(){
        this.driverInstance=driverInstance;
    }

    public RemoteWebDriver navigator(String browser) {
        BROWSER=browser;
        if("ie".equals(browser)) {
                capabilities = DesiredCapabilities.internetExplorer();
                System.setProperty("webdriver.ie.driver", "C:\\java\\seleniumDriver\\IEDriverServer.exe");
                driverInstance = new InternetExplorerDriver(capabilities);
                driverInstance.manage().window().setSize(new Dimension(1024, 768));
                driverInstance.manage().window().setPosition(new Point(0, 0));
                return driverInstance;
        } else if("firefox".equals(browser)) {
            capabilities = DesiredCapabilities.firefox();
            driverInstance = new FirefoxDriver(capabilities);
            driverInstance.manage().window().setSize(new Dimension(1680, 1050));
            driverInstance.manage().window().setPosition(new Point(0, 0));
            return driverInstance;
        } else{

            System.out.println("Page title is: " + browser);
            System.setProperty("webdriver.chrome.driver", "C:\\java\\seleniumDriver\\chromedriver.exe");
            capabilities = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            driverInstance = new ChromeDriver(capabilities);
            driverInstance.manage().window().setSize(new Dimension(1680, 1050));
          driverInstance.manage().window().setPosition(new Point(0, 0));
            return driverInstance;
        }
    }
}
