package course3Week2Assignments;

import java.util.ArrayList;

import edu.duke.FileResource;

public class CharactersInPlay {
	
	private ArrayList<String> characters;
	private ArrayList<Integer> countOfParts;
	
	public CharactersInPlay() {
		characters = new ArrayList<String>();
		countOfParts = new ArrayList<Integer>();
	}
	
	public void update(String person) {
		int index = characters.indexOf(person);
		if(index == -1) {
			characters.add(person);
			countOfParts.add(1);
		} else {
			int val = countOfParts.get(index);
			countOfParts.set(index, val + 1);
		}	
	}
	
	public void findAllCharacters() {
		characters.clear();
		countOfParts.clear();
		FileResource resource = new FileResource();
		for(String s : resource.lines()) {
			int period = s.indexOf('.');
			if(period != -1) {
				String character = s.substring(0, period);
				update(character);
			}
		}
	}
	
	public void charactersWithNumParts(int num1, int num2) {
		//findAllCharacters();
		System.out.println("\nCharacters with number of parts between " + num1 + " and " + num2);
		for(int i = 0; i < characters.size(); i++) {
			if(countOfParts.get(i) >= num1 && countOfParts.get(i) <= num2) {
				System.out.println(characters.get(i) + "\t" + countOfParts.get(i));
			}
		}
	}
	
	public void tester() {
		findAllCharacters();
		//System.out.println("# characters: " + characters.size());
		for(int i = 0; i < characters.size(); i++) {
			if(countOfParts.get(i) > 50) {
				System.out.println(characters.get(i) + "\t" + countOfParts.get(i));
			}
		}
		charactersWithNumParts(10, 15);
	}

	public static void main(String[] args) {
		CharactersInPlay cip = new CharactersInPlay();
		cip.tester();

	}

}
