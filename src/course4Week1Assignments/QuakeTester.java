package course4Week1Assignments;

public class QuakeTester {

	// Uses nov20quakedatasmall.atom in data folder for testing
	public static void main(String[] args) {
		EarthQuakeClient eqc = new EarthQuakeClient();
		ClosestQuakes cq = new ClosestQuakes();
		LargestQuakes lq = new LargestQuakes();
		EarthQuakeClient2 eqc2 = new EarthQuakeClient2();
		//eqc.bigQuakes();
		eqc.closeToMe();
		//eqc.quakesOfDepth();
		//eqc.quakesByPhrase();
		//cq.findClosestQuakes();
		//lq.findLargestQuakes();
		//eqc2.quakesWithFilter();
		//eqc2.testMatchAllFilter();
		//eqc2.testMatchAllFilter2();
	}

}
