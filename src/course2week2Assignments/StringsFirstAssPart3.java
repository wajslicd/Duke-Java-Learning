package course2week2Assignments;

public class StringsFirstAssPart3 {
	// This method returns true if stringa appears at least twice in stringb, 
	// otherwise it returns false.
	public boolean twoOccurrences(String stringa, String stringb) {
		int index1 = stringb.indexOf(stringa);
		if(index1 != -1) {
			int index2 = stringb.indexOf(stringa, index1 + 1);
			if(index2 != -1) {
				return true;
			}
		}
		return false;
	}
	
	// This method finds the first occurrence of stringa in stringb, 
	// and returns the part of stringb that follows stringa. 
	// If stringa does not occur in stringb, then returns stringb. 
	public String lastPart(String stringa, String stringb) {
		int index = stringb.indexOf(stringa);
		if(index != -1) {			
			return stringb.substring(index + stringa.length());
		}
		return stringb;
	}
	
	// calls twoOccurrences on several pairs of strings and prints the strings and the result for each pair. 
	// calls the method lastPart with several pairs of strings. For each call prints the strings passed in and the result. 
	public void testing() {
		String stringb = "A story by Abby Long";
		String stringa = "by";
		boolean atLeast2 = twoOccurrences(stringa, stringb);		
		System.out.println("Search for at least 2 occurrences of \'" + stringa + "\' in \'" + stringb + "\' is " + atLeast2);
		
		stringb = "banana";
		stringa = "a";
		atLeast2 = twoOccurrences(stringa, stringb);		
		System.out.println("Search for at least 2 occurrences of \'" + stringa + "\' in \'" + stringb + "\' is " + atLeast2);
		
		stringb = "ctgtatgta";
		stringa = "atg";
		atLeast2 = twoOccurrences(stringa, stringb);		
		System.out.println("Search for at least 2 occurrences of \'" + stringa + "\' in \'" + stringb + "\' is " + atLeast2);
		
		stringb = "baah";
		stringa = "a";
		atLeast2 = twoOccurrences(stringa, stringb);		
		System.out.println("Search for at least 2 occurrences of \'" + stringa + "\' in \'" + stringb + "\' is " + atLeast2);
		
		stringb = "bongobongo";
		stringa = "y";
		atLeast2 = twoOccurrences(stringa, stringb);		
		System.out.println("Search for at least 2 occurrences of \'" + stringa + "\' in \'" + stringb + "\' is " + atLeast2);
		
		stringb = "banana";
		stringa = "an";
		String str = lastPart(stringa, stringb);
		System.out.println("The part of the string after \'" + stringa + "\' in \'" + stringb + "\' is \'" + str + "\'");
		
		stringb = "forest";
		stringa = "zoo";
		str = lastPart(stringa, stringb);
		System.out.println("The part of the string after \'" + stringa + "\' in \'" + stringb + "\' is \'" + str + "\'");
	}

	public static void main(String[] args) {
		StringsFirstAssPart3 p3 = new StringsFirstAssPart3();
		p3.testing();
	}

}
