package course2week2Assignments;

public class StringsFirstAssPart2 {
	// has String parameter dna, representing a string of DNA and strings representing start and stop codons
	// returns a substring in either all uppercase or all lowercase letters that starts with the start codon 
	// and ends with the stop codon if the length of the substring between the start codon and stop codon is a multiple of 3
	// If there is no start codon or stop codon or the length of the substring is not a multiple of 3 then returns an empty string
	public String findSimpleGene(String dna, String startCodon, String stopCodon) {
		String result = "";
		String dnaCopy = dna;
		
		if(dna == dnaCopy.toLowerCase()) {
			startCodon = startCodon.toLowerCase();
			stopCodon = stopCodon.toLowerCase();
		} else {
			startCodon = startCodon.toUpperCase();
			stopCodon = stopCodon.toUpperCase();
			dnaCopy = dnaCopy.toUpperCase();
		}
		
		int startIndex= dnaCopy.indexOf(startCodon);
		if(startIndex == -1) {
			return "";
		}
		int stopIndex = dnaCopy.indexOf(stopCodon, startIndex + 3);
		if(stopIndex == -1) {
			return "";
		}
		result = dnaCopy.substring(startIndex, stopIndex + 3);
		if(result.length() % 3 == 0) {
			return result;
		}else {
			return "";
		}
		
	}
	
	public boolean isUpper(String str) {
		if(str.equals(str.toUpperCase())) {
			return true;
		}
		return false;
	}
	
	// Tests findSimpleGene
	public void testSimpleGene() {
		// DNA with no 'ATG'
		String dna = "AATCGCCGATAAGCGA";
		System.out.println("DNA strand is " + dna);
		String gene = findSimpleGene(dna, "ATG", "TAA");
		System.out.println("Gene is " + gene);
		
		// DNA with no 'TAA'
		dna = "ACATGCCGATGAGCGAC";
		System.out.println("DNA strand is " + dna);
		gene = findSimpleGene(dna, "ATG", "TAA");
		System.out.println("Gene is " + gene);
		
		// DNA with no 'ATG' or 'TAA'
		dna = "ACACGCCGATTAGCGAACAA";
		System.out.println("DNA strand is " + dna);
		gene = findSimpleGene(dna, "ATG", "TAA");
		System.out.println("Gene is " + gene);
		
		// DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene) - uppercase
		dna = "ACATGCCGATTAGCGAATAAATGGCC";
		System.out.println("DNA strand is " + dna);
		gene = findSimpleGene(dna, "ATG", "TAA");
		System.out.println("Gene is " + gene);
		
		// DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene) - lowercase
		dna = "acatgccgattagcgaataaatggcc";
		System.out.println("DNA strand is " + dna);
		gene = findSimpleGene(dna, "ATG", "TAA");
		System.out.println("Gene is " + gene);
		
		// DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene) - mixed case
		dna = "aCAtGCCgATTAgCGAATaaaTGGCC";
		System.out.println("DNA strand is " + dna);
		gene = findSimpleGene(dna, "ATG", "TAA");
		System.out.println("Gene is " + gene);
		
		// DNA with ATG, TAA and the substring between them is not a multiple of 3
		dna = "ACATGCCGATTAGCGATAAATGGCC";
		System.out.println("DNA strand is " + dna);
		gene = findSimpleGene(dna, "ATG", "TAA");
		System.out.println("Gene is " + gene);
	}

	public static void main(String[] args) {
		StringsFirstAssPart2 p2 = new StringsFirstAssPart2();
		p2.testSimpleGene();

	}

}
