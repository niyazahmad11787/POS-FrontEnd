package com.qa.hippo.tests;

import com.qa.hippo.baseclass.BaseClass;
import com.qa.hippo.pages.DashboardPage;
import com.qa.hippo.pages.LoginPageFrontOffice;
import com.qa.hippo.utilities.HTPLLogger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseClass {
    LoginPageFrontOffice loginPageFrontOffice;
    DashboardPage dashboardPage;
    @BeforeMethod
    public void setUp(){
        openApplication();
        loginPageFrontOffice=new LoginPageFrontOffice(driver);
        dashboardPage=new DashboardPage(driver);
    }

    @Test(priority = 1)
    public void verifyLoginFunctionality(){
        loginPageFrontOffice.performLoginOperation();
    }

    @Test(priority = 2)
    public void verifyOpeningBalanceDialogBox(){
        dashboardPage.getOpeningBalanceTextBox();
    }



}
