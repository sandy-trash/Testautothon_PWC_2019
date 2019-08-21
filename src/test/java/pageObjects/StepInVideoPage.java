package pageObjects;

import static stepDefinition.CountryCapitalsSteps.threadInfo;
import static utils.ScreenShotUtility.takeScreenShot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.thucydides.core.pages.PageObject;

public class CountryWikiPage extends PageObject {

	public void extractCountryCapitalFromWiki(String country) throws Exception {

		String url = "https://en.wikipedia.org/wiki/" + country;

		if (threadInfo.getDo(country).isMobile) {
			url = url.replaceFirst("en", "en.m");
		}
		threadInfo.getDo(country).getDriver().get(url);
		WebDriver driver = threadInfo.getDriver(country);
		// takeScreenshotStart
//		String wikiImageName = threadInfo.getDo(country).countryname + "_wiki.png";
//		takeScreenShot(driver, wikiImageName);
//		threadInfo.getDo(country).worldmapscreenShot = wikiImageName;
		// takeScreenshotEnd

		WebElement capitalEl = driver.findElement(By.xpath("//table[contains(@class,'infobox') and contains(@class,'geography') and contains(@class,'vcard')]/tbody//tr/th[contains(text(),'Capital')]/../td/a"));
		String capital = capitalEl.getText().trim();
		threadInfo.getDo(country).wikicountrycapitalname = capital;

	}

	public void extractCountryCapitalFromRestCountries(String country) throws Exception {

		String url = "https://geographyfieldwork.com/WorldCapitalCities.htm";

		threadInfo.getDo(country).getDriver().get(url);
		WebDriver driver = threadInfo.getDriver(country);

		WebElement capitalEl = driver.findElement(By.xpath("//table[@summary='World Capitals']//tr/td[contains(text(),'"+ country +"')]/../td[2]"));
		String capital = capitalEl.getText();
		System.out.println(capital);
		threadInfo.getDo(country).restcountrycapitalname = capital;
	}

}
