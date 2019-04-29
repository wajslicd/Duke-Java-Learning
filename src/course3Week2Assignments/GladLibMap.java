package course3Week2Assignments;


import edu.duke.*;
import java.util.*;

public class GladLibMap {
	private HashMap<String, ArrayList<String>> myMap;
	private ArrayList<String> usedWords;
	private ArrayList<String> usedCategories;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLibMap(){
		myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
		usedWords = new ArrayList<String>();
		usedCategories = new ArrayList<String>();
	}
	
	public GladLibMap(String source){
		myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(source);
		myRandom = new Random();
		usedWords = new ArrayList<String>();
	}
	
	private void initializeFromSource(String source) {
		String[] labels = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
		for(String s : labels) {
			ArrayList<String> list = readIt(source + "/" + s + ".txt");
			myMap.put(s, list);
		}
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("number")){
			return "" + myRandom.nextInt(50)+5;
		}
		if(myMap.containsKey(label)) {
			if(!usedCategories.contains(label)) {
				usedCategories.add(label);
			}			
			return randomFrom(myMap.get(label));
		}
		return "**UNKNOWN**";
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		while(isUsed(sub)) {
			sub = getSubstitute(w.substring(first+1,last));
		}
		return prefix+sub+suffix;
	}
	
	private boolean isUsed(String w) {
		int idx = usedWords.indexOf(w);
		if(idx != -1) {
			return true;
		} else {
			usedWords.add(w);
		}
		return false;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public int totalWordsInMap() {
		int total = 0;
		for(ArrayList<String> list : myMap.values()) {
			total += list.size();
		}
		return total;
	}
	
	public int totalWordsConsidered() {
		int total = 0;
		for(String s : usedCategories) {
			total += myMap.get(s).size();
		}
		return total;
	}
	
	public void makeStory(){
		usedWords.clear();
		System.out.println("\n");
		String story = fromTemplate("datalong/madtemplate2.txt");
		printOut(story, 60);
		System.out.println("\n\nTotal number of words that were replaced is " + usedWords.size());
		System.out.println("Total number of words that were possible to pick from is " + totalWordsInMap());
		System.out.println("Total number of words in categories that were used is " + totalWordsConsidered());
	}
	
	public static void main(String[] args) {
		GladLibMap gl = new GladLibMap();
		gl.makeStory();
	}

}
