package com.qa.hippo.pages;

import com.qa.hippo.utilities.HTPLLogger;
import com.qa.hippo.utilities.UtilClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class DashboardPage {

    WebDriver driver;

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
     * @param customerName
     */
    public void searchGivenCustomer(String customerName) {
       try {
           WebDriverWait waitForReload=new WebDriverWait(driver, Duration.ofSeconds(2000));
           waitForReload.until(ExpectedConditions.visibilityOf(searchCustomerTextbox));
           searchCustomerTextbox.sendKeys(customerName);
           HTPLLogger.info("Customer is searched by mobile number" + customerName);
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
           HTPLLogger.info("Searches product" + articleNumber);
       } catch (Exception e) {
           HTPLLogger.error("Failure in selectProduct method, Unable to select Article!!",e);
           Assert.fail("Failure in selectProduct method, Unable to select Article!!",e);
       }
    }


}
