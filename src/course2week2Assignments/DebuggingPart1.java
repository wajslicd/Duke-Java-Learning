package course2week2Assignments;

public class DebuggingPart1 {
	// finds each occurrence of �abc_� in a String input (where _ is a single character) 
	// and prints �bc_� for each such occurrence.
	public void findAbc(String input) {
	    int index = input.indexOf("abc");
	    while (true) {
	        if (index == -1 || index >= input.length() - 3) {
	            break;
	        }
	        //System.out.println(index+1);
	        //System.out.println(index+4);
	        //System.out.println("index " + index);

	        String found = input.substring(index+1, index+4);
	        System.out.println(found);
	        index = input.indexOf("abc", index+3);
	        System.out.println("index after updating " + index);
	    }
	}
	   public void test() {
		   //findAbc("abcd");
		   //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
		   findAbc("abcabcabcabca");
	}

	public static void main(String[] args) {
		DebuggingPart1 p1 = new DebuggingPart1();
		p1.test();
	}

}
