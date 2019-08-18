package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import net.thucydides.core.webdriver.DriverSource;

public class MyFirefoxDriver  implements DriverSource {


	  @Override
	  public WebDriver newDriver() {
		System.setProperty("webdriver.gecko.driver", "C:\\Source_Tree\\Testautothon-master\\geckodriver.exe");
	    return new FirefoxDriver();
	  }

	  @Override
	  public boolean takesScreenshots() {
	    return true;
	  }

	}
