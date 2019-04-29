package course4Week1Assignments;

import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        //Filter f = new MinMagFilter(4.0);
        //ArrayList<QuakeEntry> m7  = filter(list, f);
        
        Filter f = new MagnitudeFilter(3.5, 4.5); 
        ArrayList<QuakeEntry> mag  = filter(list, f); 
        f = new DepthFilter(-55000.0, -20000.0);
        ArrayList<QuakeEntry> ans  = filter(mag, f);
        
        /*
        // This location is Tokyo, Japan        
        //Location city = new Location(35.42, 139.43);
        // This location is Denver, Colorado
        Location city = new Location(39.7392, -104.9903);
        Filter f = new DistanceFilter(city, 1000.0);
        ArrayList<QuakeEntry> dist  = filter(list, f);
        f = new PhraseFilter("end", "a");
        ArrayList<QuakeEntry> ans  = filter(dist, f);*/
        for (QuakeEntry qe: ans) { 
            System.out.println(qe);
        } 
        System.out.println("\nFound "+ ans.size()+" quakes that match that criteria\n");
    }
    
    public void testMatchAllFilter() {
    	EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(1.0, 4.0));
        maf.addFilter(new DepthFilter(-180000.0, -30000.0));
        maf.addFilter(new PhraseFilter("any", "o"));
        
        ArrayList<QuakeEntry> quakes = filter(list, maf);
        for (QuakeEntry qe: quakes) { 
            System.out.println(qe);
        } 
        
        System.out.println("\nFound "+ quakes.size()+" quakes that match that criteria\n");
        System.out.println("\nFilters used are: " + maf.getName());
    }
    
    public void testMatchAllFilter2() {
    	EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 5.0));
        // This location is Tulsa, Oklahoma
        //Location city = new Location(36.1314, -95.9372);
        // This location is Billund, Denmark
        Location city = new Location(55.7308, 9.1153);
        maf.addFilter(new DistanceFilter(city, 3000.0));
        maf.addFilter(new PhraseFilter("any", "e"));
        
        ArrayList<QuakeEntry> quakes = filter(list, maf);
        for (QuakeEntry qe: quakes) { 
            System.out.println(qe);
        }
        System.out.println("\nFound "+ quakes.size()+" quakes that match that criteria\n");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
