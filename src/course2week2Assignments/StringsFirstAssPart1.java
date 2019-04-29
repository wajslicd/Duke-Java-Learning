package course2week2Assignments;

public class StringsFirstAssPart1 {
	// has one String parameter dna, representing a string of DNA.
	// returns a substring that starts with “ATG” and ends with “TAA” if the
	// length of the substring between the “ATG” and “TAA” is a multiple of 3
	// If there is no “ATG” or “TAA” or the length of the substring is not a multiple of 3 then returns an empty string
	public String findSimpleGene(String dna) {
		String result = "";
		int startIndex= dna.indexOf("ATG");
		if(startIndex == -1) {
			return "";
		}
		int stopIndex = dna.indexOf("TAA", startIndex + 3);
		if(stopIndex == -1) {
			return "";
		}
		result = dna.substring(startIndex, stopIndex + 3);
		if(result.length() % 3 == 0) {
			return result;
		}else {
			return "";
		}
		
	}
	
	// Tests findSimpleGene
	public void testSimpleGene() {
		// DNA with no 'ATG'
		String dna = "AATCGCCGATAAGCGA";
		System.out.println("DNA strand is " + dna);
		String gene = findSimpleGene(dna);
		System.out.println("Gene is " + gene);
		
		// DNA with no 'TAA'
		dna = "ACATGCCGATGAGCGAC";
		System.out.println("DNA strand is " + dna);
		gene = findSimpleGene(dna);
		System.out.println("Gene is " + gene);
		
		// DNA with no 'ATG' or 'TAA'
		dna = "ACACGCCGATTAGCGAACAA";
		System.out.println("DNA strand is " + dna);
		gene = findSimpleGene(dna);
		System.out.println("Gene is " + gene);
		
		// DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene)
		//dna = "ACATGCCGATTAGCGAATAAATGGCC";
		dna = "AAATGCCCTAACTAGATTAAGAAACC";
		System.out.println("DNA strand is " + dna);
		gene = findSimpleGene(dna);
		System.out.println("Gene is " + gene);
		
		// DNA with ATG, TAA and the substring between them is not a multiple of 3
		dna = "ACATGCCGATTAGCGATAAATGGCC";
		System.out.println("DNA strand is " + dna);
		gene = findSimpleGene(dna);
		System.out.println("Gene is " + gene);
	}

	public static void main(String[] args) {
		StringsFirstAssPart1 p1 = new StringsFirstAssPart1();
		p1.testSimpleGene();
	}

}
