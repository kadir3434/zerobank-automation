package com.zerobank.stepdefinitions;

import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hook {

    private static Logger logger = Logger.getLogger(Hook.class);

    @Before
    public void setup(){
        logger.info("##############################");
        logger.info("Test setup!");
        if(!ConfigurationReader.getProperty("browser").contains("remote")) {
            Driver.getDriver().manage().window().maximize();
        }
    }

    @After
    public void teardown(Scenario scenario){
        //if test failed - do this
        if(scenario.isFailed()){
            logger.error("Test failed!");
            byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        }else{
            logger.info("Cleanup!");
            logger.info("Test completed!");
        }
        logger.info("##############################");
        //after every test, we gonna close browser
        Driver.close();
    }

}
