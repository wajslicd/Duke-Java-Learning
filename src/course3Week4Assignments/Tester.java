package course3Week4Assignments;

import java.util.Arrays;
import java.util.HashSet;

import edu.duke.*;

/**
 * Write a description of class Tester here.
 * 
 * @author Diane Wajslic 
 * @version 29/01/19
 */

public class Tester {
	// Use titus-small.txt in vigenereTestData folder for testing
	public void TestCaesarCipher() {
		int key = 9;
		FileResource fr = new FileResource();
		String input = fr.asString();
		
		CaesarCipher cc = new CaesarCipher(key);
		String encrypted = cc.encrypt(input);
		System.out.println("Encrypted message:\n" + encrypted);
		System.out.println("Decrypted message:\n" + cc.decrypt(encrypted));
		
		StringBuilder sb = new StringBuilder(input);
		
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			c = cc.encryptLetter(c);
			sb.setCharAt(i, c);
		}
		System.out.println("Encrypted message:\n" + sb.toString());
		
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			c = cc.decryptLetter(c);
			sb.setCharAt(i, c);
		}
		System.out.println("Decrypted message:\n" + sb.toString());
	}
	
	// Use small_key5.txt or oslusiadas_key17.txt in vigenereTestData folder for testing
	public void testCaesarCracker() {
		FileResource fr = new FileResource();
		String input = fr.asString();
		CaesarCracker cc = new CaesarCracker();
		System.out.println("Decrypted message:\n" + cc.decrypt(input));
	}
	
	// Use titus-small.txt in vigenereTestData folder for testing
	public void testVigenereCipher() {
		FileResource fr = new FileResource();
		String input = fr.asString();
		int[] key = {17, 14, 12, 4};
		
		VigenereCipher vc = new VigenereCipher(key);
		String encrypted = vc.encrypt(input);
		System.out.println("Encrypted message:\n" + encrypted);
		System.out.println("Decrypted message:\n" + vc.decrypt(encrypted));
		System.out.println(vc.toString());
	}
	
	// Use athens_keyflute.txt in vigenereTestData folder for testing
	// Select a dictionary/dictionaries from dictionaries folder
	public void testVigenereBreaker() {
		//FileResource fr = new FileResource();
		//String input = fr.asString();
		
		VigenereBreaker vb = new VigenereBreaker();
		//System.out.println(vb.sliceString("abcdefghijklm", 4, 5));
		//int[] key = vb.tryKeyLength(input, 4, 'e');
		//System.out.println(Arrays.toString(key));
		vb.breakVigenere();
		//HashSet<String> dict = vb.readDictionary(fr);
		//System.out.println("Most common char is " + vb.mostCommonCharIn(dict));
	}

	public static void main(String[] args) {
		Tester test = new Tester();
		//test.TestCaesarCipher();
		//test.testCaesarCracker();
		//test.testVigenereCipher();
		test.testVigenereBreaker();
	}

}
