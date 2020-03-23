package com.zerobank.stepdefinitions;

import com.zerobank.pages.AccountSummary;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;

public class AccountSummaryStepDefinitions {
    LoginPage loginPage = new LoginPage();
    AccountSummary accountSummary = new AccountSummary();

    @Then("user verifies that {string} account type is displayed")
    public void user_verifies_that_account_type_is_displayed(String accountTypeName) {
        accountSummary.verifyAccountTypesDisplayed(accountTypeName);
    }

    @Then("user verifies that All Account Types are displayed")
    public void user_verifies_that_All_Account_Types_are_displayed(List<String> dataTable) {
        accountSummary.verifyAccountTypesAreDisplayedWithDatatable(dataTable);
    }



    @Then("user verifies Credit Accounts column names")
    public void user_verifies_Credit_Accounts_column_names(List<String> dataTable) {
        Assert.assertEquals(dataTable, accountSummary.getColumnNames());
    }
}
