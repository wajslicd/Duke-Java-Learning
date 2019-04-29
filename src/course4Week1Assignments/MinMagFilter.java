package course4Week1Assignments;

/**
 * Find quakes with magnitude greater than or equal to magMin.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinMagFilter implements Filter
{
    private double magMin;
    private String filterName;
    
    public MinMagFilter(double min) { 
        magMin = min;
        filterName = "MinMag";
    }
    
    public MinMagFilter(double min, String fName) { 
        magMin = min;
        filterName = fName;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    }
    
    public String getName() {
    	return filterName;
    }

}
