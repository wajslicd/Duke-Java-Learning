package course3Week4Assignments;

import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
	// This method returns a String consisting of every totalSlices-th character 
	// from message, starting at the whichSlice-th character.
    public String sliceString(String message, int whichSlice, int totalSlices) {
    	StringBuilder slice = new StringBuilder();
    	for (int i = whichSlice; i < message.length(); i += totalSlices) {
    		char c = message.charAt(i);
    		slice.append(c);
    	}
        return slice.toString();
    }
    
    // This method finds the Vigenere key for an encrypted message, assuming the key length is klength
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i = 0; i < klength; i++) {
        	String slice = sliceString(encrypted, i, klength);
        	CaesarCracker cc = new CaesarCracker();
        	key[i] = cc.getKey(slice);
        }
        return key;
    }
    
    // This method returns a HashSet representing the words in a dictionary.
    public HashSet<String> readDictionary(FileResource fr) {
    	HashSet<String> dict = new HashSet<String>();
    	for(String line : fr.lines()) {
    		dict.add(line.toLowerCase());
    	}
    	return dict;
    }
    
    // This method returns a count of how many valid words were found in a string
    public int countWords(String message, HashSet<String> dictionary) {
    	int count = 0;

    	for(String word: message.split("\\W+")) {
    		if(dictionary.contains(word.toLowerCase())) {
    			count++;
    		}
    	}
    	return count;
    }
    
    // This method finds out which character, of the letters in the English alphabet, appears most often
    // in the words in dictionary and returns this most commonly occurring character.
    public char mostCommonCharIn(HashSet<String> dictionary) {
    	String alph = "abcdefghijklmnopqrstuvwxyz";
    	int[] counts = new int[26];
    	int maxIndex = 0;
    	
    	for(String word : dictionary) {
     		for(int k=0; k < word.length(); k++) {
    			int index = alph.indexOf(Character.toLowerCase(word.charAt(k)));
    			if (index != -1){
                    counts[index] += 1;
                }
    		}    		
    	}
    	for(int i=0; i < counts.length; i++) {
    		if (counts[i] > counts[maxIndex]){
    			maxIndex = i;
            }
    	}
    	return alph.charAt(maxIndex);
    }
    
    // This method figures out which decryption key length gives the largest count of real words, 
    // and returns that String decryption.
    public HashMap<String, Object> breakForLanguage(String encrypted, HashSet<String> dictionary) {
    	int max = 0;
    	String decrypted = "";
    	int klength = 0;
    	int totalWords = 0;
    	char mostCommon = mostCommonCharIn(dictionary);
    	HashMap<String, Object> info = new HashMap<String, Object>();
    	
    	for(String word: encrypted.split("\\W+")) {
    		totalWords++;
    	}
    	
    	for(int i = 1; i <= 100; i++) {
    		int[] key = tryKeyLength(encrypted, i, mostCommon);
    		VigenereCipher vc = new VigenereCipher(key);
    		String tempDecrypt = vc.decrypt(encrypted);
    		int count = countWords(tempDecrypt, dictionary);
    		if(count > max) {
    			max = count;
    			klength = i;
    			decrypted = tempDecrypt;
    		}
    	}
    	int[] key = tryKeyLength(encrypted, klength, 'e');
    	info.put("decrypted", decrypted);
    	info.put("klength", klength);
    	info.put("key", key);
    	info.put("validWords", max);
    	info.put("totalWords", totalWords);

    	return info;
    }
    
    // This method tries each language and picks the best language, the one with the largest count of real words,
    // and returns that String decryption.
    public HashMap<String, Object> breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
    	int maxWords = 0;
    	String lang = null;
    	HashMap<String, Object> info = new HashMap<String, Object>();
    	
    	for(String language : languages.keySet()) {
    		HashMap<String, Object> langInfo = breakForLanguage(encrypted, languages.get(language));
    		if((int)langInfo.get("validWords") > maxWords) {
    			maxWords = (int)langInfo.get("validWords");
    			lang = language;
    			info = langInfo;
    		}
    	}
    	info.put("language", lang);
    	return info;
    }

    public void breakVigenere () {
    	HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
    	System.out.println("Select a file to decrypt\n");
    	FileResource fr = new FileResource();
		String encrypted = fr.asString();
		int klength = 0;
		System.out.println("Select the required dictionaries\n");
		DirectoryResource dr = new DirectoryResource();		
		for(File f: dr.selectedFiles()) {
			FileResource resource = new FileResource(f);
			System.out.println("Loading " + f.getName() + " dictionary");
			languages.put(f.getName(), readDictionary(resource));
		}
		
		if(klength != 0) {
			int[] key = tryKeyLength(encrypted, klength, 'e');
			VigenereCipher vc = new VigenereCipher(key);
			String decrypted = vc.decrypt(encrypted);
			int count = countWords(decrypted, languages.get("English"));
			System.out.println("\nDecrypted message:\n" + decrypted.substring(0, 100));
			System.out.println("\nkey length is " + klength);
			System.out.println("Key is " + Arrays.toString(key));
			System.out.println("This file contains " + count + " valid words");
		} else {
			HashMap<String, Object> data = breakForAllLangs(encrypted, languages);
			String decrypted = data.get("decrypted").toString();
			System.out.println("\nDecrypted message:\n" + decrypted.substring(0, 100));
			System.out.println("\nkey length is " + data.get("klength"));
	    	System.out.println("Key is " + Arrays.toString((int[]) data.get("key")));
			System.out.println("This file contains " + data.get("validWords") + " valid words");
			System.out.println("out of " + data.get("totalWords") + " (total word count ignores whitespace)");
			System.out.println("Language is " + data.get("language"));
		}					
    }
    
}
