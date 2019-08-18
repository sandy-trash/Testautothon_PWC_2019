package utils;

import stepDefinition.CountryCapitalsSteps;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSONObjects {

	public void createJSON() throws IOException {
		JSONObject jo = new JSONObject();
		JSONArray data = new JSONArray();

		for (int i = 0; i < CountryCapitalsSteps.threadInfo.getDo().size(); i++) {
			JSONObject capitalsData = new JSONObject();
			Do dos = CountryCapitalsSteps.threadInfo.getDo().get(i);
			capitalsData.put("countryID", (i + 1));
			capitalsData.put("countryName", dos.countryname);
			capitalsData.put("worldMapScreenShot", dos.worldmapscreenShot);
			capitalsData.put("wikipediaCapital", dos.wikicountrycapitalname);
			capitalsData.put("restCountriesCapital", dos.restcountrycapitalname);
			capitalsData.put("Assert", dos.notFound);
			capitalsData.put("threadID", dos.threadID);

			if (dos.isMobile) {
				capitalsData.put("platformName", "Mobile");
			} else if (dos.isHTTP) {
				capitalsData.put("platformName", "HTTP");
			} else if (dos.isWeb) {
				capitalsData.put("platformName", "Web");
			}

			capitalsData.put("startTime", dos.startTime);
			capitalsData.put("endTime", dos.endTime);
			/*if (dos.notFound.equals("NA")) {
				capitalsData.put("status", "Pass");
			} else {
			capitalsData.put("status", "Fail");
			}*/
			data.add(capitalsData);
		}
		jo.put("Data", data);
		System.out.println(jo.toJSONString());

		String write = "function getJSON() {\n" + "\tvar JSON = " + jo.toJSONString() + ";\n" + "return JSON;\n" + "};";

		FileWriter file = null;
		try {
			File f = new File("src/test/java/report/js/data.js");
			if (!(f.exists() && f.isFile())) {
				f.createNewFile();
			}
			file = new FileWriter("src/test/java/report/js/data.js");
			file.write(write);
		} finally {
			file.flush();
			file.close();
		}

	}
}