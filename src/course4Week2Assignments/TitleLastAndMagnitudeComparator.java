package course4Week2Assignments;

/**
 * Implements Comparator to allow one to sort QuakeEntry’s by the last word of their title 
 * When the titles are the same, then additionally sorts by the magnitude from smallest to largest.
 * 
 * @author Diane Wajslic 
 * @version v1.0 14/03/2019
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
	public int compare(QuakeEntry q1, QuakeEntry q2) {
		String[] state1 = q1.getInfo().split("\\W");
		String[] state2 = q2.getInfo().split("\\W");
		if (state1[state1.length -1].compareTo(state2[state2.length -1]) == 0) {
			return Double.compare(q1.getMagnitude(), q2.getMagnitude());
		}
		if (state1[state1.length -1].compareTo(state2[state2.length -1]) < 0) return -1;
		if (state1[state1.length -1].compareTo(state2[state2.length -1]) > 0) return 1;
		return 0;
	}
}
