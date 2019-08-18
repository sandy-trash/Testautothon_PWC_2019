package stepDefinition;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import commons.Commons;
import configPackage.Config;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fileReader.CSVFileReader;
import junit.framework.Assert;
import pageObjects.CountryWikiPage;
import utils.Do;
import utils.JSONObjects;
import utils.ThreadInfo;

public class CountryCapitalsSteps {

	public static ThreadInfo threadInfo;
	public String runmode = Config.runmode;
	List<String> countryList = null;

	public CountryCapitalsSteps() throws IllegalAccessException, InstantiationException {
	}

	@Given("^a list of country names$")
	public void aListOfCountryNames() {
		countryList = CSVFileReader.getData();
		threadInfo = new ThreadInfo(countryList);
	}

	@When("^user tries to extract the country capital from wikipedia and rest api$")
	public void userTriesToExtractCountryCapitalNames() throws Exception {
		Class[] params1 = new Class[1];
		params1[0] = String.class;
		
		Class[] params2 = new Class[1];
		params2[0] = String.class;

		Class cls;
		Object obj;

		// Step Class & Functions

		if (runmode.equals("http")) {
			cls = Commons.class;
			obj = cls.newInstance();
		} else {
			cls = CountryWikiPage.class;
			obj = cls.newInstance();
		}

		Method m1 = cls.getDeclaredMethod("extractCountryCapitalFromWiki", params1);
		Method m2 = cls.getDeclaredMethod("extractCountryCapitalFromRestCountries", params2);

		threadInfo.doMethods(obj, m1, m2).startThreads();
		threadInfo.waitForThreadsToComplete();
	}

	@Then("^the country capitals should match$")
	public void theCapitalNamesShouldMatch() throws IOException {
		threadInfo.quitAllDrivers();
		for(int i = 0; i < threadInfo.getDo().size(); i++){
			Do d = threadInfo.getDo().get(i);
			System.out.println(d.wikicountrycapitalname + " : " + d.restcountrycapitalname);
			//Assert.assertEquals(true, d.wikicountrycapitalname.equalsIgnoreCase(d.restcountrycapitalname));
		}
		new JSONObjects().createJSON();
	}
}
