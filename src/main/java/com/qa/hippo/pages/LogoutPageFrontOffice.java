package com.qa.hippo.pages;

import com.qa.hippo.utilities.HTPLLogger;
import com.qa.hippo.utilities.UtilClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LogoutPageFrontOffice {
    WebDriver driver;

    @FindBy(xpath = "//li[contains(text(),'Log out')]")
    WebElement logoutButton;
    @FindBy(xpath = "//button[@id='dashBoardNavigationBarUserNameButton']")
    WebElement profileDropDown;

    public LogoutPageFrontOffice(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks on logout button
     */
    public void clickOnLogout() {
       try {
           WebDriverWait waitForReload = new WebDriverWait(driver, Duration.ofSeconds(30));
           waitForReload.until(ExpectedConditions.elementToBeClickable(logoutButton));
           logoutButton.click();
           HTPLLogger.info("Clicked on LogOut Button !!");
       } catch (Exception e) {
           HTPLLogger.error("Failure in test method clickOnLogout!!", e);
            Assert.fail("Failure in test method clickOnLogout!!",e);
       }
    }
    /**
     * Performs logout operation
     */
    public void logoutFromApplication() {
   try {
//       driver.navigate().refresh();
//        driver.switchTo().alert().accept();
       clickOnProfileDropDown();
       clickOnLogout();
       UtilClass.sleep(2000);
       closeDriver();
   }catch (Exception e){
       HTPLLogger.error("Failure in test method clickOnLogout!!",e);
       Assert.fail("Failure in test method clickOnLogout!!",e);
   }
    }
    /**
     * Clicks on drop down from profile section
     */
    public void clickOnProfileDropDown() {
        try {
            WebDriverWait waitForReload = new WebDriverWait(driver, Duration.ofSeconds(30));
            waitForReload.until(ExpectedConditions.elementToBeClickable(profileDropDown));
            profileDropDown.click();
            HTPLLogger.info("Clicked on Profile Dropdown..");

        } catch (Exception e) {
            HTPLLogger.error("Failure in test method clickOnLogout!!",e);
            Assert.fail("Failure in test method clickOnLogout!!");
        }
    }

    public void closeDriver(){
        driver.quit();
    }
}
