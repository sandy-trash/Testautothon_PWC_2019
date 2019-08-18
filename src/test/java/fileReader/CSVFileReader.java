package fileReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import au.com.bytecode.opencsv.CSVReader;

public class CSVFileReader {
	private static String PATH_OF_FILE = "testData/movie.csv";

	public static List<String> getData() {

		List<String> countryList = new ArrayList();

		try {
			CSVReader reader = new CSVReader(new FileReader(PATH_OF_FILE));
			String[] csvCell;

			// while loop will be executed till the last line In CSV.
			while ((csvCell = reader.readNext()) != null) {
				System.out.println("---csvCell[0]-" + csvCell[0]);
				String countryName;
				String temp = csvCell[0].split("\\.")[1];
				if (temp != null || temp.length() != 0)
					countryName = temp.trim();
				else
					countryName = csvCell[0];
				System.out.println("----Country Name----" + countryName);

				countryList.add(countryName);
			}
		} catch (Exception e) {
		}
		return countryList;
	}
}
