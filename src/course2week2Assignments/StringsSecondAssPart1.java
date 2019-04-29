package course2week2Assignments;

public class StringsSecondAssPart1 {
	// This method returns the index of the first occurrence of stopCodon that appears 
	// past startIndex and is a multiple of 3 away from startIndex. 
	// If there is no such stopCodon, this method returns the length of the dna strand.
	public int findStopCodon(String dna, int startIndex, String stopCodon) {
		int currIndex = dna.indexOf(stopCodon, startIndex + 3);
		while(currIndex != -1) {
			int diff = currIndex - startIndex;
			if (diff%3 == 0) {
				return currIndex;
			} else {
				currIndex = dna.indexOf(stopCodon, currIndex + 1);
			}
		}
		return dna.length();
	}
	
	// This method returns a gene as a string from a DNA string
	// An empty string is returned if a gene was not found
	public String findGene(String dna, int where) {
		int startIndex = dna.indexOf("ATG", where);
		if (startIndex == -1) {
			return "";
		}
		int taaIndex = findStopCodon(dna, startIndex, "TAA");
		int tagIndex = findStopCodon(dna, startIndex, "TAG");
		int tgaIndex = findStopCodon(dna, startIndex, "TGA");
		
		int temp = Math.min(taaIndex, tagIndex);
		int minIndex = Math.min(temp, tgaIndex);
		if(minIndex == dna.length()) {
			return "";
		}
		return dna.substring(startIndex, minIndex + 3);
	}
	
	// repeatedly finds and prints genes from a string until there are no more genes in the string
	public void printAllGenes(String dna) {
		int startIndex = 0;
		
		while(true) {
			String currentGene = findGene(dna, startIndex);
			if(currentGene.isEmpty()) {
				break;
			}
			System.out.println(currentGene);
			startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
		}
		
	}
	
	// Calls the method findStopCodon with several examples and prints out the results 
	// to check if findStopCodon is working correctly.
	public void testFindStopCodon() {
		//            01234567890123456789012345
		String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
		int dex = findStopCodon(dna, 0, "TAA"); // first stop codon
		System.out.println("index is " + dex);
		if(dex != 9) {
			System.out.println("error on 9");
		}
		dex = findStopCodon(dna, 9, "TAA");  // next stop codon
		System.out.println("index is " + dex);
		if(dex != 21) {
			System.out.println("error on 21");
		}
		dex = findStopCodon(dna, 1, "TAA"); // no multiples of 3
		System.out.println("index is " + dex);
		if(dex != 26) {
			System.out.println("error on 26");
		}
		dex = findStopCodon(dna, 1, "TAG"); // no codons
		System.out.println("index is " + dex);
		if(dex != 26) {
			System.out.println("error on 26");
		}
		//     01234567890123456789012345
		dna = "xxxyyyzzTAAxxxyyyzzzzTAAxx";
		dex = findStopCodon(dna, 0, "TAA");  // first stop codon not multiple of 3
		System.out.println("index is " + dex);
		if(dex != 21) {
			System.out.println("error on 21");
		}
	}
	
	// Calculates the gene by sending a DNA string as an argument to findGene. 
	// If a gene exists, then prints the gene, otherwise prints the empty string.
	public void testFindGene() {
		// DNA with no “ATG”,
		String gene = findGene("xxxyyyzzzTAAxxxyyyzzzTAAxx", 0);
		System.out.println("gene is " + gene);
		//  DNA with “ATG” and one valid stop codon
		gene = findGene("xxxyyyATGxxTGAxxxyyyzzTAAxxxyyTAGxx", 0); //(-1 -1 x)
		System.out.println("gene is " + gene);
		gene = findGene("TAAxxATGxxxyyyzzzTAAxxxyyyzzzTAGxx", 0); //(x -1 x)
		System.out.println("gene is " + gene);
		// DNA with “ATG” and multiple valid stop codons
		gene = findGene("xATGyyyzzzTGAxxxyyyzzzTAAxxxyyyTAGxx", 0); //(x x x)
		System.out.println("gene is " + gene);
		gene = findGene("xATGyyyzzzxTAAxxyyyzzzTGAxxxyyyTAGxx", 0); //(-1 x x)
		System.out.println("gene is " + gene);
		gene = findGene("xATGyyyzzzTGAxxxyyyzzzTAAxxxyyTAGxx", 0); //(x x -1)
		System.out.println("gene is " + gene);
		// DNA with “ATG” and no valid stop codons
		gene = findGene("xxxyyyATGxxTGAxxxyyyzzTAAxxxyTAGxx", 0);
		System.out.println("gene is " + gene);
	}
	
	// Test the printAllGenes method
	public void testOn(String dna) {
		System.out.println("Testing printAllGenes on " + dna);
		printAllGenes(dna);
	}
	
	// Calls testOn to test the printAllGenes method
	public void test() {
		//      v  ATGv  v  TGAv  ATGv  TAAATGv  TAGATGTAA
		testOn("TCTATGATCTTTTGAAACATGGACTAAATGCCCTAGATGTAA");		
		//      ATGv  TAAv  ATGv  v  v  v  TGAATGv  v  TAG
		testOn("ATGCTCTAAACCATGTTCCCTTATAGTTGAATGAAATTTTAGAGA");
		//      ATGv  v  v  TAG         ATGv  v  v  ATGv  TAA 
		testOn("ATGTTAGATGATTAGGATAGGGATATGAGAGTATATATGAGTTAAA"); // stray ATG
		testOn("TAATAATAGTGA"); // no ATG
		testOn("GGATGCCCTTT"); // no stop codons
		testOn(""); // empty string
		testOn("AATGCTAACTAGCTGACTAAT"); //quiz
	}
	
	public static void main(String[] args) {
		StringsSecondAssPart1 p1 = new StringsSecondAssPart1();
		p1.testFindStopCodon();
		p1.testFindGene();
		p1.test();
	}

}
