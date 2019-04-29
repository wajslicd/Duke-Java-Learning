package course4Week3Assignments;

/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovWordRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(5); 
        markovWord.setRandom(844);
        runModel(markovWord, st, 120); 
    }
    
    public void runMarkovTwo() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordTwo markovWord = new MarkovWordTwo(); 
        markovWord.setRandom(832);
        runModel(markovWord, st, 120); 
    }
    
    public void testMarkov() { 
    	String st = "this is just a test yes this is a simple test";
        MarkovWord markovWord = new MarkovWord(2); 
        runModel(markovWord, st, 20); 
    }
    
    public void testHashMap() {
    	//String st = "this is a test yes this is really a test";
    	String st = "this is a test yes this is really a test yes a test this is wow";
    	int size = 50;
		int seed = 42;
		
		EfficientMarkovWord mwTwo = new EfficientMarkovWord(2);
		runModel(mwTwo, st, size, seed);
    }
    
    public void compareMethods() {
    	FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 100;
		int seed = 65;
		
		MarkovWord mw = new MarkovWord(2);
		double begin = System.nanoTime();
        runModel(mw, st, size, seed);
        double end = System.nanoTime();
        System.out.println("Time: " + (end - begin)/1e9 + "\n");
        
        EfficientMarkovWord emw = new EfficientMarkovWord(2);
        begin = System.nanoTime();
		runModel(emw, st, size, seed);
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
    	MarkovWordRunner t = new MarkovWordRunner();
    	//t.testMarkov();
		//t.runMarkov();
    	//t.runMarkovTwo(); 	
		//t.testHashMap();		
		t.compareMethods();
	}

}
