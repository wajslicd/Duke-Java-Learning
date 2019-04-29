package course2week2Assignments;

import edu.duke.*;

public class StringsFirstAssPart4 {
	// reads the lines from a file at a URL location
	// and prints each URL on the page that is a link to the search string.
	public void printUrls(URLResource ur, String search) {
		
		for(String word : ur.words()) {
			int index = word.toLowerCase().indexOf(search);
			if(index != -1) {
				int start = word.lastIndexOf('\"', index);
				int end = word.indexOf('\"', index);
				System.out.println(word.substring(start + 1, end));				
			}
			
		}
	}
	
	// Tests printUrl
	public void testing() {
		String url = "http://www.dukelearntoprogram.com/course2/data/manylinks.html";
		String search = "youtube.com";
		URLResource ur = new URLResource(url);
		
		printUrls(ur, search.toLowerCase());
		
		//String urlString = url.asString();
		//System.out.println(urlString);
		//for(String line : url.lines()) {
		//	System.out.println(line);			
		//}
	}
	
	

	public static void main(String[] args) {
		StringsFirstAssPart4 p4 = new StringsFirstAssPart4();
		p4.testing();

	}

}
