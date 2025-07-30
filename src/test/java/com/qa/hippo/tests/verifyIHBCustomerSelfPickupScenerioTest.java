package com.qa.hippo.tests;

import com.qa.hippo.baseclass.BaseClass;
import com.qa.hippo.pages.DashboardPage;
import com.qa.hippo.utilities.ConfigLoader;
import com.qa.hippo.utilities.UtilClass;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class verifyIHBCustomerSelfPickupScenerioTest extends BaseClass {


    DashboardPage dashboardPage;

    @BeforeMethod
    public void setUp(){
        dashboardPage=new DashboardPage(driver);

    }

    @Test(priority = 1,enabled = false)
    public void searchCustomerInputField(){
        dashboardPage.getOpeningBalanceTextBox();
    }

    @Test(priority = 2, dependsOnMethods = "searchCustomerInputField",enabled = false)
    public void selectIHBCustomerSelfPickupTest(){
        String mobile=BaseClass.getMobile();
        dashboardPage.searchGivenCustomer(mobile);
        dashboardPage.CustomerAddButton();
    }

    /**
     * Checks after IHB customer is selected the cursor is moved to scan/search
     * product text box and it is highlighted
     */

    @Test(priority = 3)
    public void verifySearchProductTextboxIsHighlightedIHBSelfPickup() {
        UtilClass.sleep(2000);
        dashboardPage.getSearchProductTextbox();
    }

    /**
     * Verifies self pick up is selected by default
     */
    @Test(priority = 4, dependsOnMethods = "verifySearchProductTextboxIsHighlightedIHBSelfPickup")
    public void verifySelfPickupIsSelectedByDefaultIHBSelfPickup() {
        dashboardPage.clickOnDeliveryMode();
    }

    /**
     * Searches and selects article
     */

    @Test(priority = 5,dependsOnMethods = "verifySelfPickupIsSelectedByDefaultIHBSelfPickup")
   public void selectProductIHBSelfPickupTest(){
    dashboardPage.selectProduct(ConfigLoader.get("articleNumber"));
   }

   @Test(priority = 6,dependsOnMethods = "selectProductIHBSelfPickupTest")
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
    @Test(priority = 9)
    public void verifyCustomerDetailsOnOrderPage(){
        dashboardPage.verifyCustomerName();
    }
    @Test(priority = 10)
    public void verifyProceedToPaybutton(){
        dashboardPage.clickOnProceedToPayButton();
    }
    @Test(priority = 11)
    public void verifyPaymentTender(){
        dashboardPage.selectPaymentTender();
    }
    @Test(priority = 12)
    public void verifyPaymentDetailProceedToPay(){
        dashboardPage.verifyPaymentDetailProceedToPayButton();
    }
    @Test(priority = 13)
    public void verifyOrderIsCreated(){
        dashboardPage.checkOrderCreation();
    }


}
