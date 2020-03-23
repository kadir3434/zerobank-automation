package com.zerobank.stepdefinitions;

import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class LoginStepDefinitions {
    LoginPage loginPage = new LoginPage();

    @Given("user is on login page")
    public void user_is_on_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage.signInButtonClick();
    }

    @Then("user logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        loginPage.login();
    }

    @Then("user verifies that {string} page subtitle is displayed")
    public void user_verifies_that_page_subtitle_is_displayed(String string) {
        Assert.assertEquals(string, loginPage.getPageSubtitle());
    }

    @Then("user tries to log in with invalid credentials")
    public void user_tries_to_log_in_with_invalid_credentials() {
        loginPage.login("wrongusername", "wrongpassword");
    }

    @Then("user verifies that {string} error message is displayed.")
    public void user_verifies_that_error_message_is_displayed(String string) {
        Assert.assertEquals(string, loginPage.getErrorMessage());
    }

    @Then("user tries to login with {string} and {string} blank credentials")
    public void userTriesToLoginWithAndBlankCredentials(String username, String password) {
        loginPage.login(username,password);
    }

    @And("user verifies that page title is {string}")
    public void userVerifiesThatPageTitleIs(String pageTitle) {
        Assert.assertEquals(pageTitle, Driver.getDriver().getTitle());
    }
}
