package course4Week3Assignments;

import java.util.Random;

public class MarkovZeroWithInt extends AbstractMarkovModel{
	
	public MarkovZeroWithInt() {
		myModel = 0;
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int k=0; k < numChars; k++){
			int index = myRandom.nextInt(myText.length());
			sb.append(myText.charAt(index));
		}
		
		return sb.toString();
	}
}
