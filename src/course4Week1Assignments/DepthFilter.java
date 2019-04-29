package course4Week1Assignments;

/**
 * Find quakes between the minimum and maximum depths.
 * 
 * @author Diane Wajslic 
 * @version 1.0, February 2019
 */

public class DepthFilter implements Filter{
	private double depthMin;
	private double depthMax;
	private String filterName;
	
	public DepthFilter(double min, double max) {
		depthMin = min;
		depthMax = max;
		filterName = "Depth";
	}
	
	public DepthFilter(double min, double max, String fName) {
		depthMin = min;
		depthMax = max;
		filterName = fName;
	}
	
	public boolean satisfies(QuakeEntry qe) {
		
		return qe.getDepth() >= depthMin && qe.getDepth() <= depthMax;
	}
	
	public String getName() {
    	return filterName;
    }
}
