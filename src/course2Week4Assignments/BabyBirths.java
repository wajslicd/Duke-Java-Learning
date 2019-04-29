package course2Week4Assignments;

import edu.duke.*;

import java.io.File;

import org.apache.commons.csv.*;

/**
 * Answers questions about the most popular male and female baby names in a given year (US)
 * 
 * @author Diane Wajslic
 * @version 25/12/2018
 */

public class BabyBirths {
	// This method calculates and prints the total number of babies born, total number of boys and total number of girls.
	// This method also prints the number of girls names , the number of boys names and the total names in a file.
	public void totalBirths(FileResource fr) {
		int totalBirths = 0;
		int totalMales = 0;
		int totalFemales = 0;
		int totalNames = 0;
		int totalMaleNames = 0;
		int totalFemaleNames = 0;
		
		for(CSVRecord record : fr.getCSVParser(false)) {
			totalNames += 1;
			int numBorn = Integer.parseInt(record.get(2));
			totalBirths += numBorn;
			if(record.get(1).equals("M")) {
				totalMaleNames += 1;
				totalMales += numBorn;
			} else {
				totalFemaleNames += 1;
				totalFemales += numBorn;
			}
				
		}
		System.out.println("Total births = " + totalBirths);
		System.out.println("Total girls born = " + totalFemales);
		System.out.println("Total boys born = " + totalMales);
		System.out.println("Total names = " + totalNames);
		System.out.println("Total girls names = " + totalFemaleNames);
		System.out.println("Total boys names = " + totalMaleNames);
	}
	
	// This method returns the rank of the name in the file for the given gender, 
	// where rank 1 is the name with the largest number of births. 
	// If the name is not in the file, then -1 is returned.
	public int getRank(int year, String name, String gender) {
		//String dir = "us_babynames_test\\";
		//String fName = dir + "yob" + year + "short.csv";
		String dir = "us_babynames_by_year\\";		
		String fName = dir + "yob" + year + ".csv";
		try {
			FileResource fr = new FileResource(fName);
			int rank = calculateRank(fr, name, gender);
			return rank;
		} catch (Exception e) {
			System.out.println("Cannot open file " + fName);
			return -1;
		}
		
	}
	
	// This method calculates the rank of the name in a file for the given gender, 
	// where rank 1 is the name with the largest number of births. 
	// If the name is not in the file, then -1 is returned.
	public int calculateRank(FileResource fr, String name, String gender) {
		int count = 0;
		int currentRank = 0;
		int currentFrequency = 0;
		
		for(CSVRecord record : fr.getCSVParser(false)) {
			if(record.get(1).equals(gender)) {
				count += 1;
				int frequency = Integer.parseInt(record.get(2));
				if(frequency != currentFrequency) {
					currentRank = count;
					currentFrequency = frequency;
				} 
				if(record.get(0).equals(name)) {
					return currentRank;
				}
			}
		}
		return -1;
	}
	
	// This method returns the name of the person in the file at this rank,
	// for the given gender, where rank 1 is the name with the largest number of births. 
	// If the rank does not exist in the file, then “NO NAME” is returned.
	public String getName(int year, int rank, String gender) {
		//String dir = "us_babynames_test\\";
		//String fName = dir + "yob" + year + "short.csv";
		String dir = "us_babynames_by_year\\";		
		String fName = dir + "yob" + year + ".csv";
		FileResource fr = new FileResource(fName);
		int count = 0;
		int currentRank = 0;
		int currentFrequency = 0;
		
		for(CSVRecord record : fr.getCSVParser(false)) {
			if(record.get(1).equals(gender)) {
				count += 1;
				int frequency = Integer.parseInt(record.get(2));
				if(frequency != currentFrequency) {
					currentRank = count;
					currentFrequency = frequency;
				}
				if(currentRank == rank) {
					return record.get(0);
				}
			}
		}
		return "NO NAME";
	}
	
	// This method determines what name would have been named if they were born in a different year, 
	//based on the same popularity.
	public void whatIsNameInYear(String name, int year, int newYear, String gender) {
		int rank = getRank(year, name, gender);
		if(rank != -1) {
			String newName = getName(newYear, rank, gender);
			if(gender.equals("M")) {
				System.out.println(name + " born in " + year + " would be " + newName + " if he was born in " + newYear);
			} else {
				System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
			}			
		} else {
			System.out.println(name + " was not found in " + year);
		}				
	}
	
	// This method selects a range of files to process and returns an integer, 
	// the year with the highest rank for the name and gender. 
	// If the name and gender are not in any of the selected files, then -1 is returned.
	public int yearOfHighestRank(String name, String gender) {
		DirectoryResource dr = new DirectoryResource();
		int highestRank = -1;
		int year = -1;	
		
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);								
			int rank = calculateRank(fr, name, gender);
			//System.out.println("Rank is " + rank);
			//System.out.println("Highest rank before is " + highestRank);
			
			if((highestRank == -1 && rank > highestRank) || (highestRank != -1 && rank != -1 && rank < highestRank)) {
				highestRank = rank;
				//System.out.println("Highest rank after is " + highestRank);
				String fName = f.getName();
				//System.out.println("File is " + fName);
				year = Integer.parseInt(fName.substring(3, 7));
			}								
		}
		return year;
	}
	
	// This method selects a range of files to process and returns a double representing the average rank 
	// of the name and gender over the selected files. 
	// It should return -1.0 if the name is not ranked in any of the selected files.
	public double getAverageRank(String name, String gender) {
		DirectoryResource dr = new DirectoryResource();
		double totalRank = 0;
		int count = 0;
		
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			int rank = calculateRank(fr, name, gender);
			if(rank != -1) {
				count +=1;
				totalRank += rank;
			}
		}
		if(count != 0) {
			return totalRank/count;
		} else {
			return -1.0;
		}
	}
	
	// This method returns an integer, the total number of births of those names with 
	// the same gender and same year who are ranked higher than name.
	public int getTotalBirthsRankedHigher(int year, String name, String gender) {
		//String dir = "us_babynames_test\\";
		//String fName = dir + "yob" + year + "short.csv";
		String dir = "us_babynames_by_year\\";	
		String fName = dir + "yob" + year + ".csv";
		
		int totalBirths = 0;		
		FileResource fr = new FileResource(fName);
		int nameRank = calculateRank(fr, name, gender);
		
		for(CSVRecord record : fr.getCSVParser(false)) {
			if(record.get(1).equals(gender)) {
				int rank = calculateRank(fr, record.get(0), gender);
				//if(record.get(0).equals(name)) {
				if(rank >= nameRank) {
					return totalBirths;
				} else {
					totalBirths += Integer.parseInt(record.get(2));
				}
			}
		}
		return totalBirths;
	}
	
	public void testTotalBirths() {
		FileResource fr = new FileResource();
		totalBirths(fr);
	}
	
	public void testRank() {
		int rank = getRank(1974, "Owen", "M");
		System.out.println("Rank of selected name is " + rank);
	}
	
	public void testName() {
		String name = getName(2014, 430, "M");
		System.out.println("Name of selected rank is " + name);
	}
	
	public void testWhatIsNameInYear() {
		whatIsNameInYear("Isabella", 2012, 2014, "F");
		whatIsNameInYear("Noah", 2012, 2014, "M");
		whatIsNameInYear("Owen", 1974, 2014, "M");
		whatIsNameInYear("Susan", 1972, 2014, "F");
	}
	
	public void testYearOfHighestRank(){
		int year = yearOfHighestRank("Genevieve", "F");
		System.out.println("Year of highest rank is " + year);
	}
	
	public void testAverageRank(){
		double average = getAverageRank("Susan", "F");
		System.out.println("Average rank is " + average);
		//average = getAverageRank("Jacob", "M");
		//System.out.println("Average rank for Jacob as M is " + average);
		//average = getAverageRank("Jacob", "F");
		//System.out.println("Average rank for Jacob as F is " + average);
	}
	
	public void testTotalBirthsRankedHigher() {
		int total = getTotalBirthsRankedHigher(1990, "Drew", "M");
		System.out.println("Total number of births of names with the same gender and same year who are ranked higher than name is " + total);
	}

	public static void main(String[] args) {
		BabyBirths bb = new BabyBirths();
		//bb.testTotalBirths();
		//bb.testRank();
		//bb.testName();
		//bb.testWhatIsNameInYear();
		//bb.testYearOfHighestRank();
		//bb.testAverageRank();
		bb.testTotalBirthsRankedHigher();
	}

}
