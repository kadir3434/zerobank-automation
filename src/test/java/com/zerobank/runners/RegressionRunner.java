package com.zerobank.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/Account_Summary",
        glue = "com/zerobank/stepdefinitions",
        dryRun = false,
        //tags = "@Account_Activity",
        plugin = {
                "html:target/html-cucumber-reports",
                "json:target/cucumber.json"
        }

)
public class RegressionRunner {

}
