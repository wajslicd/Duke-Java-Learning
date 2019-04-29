package course4Week3Assignments;

/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.ArrayList;

public class MarkovModel {
	private String myText;
	private Random myRandom;
	private int myModel;
	
	public MarkovModel(int n) {
		myRandom = new Random();
		myModel = n;
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
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
	
	public ArrayList<String> getFollows(String key){
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
			String next = myText.substring(start+key.length(), start+key.length() + 1);
			follows.add(next);
			//pos = start + key.length();
			pos = start + 1;
		}
		return follows;
	}

}
