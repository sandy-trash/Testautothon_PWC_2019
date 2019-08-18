package commons;

import static stepDefinition.CountryCapitalsSteps.threadInfo;

import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import utils.HttpHelper;

public class Commons {

	private static HttpHelper httpHelper = new HttpHelper();

	private static String REST_COUNTRIES_URL = "https://restcountries.eu/rest/v2/name/";

	public void extractCountryCapitalFromRestCountries(String countryName) {
		try {
			String response = httpHelper.doGetRequest(REST_COUNTRIES_URL + countryName);
			JSONParser parser = new JSONParser();
			JSONArray jsonArray = (JSONArray) parser.parse(response);
			JSONObject jsonObj = null;
			for(int index = 0; index < jsonArray.size(); index++){
				jsonObj = (JSONObject)jsonArray.get(index);
				if(jsonObj.get("name").toString().equalsIgnoreCase(countryName))
					threadInfo.getDo(countryName).restcountrycapitalname = jsonObj.get("capital").toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void extractCountryCapitalFromWiki(String country) {

		String url = "https://en.wikipedia.org/wiki/" + country;
		try {
			Document document = Jsoup.connect(url).get();
			// System.out.println(document);
			Elements table = document.select("table[class='infobox geography vcard']");
			Elements trs = table.select("tr");
			for (Element tr : trs) {
				if (tr.text().contains("Capital")) {
					threadInfo.getDo(
							country).wikicountrycapitalname = tr.select("a[href]").text().split("(?<=\\D)(?=\\d)")[0]
									.trim();
					break;
				}

			}

			/*Elements td = tr.select("td");
			Elements a = td.select("a[title]");
			System.out.println(a.text());
			threadInfo.getDo(country).wikicountrycapitalname = a.text();
*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		new Commons().extractCountryCapitalFromWiki("India");
	}

}
