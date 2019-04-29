package course2Week1Assignments;
import edu.duke.*;

public class HelloWorld {	
	public void runHello () {
		FileResource res = new FileResource("data\\hello_unicode.txt");
		for (String line : res.lines()) {
			System.out.println(line);
		}
	}
	
	public static void main(String[] args) {
		HelloWorld h = new HelloWorld();
		h.runHello();
	}
}