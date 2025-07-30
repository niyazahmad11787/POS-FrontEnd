package com.qa.hippo.tests;

import com.qa.hippo.baseclass.BaseClass;
import com.qa.hippo.pages.DashboardPage;
import com.qa.hippo.pages.WalkInCustomerPage;
import com.qa.hippo.utilities.ConfigLoader;
import com.qa.hippo.utilities.UtilClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class verifyWalkingCustomerFunctionalitySelfPickup extends BaseClass {


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
        dashboardPage.searchGivenCustomer(UtilClass.generateMobileNumber());
        walkInCustomerPage.clickOnCustomerAddButton();
        walkInCustomerPage.verifyWalkinCustomerIsSelected();
    }

    @Test(priority = 3)
    public void verifyDeliveryModeIsSelfPickup(){
        dashboardPage.clickOnDeliveryMode();
    }

    @Test(priority = 4)
    public void verifySearchProdcut(){
        dashboardPage.getSearchProductTextbox();
        dashboardPage.selectProduct(ConfigLoader.get("articleNumber"));
    }
    @Test(priority = 5)
    public void verifyContinueButton(){
        dashboardPage.clickOnContinueButton();
    }
    @Test(priority = 6)
    public void verifyPromotionIsAppliedOrNot(){
        dashboardPage.promotionAppliedOrNot();
    }
    @Test(priority = 7)
    public void verifyPayNowButton(){
        dashboardPage.clickOnPayNowButton();
    }
    @Test(priority = 8)
    public void verifyCustomerDetailsOnOrderPage(){
        dashboardPage.verifyCustomerName();
    }
    @Test(priority = 9)
    public void verifyProceedToPaybutton(){
        dashboardPage.clickOnProceedToPayButton();
    }
    @Test(priority = 10)
    public void verifyPaymentTender(){
        dashboardPage.selectPaymentTender();
    }
    @Test(priority = 11)
    public void verifyPaymentDetailProceedToPay(){
        dashboardPage.verifyPaymentDetailProceedToPayButton();
    }
    @Test(priority = 12)
    public void verifyOrderIsCreated(){
        dashboardPage.checkOrderCreation();
    }

}
