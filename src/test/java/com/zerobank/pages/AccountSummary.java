package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountSummary extends BasePage{

    @FindBy(xpath = "//div[@class='offset2 span8']//div[3]//tr/th")
    public List<WebElement> CreditAccountColumnNamesElement;


    //Verifies Account Types on Account Summary page
    public void verifyAccountTypesDisplayed(String accountTypeName){
        BrowserUtils.waitForPageToLoad(10);
        WebElement accountType = Driver.getDriver().findElement(By.xpath("//h2[@class = 'board-header' and text()='" + accountTypeName + "']"));
        String actualAccountTypeName = accountType.getText();;
        Assert.assertEquals(accountTypeName, actualAccountTypeName);
    }

    public List<String> getColumnNames(){

        return BrowserUtils.getListOfString(CreditAccountColumnNamesElement);
    }

    public void verifyAccountTypesAreDisplayedWithDatatable(List<String> datatable){
        List<String> accuntTypes = BrowserUtils.getListOfString(Driver.getDriver().findElements(By.xpath("//h2[@class = 'board-header']")));
        Assert.assertEquals(datatable, accuntTypes);
    }

}
