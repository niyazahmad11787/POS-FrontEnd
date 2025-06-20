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
    private String promotionAppliedMessage="Promotion applied successfully";

    @FindBy(xpath = "//button[contains(.,'Walkin Customer')]")
    WebElement walkinCustomerButton;
    @FindBy(xpath="//button[@id='customButton']")
    WebElement customerAddBtn;
    @FindBy(xpath = "//span[@id='tableFilterBarCustomerName']")
    WebElement customerNameContactArea;

    @FindBy(xpath = "//button[@id='dashBoardCashDetailsContinueButton']")
    WebElement continueButton;

    @FindBy(xpath = "//p[@id='dashBoardErrorMessageModalErrorMessage']")
    WebElement promotionMessage;

    @FindBy(xpath = "//button[@id='dashBoardErrorMessageModalOkButton']")
    WebElement promotionOkButton;

    @FindBy(xpath = "//button[@id='dashBoardCashDetailsContinueButton']")
    WebElement payNowButton;

    @FindBy(xpath = "//div[@id='customerDetailsUserName']")
    WebElement customerNameTextField;

    @FindBy(xpath = "//button[@id='customerDetailsProceedToPayButton']")
    WebElement proceedToPayButton;

    @FindBy(xpath = "//button[@id='paymentsDetails1']")
    WebElement cashTender;

    @FindBy(xpath = "//button[@id='cashModalOkButton']")
    WebElement cashTenderOKButton;

    @FindBy(xpath = "//button[@id='paymentDetailProceedToPay']")
    WebElement paymentDetailProceedToPay;

    @FindBy(xpath = "//div[@id='closePdfButton']")
    WebElement closePDFButton;

    @FindBy(xpath = "//span[@id='orderPDFOrderNumberTop']")
    WebElement getOrderNumber;

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

    public void clickOnContinueButton(){
        try {
            UtilClass.waitForElementAndClick(continueButton,3000);
            HTPLLogger.info("Clicked on continue button!!");
        }catch (Exception e)
        {
            HTPLLogger.error("Unable to click on Continue button!!");
            Assert.fail("Unable to click on Continue button!!",e);
        }
    }

    public void promotionAppliedOrNot(){

        try {
            UtilClass.waitForElementPresent(promotionMessage,3000);
            if (promotionMessage.getText().equalsIgnoreCase(promotionAppliedMessage)){
                UtilClass.waitForElementAndClick(promotionOkButton,2000);
                HTPLLogger.info(promotionAppliedMessage);
            }
        }catch (Exception e){
            HTPLLogger.error("Promotion is not Applied!!");
            Assert.fail(e.getMessage());
        }

    }

    public void clickOnPayNowButton(){
        try {
            UtilClass.waitForElementPresent(payNowButton,3000);
            if (payNowButton.isEnabled()){
                UtilClass.waitForElementAndClick(payNowButton,2000);
                HTPLLogger.info("Clicked on PayNow Button!!");
            }else {
                HTPLLogger.error("Paynow Button is not enabled!!");
            }
        }catch (Exception e){
            HTPLLogger.error("Paynow Button is not enabled!!");
            Assert.fail(e.getMessage());
        }
    }

    public void verifyCustomerName(){
        UtilClass.waitForElementPresent(customerNameTextField,3000);
        UtilClass.sleep(2000);
        System.out.println(customerNameTextField.getText());
        if (customerNameTextField.getText().contains(actualWalkinCustomerName)){

            HTPLLogger.info(customerNameTextField.getText()+" customer is verified on order details page!!");
        }
        else {
            HTPLLogger.info("Unable to verified customer name !!!!");
        }
    }

    public void clickOnProceedToPayButton(){
        try {
            UtilClass.waitForElementAndClick(proceedToPayButton,3000);
            HTPLLogger.info("Clicked on proceed to pay button!!");
        }catch (Exception e){
            HTPLLogger.error("Unable to click on proceed to pay button",e);
        }
    }

    public void selectPaymentTender(){
        try {
            UtilClass.sleep(2000);
            if (!cashTender.isSelected()){
                UtilClass.waitForElementAndClick(cashTender,3000);
                UtilClass.sleep(2000);
                UtilClass.waitForElementAndClick(cashTenderOKButton,3000);
                HTPLLogger.info("Cash payment tender was successfully selected and confirmed.");
            }
            else {
                HTPLLogger.error("Cash payment tender appears to be already selected or the tender option is not interactable.");
            }
        }catch (Exception e){
            HTPLLogger.error("Exception occurred while selecting the cash payment tender. Possible cause: element not visible or clickable. Exception Message: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    public void verifyPaymentDetailProceedToPayButton(){
        try {
            UtilClass.sleep(2000);
            if (paymentDetailProceedToPay.isDisplayed() && paymentDetailProceedToPay.isEnabled()){
                UtilClass.waitForElementAndClick(paymentDetailProceedToPay,2000);
                HTPLLogger.info("Successfully clicked on the 'Proceed to Pay' button in the Payment Details section.");
            }
            else {
                HTPLLogger.error("'Proceed to Pay' button in the Payment Details section is either not visible or not enabled.");
            }
        } catch (Exception e) {
            HTPLLogger.error("Exception occurred while interacting with the 'Proceed to Pay' button in the Payment Details section. Exception Message: " + e.getMessage());
            Assert.fail(e.getMessage());
        }

    }

    public void checkOrderCreation(){
        try {
            UtilClass.sleep(30000);
            if (closePDFButton.isDisplayed() && closePDFButton.isEnabled()){

                HTPLLogger.info("✅ Order has been created successfully. PDF close button is visible and enabled.");
                HTPLLogger.info("Order Creation Successful → Order Number: " + getOrderNumber.getText());
                UtilClass.waitForElementAndClick(closePDFButton,3000);
            }
            else {
                HTPLLogger.error("❌ Order creation failed. PDF close button is either not visible or not enabled.");
            }
        }catch (Exception e){
            HTPLLogger.error("⚠️ Exception occurred while verifying order creation. Possible reasons: PDF close button not found or page not loaded properly. Exception Message: " + e.getMessage());
        }
    }
}
