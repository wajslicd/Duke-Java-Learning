package course3Week2Assignments;

import java.util.ArrayList;

import edu.duke.FileResource;

public class WordFrequencies {
	private ArrayList<String> myWords;
	private ArrayList<Integer> myFreqs;
	
	public WordFrequencies() {
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}
	
	void findUnique() {
		myWords.clear();
		myFreqs.clear();
		FileResource resource = new FileResource();
		for(String s : resource.words()) {
			s = s.toLowerCase();
			int index = myWords.indexOf(s);
			if(index == -1) {
				myWords.add(s);
				myFreqs.add(1);
			} else {
				int val = myFreqs.get(index);
				myFreqs.set(index, val+1);
			}
		}
	}
	
	public int findIndexOfMax() {
		int max = 0;
		int index = 0;
		
		for(int i = 0; i < myFreqs.size(); i++) {
			int val = myFreqs.get(i);
			if(val > max) {
				max = val;
				index = i;
			}			
		}
		return index;
	}
	
	public void tester() {
		findUnique();
		System.out.println("# unique words: " + myWords.size());
		for(int i = 0; i < myWords.size(); i++) {
			System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
		}
		int index = findIndexOfMax();
		System.out.println("Most common word '" + myWords.get(index) + "' occurs " + myFreqs.get(index) + " times.");
	}

	// Use testwordfreqs.txt in data folder for testing
	public static void main(String[] args) {
		WordFrequencies wf = new WordFrequencies();
		wf.tester();
	}

}
