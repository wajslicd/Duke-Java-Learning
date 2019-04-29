package course3Week1Assignments;
/**
 * Determines the most common word length of words from a file. 
 * 
 * @author Diane Wajslic
 * @version 1.0
 */

import edu.duke.*;

public class WordLengths {
	public int countWordLengths(FileResource resource, int[] counts) {
		for(String word : resource.words()) {
			//System.out.println(word);
			int wordLength = word.length();
			if(!Character.isLetter(word.charAt(0))) {
				wordLength--;
			}
			if(wordLength > 0 && !Character.isLetter(word.charAt(word.length() - 1))) {
				wordLength--;
			}
			if(wordLength > 0) {
				if(wordLength < 30) {
					counts[wordLength] += 1;
				} else {					
					counts[30] += 1;					
				}				
			}						
		}
		return indexOfMax(counts);
	}
	
	
	public int indexOfMax(int[] values) {
		int max = 0;
		int index = 0;
		
		for(int i = 0; i < values.length; i++) {
			if(values[i] > max) {
				max = values[i];
				index = i;
			}
		}
		return index;
	}
	
	// Use smallHamlet.txt in data folder for testing
	public void testCountWordLengths() {
		FileResource fr = new FileResource();
		int[] counts = new int[31];
		int max = countWordLengths(fr, counts);
		System.out.println("Most common word length is " + max);
		for(int i = 1; i < counts.length; i++) {
			System.out.println("Count of " + i + " is " + counts[i]);
		}
	}

	// Use romeo.txt or lotsOfWords.txt in data folder
	public static void main(String[] args) {
		WordLengths wl = new WordLengths();				
		wl.testCountWordLengths();

	}

}
