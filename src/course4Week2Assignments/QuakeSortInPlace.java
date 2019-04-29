package course4Week2Assignments;

/**
 * Implements the selection sort algorithm that sorts earthquakes by magnitude in place, in one ArrayList of QuakeEntry’s.
 * Implements the selection sort algorithm that sorts earthquakes by depth in place, in one ArrayList of QuakeEntry’s.
 * Implements the bubble sort algorithm that sorts earthquakes by magnitude in place, in one ArrayList of QuakeEntry’s.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
    
    // This method returns an integer representing the index position of the QuakeEntry with the smallest magnitude 
    // considering only those QuakeEntry’s from position 'from' to the end of the ArrayList.
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    // This method sorts the QuakeEntry’s in the ArrayList by magnitude using the selection sort algorithm, 
    // from smallest magnitude to largest magnitude.
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    // This method returns an integer representing the index position of the QuakeEntry with the largest depth 
    // considering only those QuakeEntry’s from position 'from' to the end of the ArrayList.
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
    	int maxIdx = from;
    	for(int i=from+1; i < quakeData.size(); i++) {
    		if(quakeData.get(i).getDepth() > quakeData.get(maxIdx).getDepth()) {
    			maxIdx = i;
    		}
    	}
    	return maxIdx;
    }
    
    // This method sorts the QuakeEntry’s in the ArrayList by depth using the selection sort algorithm, 
    // but in reverse order from largest depth to smallest depth.
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
    	for(int i=0; i< in.size(); i++) {
    		int maxIdx = getLargestDepth(in, i);
    		QuakeEntry qi = in.get(i);
    		QuakeEntry qmax = in.get(maxIdx);
    		in.set(i, qmax);
    		in.set(maxIdx, qi);
    	}
    }
    
    // This method makes one pass of bubble sort on the ArrayList.
    // numSorted represents the number of times this method has already been called on this ArrayList and thus
    // also represents the number of the elements that are guaranteed to already be where they belong when the
    // ArrayList is sorted by magnitude.
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
    	for(int i=0; i< quakeData.size()-numSorted; i++) {
    		QuakeEntry el1 = quakeData.get(i);
    		QuakeEntry el2 = quakeData.get(i+1);
    		if(el2.getMagnitude() < el1.getMagnitude()) {
    			quakeData.set(i, el2);
    			quakeData.set(i+1, el1);
    		}
    	}
    }
    
    // This method sorts earthquakes by their magnitude from smallest to largest using the bubble sort algorithm.
    // If the ArrayList in has N elements in it, this method should call onePassBubbleSort N – 1 times
    // to sort the elements in 'in'. 
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
    	/*System.out.println("read data for "+in.size()+" quakes");
    	for (QuakeEntry qe: in) { 
            System.out.println(qe);
        }*/
    	for(int i=0; i < in.size()-1; i++) {
    		onePassBubbleSort(in, i+1);
        	/*System.out.println("Printing Quakes after pass " + (i+1));
        	for (QuakeEntry qe: in) { 
                 System.out.println(qe);
            }*/  		
    	}
    	/*System.out.println("EarthQuakes in sorted order: ");
    	for (QuakeEntry qe: in) { 
            System.out.println(qe);
        }*/
    }
    
    // This method returns true if the earthquakes are in sorted order by magnitude from smallest to largest.
    // Otherwise this methods returns false. 
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
    	for(int i=0; i < quakes.size()-1; i++) {
    		if(quakes.get(i+1).getMagnitude() < quakes.get(i).getMagnitude()) {
    			return false;
    		}
    	}
    	return true;
    }
    
    // This method sorts earthquakes by their magnitude from smallest to largest using bubble sort.
    // If the ArrayList 'in' has N elements in it, this method should call onePassBubbleSort at most N – 1 times
    // This method will stop early if the ArrayList is already sorted and prints how many passes were needed to sort the elements.
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
    	for(int i=0; i < in.size()-1; i++) {
    		if(checkInSortedOrder(in)) {
    			System.out.println(i + " passes were required to sort the elements");
    			break;
    		} else {
    			onePassBubbleSort(in, i+1);
    		}   		
    	}
    }
    
    // This method sorts earthquakes by their magnitude from smallest to largest using selection sort.
    // This method will stop early if the ArrayList is already sorted and prints how many passes were needed to sort the elements.
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
    	for (int i=0; i< in.size(); i++) {
    		if(checkInSortedOrder(in)) {
    			System.out.println(i + " passes were required to sort the elements");
    			break;
    		} else {
    			int minIdx = getSmallestMagnitude(in,i);
                QuakeEntry qi = in.get(i);
                QuakeEntry qmin = in.get(minIdx);
                in.set(i,qmin);
                in.set(minIdx,qi);
    		}           
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
}
