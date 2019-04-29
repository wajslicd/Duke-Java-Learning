package course4Week2Assignments;

/**
 * Implements Comparator to allow one to sort QuakeEntry’s by their distance to a specified location 
 * that is passed in as a parameter.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class DistanceComparator implements Comparator<QuakeEntry> {
    Location fromWhere;
    
    public DistanceComparator(Location where) {
        fromWhere = where;
    }
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        double dist1 = q1.getLocation().distanceTo(fromWhere);
        double dist2 = q2.getLocation().distanceTo(fromWhere);
        return Double.compare(dist1, dist2);
    }
    
}
 