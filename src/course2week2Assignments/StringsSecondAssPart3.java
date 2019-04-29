package course2week2Assignments;

public class StringsSecondAssPart3 {
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
		
		// This method returns the number of genes found in a dna string. 
		public int countGenes(String dna) {
			int startIndex = 0;
			int count = 0;
			
			while(true) {
				String currentGene = findGene(dna, startIndex);
				if(currentGene.isEmpty()) {
					break;
				}
				count = count + 1;
				startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
			}
			return count;
		}
		
		// Test the countGenes method
		public void testOn(String dna, int expected) {
			System.out.println("Testing countGenes on " + dna);
			int count = countGenes(dna);
			if(count != expected) {
				System.out.println("Error, expected " + expected + " but returned " + count);
			} else {
				System.out.println("Count was " + count);
			}
			
		}
		
		public void testCountGenes() {

			testOn("ATGTAAGATGCCCTAGT", 2);
			testOn("ATGCTCTAAACCATGTTCCCTTATAGTTGAATGAAATTTTAGAGA", 3);
			testOn("ATGCTCTAA", 1);
			testOn("TCTATGATCTTTTGAAACATGGACTAAATGCCCTAGATGTAA", 4);
			testOn("ATGTTAGATGATTAGGATAGGGATATGAGAGTATATATGAGTTAAA", 2); // stray ATG
			testOn("TAATAATAGTGA", 0); // no ATG
			testOn("GGATGCCCTTT", 0); // no stop codons
			testOn("", 0); // empty string
		}

	public static void main(String[] args) {
		StringsSecondAssPart3 p3 = new StringsSecondAssPart3();
		p3.testCountGenes();

	}

}
