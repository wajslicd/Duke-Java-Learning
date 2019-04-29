package course4Week3Assignments;

/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel{
	private HashMap<String,ArrayList<String>> myMap;
	 
	public EfficientMarkovModel(int order) {
		myModel = order;		
	}
	
	@Override
	public void setTraining(String s) {
        myText = s.trim();
        myMap = buildMap2();
		printHashMapInfo();
    }
	
	public String getRandomText(int numChars){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-myModel);
		String key = myText.substring(index, index+myModel);
		sb.append(key);
		
		for(int k=0; k < numChars-myModel; k++){
			ArrayList<String> follows = getFollows(key);
			if(follows.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			key = key.substring(1)+next;
		}
		
		return sb.toString();
	}
	
	@Override
	public ArrayList<String> getFollows(String key){
		
		return myMap.get(key);
	}
	
	public HashMap<String,ArrayList<String>> buildMap(){
		HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
		int keyPos = 0;
		
		while(keyPos < myText.length()-myModel+1) {
			String key = myText.substring(keyPos, keyPos+myModel);
			if(!map.containsKey(key)) {
				ArrayList<String> follows = new ArrayList<String>();
				int pos = 0;
				while(pos < myText.length()) {
					int start = myText.indexOf(key, pos);
					if(start == -1) {
						break;
					}
					if(start + key.length() > myText.length()-1) {
						break;
					}
					String next = myText.substring(start+key.length(), start+key.length()+1);
					follows.add(next);
					//pos = start + key.length();
					pos = start + 1;
				}
				map.put(key, follows);
			}
			keyPos++;
		}
				
		return map;
	}
	
	// faster
	public HashMap<String,ArrayList<String>> buildMap2(){
		HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
		int len = myText.length()-myModel;
		
		for(int k=0; k < len+1; k++) {
			String key = myText.substring(k, k+myModel);
			
			if(!map.containsKey(key)) {
				ArrayList<String> follows = new ArrayList<String>();
				if(k + key.length() <= myText.length()-1) {
					String next = myText.substring(k+key.length(), k+key.length()+1);
					follows.add(next);
				}				
				map.put(key, follows);
			} else {
				if(k + key.length() <= myText.length()-1) {
					String next = myText.substring(k+key.length(), k+key.length()+1);
					ArrayList<String> follows = map.get(key);
					follows.add(next);
					map.put(key, follows);
				}								
			}
		}				
		return map;
	}
	
	@Override
	public String toString() {
    	return "EfficientMarkovModel of order " + myModel;
    }
	
	public void printHashMapInfo() {
		int max=0;
		ArrayList<String> largest = new ArrayList<String>();
		
		//System.out.println(myMap);
		System.out.println("Number of keys: " + myMap.size());
		for(String s : myMap.keySet()) {
			ArrayList<String> temp = myMap.get(s);
			if(temp.size() > max) {
				max = temp.size();
			}
		}
		System.out.println("The maximum number of chars following a key is " + max);
		for(String s : myMap.keySet()) {
			ArrayList<String> temp = myMap.get(s);
			if(temp.size() == max) {
				largest.add(s);
			}
		}
		System.out.println("Keys that have the largest ArrayList " + largest);
	}
}
