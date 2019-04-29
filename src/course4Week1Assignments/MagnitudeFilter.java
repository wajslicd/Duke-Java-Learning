package course4Week1Assignments;

/**
 * Find quakes between the minimum and maximum magnitudes.
 * 
 * @author Diane Wajslic 
 * @version 1.0, February 2019
 */

public class MagnitudeFilter implements Filter{
	private double magMin;
	private double magMax;
	private String filterName;
	
	public MagnitudeFilter(double min, double max) {
		magMin = min;
		magMax = max;
		filterName = "Magnitude";
	}
	
	public MagnitudeFilter(double min, double max, String fName) {
		magMin = min;
		magMax = max;
		filterName = fName;
	}
	
	public boolean satisfies(QuakeEntry qe) {
		
		return qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax;
	}
	
	public String getName() {
    	return filterName;
    }
}
