package course2week2Assignments;

import edu.duke.*;

// files used are in dnaData folder
public class StringsThirdAssPart1 {
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
	
	// this method creates and returns a StorageResource containing the genes found. 
	public StorageResource getAllGenes(String dna) {
		StorageResource geneList = new StorageResource();
		int startIndex = 0;
		
		while(true) {
			String currentGene = findGene(dna, startIndex);
			if(currentGene.isEmpty()) {
				break;
			}
			geneList.add(currentGene);
			startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
		}
		return geneList;
	}
	
	// returns the ratio of C’s and G’s in a dna string as a fraction of the entire strand of DNA
	public float cgRatio(String dna) {
		int c = howMany("C", dna);
		int g = howMany("G", dna);
		if(dna.length() != 0) {
			return (c + g) / (float)dna.length();
		} else {
			return 0;
		}
		
	}
	
	// returns the number of times the codon CTG appears in dna
	public int countCTG(String dna) {
		return howMany("CTG", dna.toUpperCase());
	}
	
	// returns an integer indicating how many times stringa appears in stringb, 
	// where each occurrence of stringa must not overlap with another occurrence of it.
	public int howMany(String stringa, String stringb) {
		int count = 0;
		int startIndex = 0;
		int currIndex = stringb.indexOf(stringa, startIndex);
		while(currIndex != -1) {
			count = count + 1;
			currIndex = stringb.indexOf(stringa, currIndex + stringa.length());
		}
		return count;		
	}
	
	// This method processes all the strings in sr and prints information about them
	public void processGenes(StorageResource sr) {
		StorageResource lengthOver9 = new StorageResource();
		StorageResource cgRatioOver35 = new StorageResource();
		int count9 = 0;
		int countCg = 0;
		int longest = 0;
		
		for (String g : sr.data()) {
			int length = g.length();
			if(length > 9 ) {
				lengthOver9.add(g);;
				count9 = count9 + 1;
			}
			if(cgRatio(g) > 0.35) {
				cgRatioOver35.add(g);
				countCg = countCg + 1;
			}
			if(length > longest) {
				longest = length;
			}
		}
		System.out.println("Strings that are longer than 9 characters:");
		for(String g : lengthOver9.data()) {
			System.out.println(g);
		}
		System.out.println("Count of Strings that are longer than 9 characters is " + count9);
		
		System.out.println("Strings whose C-G-ratio is higher than 0.35:");
		for (String g : cgRatioOver35.data()) {		
			System.out.println(g);
		}
		System.out.println("Count of Strings whose C-G-ratio is higher than 0.35 is " + countCg);
		System.out.println("Longest gene is " + longest);
		System.out.println("");
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
	
	// Test the getAllGenes method
	public void testOn(String dna) {
		System.out.println("Testing getAllGenes on " + dna);
		StorageResource genes = getAllGenes(dna);
		for(String g : genes.data()) {
			System.out.println(g);
		}
	}
	
	// Calls testOn to test the getAllGenes method
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
	
	// tests the method cgRatio
	public void testCgRatio() {
		float ratio = cgRatio("ATGCCATAG"); // c and g
		System.out.println("ratio is " + ratio);
		ratio = cgRatio(""); // empty string
		System.out.println("ratio is " + ratio);
		ratio = cgRatio("ATGTTATAG"); // no C
		System.out.println("ratio is " + ratio);
		ratio = cgRatio("ATACCATAA"); // no G
		System.out.println("ratio is " + ratio);
	}
	
	// tests the method countCTG
	public void testCountCtg() {
		int count = countCTG("ATGCTGCTGTCTGAG"); // ctg
		System.out.println("count is " + count);
		count = countCTG(""); // empty string
		System.out.println("count is " + count);
		count = countCTG("ATGATGATGATG"); // no CTG
		System.out.println("count is " + count);
	}
	
	public void testProcessGenes(String dna) {
		String resource = dna.toUpperCase();
		StorageResource sr = getAllGenes(resource);
		processGenes(sr);		
	}
	
	public void test2() {
		// DNA string that has some genes longer than 9 characters
		testProcessGenes("ATGCTCTAAACCATGTTCCCTTATAGTTGAATGAAATTTTAGAGA");
		// no genes longer than 9 characters
		testProcessGenes("AATGTAACATGTAGCTGACTAAT");
		// genes whose C-G-ratio is higher than 0.35
		testProcessGenes("ATGCCATAG");
		// genes whose C-G-ratio is lower than 0.35
		testProcessGenes("ATACCATAA");
		testProcessGenes("CTGONEAtGONEcCCGGGAAAXXXYYYGGGGTAGYYCTGCCCATGENDZZZTAAONEXXXYYYZZZTAAXXxXXTWOATGTWOYYYZZZCCCATGATGENDZZZTAGTWOXXTHREEATGATGTAATHREESTOPTAGAGGGCCCCCFOURATGTAGGXXXFIVEAtgYYYFIVZZZAAAXXXFIVENDZZZTGA");
		testProcessGenes("CTGONEAtGONEcCCGGGAAAXXXYYYGGGGTAGYYCTGCCCATGENDZZZTAAONEXXXYYYZZZTAAXXxXXTWOATGTWOYYYZZZCCCATGATGENDZZZTAGTWOXXTHREEATGATGTAATHREESTOPTAGAGGGCCCCCFOURATGTAGGXXXFIVEAtgYYYFIVZZZAAAXXXFIVENDZZZTGAFIVESTOPSIXATGSIXCGGGCCGGGATCAAASIXENDTAASEVATGSIXCGGGCCGGGATCAAASEVENDENDtaAEIGSTOPTAGAGLASTONEATgtAACTG");
	}
	
	public String mystery(String dna) {
		  int pos = dna.indexOf("T");
		  int count = 0;
		  int startPos = 0;
		  String newDna = "";
		  if (pos == -1) {
		    return dna;
		  }
		  while (count < 3) {
		    count += 1;
		    newDna = newDna + dna.substring(startPos,pos);
		    startPos = pos+1;
		    pos = dna.indexOf("T", startPos);
		    if (pos == -1) {
		      break;
		    }
		  }
		  newDna = newDna + dna.substring(startPos);
		  return newDna;
		}
	
	
	public static void main(String[] args) {
		StringsThirdAssPart1 p1 = new StringsThirdAssPart1();
		//p1.testFindStopCodon();
		//p1.testFindGene();
		//p1.test();
		//p1.testCgRatio();
		//p1.testCountCtg();
		//p1.test2();
		//FileResource fr = new FileResource("dnaData\\brca1line.fa");
		FileResource fr = new FileResource("dnaData\\GRch38dnapart.fa");
		String dna = fr.asString();
		p1.testProcessGenes(dna);
		int count = p1.countCTG(dna);
		System.out.println("Count of codon CTG is " + count);
		//String s = p1.mystery("ATGCTCTAAACCATGTTCCCTTATAGTTGAATGAAATTTTAGAGA");
		//System.out.println(s);

	}

}
