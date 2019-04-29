package course4Week3Assignments;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 42;
		
		MarkovZeroWithInt mz = new MarkovZeroWithInt();
        runModel(mz, st, size, seed);
    
        MarkovOneWithInt mOne = new MarkovOneWithInt();
        runModel(mOne, st, size, seed);
        
        MarkovModelWithInt mThree = new MarkovModelWithInt(3);
        runModel(mThree, st, size, seed);
        
        MarkovFourWithInt mFour = new MarkovFourWithInt();
        runModel(mFour, st, size, seed);

    }
    
    public void testHashMap() {
    	String st = "yes-this-is-a-thin-pretty-pink-thistle";
    	int size = 50;
		int seed = 42;
		
		EfficientMarkovModel mTwo = new EfficientMarkovModel(2);
		runModel(mTwo, st, size, seed);
    }
    
    public void compareMethods() {
    	FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 1000;
		int seed = 615;
		
		double begin = System.nanoTime();
		MarkovModelWithInt mTwo = new MarkovModelWithInt(5);
        runModel(mTwo, st, size, seed);
        double end = System.nanoTime();
        System.out.println("Time: " + (end - begin)/1e9 + "\n");
        
        begin = System.nanoTime();
        EfficientMarkovModel mTwo2 = new EfficientMarkovModel(5);
		runModel(mTwo2, st, size, seed);
		end = System.nanoTime();
		System.out.println("Time: " + (end - begin)/1e9 + "\n");
    }

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
	
	public static void main(String[] args) {
		MarkovRunnerWithInterface t = new MarkovRunnerWithInterface();
		//t.runMarkov();
		//t.testHashMap();
		t.compareMethods();
	}
}
