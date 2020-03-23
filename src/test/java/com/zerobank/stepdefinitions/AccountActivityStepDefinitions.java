package com.zerobank.stepdefinitions;

import com.zerobank.pages.AccountActivity;
import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class AccountActivityStepDefinitions {
    AccountActivity accountActivity = new AccountActivity();

    @Then("user navigates to {string} page")
    public void user_navigates_to_page(String page) {
        accountActivity.navigateTo(page);
    }

    @Then("user verifies that account DropDown default value is {string}")
    public void user_verifies_that_account_DropDown_default_value_is(String value) {
        Assert.assertEquals(value, BrowserUtils.getTextFromSelectedDropDown(accountActivity.accountDropDown));
    }

    @Then("user verifies Account DropDown options are")
    public void user_verifies_Account_DropDown_options_are(List<String> dataTable) {
        Assert.assertEquals(dataTable, BrowserUtils.getListOfString(accountActivity.valueOfAccountDropdown));

    }

    @Then("user verifies Transactions table has")
    public void user_verifies_Transactions_table_has(List<String> dataTable) {
        accountActivity.verifyTransactionColumnNames(dataTable);
    }


}
