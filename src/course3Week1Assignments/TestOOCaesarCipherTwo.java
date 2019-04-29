package course3Week1Assignments;

import edu.duke.FileResource;

public class TestOOCaesarCipherTwo {
	
	private String halfOfString(String message, int start) {
		String answer = "";
		
		for(int k = start; k < message.length(); k+=2) {
			answer = answer + message.charAt(k);			
		}
		//System.out.println(newMessage.toString());
		return answer;
	}
	
	private int[] countLetters(String message) {
		String alph = "abcdefghijklmnopqrstuvwxyz";
		int[] counts = new int[26];
		
		for(int k = 0; k < message.length(); k++) {
			char ch = Character.toLowerCase(message.charAt(k));
			int idx = alph.indexOf(ch);
			if(idx != -1) {
				counts[idx] += 1;
			}
		}
		return counts;
	}
	
	private int maxIndex(int[] vals) {
		int maxIdx = 0;
		
		for(int k = 0; k < vals.length; k++) {
			if(vals[k] > vals[maxIdx]) {
				maxIdx = k;
			}
		}
		return maxIdx;
	}
	
	private int getKey(String s) {
		int[] freqs = countLetters(s);
		int maxDex = maxIndex(freqs);
		int key = maxDex - 4;
		if(maxDex < 4) {
			key = 26 - (4 - maxDex);
		}
		return key;
	}
	
	public String breakCaesarCipher(String input) {
		String firstChar = halfOfString(input, 0);
		String secondChar = halfOfString(input, 1);
		
		int dKey1 = getKey(firstChar);
		int dkey2 = getKey(secondChar);
		
		OOCaesarCipherTwo cc2 = new OOCaesarCipherTwo(dKey1, dkey2);
		return cc2.decrypt(input);
	}
	
	public void simpleTests() {
		//int key1 = 14;
		//int key2 = 24;
		//String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
		//String encrypted = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
		//int key1 = 17;
		//int key2 = 3;
		FileResource fr = new FileResource();
		String message = fr.asString();
		
		//OOCaesarCipherTwo cc2 = new OOCaesarCipherTwo(key1, key2);
		//String encrypted = cc2.encrypt(message);
		//System.out.println("Encrytped message:\n" + encrypted);
		//encrypted = cc2.decrypt(encrypted);
		//System.out.println("Original message:\n" + encrypted);
		String decrypted = breakCaesarCipher(message);
		System.out.println("Decrytped message from breaking cipher:\n" + decrypted);
	}

	// Use file mysteryTwoKeysPractice.txt in data folder for testing
	public static void main(String[] args) {
		TestOOCaesarCipherTwo tcc2 = new TestOOCaesarCipherTwo();
		tcc2.simpleTests();
	}

}
