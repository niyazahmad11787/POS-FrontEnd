package com.qa.hippo.baseclass;

import com.aventstack.extentreports.ExtentTest;
import com.qa.hippo.utilities.ConfigLoader;
import com.qa.hippo.utilities.HTPLLogger;
import com.qa.hippo.utilities.ReadTestData;
import com.qa.hippo.utilities.UtilClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.awt.*;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public static Properties userProperties;
    public static String downloadFilepath = "C:\\Downloads";
    protected static ExtentTest extentTest;
    public ReadTestData testData;

    /**
     * Initializes the WebDriver and browser settings.
     */
    @Parameters("Env")
    @BeforeSuite()
    public void setupBaseClass(String Env){
    initializeLogger();
    launchBrowser(Env);
    loadBrowserConfiguration();
    }

    /**
     * Launch browser
     *
     * @throws AWTException
     */
    private void launchBrowser(String Env) {
        ConfigLoader.load(Env);
        String browserName = ConfigLoader.get("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            if (ConfigLoader.get("runonjenkins").equalsIgnoreCase("no")) {
                initiateBrowser();
            } else if (ConfigLoader.get("runonjenkins").equalsIgnoreCase("yes")) {
                initiateHeadlessBrowser();
            }
            HTPLLogger.info("Chrome launched successfully");
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            HTPLLogger.info("firefox launch");
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            HTPLLogger.error("Browser not found, Please enter valid browser name");
        }
    }
    /**
     * Initializes required resources
     */
    private void initializeResource() {
        testData = new ReadTestData();
    }

    /**
     * Opens the application at the given URL.
     */
    public void openApplication() {
        String url=ConfigLoader.get("base.url");
        driver.get(url);
        driver.manage().deleteAllCookies();
    }

    /**
     * Initializes logger
     */
    private void initializeLogger() {
        HTPLLogger.setClass(this);
    }
    private void initiateBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("force-device-scale-factor=0.75");
        options.addArguments("high-dpi-support=0.75");
        driver = new ChromeDriver(options);
    }
    private void initiateHeadlessBrowser() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("force-device-scale-factor=0.75");
        options.addArguments("high-dpi-support=0.75");
        driver = new ChromeDriver(options);
    }
    /**
     * Configures browser
     */
    private void loadBrowserConfiguration() {
        driver.manage().window().maximize();
        HTPLLogger.info("Maximized browser");
        driver.manage().deleteAllCookies();
        HTPLLogger.info("Deleted all cookies");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
}
