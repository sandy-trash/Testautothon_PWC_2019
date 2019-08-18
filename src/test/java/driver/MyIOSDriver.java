package driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MyIOSDriver {
	 private DesiredCapabilities capabilities = new DesiredCapabilities();
	    public IOSDriver iOSDriver;

	    public IOSDriver newMobileDriver() throws MalformedURLException {
	    	capabilities.setCapability("platformName", "iOS");
	    	capabilities.setCapability("platformVersion", "12.0.1");
	    	capabilities.setCapability("deviceName", "iPhone 8");
	    	capabilities.setCapability("udid", "auto");
	    	capabilities.setCapability("bundleId", "<your bundle id>");
	    	capabilities.setCapability("xcodeOrgId", "<your org id>");
	    	capabilities.setCapability("xcodeSigningId", "iPhone Developer");
	    	capabilities.setCapability("updatedWDABundleId", "<bundle id in scope of provisioning profile>");
	    	
	    	iOSDriver = new IOSDriver<>	(new URL("http://localhost:4723/wd/hub"), capabilities);
	        return iOSDriver;
	    }
}
