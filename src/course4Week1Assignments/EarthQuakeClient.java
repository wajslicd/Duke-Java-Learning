package course4Week1Assignments;

import java.util.*;
import edu.duke.*;

// creates an EarthQuakeParser to read in an earthquake data file, creating an ArrayList of QuakeEntrys.
public class EarthQuakeClient {
	
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
        	if(qe.getMagnitude() > magMin) {
        		answer.add(qe);
        	}
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    		double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
        	if(from.distanceTo(qe.getLocation())/1000 < distMax) {
        		answer.add(qe);
        	}
        }

        return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth,
    		double maxDepth){
    	ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    	for(QuakeEntry qe : quakeData) {
    		double depth = qe.getDepth();
    		if(depth > minDepth && depth < maxDepth) {
    			answer.add(qe);
    		}
    	}
    	
    	return answer;
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where,
    		String phrase){
    	ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    	for(QuakeEntry qe : quakeData) {
    		if(where.equals("start")) {
        		if(qe.getInfo().startsWith(phrase)) {
        			answer.add(qe);
        		}
        	} 
    		if(where.equals("end")) {
        		if(qe.getInfo().endsWith(phrase)) {
        			answer.add(qe);
        		}
        	} 
        	if(qe.getInfo().indexOf(phrase) != -1) {
        		answer.add(qe);
        	}      	
    	}
    	   	
    	return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> listBig  = filterByMagnitude(list, 5.0);
        for(QuakeEntry qe : listBig) {
        	System.out.println(qe);
        }
        System.out.println("Found "+listBig.size()+" quakes that match that criteria\n");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> listCloseTo = filterByDistanceFrom(list, 1000.00, city);
        for(QuakeEntry qe : listCloseTo) {
        	System.out.println(city.distanceTo(qe.getLocation()) + " " + qe.getInfo() );
        }
        System.out.println("Found "+listCloseTo.size()+" quakes that match that criteria\n");
    }
    
    public void quakesOfDepth() {
    	EarthQuakeParser parser = new EarthQuakeParser();
    	//String source = "data/nov20quakedatasmall.atom";
    	String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        double min = -4000.0;
        double max = -2000.0;
        ArrayList<QuakeEntry> listDepthsBetween = filterByDepth(list, min, max);
        System.out.println("Find quakes with depth between " + min + " and " + max);
        for(QuakeEntry qe : listDepthsBetween) {
        	System.out.println(qe);
        }
        System.out.println("Found " + listDepthsBetween.size() +" quakes that match that criteria\n");
    }
    
    public void quakesByPhrase() {
    	EarthQuakeParser parser = new EarthQuakeParser();
    	//String source = "data/nov20quakedatasmall.atom";
    	String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        String where = "any";
        String phrase = "Can";
        ArrayList<QuakeEntry> listPhrases = filterByPhrase(list, where, phrase);
        for(QuakeEntry qe : listPhrases) {
        	System.out.println(qe);
        }
        System.out.println("Found " + listPhrases.size() + " quakes that match " + phrase + " at " + where + "\n");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
