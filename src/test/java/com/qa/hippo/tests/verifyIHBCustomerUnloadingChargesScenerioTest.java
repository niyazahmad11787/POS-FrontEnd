package com.qa.hippo.tests;

import com.qa.hippo.baseclass.BaseClass;
import com.qa.hippo.pages.DashboardPage;
import com.qa.hippo.utilities.ConfigLoader;
import com.qa.hippo.utilities.UtilClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class verifyIHBCustomerUnloadingChargesScenerioTest extends BaseClass{

    DashboardPage dashboardPage;
    @BeforeMethod
    public void setUp(){
        dashboardPage=new DashboardPage(driver);

    }

    @Test(priority = 1)
    public void selectIHBCustomerDeliveryTest(){
        String mobile= BaseClass.getMobile();
        dashboardPage.searchGivenCustomer(mobile);
        dashboardPage.CustomerAddButton();
    }

    @Test(priority = 2)
    public void verifyDeliveryModeIsSelected(){
        dashboardPage.clickOnDeliveryModeButton();
        dashboardPage.selectDeliveryOption();
    }

    @Test(priority = 3)
    public void verifyAddressIsSelected(){
        dashboardPage.selectAddress(BaseClass.getMobile());
    }

    @Test(priority = 4)
    public void verifySearchProductTextboxIsHighlightedIHBDelivery() {
        UtilClass.sleep(2000);
        dashboardPage.getSearchProductTextbox();
    }
    @Test(priority = 5,dependsOnMethods = "verifySearchProductTextboxIsHighlightedIHBDelivery")
    public void selectProductIHBDeliveryTest(){
        dashboardPage.selectProduct(ConfigLoader.get("articleNumber"));
    }

    @Test(priority = 6,dependsOnMethods = "selectProductIHBDeliveryTest")
    public void verifyContinueButton(){
        dashboardPage.clickOnContinueButton();
    }
    @Test(priority = 7,dependsOnMethods = "verifyContinueButton")
    public void verifyPromotionIsAppliedOrNot(){
        dashboardPage.promotionAppliedOrNot();
    }

    @Test(priority = 8,dependsOnMethods = "verifyPromotionIsAppliedOrNot")
    public void verifyPayNowButton(){
        dashboardPage.clickOnPayNowButton();
    }
    @Test(priority = 9,dependsOnMethods = "verifyPayNowButton")
    public void verifyUnloadingChargesAppliedOrNot(){
        dashboardPage.unloadingChargesCalculation("Yes");
        UtilClass.sleep(2000);
        dashboardPage.clickOnPayNowButton();
    }
    @Test(priority = 10)
    public void verifyCustomerDetailsOnOrderPage(){
        dashboardPage.verifyCustomerName();
    }
    @Test(priority = 11)
    public void verifyProceedToPaybutton(){
        dashboardPage.clickOnProceedToPayButton();
    }
    @Test(priority = 12)
    public void verifyPaymentTender(){
        dashboardPage.selectPaymentTender();
    }
    @Test(priority = 13)
    public void verifyPaymentDetailProceedToPay(){
        dashboardPage.verifyPaymentDetailProceedToPayButton();
    }
    @Test(priority = 14)
    public void verifyOrderIsCreated(){
        dashboardPage.checkOrderCreation();
    }
}
