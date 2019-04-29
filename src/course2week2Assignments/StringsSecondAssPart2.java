package course2week2Assignments;

public class StringsSecondAssPart2 {
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
	
	void testHowMany() {
		int count = howMany("GAA", "ATGAACGAATTGAATC");
		if(count != 3) {
			System.out.println("Error, expected 3 but returned " + count); 
		} else {
			System.out.println("Count was " + count);
		}
		count = howMany("AA", "ATAAAA");
		if(count != 2) {
			System.out.println("Error, expected 2 but returned " + count); 
		} else {
			System.out.println("Count was " + count);
		}
		count = howMany("BB", "ATGAACGAATTGAATC");
		if(count != 0) {
			System.out.println("Error, expected 0 but returned " + count); 
		} else {
			System.out.println("Count was " + count);
		}
		count = howMany("BB", "");
		if(count != 0) {
			System.out.println("Error, expected 0 but returned " + count); 
		} else {
			System.out.println("Count was " + count);
		}
	}

	public static void main(String[] args) {
		StringsSecondAssPart2 p2 = new StringsSecondAssPart2();
		p2.testHowMany();

	}

}
