package course3Week2Assignments;

import java.util.HashMap;
import java.io.File;
import java.util.ArrayList;
import edu.duke.*;

public class WordsInFiles {
	private HashMap<String, ArrayList<String>> map;
	
	public WordsInFiles() {
		map = new HashMap<String, ArrayList<String>>();
	}
	
	private void addWordsFromFile(File f) {
		FileResource resource = new FileResource(f);
		for(String word : resource.words()) {
			if(!map.containsKey(word)) {
				ArrayList<String> files= new ArrayList<String>();
				files.add(f.getName());
				map.put(word, files);
			} else {
				if(!map.get(word).contains(f.getName())) {
					map.get(word).add(f.getName());
				}
			}
		}
	}
	
	public void buildWordFileMap() {
		map.clear();
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()){
			addWordsFromFile(f);
		}
	}
	
	public int maxNumber() {
		int max = 0;
		for(ArrayList<String> list : map.values()) {
			if(list.size() > max) {
				max = list.size();
			}
		}
		return max;
	}
	
	public ArrayList<String> wordsInNumFiles(int number) {
		ArrayList<String> list = new ArrayList<String>();
		for(String word : map.keySet()) {
			if(map.get(word).size() == number) {
				list.add(word);
			}
		}
		return list;
	}
	
	public void printFilesIn(String word) {
		if(map.containsKey(word)) {
			System.out.println("\n'" + word +  "' appears in the files: ");
			for(int i = 0; i < map.get(word).size(); i++) {				
				System.out.println(map.get(word).get(i));	
			}
		}
	}
	
	public void tester() {
		buildWordFileMap();
		int max = maxNumber();
		System.out.print("The greatest number of files a word appears in is " + max);
		ArrayList<String> list = wordsInNumFiles(max);
		System.out.println(", and there are " + list.size() + " such words: ");
		for(int i = 0; i < list.size(); i++) {
			System.out.println("'" + list.get(i) + "'");
		}
		for(String s : list) {
			printFilesIn(s);
		}
	}

	// Use brief1.txt, brief2.txt, brief3.txt and brief4.txt in data folder for testing
	public static void main(String[] args) {
		WordsInFiles wif = new WordsInFiles();
		wif.tester();
	}

}
