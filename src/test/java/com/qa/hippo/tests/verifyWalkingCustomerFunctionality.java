package com.qa.hippo.tests;

import com.qa.hippo.baseclass.BaseClass;
import com.qa.hippo.pages.DashboardPage;
import com.qa.hippo.pages.WalkInCustomerPage;
import com.qa.hippo.utilities.ConfigLoader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class verifyWalkingCustomerFunctionality extends BaseClass {


    WalkInCustomerPage walkInCustomerPage;
    DashboardPage dashboardPage;


    @BeforeMethod
    public void setUp(){
        walkInCustomerPage=new WalkInCustomerPage(driver);
        dashboardPage=new DashboardPage(driver);
    }

    @Test(priority = 1)
    public void verifyCustomerInputField(){
        dashboardPage.getOpeningBalanceTextBox();
    }

    @Test(priority = 2)
    public void verifyCustomerIsNotRegistered(){
        dashboardPage.searchGivenCustomer("987666655");
        walkInCustomerPage.clickOnCustomerAddButton();
        walkInCustomerPage.verifyWalkinCustomerIsSelected();
    }

}
