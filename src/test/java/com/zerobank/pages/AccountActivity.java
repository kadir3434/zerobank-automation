package com.zerobank.pages;


import com.zerobank.utilities.BrowserUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountActivity extends BasePage {

    @FindBy(css = "#aa_accountId")
    public WebElement accountDropDown;

    @FindBy(css = "#aa_accountId>option")
    public List<WebElement> valueOfAccountDropdown;

    @FindBy (xpath = "//thead/tr/th")
    public List<WebElement> transactionTableColumns;

    @FindBy(xpath = "//thead/tr/th[1]")
    public WebElement transactionTableColumnDate;

    public void verifyTransactionColumnNames(List<String> datatable){
        BrowserUtils.waitForVisibility(transactionTableColumnDate,60);
        List<String> columnNames = BrowserUtils.getListOfString(transactionTableColumns);
        Assert.assertEquals(datatable, columnNames);
    }



}
