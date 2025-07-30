package com.qa.hippo.pages;

import com.qa.hippo.baseclass.BaseClass;
import com.qa.hippo.utilities.HTPLLogger;
import com.qa.hippo.utilities.UtilClass;
import jdk.jshell.execution.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.security.PublicKey;
import java.time.Duration;
import java.util.List;

public class DashboardPage {

    WebDriver driver;
    private String promotionAppliedMessage="Promotion applied successfully";
    private String actualWalkinCustomerName = "Name: walk-in (IHB)";
    private String firstQuestion="Adequate space for movement of the tiles/ply?";
    private String secondQuestion="Service Lifts are available and functional?";
    private String thirdQuestion="Adequate space for vehicle parking?";
    private String fourQuestion="Is it House or Multi floor apartment?";


    public DashboardPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@id='searchCustomerField']")
    WebElement searchCustomerInputField;
    @FindBy(xpath = "//input[@id='searchCustomerField']")
    WebElement searchCustomerTextbox;
    @FindBy(xpath="//button[@id='customButton']")
    WebElement customerAddBtn;
    @FindBy(xpath = "//span[@id='tableFilterBarCustomerName']")
    WebElement customerNameContactArea;

    @FindBy(xpath = "//input[@id='openTillAmount']")
    WebElement OpeningAmountInputField;

    @FindBy(xpath = "//button[text()='Done']")
    WebElement DoneButton;

    @FindBy(xpath = "//button[@id='CustomModalOkButton']")
    WebElement okButton;

    @FindBy(xpath = "//input[@id='scanArticleSearchField']")
    WebElement searchProductTextbox;

    @FindBy(xpath = "//button[contains(@class,'deliveryMode')]")
    WebElement deliveryModeButton;

    @FindBy(xpath = "//tr[@role='row']/th[text()='Qty.']")
    WebElement randomclick;
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
    @FindBy(xpath = "//input[@type='checkbox' and @id='deliveryMode']")
    WebElement deliveryCheckbox;
    @FindBy(xpath = "//input[@type='checkbox' and @id='SelfPickUp']")
    WebElement selfPickupChecBox;

    @FindBy(xpath = "//div[@role='dialog']/div/p[text()='Do you want to avail that service?']")
    WebElement unloadingDialogBox;

    @FindBy(xpath = "//button[@id='changeShippingAddressSaveButton']")
    WebElement saveSelectionButton;

    @FindBy(xpath = "//button[@id='unloadingChargesModalYesButton']")
    WebElement unloadingChargesModalYesButton;

    @FindBy(xpath = "//div[@id='unloadingChargesModalSelectFloorNo']")
    WebElement unloadingChargesModalSelectFloorNo;

    @FindBy(xpath = "//div[contains(@class, 'loadingChargesStyle')]/p[2]")
    WebElement unloadingAmount;

    @FindBy(xpath = "//button[@id='unloadingChargesModalConfirmButton']")
    WebElement unloadingChargesModalConfirmButton;


    @FindBy(xpath = "//button[@id='unloadingChargesModalNoButton']")
    WebElement unloadingChargesModalNoButton;

    public void getOpeningBalanceTextBox() {
        try {
            // Use same XPath as @FindBy to check safely
            List<WebElement> elements = driver.findElements(By.xpath("//input[@id='openTillAmount']"));
            if (!elements.isEmpty() && elements.get(0).isDisplayed() && elements.get(0).isEnabled()) {
                elements.get(0).sendKeys("0");
                UtilClass.waitForElementAndClick(DoneButton, 2000);
                HTPLLogger.info("Entered amount in open till text box");
                UtilClass.waitForElementAndClick(okButton, 2000);
            } else {
                getSearchCustomerTextBox();
            }
        } catch (Exception e) {
            HTPLLogger.error("Failure in Test method getOpeningBalanceTextBox",e);
            Assert.fail("Failure in Test method getOpeningBalanceTextBox",e);
        }
    }



    public void getSearchCustomerTextBox(){
       try {
           if (searchCustomerInputField.isDisplayed() && searchCustomerInputField.isEnabled()){
               HTPLLogger.info("Search customer cursor is blinking in the input field.");
           } else {
              HTPLLogger.warn("Search customer cursor is not blinking in the input field.");
           }
       } catch (RuntimeException e) {
           HTPLLogger.error("Failure in Test method getSearchCustomerTextBox",e);
           Assert.fail("Failure in Test method getSearchCustomerTextBox",e);       }
    }

    /**
     * Searches given customer
     *
     * @param CustomerNumber
     */
    public void searchGivenCustomer(String CustomerNumber) {
       try {
           WebDriverWait waitForReload=new WebDriverWait(driver, Duration.ofSeconds(2000));
           waitForReload.until(ExpectedConditions.visibilityOf(searchCustomerTextbox));
           searchCustomerTextbox.sendKeys(CustomerNumber);
           HTPLLogger.info("Customer is searched by mobile number :" + CustomerNumber);
       }catch (Exception e){
           HTPLLogger.error("Failure in Test method searchGivenCustomer",e);
           Assert.fail("Failure in Test method searchGivenCustomer",e);
       }
    }

    public void CustomerAddButton(){
        UtilClass.waitForElementAndClick(customerAddBtn,2000);
        HTPLLogger.info("Clicked on Add button!!");
    }


    public void getSearchProductTextbox(){
    try {
        String className=searchProductTextbox.getAttribute("class");
        Assert.assertEquals(true,className.contains("Focused"),"Failed to highlight the scan/search product text box");
    }catch (Exception e){
        HTPLLogger.error("Failure in test method getSearchProductTextbox", e);
        Assert.fail("Failure in test method getSearchProductTextbox", e);
    }
    }

    public void clickOnDeliveryMode(){
        try {
            UtilClass.waitForElementAndClick(deliveryModeButton,2000);
            UtilClass.sleep(5000);
            int count = driver.findElements(By.xpath("(//input[@type='checkbox'])[1]")).size();
            Assert.assertEquals(count, 1, "Self pick up option is not enabled by default");
            HTPLLogger.info("Delivery mode as Self pickup is selected!!");
        } catch (RuntimeException e) {
            HTPLLogger.error("Failure in clickOnDeliveryMode method, Unable to select Self pick Up!!",e);
            Assert.fail("Failure in test method verifySelfPickupIsSelectedByDefaultIHBSelfPickup!",e);
        }
    }

    public void clickOnDeliveryModeButton(){
        try {
            UtilClass.waitForElementPresent(deliveryModeButton, 2000);
            if (deliveryModeButton.isDisplayed()) {
                deliveryModeButton.click();
                HTPLLogger.info("Clicked on Delivery Mode button!!");
            } else {
                HTPLLogger.error("Delivery mode button is not displayed!!");
            }
        } catch (Exception e) {
            HTPLLogger.error("Delivery mode button is not displayed!!",e);
        }
    }

    /**
     * Selects product
     * @param articleNumber
     */

    public void selectProduct(String articleNumber) {
       try {
           UtilClass.waitForElementPresent(searchProductTextbox,2000);
           searchProductTextbox.sendKeys(articleNumber);
           UtilClass.sleep(5000);
           randomclick.click();
           HTPLLogger.info("Searched product - " + articleNumber);
       } catch (Exception e) {
           HTPLLogger.error("Failure in selectProduct method, Unable to select Article!!",e);
           Assert.fail("Failure in selectProduct method, Unable to select Article!!",e);
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
            UtilClass.sleep(20000);
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

    /**
     * Selects delivery mode as delivery
     *
     */

    public void selectDeliveryOption() {
        UtilClass.sleep(3000);
        deliveryCheckbox.click();
        HTPLLogger.info("Selected delivery mode as delivery");
    }

    public void selectAddress(String mobile){
        try {
            UtilClass.sleep(2000);
          WebElement address=driver.findElement(By.xpath("//div[contains(@id,'changeShippingAddressList')]/div/p[text()='"+mobile+"']"));
          if (address.getText().equalsIgnoreCase(BaseClass.getMobile()))
          {
              address.click();
              HTPLLogger.info("Address is Selected!!");
              clickOnSaveSelectionButton();
          }
          else {
              HTPLLogger.error("Unable to select Address!!");
          }

        }catch (Exception e){
            HTPLLogger.error("Unable to select Address!!");
        }
    }

    public void clickOnSaveSelectionButton(){
        try {
            UtilClass.sleep(2000);
            if (saveSelectionButton.isEnabled()){
                saveSelectionButton.click();
                HTPLLogger.info("Clicked on save selection button!!");
            }
            else {
                HTPLLogger.error("Unable to click on save selection button!!");
            }

        } catch (Exception e) {
            HTPLLogger.error("Unable to click on save selection button!!",e);
        }
    }

    public void unloadingChargesCalculation(String availUnloading){

        try {
            if (unloadingDialogBox.getText().contains("avail that service")){
                if (availUnloading.equalsIgnoreCase("Yes")) {
                    UtilClass.waitForElementAndClick(unloadingChargesModalYesButton, 2000);
                    unloadingChargesForm("3");
                    UtilClass.waitForElementAndClick(unloadingChargesModalConfirmButton, 2000);
                    HTPLLogger.info("Unloading charges is calculated !!!!");
                }else {
                    UtilClass.sleep(2000);
                    UtilClass.waitForElementAndClick(unloadingChargesModalNoButton,2000);
                }
            }
        }catch (Exception e){
            HTPLLogger.error("Unloading charges function got Failed!!",e);
        }

    }

    public void unloadingChargesForm(String floorNumber){

       try {
           UtilClass.waitForElementAndClick(unloadingChargesModalSelectFloorNo,2000);
          WebElement floorOption= driver.findElement(By.xpath("//ul[@role='listbox']/li[@data-value='"+floorNumber+"']"));
          floorOption.click();
          UtilClass.sleep(1000);
          unloadingFormQuestions(firstQuestion,"Yes");
          unloadingFormQuestions(secondQuestion,"Yes");
          unloadingFormQuestions(thirdQuestion,"Yes");
          unloadingFormQuestions(fourQuestion,"Yes");
          UtilClass.sleep(2000);
          HTPLLogger.info("Final unloading charges -> "+ unloadingAmount.getText());

       } catch (Exception e) {
           HTPLLogger.error("unloading charges Form function got failed!!",e);
       }
    }

    public void unloadingFormQuestions(String question,String radioOption){
        int index = radioOption.equalsIgnoreCase("Yes") ? 1 : 2;
        WebElement radioButton = driver.findElement(
                By.xpath("//p[text()='" + question + "']/following::input[@type='radio'][" + index + "]")
        );
        radioButton.click();
        HTPLLogger.info(index +"Radio button for " +question + "is Selected!!");
    }



}