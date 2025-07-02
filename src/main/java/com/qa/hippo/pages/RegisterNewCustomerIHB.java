package com.qa.hippo.pages;

import com.github.javafaker.Faker;
import com.qa.hippo.utilities.HTPLLogger;
import com.qa.hippo.utilities.OTPFetcher;
import com.qa.hippo.utilities.UtilClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class RegisterNewCustomerIHB{

    WebDriver driver;
    Faker faker = new Faker();
    public RegisterNewCustomerIHB(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[contains(.,'Register Customer')]")
    WebElement RegisterCustomerButton;
    @FindBy(xpath="//button[@id='customButton']")
    WebElement customerAddBtn;

    @FindBy(xpath = "//input[@id='otpVerificationDialogBoxMobile']")
    WebElement otpVerificationDialogBoxMobile;

    @FindBy(xpath = "//button[@id='otpVerificationDialogBoxSubmit']")
    WebElement otpVerificationDialogBoxSubmit;

    @FindBy(xpath = "//input[contains(@aria-label, 'Digit')]")
    private List<WebElement> otpFields;
    @FindBy(xpath = "//button[@id='otpVerificationDialogBoxSubmit']")
    private WebElement submitOtpButton;

    @FindBy(xpath = "//div[@id='selectsubcustomer']")
    WebElement selectsubcustomerDropdown;

    @FindBy(xpath = "//input[@type='radio']/preceding::span[text()='IHB']")
    WebElement ihbRadioButton;

    @FindBy(xpath = "//ul[@role='listbox']/li[text()='APPLICATOR']")
    WebElement applicatorSubCustomerType;

    @FindBy(xpath = "//input[@id='createCustomerFormFirstName']")
    WebElement firstNameInputField;

    @FindBy(xpath = "//input[@id='createCustomerFormLast Name']")
    WebElement lastNameInputField;

    @FindBy(xpath = "//input[@id='createCustomerFormMobile']")
    WebElement mobileNumberField;

    @FindBy(xpath = "//input[@id='createCustomerFormEmail']")
    WebElement emailInputField;

    @FindBy(xpath = "//input[@id='createCustomerFormPanNumber']")
    WebElement panNumberInputField;

    @FindBy(xpath = "//input[@id='createCustomerFormGST']")
    WebElement GstNumberInputField;

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
    @FindBy(xpath = "//input[@id='createCustomerFormLandmark']")
    WebElement landMarkInputField;

    @FindBy(xpath = "//input[@id='createCustomerFormZipcode']")
    WebElement pinCodeInputField;

    @FindBy(xpath = "//input[@id='default-billingAddress']")
    WebElement defualtBillingAddressCheckBox;

    @FindBy(xpath = "//div[@id='createCustomerHearHippo']")
    WebElement hearAboutHippoDropdown;

    @FindBy(xpath = "//button[@id='createCustomerFormContinueButton']")
    WebElement continueToRegisterButton;



    /**
     * Selects walk-in customer
     *
     */
    public void clickOnCustomerAddButton() {
        try {
            if (isElementPresent(customerAddBtn)) {
                HTPLLogger.info("Customer is already registered. 'Add' button is visible.");
            } else if (RegisterCustomerButton.isDisplayed()) {
                HTPLLogger.info("Customer is not registered. Clicking 'Register Customer' button.");
                RegisterCustomerButton.click();
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

    public void enterPhoneNumber(String phone){

        UtilClass.waitForElementPresent(otpVerificationDialogBoxMobile,2000);
        if (otpVerificationDialogBoxMobile.isDisplayed()){
            otpVerificationDialogBoxMobile.sendKeys(phone);
            HTPLLogger.info("Mobile number is Entered!!");
        }
        else {
            HTPLLogger.error("Unable to enter mobile number");
        }
    }

    public void generateOtpButton(){
        UtilClass.waitForElementPresent(otpVerificationDialogBoxSubmit,2000);
        if (otpVerificationDialogBoxSubmit.isEnabled()){
            UtilClass.waitForElementAndClick(otpVerificationDialogBoxSubmit,2000);
            HTPLLogger.info("Clicked on Generate Otp button!!");
        }else {
            HTPLLogger.error("Unable to click on generate otp button!!");
        }
    }
    public boolean verifyOTPFunctionality(String mobile_number) throws InterruptedException {
        try {
            String otp= OTPFetcher.fetchOTP(mobile_number);
            if (otp != null) {
                enterOTP(otp);
                HTPLLogger.info("Enter otp and click on Submit button!!");
                clickSubmitOtpButton();
            } else {
                HTPLLogger.info("OTP not found for the specified mobile number.");
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void enterOTP(String otp) {

        if (otpFields.size() == 6) {
            for (int i = 0; i < otp.length(); i++) {
                otpFields.get(i).sendKeys(String.valueOf(otp.charAt(i)));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            HTPLLogger.info("Expected 6 OTP input fields, found: " + otpFields.size());
        }
    }
    public void clickSubmitOtpButton() throws InterruptedException {
        UtilClass.sleep(2000);
        submitOtpButton.click();
    }

    public boolean isRadioButtonSelected(WebElement radioButton) {
        return radioButton.isSelected();
    }

    public void customerCreationForm(){
        ihbRadioButtonIsSelected();
        selectSubCustomerType();
        UtilClass.sleep(1000);
        enterCustomerDetails();
        UtilClass.sleep(1000);
        isMobileNumberFieldIsDisabled();
        UtilClass.sleep(1000);
        isPanNumberFieldIsEnabled();
        UtilClass.sleep(1000);
        isGSTNumberFieldIsEnabled();
        UtilClass.sleep(1000);
        enterAddressDetails();
        UtilClass.sleep(1000);
        hearAboutHippoDropdown("Radio");
        clickOnContinueToRegisterButton();
    }

    public void ihbRadioButtonIsSelected(){
      try {
          if (isRadioButtonSelected(ihbRadioButton)){
              HTPLLogger.info("IHB radio button is selected!!");
          }
          else {
              HTPLLogger.error("IHB radio button is not Selected!!!");
          }
      } catch (Exception e) {
          HTPLLogger.error("IHB radio button is not Selected!!!",e);
      }
    }

    public void selectSubCustomerType(){
        try {
            UtilClass.waitForElementAndClick(selectsubcustomerDropdown,2000);
            UtilClass.waitForElementAndClick(applicatorSubCustomerType,2000);
            HTPLLogger.info(applicatorSubCustomerType.getText() +" is Selected!!");
        }
        catch (Exception e){
            HTPLLogger.error("Unable to select subCustomer type!!");
        }
    }

    public void enterCustomerDetails(){

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


    public static boolean isFieldDisabledWithValue(WebElement locator, String fieldName) {
        try {
            boolean isDisabled = !locator.isEnabled(); // field is disabled
            boolean hasValue = !locator.getAttribute("value").isEmpty(); // value exists

            if (isDisabled && hasValue) {
                System.out.println("✅ " + fieldName + " field is disabled and has a value: " + locator.getAttribute("value"));
                return true;
            } else {
                System.out.println("❌ Either the " + fieldName + " field is enabled or has no value.");
                return false;
            }

        } catch (Exception e) {
            System.out.println("⚠️ Exception occurred while validating " + fieldName + " field: " + e.getMessage());
            return false;
        }
    }
    public static boolean isAddressFieldDisabledWithValue(WebElement locator,String fieldName,String inpuData) {
        try {
            boolean isDisabled = !locator.isEnabled(); // field is disabled
            boolean hasValue = !locator.getAttribute("value").isEmpty(); // value exists

            if (isDisabled && hasValue) {
                HTPLLogger.info("✅ " + fieldName + " field is disabled and has a value: " + locator.getAttribute("value"));
                return true;
            } else if (hasValue) {
                HTPLLogger.info("✅ "+ fieldName + "field has a value : " + locator.getAttribute("value"));
                return true;
            } else {
                HTPLLogger.info(fieldName + " field is enabled and has no value.");
                locator.sendKeys(inpuData);
                return false;
            }

        } catch (Exception e) {
            HTPLLogger.error("⚠️ Exception occurred while validating " + fieldName + " field: " + e.getMessage());
            return false;
        }
    }



    public void isMobileNumberFieldIsDisabled(){
        isFieldDisabledWithValue(mobileNumberField,"Mobile Number");
    }

    public void isPanNumberFieldIsEnabled(){
        isFieldDisabledWithValue(panNumberInputField,"Pan Number");
    }

    public void isGSTNumberFieldIsEnabled(){
        isFieldDisabledWithValue(GstNumberInputField,"GST Number");
    }
    public void enterAddressDetails(){
       try {
           UtilClass.waitForElementAndClick(selectGeoCodeButton,2000);
           HTPLLogger.info("Clicked on Select GeoCode button!!!");
           UtilClass.waitForElementPresent(searchGeoCodeInputField,2000);
           UtilClass.sleep(2000);
           if (searchGeoCodeInputField.isDisplayed()){
               searchGeoCodeInputField.sendKeys("122002");
               UtilClass.sleep(2000);
               selectAddressFromGeoCode("122002");
               confirmLocation();
               UtilClass.sleep(2000);
               addressInputFieldIsPrefilledAfterGeoCode();
               UtilClass.sleep(2000);
               verifyCheckBox();
           }else {
               HTPLLogger.error("Unable to enter address details !!");
           }
       } catch (Exception e) {
           Assert.fail("Unable to enter address details !!",e);
       }

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
    public void addressInputFieldIsPrefilledAfterGeoCode(){
        isAddressFieldDisabledWithValue(addressLineOneInputField,"Address Line 1","Good Earth Business Bay 2");
        UtilClass.waitForElementPresent(addressLinetwoInputField,2000);
        isAddressFieldDisabledWithValue(addressLinetwoInputField,"Address Line 2","Testing Address 2");
        UtilClass.waitForElementPresent(stateInputField,2000);
        isAddressFieldDisabledWithValue(stateInputField,"State","State input Field");
        UtilClass.waitForElementPresent(cityInputField,2000);
        isAddressFieldDisabledWithValue(cityInputField,"City","City input Field");
        UtilClass.waitForElementPresent(landMarkInputField,2000);
        isAddressFieldDisabledWithValue(landMarkInputField,"Landmark","Testing LandMark");
        UtilClass.waitForElementPresent(pinCodeInputField,2000);
        isAddressFieldDisabledWithValue(pinCodeInputField,"PinCode","PinCode Input field.");
    }

    public void selectAddressFromGeoCode(String locationValue){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='presentation']//div[@class='MuiAutocomplete-option' or contains(@class, 'MuiAutocomplete-option')][.//text()[contains(., '"+locationValue+"')]]")
        ));
        option.click();

    }

    public void verifyCheckBox(){
       try {
           if (!defualtBillingAddressCheckBox.isEnabled()){
               HTPLLogger.info("Default checkbox is  disabled and unable to clicked on it.");
           }else {
               defualtBillingAddressCheckBox.click();
               HTPLLogger.error("Default checkbox is enabled and clicked on it");
           }
       } catch (Exception e) {
           Assert.fail("Default checkbox is enabled!!",e);
           HTPLLogger.error("Default checkbox is enabled!!");
       }

    }

    public void hearAboutHippoDropdown(String optionValue){
        try {
            UtilClass.waitForElementAndClick(hearAboutHippoDropdown,2000);
            HTPLLogger.info("Clicked on Hear About Hippo Dropdown!!");
            WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
            WebElement dropDownOption=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@role='option'][text()='"+optionValue+"']")));
            dropDownOption.click();
            HTPLLogger.info(optionValue+ " is Selected from dropdown.");
        } catch (Exception e) {
            HTPLLogger.error("Unable to select Hear About Hippo Dropdown",e);
        }
    }

    public void clickOnContinueToRegisterButton(){
       try {
           UtilClass.waitForElementAndClick(continueToRegisterButton,2000);
           HTPLLogger.info("Clicked on Proceed to Continue Button!!");
       } catch (Exception e) {
           HTPLLogger.error("Unable to click on Proceed to Continue button",e);
       }
    }



}
