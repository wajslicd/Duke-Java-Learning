package course3Week1Assignments;

public class OOCaesarCipher {
	private String alphabet;
	private String shiftedAlphabet;
	private int mainKey;
	
	public OOCaesarCipher(int key) {
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
		mainKey = key;
	}
	
	public String encrypt(String input) {
		StringBuilder encrypted = new StringBuilder(input);	
		
		for(int i = 0; i < encrypted.length(); i++) {
			char currChar = encrypted.charAt(i);
			int idx = alphabet.indexOf(Character.toUpperCase(currChar));
			if(idx != -1) {
				char ch;
				
				if(Character.isLowerCase(currChar)) {
					ch = Character.toLowerCase(shiftedAlphabet.charAt(idx));
				} else {
					ch = shiftedAlphabet.charAt(idx);
				}
				encrypted.setCharAt(i, ch);
			}
		}
		return encrypted.toString();
	}
	
	public String decrypt(String input) {
		OOCaesarCipher cc = new OOCaesarCipher(26 - mainKey); 
		return cc.encrypt(input);
	}

}
