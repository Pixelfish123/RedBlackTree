import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Placeholder class so CollegeSearchFrontendFD will function
 */
public class CollegeSearchBackendFD implements CollegeSearchBackendInterface {

	private RedBlackTreeFD<String> rbt;
	private CollegeInterface collegeList;

	public CollegeSearchBackendFD(RedBlackTreeFD<String> enhancedRBT, CollegeInterface collegeList) {
		this.rbt = enhancedRBT;
		this.collegeList = collegeList;
	}

	public void loadData(String filename) throws FileNotFoundException {

	}

	public List<String> findCollegesByMostExpensive(int numColleges, List<String> states) {
		List<String> colleges = new ArrayList<>();
		colleges.add("University of Wisconsin-Madison");
		return colleges;
	}

	public List<String> findCollegesByLeastExpensive(int numColleges, List<String> states) {
		List<String> colleges = new ArrayList<>();
		colleges.add("University of Wisconsin-Madison");
		return colleges;
	}

	public List<String> findCollegesByPriceRange(int min, int max, List<String> states) {
		List<String> colleges = new ArrayList<>();
		colleges.add("University of Wisconsin-Madison");
		return colleges;
	}

	public void addCollegeToTree(CollegeInterface college) {

	}

	@Override
	public String getStatisticsString() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getStatisticsString'");
	}
}
