package com.qa.hippo.pages;

import com.qa.hippo.utilities.HTPLLogger;
import com.qa.hippo.utilities.UtilClass;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class WalkInCustomerPage {

    WebDriver driver;
    DashboardPage dashboardPage;
    private String actualWalkinCustomerName = "Name: walk-in (IHB)";
    @FindBy(xpath = "//button[contains(.,'Walkin Customer')]")
    WebElement walkinCustomerButton;
    @FindBy(xpath="//button[@id='customButton']")
    WebElement customerAddBtn;
    @FindBy(xpath = "//span[@id='tableFilterBarCustomerName']")
    WebElement customerNameContactArea;

    public WalkInCustomerPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    /**
     * Selects walk-in customer
     *
     */
    public void clickOnCustomerAddButton() {
        try {
            if (isElementPresent(customerAddBtn)) {
                HTPLLogger.info("Customer is already registered. 'Add' button is visible.");
            } else if (walkinCustomerButton.isDisplayed()) {
                HTPLLogger.info("Customer is not registered. Clicking 'Walk-in Customer' button.");
                walkinCustomerButton.click();
            } else {
                HTPLLogger.warn("Walk-in Customer button not found, and customer may be registered.");
            }
        } catch (Exception e) {
            HTPLLogger.error("Error occurred while clicking Walk-in Customer button: " + e.getMessage(), e);
        }
    }
    public boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    /**
     * Checks after walk-in customer is selected the cursor is moved to scan/search
     * product text box and it is highlighted
     */
    public void verifyWalkinCustomerIsSelected() {
       try {
           UtilClass.waitForElementPresent(customerNameContactArea,2000);
           UtilClass.sleep(5000);
           String expectedWalkinCustomerName = customerNameContactArea.getText();
           Assert.assertEquals(true, expectedWalkinCustomerName.contains(actualWalkinCustomerName),
                   "Failed to select walkin customer");
           HTPLLogger.info("Walk-in customer selected successfully!!");
       } catch (Exception e) {
           HTPLLogger.error("Walk-in is not selected!!");
       }
    }
}
