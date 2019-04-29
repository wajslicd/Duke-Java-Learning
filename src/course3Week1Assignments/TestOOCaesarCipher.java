package course3Week1Assignments;

import edu.duke.FileResource;

public class TestOOCaesarCipher {
	
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
	
	public String breakCaesarCipher(String input) {
		int[] freqs = countLetters(input);
		int maxDex = maxIndex(freqs);
		int dKey = maxDex - 4;
		if(maxDex < 4) {
			dKey = 26 - (4 - maxDex);
		}
		OOCaesarCipher cc = new OOCaesarCipher(dKey);
		return cc.decrypt(input);
	}
	
	public void simpleTests() {
		int key = 15;
		String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
		//int key = 18;
		//FileResource fr = new FileResource();
		//String message = fr.asString();
		
		OOCaesarCipher cc = new OOCaesarCipher(key);
		String encrypted = cc.encrypt(message);
		System.out.println("Encrytped message:\n" + encrypted);
		encrypted = cc.decrypt(encrypted);
		//System.out.println("Original message:\n" + encrypted);
		String decrypted = breakCaesarCipher(encrypted);
		System.out.println("Decrytped message from breaking cipher:\n" + decrypted);
	}
	
	

	public static void main(String[] args) {
		TestOOCaesarCipher tcc = new TestOOCaesarCipher();
		tcc.simpleTests();
	}

}
