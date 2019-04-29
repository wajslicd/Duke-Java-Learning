package course3Week1Assignments;

/**
 * Transforms words from a file into another form
 * 
 * @author Diane Wajslic
 * @version 01/01/2019 
 */

public class WordPlay {
	// This method returns true if ch is a vowel 
	// (one of 'a', 'e', 'i', 'o', or 'u' or the uppercase versions) and false otherwise
	public boolean isVowel(char ch) {
		String vowel = "aeiou";
		
		for(int i=0; i < vowel.length(); i++) {
			char currChar = vowel.charAt(i);
			if(Character.toLowerCase(ch) == currChar) {
				return true;
			}
		}
		return false;
	}
	
	// This method returns a String that is the string phrase with all the vowels (uppercase or lowercase) 
	// replaced by ch
	public String replaceVowels(String phrase, char ch) {
		StringBuilder newPhrase = new StringBuilder(phrase);
		
		for(int i = 0; i < phrase.length(); i++) {
			char currChar = phrase.charAt(i);
			if(isVowel(currChar)) {
				newPhrase.setCharAt(i, ch);
			}
		}
		return newPhrase.toString();
	}
	
	// This method returns a String that is the string phrase but with the Char ch (upper- or lowercase) replaced by
	// ‘*’ if it is in an odd number location in the string 
	// (e.g. the first character has an odd number location but an even index, it is at index 0), or
	// ‘+’ if it is in an even number location in the string 
	// (e.g. the second character has an even number location but an odd index, it is at index 1)
	public String emphasize(String phrase, char ch) {
		StringBuilder newPhrase = new StringBuilder(phrase);
		
		for(int i = 0; i < phrase.length(); i++) {
			char currChar = phrase.charAt(i);
			if(Character.toLowerCase(currChar) == ch) {
				if(i % 2 == 0) {
					newPhrase.setCharAt(i, '*');
				} else {
					newPhrase.setCharAt(i, '+');
				}
			}		
		}
		return newPhrase.toString();
	}
	
	public void testIsVowel() {
		char ch = 'S';
		System.out.println(ch + " is a vowel: " + isVowel(ch));
	}
	
	public void testReplaceVowels() {
		String phrase = "Hello World";
		System.out.println(replaceVowels(phrase, '*'));
		
	}
	
	public void testEmphasize() {
		String phrase = "Mary Bella Abracadabra";
		char ch = 'a';
		System.out.println(emphasize(phrase, ch));
	}

	public static void main(String[] args) {
		WordPlay wp = new WordPlay();
		wp.testIsVowel();
		wp.testReplaceVowels();
		wp.testEmphasize();
	}

}
