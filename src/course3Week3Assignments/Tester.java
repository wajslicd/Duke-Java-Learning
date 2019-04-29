package course3Week3Assignments;

/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
    	LogAnalyzer log = new LogAnalyzer();
    	log.readFile("logsData\\weblog3-short_log");
    	log.printAll();
    }
    
    public void testCountUniqueIPs() {
    	LogAnalyzer log = new LogAnalyzer();
    	log.readFile("logsData\\weblog2_log");
    	System.out.println("The number of unique IP addresses is " + log.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanNum() {
    	LogAnalyzer log = new LogAnalyzer();
    	log.readFile("logsData\\weblog1_log");
    	log.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
    	LogAnalyzer log = new LogAnalyzer();
    	log.readFile("logsData\\weblog2_log");
    	ArrayList<String> IPVisits = log.uniqueIPVisitsOnDay("Sep 27");
    	System.out.println("Count of IP visits on requested date is " + IPVisits.size());
    	for(String s : IPVisits) {
    		System.out.println(s);
    	}
    }
    
    public void testCountUniqueIPsInRange(int low, int high) {
    	LogAnalyzer log = new LogAnalyzer();
    	log.readFile("logsData\\weblog2_log");
    	System.out.println("Count of IPs with status code in requested range is " + log.countUniqueIPsInRange(low, high));
    }
    
    public void testCountVisitsPerIP() {
    	LogAnalyzer log = new LogAnalyzer();
    	log.readFile("logsData\\short-test_log");
    	HashMap<String, Integer> counts = log.countVisitsPerIP();
    	System.out.println(counts);
    }
    
    public void testMostNumberVisitsByIP() {
    	LogAnalyzer log = new LogAnalyzer();
    	log.readFile("logsData\\weblog2_log");
    	HashMap<String, Integer> counts = log.countVisitsPerIP();
    	System.out.println("maximum number of visits by a single IP address is " + log.mostNumberVisitsByIP(counts));
    }
    
    public void testIPsMostVisits() {
    	LogAnalyzer log = new LogAnalyzer();
    	log.readFile("logsData\\weblog2_log");
    	HashMap<String, Integer> counts = log.countVisitsPerIP();
    	ArrayList<String> mostVisits = log.iPsMostVisits(counts);
    	System.out.println(mostVisits);
    }
    
    public void testIPsForDays() {
    	LogAnalyzer log = new LogAnalyzer();
    	log.readFile("logsData\\weblog3-short_log");
    	HashMap<String, ArrayList<String>> days = log.iPsForDays();
    	for(String s : days.keySet()) {
    		System.out.println(s + " =" + days.get(s));
    	}
    	
    }
    
    public void testDayWithMostIPVisits() {
    	LogAnalyzer log = new LogAnalyzer();
    	log.readFile("logsData\\weblog2_log");
    	String day = log.dayWithMostIPVisits(log.iPsForDays());
    	System.out.println("day most visited is " + day);
    }
    
    public void testIPsWithMostVisitsOnDay() {
    	LogAnalyzer log = new LogAnalyzer();
    	log.readFile("logsData\\weblog2_log");
    	ArrayList<String> ipsWithMostVisits = log.iPsWithMostVisitsOnDay(log.iPsForDays(), "Sep 29");
    	System.out.println(ipsWithMostVisits);
    }
    
    public static void main(String[] args) {
    	Tester test = new Tester();
    	//test.testLogAnalyzer();
    	//test.testCountUniqueIPs();
    	//test.testPrintAllHigherThanNum();
    	//test.testUniqueIPVisitsOnDay();
    	//test.testCountUniqueIPsInRange(400,499);
    	//test.testCountVisitsPerIP();
    	//test.testMostNumberVisitsByIP();
    	//test.testIPsMostVisits();
    	//test.testIPsForDays();
    	//test.testDayWithMostIPVisits();
    	test.testIPsWithMostVisitsOnDay();
    }
}
