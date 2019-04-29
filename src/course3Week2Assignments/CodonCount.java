package course3Week2Assignments;

import java.util.HashMap;
import edu.duke.*;

public class CodonCount {
	private HashMap<String, Integer> codons;
	
	public CodonCount() {
		codons = new HashMap<String, Integer>();
	}
	
	public void buildCodonMap(int start, String dna) {
		codons.clear();		
		int l = (dna.length() - start)/3 * 3 ;
		dna = dna.substring(start, l + start);
		//System.out.println(dna);
		for(int i = 0; i < dna.length(); i += 3) {
			String codon = dna.substring(i, i + 3);
			//System.out.println(codon);
			if(!codons.containsKey(codon)) {
				codons.put(codon, 1);
			} else {
				codons.put(codon, codons.get(codon) + 1);
			}
		}
	}
	
	public String getMostCommonCodon() {
		int max = 0;
		String mostCommon = "";
		
		for(String s: codons.keySet()) {
			int temp = codons.get(s);
			if(temp > max) {
				max = temp;
				mostCommon = s;
			}				 
		}
		return mostCommon;
	}
	
	public void printCodonCounts(int start, int end) {
		for(String s: codons.keySet()) {
			int count = codons.get(s);
			if(count >= start && count <= end) {
				System.out.println(s + " " + count);
			}
		}
	}
	
	// Use smalldna.txt in dnaData folder for testing
	public void tester() {
		FileResource f = new FileResource();
		String dna = f.asString().trim().toUpperCase();
		for(int i = 0; i < 3; i++) {
			buildCodonMap(i, dna);
			String mostCommon = getMostCommonCodon();
			System.out.println("\nReading frame starting with " + i + " results in " + codons.size() + " unique codons");
			System.out.println("and most common codon is " + mostCommon + " with count " + codons.get(mostCommon));
			System.out.println("Counts of codons between 1 and 5 inclusive are:");
			printCodonCounts(7, 7);
		}		
	}

	public static void main(String[] args) {
		CodonCount cc = new CodonCount();		
		cc.tester();
		
	}

}
