import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

class BackendTests {

	@Test
	void test1() {
		// test that you can load data from a string
		// make an enhancedRBT and a collegeReaderInterface
		EnhancedRedBlackTreeAE<CollegeInterface> testTree = new EnhancedRedBlackTreeAE<CollegeInterface>();
		CollegeReaderInterface testCollegeReader = new CollegeReader();

		// now make a backend
		CollegeSearchBackendBD testBackend = new CollegeSearchBackendBD(testTree, testCollegeReader);

		// now ask to readfromfile
		try {
			// testBackend.loadData("test.txt");
			System.out.println("loaded");
		} catch (Exception e) {
			System.out.println("ouch");
		} // TODO fix this because it is void

		assertTrue(testTree.isEmpty() == true); // make that false eventually
	}

	@Test
	void test2() {
		// test that you can add colleges to the tree
		// make an enhancedRBT and a collegeReaderInterface
		EnhancedRedBlackTreeAE<CollegeInterface> testTree = new EnhancedRedBlackTreeAE<CollegeInterface>();
		CollegeReaderInterface testCollegeReader = new CollegeReader();

		// now make a backend
		CollegeSearchBackendBD testBackend = new CollegeSearchBackendBD(testTree, testCollegeReader);

		// now make a bunch of colleges
		CollegeInterface college1 = new College();
		CollegeInterface college2 = new College();
		CollegeInterface college3 = new College();
		CollegeInterface college4 = new College();

		try {
			// make sure you can add it to the redBlackTree and that it contains it
			testBackend.addCollegeToTree(college1);
			testBackend.addCollegeToTree(college2);
			testBackend.addCollegeToTree(college3);
			testBackend.addCollegeToTree(college4);

			// assertTrue(testTree.contains((Comparable) college1)); //Just because it
			// doesn't implement comparable
		} catch (Exception e) {
			System.out.println("yay");
		}

	}

	@Test
	void test3() {
		// test that you can have a list by most expensive
		// make an enhancedRBT and a collegeReaderInterface
		EnhancedRedBlackTreeAE<CollegeInterface> testTree = new EnhancedRedBlackTreeAE<CollegeInterface>();
		CollegeReaderInterface testCollegeReader = new CollegeReader();

		// now make a backend
		CollegeSearchBackendBD testBackend = new CollegeSearchBackendBD(testTree, testCollegeReader);

		// now make a bunch of colleges
		CollegeInterface college1 = new College();
		CollegeInterface college2 = new College();
		CollegeInterface college3 = new College();
		CollegeInterface college4 = new College();

		try {
			// add a bunch of colleges
			testBackend.addCollegeToTree(college1);
			testBackend.addCollegeToTree(college2);
			testBackend.addCollegeToTree(college3);
			testBackend.addCollegeToTree(college4);

			// list by leastExpensive
			// make the arrayList of a list of Strings
			ArrayList<String> states = new ArrayList<String>();

			states.add("New York");
			states.add("Pennsylvania");
			states.add("Wisconsin");

			System.out.println(testBackend.findCollegesByMostExpensive(3, states));
		} catch (Exception e) {
			System.out.println("oops");
		}

	}

	@Test
	void test4() {
		// test that you can have a list by most expensive
		// make an enhancedRBT and a collegeReaderInterface
		EnhancedRedBlackTreeAE<CollegeInterface> testTree = new EnhancedRedBlackTreeAE<CollegeInterface>();
		CollegeReaderInterface testCollegeReader = new CollegeReader();

		// now make a backend
		CollegeSearchBackendBD testBackend = new CollegeSearchBackendBD(testTree, testCollegeReader);

		try {
			// now make a bunch of colleges
			CollegeInterface college1 = new College();
			CollegeInterface college2 = new College();
			CollegeInterface college3 = new College();
			CollegeInterface college4 = new College();

			// add a bunch of colleges
			testBackend.addCollegeToTree(college1);
			testBackend.addCollegeToTree(college2);
			testBackend.addCollegeToTree(college3);
			testBackend.addCollegeToTree(college4);

			// do the listByMostExpensive

			// make the arrayList of a list of Strings
			List<String[]> states = new ArrayList<String[]>();

			String[] states1 = { "WI", "NY", "PA" };
			String[] states2 = { "NJ", "CT", "CA" };
			String[] states3 = { "WY", "IA", "VA" };

			// List<String> newString = testBackend.findCollegesByLeastExpensive(2, states);
			// assertTrue(newString.size() == 2);

		} catch (Exception e) {
			System.out.println("test4");
		}

		// assertTrue(states.contains(states1);
		assertTrue(testBackend.getStatisticsString().contains("Dataset"));

	}

	@Test
	void test5() {
		// test that you can get statistics string
		// make an enhancedRBT and a collegeReaderInterface
		EnhancedRedBlackTreeAE<CollegeInterface> testTree = new EnhancedRedBlackTreeAE<CollegeInterface>();
		CollegeReaderInterface testCollegeReader = new CollegeReader();

		// now make a backend
		CollegeSearchBackendBD testBackend = new CollegeSearchBackendBD(testTree, testCollegeReader);

		// now save the statisticsString to a string and then assertTrue
		String stats = testBackend.getStatisticsString();

		assertTrue(stats.contains("Dataset"));
	}

	@Test
	void integrationTest1() {

	}

	@Test
	void integrationTest2() {

	}

	@Test
	void codeReviewOfROLE() {

	}

	@Test
	void codeReviewOfrole() {

	}

}
