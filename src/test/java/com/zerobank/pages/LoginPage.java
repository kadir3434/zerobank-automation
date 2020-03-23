package com.zerobank.pages;

import com.zerobank.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "input#user_login")
    public WebElement usernameTextBox;

    @FindBy(css = "input[name='user_password']")
    public WebElement passwordTextBox;

    @FindBy(css = "input[name='submit']")
    public WebElement signInButtonSubmit;

    @FindBy(css = "div.alert.alert-error")
    public WebElement errorMessage;

    public void login(){
        usernameTextBox.sendKeys(ConfigurationReader.getProperty("username"));
        passwordTextBox.sendKeys((ConfigurationReader.getProperty("password")));
        signInButtonSubmit.click();
    }

    public void login(String username, String password){
        usernameTextBox.sendKeys(username);
        passwordTextBox.sendKeys(password);
        signInButtonSubmit.click();
    }

    public String getErrorMessage(){
        return errorMessage.getText();
    }


}
