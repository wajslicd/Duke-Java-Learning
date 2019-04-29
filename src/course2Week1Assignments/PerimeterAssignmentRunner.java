package course2Week1Assignments;
import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start with prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int count = 0;
        
        for(Point currPt : s.getPoints()) {
        	count = count + 1;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        double perimeter = getPerimeter(s);
        int numPts = getNumPoints(s);
        
        if(numPts > 0) {return perimeter/ numPts;}         	
        else {return 0.0;}        	            
    }

    public double getLargestSide(Shape s) {
        double largest = 0.0;
        Point prevPt = s.getLastPoint();
        
        for(Point currPt : s.getPoints()) {
        	double currDist = prevPt.distance(currPt);
        	if(currDist > largest) {
        		largest = currDist;
        	}
        	prevPt = currPt;
        }
        return largest;
    }

    public double getLargestX(Shape s) {
        double largest = 0.0;
        
        for (Point currPt : s.getPoints()) {
        	int x = currPt.getX();
        	if(x > largest) {
        		largest = x;
        	}
        }
        return largest;
    }

    public double getLargestPerimeterMultipleFiles() {
    	DirectoryResource dr = new DirectoryResource();
    	double largestPerim = 0.0;
    	
    	for(File f : dr.selectedFiles()) {
    		FileResource fr = new FileResource(f);
    		Shape s = new Shape(fr);
    		double currentPerim = getPerimeter(s);
    		if(currentPerim > largestPerim) {
    			largestPerim = currentPerim;
    		}
    	}
    	
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
    	DirectoryResource dr = new DirectoryResource();
        File temp = null;    
        double largestPerim = 0.0;
        
        for(File f : dr.selectedFiles()) {
    		FileResource fr = new FileResource(f);
    		Shape s = new Shape(fr);
    		double currentPerim = getPerimeter(s);
    		if(currentPerim > largestPerim) {
    			largestPerim = currentPerim;
    			temp = f;
    		}
    	}
        
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int countPts = getNumPoints(s);
        System.out.println("count of Points = " + countPts);
        double ave = getAverageLength(s);
        System.out.println("average = " + ave);
        double largest = getLargestSide(s);
        System.out.println("largest side = " + largest);
        double largestX = getLargestX(s);
        System.out.println("largest x coordinate = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double perim = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter = " + perim);
    }

    public void testFileWithLargestPerimeter() {
        String file = getFileWithLargestPerimeter();
        System.out.println("file with largest perimeter = " + file);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
        //pr.printFileNames();
        //pr.triangle();
    }
}
