package course4Week3Assignments;

/**
 * Write a description of class MarkovZero here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;
import java.util.ArrayList;

public class MarkovFourWithInt extends AbstractMarkovModel{
	
	public MarkovFourWithInt() {
		myModel = 4;
	}
	
	public String getRandomText(int numChars){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-4);
		String key = myText.substring(index, index+4);
		sb.append(key);
		
		for(int k=0; k < numChars-4; k++){
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
