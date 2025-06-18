package com.qa.hippo.tests;

import com.qa.hippo.baseclass.BaseClass;
import com.qa.hippo.pages.LoginPageFrontOffice;
import com.qa.hippo.utilities.HTPLLogger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseClass {
    LoginPageFrontOffice loginPageFrontOffice;
    @BeforeMethod
    public void setUp(){
        openApplication();
        loginPageFrontOffice=new LoginPageFrontOffice(driver);
    }

    @Test()
    public void performLogin(){
        loginPageFrontOffice.performLoginOperation();
    }


}
