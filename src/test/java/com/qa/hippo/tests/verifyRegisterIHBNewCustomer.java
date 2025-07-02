package com.qa.hippo.tests;

import com.github.javafaker.Faker;
import com.qa.hippo.baseclass.BaseClass;
import com.qa.hippo.pages.DashboardPage;
import com.qa.hippo.pages.RegisterNewCustomerIHB;
import com.qa.hippo.utilities.UtilClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class verifyRegisterIHBNewCustomer extends BaseClass {

    DashboardPage dashboardPage;
    RegisterNewCustomerIHB registerNewCustomerIHB;

    public String mobileNumber;

    @BeforeMethod
    public void setUp() {
        registerNewCustomerIHB = new RegisterNewCustomerIHB(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Test(priority = 1)
    public void verifyCustomerIsNotRegistered() {
        mobileNumber = UtilClass.generateMobileNumber();
        dashboardPage.searchGivenCustomer(mobileNumber);
        registerNewCustomerIHB.clickOnCustomerAddButton();
    }

    @Test(priority = 2)
    public void verifyMobileNumberInputField() throws InterruptedException {
        registerNewCustomerIHB.enterPhoneNumber(mobileNumber);
        registerNewCustomerIHB.generateOtpButton();
        registerNewCustomerIHB.verifyOTPFunctionality(mobileNumber);
    }

    @Test(priority = 3)
    public void verifyCustomerCreationFormValidation() {
        registerNewCustomerIHB.customerCreationForm();
    }


}
