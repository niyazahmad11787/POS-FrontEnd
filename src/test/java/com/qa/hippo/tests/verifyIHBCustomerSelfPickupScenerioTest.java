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
    private WebDriverWait waitForReload;

    @BeforeMethod
    public void setUp(){
        dashboardPage=new DashboardPage(driver);

    }

    @Test(priority = 1)
    public void searchCustomerInputField(){
        dashboardPage.getOpeningBalanceTextBox();
    }

    @Test(priority = 2, dependsOnMethods = "searchCustomerInputField")
    public void selectIHBCustomerSelfPickupTest(){
        dashboardPage.searchGivenCustomer("9129896786");
        dashboardPage.CustomerAddButton();
    }

    /**
     * Checks after IHB customer is selected the cursor is moved to scan/search
     * product text box and it is highlighted
     */

    @Test(priority = 3, dependsOnMethods = "selectIHBCustomerSelfPickupTest")
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

}
