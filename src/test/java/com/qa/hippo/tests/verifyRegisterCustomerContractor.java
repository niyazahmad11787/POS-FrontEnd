package com.qa.hippo.tests;

import com.qa.hippo.baseclass.BaseClass;
import com.qa.hippo.pages.DashboardPage;
import com.qa.hippo.pages.RegisterNewCustomerContractor;
import com.qa.hippo.pages.RegisterNewCustomerIHB;
import com.qa.hippo.utilities.UtilClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class verifyRegisterCustomerContractor extends BaseClass {


    DashboardPage dashboardPage;
    RegisterNewCustomerIHB registerNewCustomerIHB;
    RegisterNewCustomerContractor registerNewCustomerContractor;
    UtilClass utilClass;
    public String mobileNumber;

    @BeforeMethod
    public void setUp() {
        registerNewCustomerIHB = new RegisterNewCustomerIHB(driver);
        dashboardPage = new DashboardPage(driver);
        registerNewCustomerContractor=new RegisterNewCustomerContractor(driver);

    }

    @Test(priority = 1)
    public void verifyContractorCustomerIsNotRegistered() {
        mobileNumber = UtilClass.generateMobileNumber();
        dashboardPage.searchGivenCustomer(mobileNumber);
        registerNewCustomerIHB.clickOnCustomerAddButton();
        utilClass=new UtilClass(driver);
    }
    @Test(priority = 2)
    public void verifyMobileNumberInputField() throws InterruptedException {
        registerNewCustomerIHB.enterPhoneNumber(mobileNumber);
        registerNewCustomerIHB.generateOtpButton();
        registerNewCustomerIHB.verifyOTPFunctionality(mobileNumber);
    }
    @Test(priority = 3)
    public void verifyCustomerTypeRadioButton(){
        registerNewCustomerContractor.contractorRadioButton();
    }

    @Test(priority = 4)
    public void verifyCustomerDetailsForContractor(){
        registerNewCustomerContractor.ContractoCustomerDetails();
        registerNewCustomerContractor.checkMobileInputField();
        registerNewCustomerContractor.checkPanCardInputField();
        registerNewCustomerContractor.checkGSTNumberInputFields();
    }

    @Test(priority = 5)
    public void verifyAddressDetailsForContractor(){
        registerNewCustomerContractor.selectGeoCodeBtn();
        registerNewCustomerContractor.searchLocation("122002");
        registerNewCustomerContractor.checkAddressAfterGeoCode();
    }

    @Test(priority = 6)
    public void verifyOtherDetailsForContractor(){
        registerNewCustomerContractor.checkOtherDetails();
    }

    @Test(priority = 7)
    public void verifyHearAboutHippo(){
        registerNewCustomerIHB.hearAboutHippoDropdown("Digital");
    }

    @Test(priority = 8)
    public void verifyContinueToRegister(){
        registerNewCustomerIHB.clickOnContinueToRegisterButton();
    }

    @Test(priority = 9)
    public void verifyOrderPlacementFunctionalityForContractor(){
        dashboardPage.clickOnDeliveryMode();
        dashboardPage.selectProduct("1000000023");

    }

}
