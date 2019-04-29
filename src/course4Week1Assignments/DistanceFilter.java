package course4Week1Assignments;

/**
 * Find quakes with distance less than maximum distance from location.
 * 
 * @author Diane Wajslic 
 * @version 1.0, February 2019
 */

public class DistanceFilter implements Filter{
	private Location location;
	private double distMax;
	private String filterName;
	
	public DistanceFilter(Location loc, double dist) {
		location = loc;
		distMax = dist;
		filterName = "Distance";
	}
	
	public DistanceFilter(Location loc, double dist, String fName) {
		location = loc;
		distMax = dist;
		filterName = fName;
	}
	
	public boolean satisfies(QuakeEntry qe) {
		return location.distanceTo(qe.getLocation())/1000 < distMax;
	}
	
	public String getName() {
    	return filterName;
    }

}
