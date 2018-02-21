import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
        import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
        import org.testng.annotations.BeforeMethod;
        import org.testng.annotations.Test;

        import java.net.MalformedURLException;
        import java.net.URL;

/**
 * Created by beginner's guide
 */
public class Project3Test
{
    private AppiumDriver driver;

    @BeforeMethod
    public void setupTest() throws MalformedURLException
    {
        DesiredCapabilities capabilities =new DesiredCapabilities();
        capabilities.setCapability("testobject_test_name", "Login");
        capabilities.setCapability("platformName", "ANDROID");
        capabilities.setCapability("platformVersion", "7.0");
        //capabilities.setCapability("deviceName", "FA63WBN00149");
        //String appPath = "/Users/kazeemomisore/Documents/Applications/Appium/APKs/Koomot/komoot_7.0.2.apk";
        //capabilities.setCapability("app", appPath);
        //System.out.println("Hello");
        //System.out.println(System.getenv("TESTOBJECT_API_KEY"));
        capabilities.setCapability("app", "komoot");
        capabilities.setCapability("testobject_api_key", System.getenv("TESTOBJECT_API_KEY"));
        capabilities.setCapability("appWaitActivity", "de.komoot.android.app.JoinKomootActivity");
        //  driver = new RemoteWebDriver(new URL("http://127.0.0.1:4725/wd/hub"), capabilities);
        try {
           // driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            driver = new AndroidDriver(new URL("https://eu1.appium.testobject.com/wd/hub"), capabilities);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @AfterMethod
    public void tearDown()
    {
        if(driver !=null) driver.quit();
    }
    @Test
    public void simpleTest()
    {
       // driver.findElementByName("IntegerA").sendKeys("17");
       // driver.findElementByName("IntegerB").sendKeys("13");
       // driver.findElementByName("ComputeSumButton").click();
       // int answer = Integer.parseInt(driver.findElementByName("Answer").getText());
        // assert(answer, 30);
        System.out.println(driver.getSessionId().toString());
        driver.findElementById("de.komoot.android:id/textview_login").click();
        driver.getScreenshotAs(OutputType.BASE64);
        driver.findElementById("de.komoot.android:id/edittext_email").sendKeys("kazeem.omisore@saucelabs.com");
        driver.findElementById("de.komoot.android:id/edittext_password").sendKeys("saucetest");
        driver.findElementById("de.komoot.android:id/button_login").click();
        driver.getScreenshotAs(OutputType.BASE64);

        Wait wait = new WebDriverWait(driver, 10);

        MobileElement element = (MobileElement)wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.packageinstaller:id/permission_allow_button")));
        element.click();
        driver.getScreenshotAs(OutputType.BASE64);

    }
}
