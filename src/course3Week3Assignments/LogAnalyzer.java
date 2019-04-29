package course3Week3Assignments;

/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String line : fr.lines()) {
        	 LogEntry le = WebLogParser.parseEntry(line);
        	 records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
    	 ArrayList<String> uniqueIps = new ArrayList<String>();
    	 for(LogEntry le : records) {
    		 String ipAddr = le.getIpAddress();
    		 if(!uniqueIps.contains(ipAddr)) {
    			 uniqueIps.add(ipAddr);
    		 }
    	 }
    	 return uniqueIps.size();
     }
     
     public void printAllHigherThanNum(int num) {
    	 for(LogEntry le : records) {    		 
    		 if(le.getStatusCode() > num) {
    			System.out.println(le); 
    		 }
    	 }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
    	 ArrayList<String> uniqueIps = new ArrayList<String>();
    	 for(LogEntry le : records) {
    		 String date = le.getAccessTime().toString();
    		 if(date.indexOf(someday) != -1) {
    			 String ipAddr = le.getIpAddress();
    			 if(!uniqueIps.contains(ipAddr)) {
        			 uniqueIps.add(ipAddr);
    			 }    		 
    		 }
    	 }
    	 return uniqueIps;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
    	 ArrayList<String> uniqueIps = new ArrayList<String>();
    	 for(LogEntry le : records) {
    		 int status = le.getStatusCode();
    		 if(status >= low && status <= high) {
    			 String ipAddr = le.getIpAddress();
        		 if(!uniqueIps.contains(ipAddr)) {
        			 uniqueIps.add(ipAddr);
        		 } 
    		 }
    	 }
    	 return uniqueIps.size();
     }
     
     public HashMap<String, Integer>countVisitsPerIP(){
    	 HashMap<String, Integer> counts = new HashMap<String, Integer>();
    	 for(LogEntry le : records) {
    		 String ipAddr = le.getIpAddress();
    		 if(!counts.containsKey(ipAddr)) {
    			 counts.put(ipAddr, 1);
    		 } else {
    			 counts.put(ipAddr, counts.get(ipAddr) + 1);
    		 }
    	 }
    	 return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> visits) {
    	 int max = 0;
    	 for(int i : visits.values()) {
    		 if(i > max) {
    			 max = i;
    		 }
    	 }
    	 return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> visits){
    	 ArrayList<String> mostVisits = new ArrayList<String>();
    	 int maxVisits = mostNumberVisitsByIP(visits);
    	 for(String s : visits.keySet()) {
    		 if(visits.get(s) == maxVisits) {
    			 mostVisits.add(s);
    		 }
    	 }
    	 return mostVisits;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
    	 HashMap<String, ArrayList<String>> days = new HashMap<String, ArrayList<String>>();
    	 for(LogEntry le : records) {
    		 String date = le.getAccessTime().toString();
    		 String day = date.substring(4, 10);
    		 String ipAddr = le.getIpAddress();
    		 if(!days.containsKey(day)){
    			 ArrayList<String> ips = new ArrayList<String>();   			 
    			 ips.add(ipAddr);
    			 days.put(day, ips);
    		 } else {
    			 ArrayList<String> aDay = days.get(day);
    			 aDay.add(ipAddr);
    			 days.put(day, aDay);
    		 }	 
    	 }
    	 return days;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> days) {
    	 int max = 0;
    	 String date = "";
    	 for(String s : days.keySet()) {
    		 ArrayList<String> day = days.get(s);
    		 if(day.size() > max) {
    			 max = day.size();
    			 date = s;
    		 }
    	 }
    	 return date;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> visitsPerIp, String day){
    	 HashMap<String, Integer> ipCount = new HashMap<String, Integer>();
    	 ArrayList<String> ipsOnDay = visitsPerIp.get(day);
    	 for(String s : ipsOnDay) {
    		 if(!ipCount.containsKey(s)) {
    			 ipCount.put(s, 1);
    		 } else {
    			 ipCount.put(s, ipCount.get(s) + 1);
    		 }
    	 }
    	 //ArrayList<String> ipsWithMostVisits =  iPsMostVisits(ipCount); 
    	 //return ipsWithMostVisits;
    	 return iPsMostVisits(ipCount);
     }
     
}
