package course4Week1Assignments;

/**
 * Find quakes with requested phrase in title at requested search location in the string.
 * 
 * @author Diane Wajslic 
 * @version 1.0, February 2019
 */

public class PhraseFilter implements Filter {
	private String requestType;
	private String searchPhrase;
	private String filterName;
	
	public PhraseFilter(String where, String phrase) {
		requestType = where;
		searchPhrase = phrase;
		filterName = "Phrase";
	}
	
	public PhraseFilter(String where, String phrase, String fName) {
		requestType = where;
		searchPhrase = phrase;
		filterName = fName;
	}
	
	public boolean satisfies(QuakeEntry qe) {		
		if(requestType.equals("start")) {
			return qe.getInfo().startsWith(searchPhrase);
		}	
		if(requestType.equals("end")) {
			return qe.getInfo().endsWith(searchPhrase);
		}
		if(requestType.equals("any")) {
			return qe.getInfo().indexOf(searchPhrase) != -1;
		}
		
		return false;
	}
	
	public String getName() {
    	return filterName;
    }
}
