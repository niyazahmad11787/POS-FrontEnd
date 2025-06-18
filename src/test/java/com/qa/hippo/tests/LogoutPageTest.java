package com.qa.hippo.tests;

import com.qa.hippo.baseclass.BaseClass;
import com.qa.hippo.pages.LogoutPageFrontOffice;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutPageTest extends BaseClass {

    LogoutPageFrontOffice logoutPageFrontOffice;

    @BeforeMethod
    public void setup() {
        logoutPageFrontOffice=new LogoutPageFrontOffice(driver);
    }


    @Test()
    public void TearDown(){
        logoutPageFrontOffice.logoutFromApplication();

    }
}
