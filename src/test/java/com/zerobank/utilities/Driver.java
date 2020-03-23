package com.zerobank.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {

    private static WebDriver driver;

    public static WebDriver getDriver(){
        if (driver==null){
            switch (ConfigurationReader.getProperty("browser")){
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "/Users/kadir/intellij-automation/chromedriver");
                    driver = new ChromeDriver();
                    break;
                case "chrome_headless":
                    System.setProperty("webdriver.chrome.driver", "/Users/kadir/intellij-automation/chromedriver");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setHeadless(true);
                    driver = new ChromeDriver();
                    break;

                default:
                    throw new RuntimeException("Wrong browser type");
            }

        }
        return driver;
    }

    public static void close(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }

}
