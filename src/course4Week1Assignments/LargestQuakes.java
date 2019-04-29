package course4Week1Assignments;

/**
 * Find N-largest quakes
 * 
 * @author Diane Wajslic
 * @version 1.0, February 2019
 */

import java.util.*;

public class LargestQuakes {
	
	public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
		ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
		ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
		
		for(int i = 0; i < howMany; i++) {
			int idx = indexOfLargest(copy);
			ret.add(copy.get(idx));
			copy.remove(idx);
		}
		
		return ret;
	}
	
	int indexOfLargest(ArrayList<QuakeEntry> data) {
		int index = 0;
		
		for(int i = 0; i < data.size(); i++) {
			QuakeEntry quake = data.get(i);
			if(quake.getMagnitude() > data.get(index).getMagnitude()) {
				index = i;
			}
		}
		
		return index;
	}
	
	public void findLargestQuakes() {
		EarthQuakeParser parser = new EarthQuakeParser();
		//String source = "data/nov20quakedatasmall.atom";
		String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        /*
        for(QuakeEntry qe : list) {
        	System.out.println(qe);
        }
        int largest = indexOfLargest(list);
        System.out.printf("Largest earthquake is at location " + largest +
        		" and has magnitude %4.2f\n", list.get(largest).getMagnitude());
        */
        ArrayList<QuakeEntry> largestList = getLargest(list, 50);
        for(QuakeEntry qe : largestList) {
        	System.out.println(qe);
        }
	}

}
