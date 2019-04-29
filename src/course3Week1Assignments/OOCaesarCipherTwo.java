package course3Week1Assignments;

public class OOCaesarCipherTwo {
	private String alphabet;
	private String shiftedAlphabet1;
	private String shiftedAlphabet2;
	private int mainKey1;
	private int mainKey2;
	
	public OOCaesarCipherTwo(int key1, int key2) {
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
		shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
		mainKey1 = key1;
		mainKey2 = key2;
	}
	
	public String encrypt(String input) {
		StringBuilder encrypted = new StringBuilder(input);
		
		for(int i = 0; i < encrypted.length(); i++) {
			char currChar = encrypted.charAt(i);
			int idx = alphabet.indexOf(Character.toUpperCase(currChar));
			
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
	
	public String decrypt(String input) {
		OOCaesarCipherTwo cc2 = new OOCaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
		return cc2.encrypt(input);
	}
}
