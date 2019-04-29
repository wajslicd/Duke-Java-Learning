package course4Week3Assignments;

/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.ArrayList;

public class MarkovModelWithInt extends AbstractMarkovModel{
	
	public MarkovModelWithInt(int order) {
		myModel = order;
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
	
	
}
