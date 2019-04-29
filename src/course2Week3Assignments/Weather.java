package course2Week3Assignments;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

// Use data in nc_weather folder
public class Weather {
	// This method returns the CSVRecord with the hottest temperature in the file
	public CSVRecord hottestHourInFile(CSVParser parser) {
		CSVRecord largestSoFar = null;

		for(CSVRecord currentRow : parser) {							
			largestSoFar = getLargestOfTwo(currentRow, largestSoFar);		
		}
		return largestSoFar;
	}
	
	// This method returns the CSVRecord with the coldest temperature in the file
	public CSVRecord coldestHourInFile(CSVParser parser) {
		CSVRecord smallestSoFar = null;

		for(CSVRecord currentRow : parser) {							
			smallestSoFar = getLowestOfTwo(currentRow, smallestSoFar);		
		}
		return smallestSoFar;
	}
	
	// This method returns the CSVRecord with the hottest temperature from the selected files
	public CSVRecord hottestInManyDays() {
		CSVRecord largestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
			largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
		}
		return largestSoFar;
	}
	
	// This method returns the CSVRecord with the coldest temperature from the selected files
	public CSVRecord coldestInManyDays() {
		CSVRecord smallestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			smallestSoFar = getLowestOfTwo(currentRow, smallestSoFar);
		}
		return smallestSoFar;
	}
	
	
	// This method returns the filename of the file with the coldest temperature from the selected files
	public String fileWithColdestTemperature() {
		CSVRecord smallestSoFar = null;
		String fileWithColdest = null;
		DirectoryResource dr = new DirectoryResource();
		
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			if(fileWithColdest == null) {
				fileWithColdest = f.toString();
				CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
				smallestSoFar = currentRow;
			} else {
				CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
				smallestSoFar = getLowestOfTwo(currentRow, smallestSoFar);
				if(!(fileWithColdest.equals(f.toString())) && smallestSoFar == currentRow) {
					fileWithColdest = f.toString();				
				}
			}
		}
		return fileWithColdest;
	}
	
	// This method returns the CSVRecord that has the lowest humidity. 
	// If there is a tie, then return the first such record that was found. 
	public CSVRecord lowestHumidityInFile(CSVParser parser) {
		CSVRecord lowestSoFar = null;

		for(CSVRecord currentRow : parser) {							
			lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar, "Humidity");		
		}
		return lowestSoFar;
	}
	
	// This method returns a CSVRecord that has the lowest humidity over all the files. 
	// If there is a tie, then return the first such record that was found.
	public CSVRecord lowestHumidityInManyFiles() {
		CSVRecord lowestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
			lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar, "Humidity");
		}
		return lowestSoFar;
	}
	
	public double averageTemperatureInFile(CSVParser parser) {
		double total = 0;
		int count = 0;

		for(CSVRecord currentRow : parser) {							
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			if(currentTemp != -9999) {
				total = total + currentTemp;
				count++;
			}
		}
		if(count > 0) {
			return total/count;
		} else {
			return 0.0;
		}
	}
	
	// This method returns a double that represents the average temperature of only 
	// those temperatures when the humidity was greater than or equal to value.
	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
		double total = 0;
		int count = 0;
		
		for(CSVRecord currentRow : parser) {
			int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
			if(currentHumidity >= value) {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				if(currentTemp != -9999) {
					total = total + currentTemp;
					count++;
				}
			}
		}
		
		if(count > 0) {
			return total/count;
		} else {
			return 0.0;
		}
		
	}
	
	
	// This method returns the largest temperature from two records
	public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
		if(largestSoFar == null){
			largestSoFar = currentRow;
		} else {				
			double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			if(currentTemp > largestTemp) {
				largestSoFar = currentRow;
			}					
		}
		return largestSoFar;
	}
	
	// This method returns the lowest temperature from two records
	public CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar) {
		double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
		
		if(lowestSoFar == null){			
			if(currentTemp != -9999) {
				lowestSoFar = currentRow;
			} 
		} else {				
			double lowestTemp = Double.parseDouble(lowestSoFar.get("TemperatureF"));
			if((currentTemp != -9999) && (currentTemp < lowestTemp)) {
				lowestSoFar = currentRow;
			}					
		}
		return lowestSoFar;
	}
	
	// This method return the lowest of two records from a specified column
	public CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestSoFar, String searchColumn) {
		String sCurrent = currentRow.get(searchColumn);
				
		if(lowestSoFar == null){
			if(!sCurrent.equals("N/A")) {
				lowestSoFar = currentRow;
			}				
		} else {	
			if(!sCurrent.equals("N/A")) {
				double dLowest = Double.parseDouble(lowestSoFar.get(searchColumn));
				double dCurrent = Double.parseDouble(currentRow.get(searchColumn));
				if(dCurrent < dLowest) {
					lowestSoFar = currentRow;
				}																
			} 
		}
		return lowestSoFar;
	}
	
	//=======================================================
	//Tests
	//=======================================================
	
	public void testHottestInDay() {
		FileResource fr = new FileResource();
		CSVRecord largest = hottestHourInFile(fr.getCSVParser());
		System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
	}
	
	public void testHottestInManyDays() {
		CSVRecord largest = hottestInManyDays();
		System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
	}
	
	public void testColdestHourInFile() {
		FileResource fr = new FileResource();
		CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
		if(coldest !=null) {
			System.out.println("Coldest temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("DateUTC"));
		}else {
			System.out.println("Coldest temperature was not available");
		}
	}
	
	public void testColdestInManyDays() {
		CSVRecord coldest = coldestInManyDays();
		System.out.println("Coldest temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("DateUTC"));
	}
	
	public void testFileWithColdestTemperature() {
		String fileWithColdestTemp = fileWithColdestTemperature();
		String filename = fileWithColdestTemp.substring(fileWithColdestTemp.lastIndexOf("weather"));
		System.out.println("Coldest day was in file " + filename);
		FileResource fr = new FileResource(fileWithColdestTemp);
		CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
		System.out.println("All the Temperatures on the coldest day were:");
		for (CSVRecord record : fr.getCSVParser()) {
			System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
		}
	}
	
	public void testLowestHumidityInFile() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord csv = lowestHumidityInFile(parser);
		System.out.println("Lowest humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
	}
	
	public void testLowestHumidityInManyFiles() {
		CSVRecord lowest = lowestHumidityInManyFiles();
		System.out.println("Lowest humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
	}
	
	public void testAverageTemperatureInFile() {
		FileResource fr = new FileResource();
		double average = averageTemperatureInFile(fr.getCSVParser());
		System.out.println("Average temperature in file is " + average);
	}
	
	public void testAverageTemperatureWithHighHumidityInFile() {
		FileResource fr = new FileResource();
		double average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
		if(average > 0.0) {
			System.out.println("Average Temp when high Humidity is " + average);
		} else {
			System.out.println("No temperatures with that humidity");
		}
		
	}

	public static void main(String[] args) {
		Weather w = new Weather();
		//w.testHottestInDay();
		//w.testHottestInManyDays();
		//w.testColdestHourInFile();
		w.testColdestInManyDays();
		//w.testFileWithColdestTemperature();
		//w.testLowestHumidityInFile();
		//w.testLowestHumidityInManyFiles();
		//w.testAverageTemperatureInFile();
		//w.testAverageTemperatureWithHighHumidityInFile();
	}

}
