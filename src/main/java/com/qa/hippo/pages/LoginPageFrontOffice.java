package com.qa.hippo.pages;

import com.qa.hippo.utilities.ConfigLoader;
import com.qa.hippo.utilities.HTPLLogger;
import com.qa.hippo.utilities.UtilClass;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class LoginPageFrontOffice {

    @FindBy(xpath = "(//input)[1]")
    WebElement userIdTextbox;

    @FindBy(xpath = "(//input)[2]")
    WebElement passwordTextbox;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    WebElement loginButton;

    @FindBy(xpath = "//div[text()='This terminal already in use']")
    WebElement terminalUseAlert;

    WebDriver driver;
    public LoginPageFrontOffice(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Enters user id
     *
     * @param userId
     * @param waitForReload
     */
    public void enterUserId(String userId, WebDriverWait waitForReload) {
      try {
          waitForReload.until(ExpectedConditions.visibilityOf(userIdTextbox));
          userIdTextbox.sendKeys(userId);
          HTPLLogger.info("UserId is Entered!!");
      } catch (RuntimeException e) {
          HTPLLogger.error("Failure in test method EnterUserId", e);
          Assert.fail("Failure in test Method performLoginOperation!",e);
      }
    }

    /**
     * Enters user password
     *
     * @param waitForReload
     */
    public void enterPassword(String password, WebDriverWait waitForReload) {
        try {
            waitForReload.until(ExpectedConditions.visibilityOf(userIdTextbox));
            passwordTextbox.sendKeys(password);
            HTPLLogger.info("Password is Entered!!!");
        } catch (RuntimeException e) {
            HTPLLogger.error("Failure in test method enterPassword!!", e);
            Assert.fail("Failure in test method enterPassword!!",e);
        }
    }

    /**
     * Clicks on login button
     *
     * @param waitForReload
     */
    public void clickOnLoginButton(WebDriverWait waitForReload) {
      try {
          waitForReload.until(ExpectedConditions.elementToBeClickable(loginButton));
          loginButton.click();
          HTPLLogger.info("Clicked on Login Button!!");
      } catch (Exception e) {
          HTPLLogger.error("Failure in test method clickOnLoginButton!!", e);
          Assert.fail("Failure in test method clickOnLoginButton",e);
      }
    }
    public void selectTerminal(WebDriverWait waitForReload, String terminalName) {
        try {
            waitForReload.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[.='" + terminalName + "']")));
            driver.findElement(By.xpath("//p[contains(text(),'" + terminalName + "')]")).click();

            // Add a short wait to allow alert to appear (if any)
            Thread.sleep(1000); // Optional: Replace with explicit wait if possible
            HTPLLogger.info(terminalName + " Is selected!!");
            // Check if the "already in use" alert is displayed
            List<WebElement> alertList = driver.findElements(By.xpath("//div[text()='This terminal already in use']"));
            if (!alertList.isEmpty() && alertList.get(0).isDisplayed()) {
                HTPLLogger.warn("Terminal '" + terminalName + "' is already in use.");
                throw new AssertionError("Terminal '" + terminalName + "' is already in use.");
            }

        } catch (TimeoutException e) {
            HTPLLogger.error("Failure in test method selectTerminal!!", e);
            throw new AssertionError("Terminal '" + terminalName + "' not found.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            HTPLLogger.error("Thread interrupted while waiting for alert", e);
        }
    }

    /**
     * Logs in with given user id and password
     */
    public void performLoginOperation() {
        WebDriverWait waitForReload = new WebDriverWait(driver, Duration.ofSeconds(30));
        selectTerminal(waitForReload,ConfigLoader.get("terminal"));
        enterUserId(ConfigLoader.get("userId"), waitForReload);
        enterPassword(ConfigLoader.get("password"), waitForReload);
        clickOnLoginButton(waitForReload);
        UtilClass.sleep(3000);
    }

}
