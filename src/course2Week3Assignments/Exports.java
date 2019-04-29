package course2Week3Assignments;

import edu.duke.*;
import org.apache.commons.csv.*;

// Use exports_small.csv or exportdata.csv in main project folder
public class Exports {
	// This method returns a string of information about the country 
	// or returns “NOT FOUND” if there is no information about the country.
	public String countryInfo(CSVParser parser, String country) {
		String countryInfo = "NOT FOUND";
		
		for(CSVRecord record : parser) {
			String countryName = record.get("Country");
			if(countryName.equals(country)) {
				String exports = record.get("Exports");
				String value = record.get("Value (dollars)");
				countryInfo = countryName + " : " + exports + " : " + value;
			}			
		}
		return countryInfo;
	}
	
	// This method prints the names of all the countries that have both exportItem1 and exportItem2 as export items. 
	public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
		for(CSVRecord record : parser) {
			String exports = record.get("Exports");
			if(exports.contains(exportItem1) && exports.contains(exportItem2)) {
				String country = record.get("Country");
				System.out.println(country);
			}
		}
	}
	
	// This method returns the number of countries that export exportItem. 
	public int numberOfExporters(CSVParser parser, String exportItem) {
		int count = 0;
		
		for(CSVRecord record : parser) {
			String exports = record.get("Exports");
			if(exports.contains(exportItem)) {
				count++;
			}
		}
		return count;
	}
	
	// This method prints the names of countries and their Value amount for all countries 
	// whose Value (dollars) string is longer than the amount string.
	public void bigExporters(CSVParser parser, String amount) {
		for(CSVRecord record : parser) {
			String value = record.get("Value (dollars)");
			if(value.length() > amount.length()) {
				String country = record.get("Country");
				System.out.println(country + " " + value);
			}
		}
	}
	
	// creates a CSVParser and calls each method
	public void tester() {
		FileResource fr = new FileResource();
		// Country info - country in file
		CSVParser parser = fr.getCSVParser();
		String country = countryInfo(parser, "Germany");
		System.out.println("Country info: " + country);
		// Country info - country not in file
		parser = fr.getCSVParser();
		country = countryInfo(parser, "Arabia");
		System.out.println("Country info: " + country);
		// exporters have both products;
		System.out.println("EXPORTERS - country has both products:");
		parser = fr.getCSVParser();
		listExportersTwoProducts(parser, "cotton", "flowers");
		// exporters do not have both products;
		System.out.println("EXPORTERS - country does not have both products:");
		parser = fr.getCSVParser();
		listExportersTwoProducts(parser, "gold", "mibs");
		parser = fr.getCSVParser();
		listExportersTwoProducts(parser, "bold", "mibs");
		// some countries export product
		parser = fr.getCSVParser();
		int count = numberOfExporters(parser, "cocoa");
		System.out.println("Number of countries that export cocoa is " + count);
		// no countries export product
		parser = fr.getCSVParser();
		count = numberOfExporters(parser, "mibs");
		System.out.println("Number of countries that export mibs is " + count);
		System.out.println("Countries with exports greater than $999,999,999,999:");
		// countries have exports greater than amount
		parser = fr.getCSVParser();
		bigExporters(parser, "$999,999,999,999");
		// countries do not have exports greater than amount
		System.out.println("Countries with exports greater than $999,999,999,999,999:");
		parser = fr.getCSVParser();
		bigExporters(parser, "$999,999,999,999,999");
	}
	

	public static void main(String[] args) {
		Exports ex = new Exports();
		ex.tester();
	}

}
