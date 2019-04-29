package course4Week3Assignments;

import java.util.ArrayList;

import edu.duke.FileResource;

public class Tester {
	
	public void testGetFollows() {
		MarkovOne m1 = new MarkovOne();
		m1.setTraining("this is a test yes this is a test.");
		String key = "t";
		ArrayList<String> test = m1.getFollows(key);
		System.out.println(test);
		System.out.println(key + " appears " + test.size() + " times");
	}
	
	// Use confucius.txt in data folder for testing
	public void testGetFollowsWithFile() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne m1 = new MarkovOne();
		m1.setTraining(st);
		String key = "he";
		ArrayList<String> test = m1.getFollows(key);
		System.out.println(test);
		System.out.println(key + " appears " + test.size() + " times");
	}

	public static void main(String[] args) {
		Tester t = new Tester();
		//t.testGetFollows();
		t.testGetFollowsWithFile();
	}

}
