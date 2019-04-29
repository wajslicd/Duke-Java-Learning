package course4Week2Assignments;

/**
 * implements Comparator to allow one to sort QuakeEntry’s by magnitude from smallest to largest magnitude.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
    }
    
}
