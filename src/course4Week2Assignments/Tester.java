package course4Week2Assignments;

public class Tester {

	public static void main(String[] args) {
		QuakeSortWithTwoArrayLists qs = new QuakeSortWithTwoArrayLists();
		//qs.testSort();
		QuakeSortInPlace qsip= new QuakeSortInPlace();
		//qsip.testSort();
		DifferentSorters ds = new DifferentSorters();
		//ds.sortWithCompareTo();
		//ds.sortByTitleAndDepth();
		ds.sortByLastWordInTitleThenByMagnitude();
	}

}
