package course3Week1Assignments;

/**
 * Count common words in Shakespeare's works
 * 
 * @author Duke Software Team
 * @version 1.0
 */

import edu.duke.*;

public class CommonWords {
	public static String path = "C:\\Users\\Diane\\Documents\\Notes\\Java coursera\\Course 3 Arrays Lists and Structured Data\\data\\";
	
	// returns an array of 20 common words from a file
	public String[] getCommon() {
		FileResource resource = new FileResource(path + "common.txt");
		String[] common = new String[20];
		int index = 0;
		
		for(String s : resource.words()) {
			common[index] = s;
			index += 1;
		}
		return common;
	}
	
	// looks for a word in a list of words and returns its index if it is found.
	// If the word is not found then -1 is returned
	public int indexOf(String[] list, String word) {
		for(int k = 0; k < list.length; k++) {
			if(list[k].equals(word)) {
				return k;
			}
		}
		return -1;
	}
	
	// counts the common words in a file
	public void countWords(FileResource resource, String[] common, int[] counts) {
		for(String word : resource.words()) {
			word = word.toLowerCase();
			int index = indexOf(common, word);
			if(index != -1) {
				counts[index] += 1;
			}			
		}
	}
	
	// counts the common words from a list of Shakespeare plays
	void countShakespeare() {
		//String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt",
		//		          "likeit.txt", "macbeth.txt", "romeo.txt"};
		String[] plays = {"small.txt"};
		String[] common = getCommon();
		int[] counts = new int[common.length];
		
		for(int k = 0; k < plays.length; k++) {
			FileResource resource = new FileResource(path + plays[k]);
			countWords(resource,common,counts);
			System.out.println("Done with " + plays[k]);
		}
		
		for(int k = 0; k < common.length; k++) {
			System.out.println(common[k] + "\t" + counts[k]);
		}
	}

	public static void main(String[] args) {
		CommonWords cw = new CommonWords();
		cw.countShakespeare();
	}

}
