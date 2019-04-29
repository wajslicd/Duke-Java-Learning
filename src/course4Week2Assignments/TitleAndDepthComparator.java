package course4Week2Assignments;

/**
 * Implements Comparator to allow one to sort QuakeEntry’s by their title 
 * When the titles are the same, then additionally sorts by the depth from smallest to largest.
 * 
 * @author Diane Wajslic 
 * @version v1.0 14/03/2019
 */

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
	public int compare(QuakeEntry q1, QuakeEntry q2) {
		if (q1.getInfo().compareTo(q2.getInfo()) == 0) {
			if(q1.getDepth() < q2.getDepth()) return -1;
			if(q1.getDepth() > q2.getDepth()) return 1;
			return 0;
		}
		if (q1.getInfo().compareTo(q2.getInfo()) < 0) return -1;
		if (q1.getInfo().compareTo(q2.getInfo()) > 0) return 1;
		return 0;
	}
}
