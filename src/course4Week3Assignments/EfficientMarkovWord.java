package course4Week3Assignments;

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{
	private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> myMap;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
		myMap = buildMap2();
		printHashMapInfo();
	}
    
    public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
		WordGram kgram = new WordGram(myText,index,myOrder);
		sb.append(kgram);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(kgram);
		    //System.out.println("Key: '" + kgram + "' Follows: " + follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			kgram = kgram.shiftAdd(next);
		}
		
		return sb.toString().trim();
	}
	
	private int indexOf(String[] words, WordGram target, int start) {
		for(int k=start; k < words.length-myOrder+1; k++) {
			WordGram current = new WordGram(myText, k, myOrder);
			if(current.equals(target)) {
				return k;
			}
		}		
		return -1;
	}
	
	private ArrayList<String> getFollows(WordGram kgram) {
		return myMap.get(kgram);
    }
	
	public HashMap<WordGram,ArrayList<String>> buildMap(){
		HashMap<WordGram,ArrayList<String>> map = new HashMap<WordGram,ArrayList<String>>();
		/*int keyPos = 0;
		
		while(keyPos < myText.length-myOrder+1) {
			WordGram kgram = new WordGram(myText,keyPos,myOrder);
			if(!map.containsKey(kgram)) {
				ArrayList<String> follows = new ArrayList<String>();*/
			int len = myText.length-myOrder;
			for(int k=0; k < len+1; k++) {
			 	WordGram kgram = new WordGram(myText,k,myOrder);
				if(!map.containsKey(kgram)) {
					ArrayList<String> follows = new ArrayList<String>();
					for (int i = 0; i < len; i++) {
						WordGram current = new WordGram(myText, i, myOrder);
						if (kgram.equals(current)) {
							String next = myText[i+myOrder];
							follows.add(next);
						}
					}
		      
				/*
				int pos = 0;
				while(pos < myText.length) {
					int start = indexOf(myText, kgram, pos);
					if(start == -1) {
						break;
					}
					if(start >= myText.length-myOrder) {
						break;
					}
					String next = myText[start + myOrder];
					follows.add(next);
					pos = start + 1;
				}*/
				map.put(kgram, follows);
			}
			//keyPos++;
		}	
		return map;
	}
	
	// faster
	public HashMap<WordGram,ArrayList<String>> buildMap2(){
		HashMap<WordGram,ArrayList<String>> map = new HashMap<WordGram,ArrayList<String>>();
		int len = myText.length-myOrder;
		
		for(int k=0; k < len+1; k++) {
			WordGram kgram = new WordGram(myText,k,myOrder);
			
			if(!map.containsKey(kgram)) {
				ArrayList<String> follows = new ArrayList<String>();
				if(k < len) {
					String next = myText[k+myOrder];
					follows.add(next);
				}				
				map.put(kgram, follows);
			} else {
				if(k < len) {
					String next = myText[k+myOrder];
					ArrayList<String> follows = map.get(kgram);
					follows.add(next);
					map.put(kgram, follows);
				}								
			}
		}				
		return map;
	}
	
	public void printHashMapInfo() {
		int max=0;
		ArrayList<WordGram> largest = new ArrayList<WordGram>();
		
		//System.out.println("Hashmap: " + myMap);
		System.out.println("Number of keys in map: " + myMap.size());
		for(WordGram w : myMap.keySet()) {
			ArrayList<String> temp = myMap.get(w);
			if(temp.size() > max) {
				max = temp.size();
			}
		}
		System.out.println("The maximum number of elements following a key is " + max);
		for(WordGram w : myMap.keySet()) {
			ArrayList<String> temp = myMap.get(w);
			if(temp.size() == max) {
				largest.add(w);
			}
		}
		System.out.println("Keys that have maximum size values: " + largest);
	}
}
