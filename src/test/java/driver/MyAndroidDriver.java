package driver;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class MyAndroidDriver {

    private DesiredCapabilities capabilities = new DesiredCapabilities();
    public AndroidDriver androidDriver;

    public AndroidDriver newMobileDriver() throws MalformedURLException {
    	System.setProperty("webdriver.chrome.driver", "C:\\Source_Tree\\Testautothon-master\\chromedriver.exe");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 5X API 27");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
//        capabilities.setCapability("appPackage", "com.android.chrome");
//        capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        capabilities.setCapability("chromedriverExecutable", "C:\\Users\\ssingh394\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\node_modules\\appium-chromedriver\\chromedriver\\win\\chromedriver.exe");
        String serverUrl = "http://0.0.0.0:4723/wd/hub";
        androidDriver = new AndroidDriver(new URL(serverUrl), capabilities);
        return androidDriver;
    }
}
