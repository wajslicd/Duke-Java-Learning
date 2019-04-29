package course3Week1Assignments;

/**
 * Caesar Cipher algorithm
 * 
 * @author Diane Wajslic
 * @version 01/01/2019 
 */

import edu.duke.*;

public class CaesarCipher {
	public String encrypt(String input, int key) {
		StringBuilder encrypted = new StringBuilder(input);		
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
		
		for(int i = 0; i < encrypted.length(); i++) {
			char currChar = encrypted.charAt(i);
			char upChar = Character.toUpperCase(currChar);
			int idx = alphabet.indexOf(upChar);
			
			if(idx != -1) {
				char newChar;
				
				if(Character.isLowerCase(currChar)) {
					newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));
				} else {
					newChar = shiftedAlphabet.charAt(idx);
				}
				encrypted.setCharAt(i, newChar);
			}		
		}
		return encrypted.toString();
	}
	
	public String encryptTwoKeys(String input, int key1, int key2) {
		StringBuilder encrypted = new StringBuilder(input);
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
		String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
		
		for(int i = 0; i < encrypted.length(); i++) {
			char currChar = encrypted.charAt(i);
			char upChar = Character.toUpperCase(currChar);
			int idx = alphabet.indexOf(upChar);
			
			if(idx != -1) {
				char newChar;
				String shiftedAlphabet; 
				
				if(i % 2 == 0) shiftedAlphabet = shiftedAlphabet1;
				else shiftedAlphabet = shiftedAlphabet2;
				
				if(Character.isLowerCase(currChar)) {
					newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));
				} else {
					newChar = shiftedAlphabet.charAt(idx);
				}
				encrypted.setCharAt(i, newChar);
			}						
		}
		return encrypted.toString();
	}
	
	public String decrypt(String encrypted) {
		CaesarCipher cc = new CaesarCipher();
		int[] freqs = countLetters(encrypted);
		int maxDex = maxIndex(freqs);
		int dKey = maxDex - 4;
		if(maxDex < 4) {
			dKey = 26 - (4 - maxDex);
		}
		return cc.encrypt(encrypted, 26 - dKey);
	}
	
	public String decryptTwoKeys(String encrypted) {
		CaesarCipher cc = new CaesarCipher();
		String firstChar = halfOfString(encrypted, 0);
		String secondChar = halfOfString(encrypted, 1);
		
		int dKey1 = getKey(firstChar);
		int dKey2 = getKey(secondChar);
		
		System.out.println(dKey1 + "," + dKey2);
		return cc.encryptTwoKeys(encrypted, 26 - dKey1, 26-dKey2);
	}
	
	private int[] countLetters(String message) {
		String alph = "abcdefghijklmnopqrstuvwxyz";
		int[] counts = new int[26];
		
		for(int k = 0; k < message.length(); k++) {
			char ch = Character.toLowerCase(message.charAt(k));
			int dex = alph.indexOf(ch);
			if(dex != -1) {
				counts[dex] += 1;
			}
		}
		return counts;
	}
	
	private int maxIndex(int[] vals) {
		int maxDex = 0;
		
		for(int k = 0; k < vals.length; k++) {
			if(vals[k] > vals[maxDex]) {
				maxDex = k;
			}
		}
		return maxDex;
	}
	
	private String halfOfString(String message, int start) {
		StringBuilder newMessage = new StringBuilder();
		for(int k = 0; k < message.length(); k++) {
			char currChar = message.charAt(k);
			if(k % 2 == start) {
				newMessage.append(currChar);
			}
		}
		//System.out.println(newMessage.toString());
		return newMessage.toString();
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
		
	// Use file test01.txt in data folder for testing
	public void testCaesar() {
		int key = 15;
		FileResource fr = new FileResource();
		String message = fr.asString();
		String encrypted = encrypt(message, key);
		System.out.println("key is " + key + "\n" + encrypted);
		String decrypted = encrypt(encrypted, 26 - key);
		System.out.println("key is " + key + "\n" + decrypted);
	}
	
	// Use file wordsLotsOfEs.txt in data folder for testing
	public void testDecrypt() {
		int key = 15;
		FileResource fr = new FileResource();
		String message = fr.asString();
		String encrypted = encrypt(message, key);
		System.out.println("key is " + key + "\n" + encrypted);
		String decrypted = decrypt(encrypted);
		System.out.println("Decrypted message is:\n" + decrypted);
	}
	
	// Use file wordsLotsOfEs.txt in data folder for testing
	public void testEncryptTwoKeys() {
		int key1 = 17;
		int key2 = 4;
		FileResource fr = new FileResource();
		String message = fr.asString();
		String encrypted = encryptTwoKeys(message, key1, key2);
		System.out.println("key1 is " + key1 + ", key2 is " + key2 + "\n" + encrypted);
		//String message = "Top ncmy qkff vi vguv vbg ycpx";
		String decrypted = encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
		//String decrypted = encryptTwoKeys(message, 26 - key1, 26 - key2);
		System.out.println("key1 is " + key1 + ", key2 is " + key2 +"\n" + decrypted);
	}
	
	// Use file wordsLotsOfEsEncrypted.txt or mysteryTwoKeysPractice.txt in data folder for testing
	public void testDecryptTwoKeys() {
		FileResource fr = new FileResource();
		String message = fr.asString();
		//String message = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
		String decrypted = decryptTwoKeys(message);
		System.out.println("Decrypted message is:\n" + decrypted);
	}

	public static void main(String[] args) {
		CaesarCipher cc = new CaesarCipher();
		//cc.testCaesar();
		//cc.testEncryptTwoKeys();
		//cc.testDecrypt();
		cc.testDecryptTwoKeys();
	}

}
