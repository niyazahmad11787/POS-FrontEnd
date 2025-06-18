package com.qa.hippo.baseclass;

import com.aventstack.extentreports.ExtentTest;
import com.qa.hippo.utilities.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public static Properties userProperties;
    public static String downloadFilepath = "C:\\Downloads";
    protected static ExtentTest extentTest;
    /**
     * Initializes the WebDriver and browser settings.
     */
    @Parameters({"Browser", "Env"})
    @BeforeSuite
    public void initializeDriver(String Browser, String Env) throws IOException {
        if (driver == null) {
            // Load the config for the selected environment
            ConfigLoader.load(Env);

            if (Browser.equalsIgnoreCase("Chrome")){
                // Set Chrome download preferences
                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("download.default_directory", downloadFilepath);
                chromePrefs.put("download.prompt_for_download", false);
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("safebrowsing.enabled", true);
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                driver = new ChromeDriver(options);
            } else if (Browser.equalsIgnoreCase("Edge")) {
                driver=new EdgeDriver();
            } else if (Browser.equalsIgnoreCase("FireFox")) {
                driver=new FirefoxDriver();
            }
            else {
                System.out.println("Invalid Browser Name!!!");
            }
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        }

    }
    /**
     * Opens the application at the given URL.
     */
    public void openApplication() {
        String url=ConfigLoader.get("base.url");
        driver.get(url);
        driver.manage().deleteAllCookies();
    }
}
