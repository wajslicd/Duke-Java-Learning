package course4Week3Assignments;

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }
    
    @Override
    public int hashCode() {
    	myHash = this.toString().hashCode();
    	return myHash;
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        
        return myWords.length;
    }
    
    @Override
    public String toString(){
        String ret = "";
        for(int i=0; i < myWords.length; i++) {
        	ret += myWords[i] + " ";
        }

        return ret.trim();
    }
    
    @Override
    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        
        if(this.length() != other.length()) {
        	return false;
        }
        for(int i=0; i < myWords.length; i++) {
        	if(!myWords[i].equals(other.wordAt(i))) {
        		return false;
        	}
        }
        return true;

    }

    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        for(int i=0; i< out.myWords.length-1; i++) {
        	out.myWords[i] = out.wordAt(i+1);
        }
        out.myWords[out.myWords.length-1] = word;
        return out;
    }

}