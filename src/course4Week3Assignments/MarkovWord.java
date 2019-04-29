package course4Week3Assignments;

import java.util.*;

public class MarkovWord implements IMarkovModel{
	private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
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
	    ArrayList<String> follows = new ArrayList<String>();
	    
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
			//pos = start + key.length();
			pos = start + 1;
		}
	    return follows;
    }
	
	public void testIndexOf() {
		String source = "this is just a test yes this is a simple test";
		String[] words = source.split("\\s+");
		WordGram target = new WordGram(words, 3, myOrder);
		int pos = 0;
		
		setTraining(source);
		int index = indexOf(myText, target , pos);
		System.out.println("Index of " + target + " is " + index);
	}
	
	public static void main(String[] args) {
		MarkovWord t = new MarkovWord(4);
		t.testIndexOf();

	}
}
