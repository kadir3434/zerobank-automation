package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.jsoup.Connection;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    @FindBy(css = "button#signin_button")
    public WebElement signInButton;

    @FindBy(xpath = "//ul[@class='nav nav-tabs']//a")
    public WebElement pageSubTitle;



    public BasePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void signInButtonClick(){

        signInButton.click();
    }

    public String getPageSubtitle(){

        return pageSubTitle.getText();
    }

    //navigates to another page with the given string
    public void navigateTo(String pageName){
        WebElement pageNameElement = Driver.getDriver().findElement(By.xpath("//ul[@class='nav nav-tabs']//a[text()='" + pageName + "']"));
        pageNameElement.click();
    }


}
