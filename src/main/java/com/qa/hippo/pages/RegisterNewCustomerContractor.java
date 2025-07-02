package com.qa.hippo.pages;

import com.github.javafaker.Faker;
import com.qa.hippo.utilities.ConfigLoader;
import com.qa.hippo.utilities.HTPLLogger;
import com.qa.hippo.utilities.UtilClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class RegisterNewCustomerContractor{

    WebDriver driver;
    Faker faker = new Faker();
    public RegisterNewCustomerContractor(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@type='radio']/preceding::span[text()='CONTRACTOR']")
    WebElement contractorRadioButton;

    @FindBy(xpath = "//input[@id='createCustomerFormFirstName']")
    WebElement firstNameInputField;

    @FindBy(xpath = "//input[@id='createCustomerFormLastName']")
    WebElement lastNameInputField;

    @FindBy(xpath = "//input[@id='createCustomerFormMobile']")
    WebElement mobileNumberField;

    @FindBy(xpath = "//input[@id='createCustomerFormEmail']")
    WebElement emailInputField;

    @FindBy(xpath = "//input[@id='createCustomerFormPanNumber']")
    WebElement panNumberInputField;

    @FindBy(xpath = "//button[@id='createCustomerFormPanVerifyButton']")
    WebElement panValidateButton;

    @FindBy(xpath = "//input[@id='createCustomerFormGST']")
    WebElement gstNumberInputField;

    @FindBy(xpath = "//button[@id='createCustomerFormGSTVerifyButton']")
    WebElement gstNumberValidateButton;

    @FindBy(xpath = "(//input[@id='createCustomerFormMobile'])[2]")
    WebElement gstAddressField;

    @FindBy(xpath = "(//input[@id='createCustomerFormMobile'])[3]")
    WebElement tradeNameField;

    @FindBy(xpath = "//button[@type='button'][text()='Select Geocode']")
    WebElement selectGeoCodeButton;

    @FindBy(xpath = "(//input[@id='controlled-demo'  and  @aria-autocomplete='list' ])[2]")
    WebElement searchGeoCodeInputField;

    @FindBy(xpath = "//button[@type='button'][text()='Confirm Location']")
    WebElement confirmLocationButton;

    @FindBy(xpath = "//textarea[@id='createCustomerFormAddressDetails']")
    WebElement addressLineOneInputField;

    @FindBy(xpath = "//textarea[@id='createCustomerFormAddressDetails2']")
    WebElement addressLinetwoInputField;

    @FindBy(xpath = "//input[@id='createCustomerFormState']")
    WebElement stateInputField;

    @FindBy(xpath = "//input[@id='createCustomerFormCity']")
    WebElement cityInputField;
    @FindBy(xpath = "(//input[@id='createCustomerFormState'])[2]")
    WebElement businessNameInputField;

    @FindBy(xpath = "//input[@id='createCustomerFormZipcode']")
    WebElement pinCodeInputField;

    @FindBy(xpath = "(//input[@id='createCustomerFormState'])[3]")
    WebElement businessLicenseNameInputField;

    @FindBy(xpath = "(//input[@id='createCustomerFormState'])[4]")
    WebElement businessLicenceNumber;

    @FindBy(xpath = "//div[@id='createCustomerHearHippo']")
    WebElement hearAboutHippoDropdown;

    @FindBy(xpath = "//input[@id='createCustomerFormCity' and @type='number']")
    WebElement alternateMobileNumber;

    @FindBy(xpath = "(//input[@id='scanArticleSearchField'])[2]")
    WebElement employeeEmailDropdown;

    @FindBy(xpath = "//button[@id='createCustomerFormContinueButton']")
    WebElement continueToRegisterButton;

    public void radioButtonIsSelected(WebElement element,String fieldName){
        try {
            UtilClass.waitForElementPresent(element,2000);
            if (!element.isSelected()){
                UtilClass.waitForElementAndClick(element,2000);
                HTPLLogger.info(fieldName+" radio button is Selected!!");
            }else {
                HTPLLogger.error(fieldName+" radio button is already selected!!");
            }

        } catch (Exception e) {
            Assert.fail(fieldName+" Unable to select!!");
        }
    }
    public void contractorRadioButton(){
        radioButtonIsSelected(contractorRadioButton,"Contractor");
    }

    public void ContractoCustomerDetails(){

        try {
            UtilClass.waitForElementPresent(firstNameInputField,2000);
            firstNameInputField.sendKeys(faker.name().firstName());
            HTPLLogger.info("First Name is entered!!");
            UtilClass.waitForElementPresent(lastNameInputField,2000);
            lastNameInputField.sendKeys(faker.name().lastName());
            HTPLLogger.info("Last Name is entered!!");
            UtilClass.waitForElementPresent(emailInputField,2000);
            emailInputField.sendKeys(faker.internet().emailAddress());
            HTPLLogger.info("Email-ID is entered!!");

        }catch (Exception e){
            HTPLLogger.error("Unable to enter Customer details!!!",e);
        }

    }
    public void checkMobileInputField(){
        try {
            UtilClass.waitForElementPresent(mobileNumberField,2000);
            checkInputFieldIsDisabled(mobileNumberField,"Mobile Number","");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void checkPanCardInputField(){
        try {

            UtilClass.waitForElementPresent(panNumberInputField,2000);
            checkInputFieldIsDisabled(panNumberInputField,"Pan Card", ConfigLoader.get("PANCARD"));
            UtilClass.waitForElementAndClick(panValidateButton,2000);
            HTPLLogger.info("Pan card validated!!");
        } catch (Exception e) {
            HTPLLogger.error("Unable to verify Pan number input field!!",e);
            throw new RuntimeException(e);
        }
    }



    public void checkGSTNumberInputFields(){
        try {

            UtilClass.waitForElementPresent(gstNumberInputField,2000);
            checkInputFieldIsDisabled(gstNumberInputField,"GST Number",ConfigLoader.get("GSTNumber"));
            UtilClass.waitForElementAndClick(gstNumberValidateButton,2000);
            HTPLLogger.info("GST number validated!!!");
            UtilClass.sleep(2000);
            checkInputFieldIsDisabled(gstAddressField,"GST Address","");
            UtilClass.sleep(2000);
            checkInputFieldIsDisabled(tradeNameField,"Trade Name","");

        } catch (Exception e) {
            HTPLLogger.error("Unable to verify GST number input field!!",e);
            throw new RuntimeException(e);
        }
    }
    public static boolean checkInputFieldIsDisabled(WebElement locator,String fieldName,String inpuData) {
        try {
            boolean isDisabled = !locator.isEnabled(); // field is disabled
            boolean hasValue = !locator.getAttribute("value").isEmpty(); // value exists

            if (isDisabled && hasValue) {
                HTPLLogger.info("✅ " + fieldName + " field is disabled and has a value: " + locator.getAttribute("value"));
                return true;
            } else if (hasValue) {
                HTPLLogger.info("✅ "+ fieldName + " field has a value : " + locator.getAttribute("value"));
                return true;
            } else {
                HTPLLogger.info(fieldName + " field is enabled and has no value. Entered "+inpuData);
                locator.sendKeys(inpuData);
                return false;
            }

        } catch (Exception e) {
            HTPLLogger.error("⚠️ Exception occurred while validating " + fieldName + " field: " + e.getMessage());
            return false;
        }
    }


    public void selectGeoCodeBtn(){
        try {

            UtilClass.waitForElementAndClick(selectGeoCodeButton,2000);
            HTPLLogger.info("Clicked on Select GeoCode Button!!");

        } catch (Exception e) {
            HTPLLogger.error("Unable to click on GeoCode button!!");
            throw new RuntimeException(e);
        }
    }

    public void searchLocation(String PinCode){

        try {
            UtilClass.waitForElementPresent(searchGeoCodeInputField,2000);
            if (searchGeoCodeInputField.isDisplayed()){
                searchGeoCodeInputField.sendKeys(PinCode);
                HTPLLogger.info(PinCode + " is searched!!");
                UtilClass.sleep(2000);
                selectAddressFromGeoCode(PinCode);
                UtilClass.sleep(2000);
                confirmLocation();
            }
        } catch (Exception e) {
            HTPLLogger.error("Unable to search "+PinCode);
            throw new RuntimeException(e);
        }

    }
    public void selectAddressFromGeoCode(String locationValue){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='presentation']//div[@class='MuiAutocomplete-option' or contains(@class, 'MuiAutocomplete-option')][.//text()[contains(., '"+locationValue+"')]]")
        ));
        option.click();
    }

    public void confirmLocation(){
        try {
            UtilClass.waitForElementPresent(confirmLocationButton,2000);
            if (confirmLocationButton.isEnabled()){
                confirmLocationButton.click();
                HTPLLogger.info("Clicked on confirm location button!!");
            }else {
                HTPLLogger.error("Confirm location button is not visible!!");
            }
        } catch (Exception e) {
            Assert.fail("Confirm location button is not visible!!",e);
        }
    }

    public void checkAddressAfterGeoCode(){
        checkInputFieldIsDisabled(addressLineOneInputField,"Address Line 1","Business bay 2");
        UtilClass.sleep(1000);
        checkInputFieldIsDisabled(addressLinetwoInputField,"Address Line 2","Testing");
        UtilClass.sleep(1000);
        checkInputFieldIsDisabled(stateInputField,"State","State");
        UtilClass.sleep(1000);
        checkInputFieldIsDisabled(cityInputField,"City","City");
        UtilClass.sleep(1000);
        checkInputFieldIsDisabled(pinCodeInputField,"PinCode","PinCode");
    }

    public void selectEmployee(String email){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='presentation']//li[contains(text(), '"+email+"')]")
        ));
        option.click();
    }

    public void checkOtherDetails(){
        UtilClass.sleep(1000);
        UtilClass.scrollToElement(hearAboutHippoDropdown);
        UtilClass.sleep(1000);
        businessNameInputField.sendKeys(ConfigLoader.get("BUSINESS_NAME"));
        UtilClass.sleep(1000);
        businessLicenseNameInputField.sendKeys(ConfigLoader.get("BUSINESS_LICENSE_NAME"));
        UtilClass.sleep(1000);
        businessLicenceNumber.sendKeys(ConfigLoader.get("BUSINESS_LICENSE_NUMBER"));
        UtilClass.sleep(1000);
        alternateMobileNumber.sendKeys("7766554451");
        UtilClass.sleep(1000);
        employeeEmailDropdown.click();
        employeeEmailDropdown.sendKeys("yash");
        UtilClass.sleep(1000);
        selectEmployee("yash@demo.com");
    }

}
