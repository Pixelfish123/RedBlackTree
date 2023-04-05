import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CollegeSearchBackendBD implements CollegeSearchBackendInterface {

	private EnhancedRedBlackTreeAE<CollegeInterface> rbt;
	private CollegeReaderInterface collegeList;
	private int collegeCount;
	private int inState;

	public CollegeSearchBackendBD(EnhancedRedBlackTreeAE<CollegeInterface> enhancedRBT,
			CollegeReaderInterface collegeList) {
		rbt = enhancedRBT;
		this.collegeList = collegeList;
		collegeCount = 0;
	}

	@Override
	public void loadData(String filename) throws FileNotFoundException {
		// readColleges from file with the list of them
		List<CollegeInterface> colleges = collegeList.readCollegesFromFile(filename);
		for (CollegeInterface college : colleges) {
			addCollegeToTree(college);
		}

	}

	public List<String> findCollegesByMostExpensive(int numColleges, List<String> states) {
		// make the returnList
		List<String> returnList = new ArrayList<>();
		// make a list of all the colleges you need
		CollegeInterface[] allColleges = rbt.getLast(rbt.size());
		// make a placeholder rbt of the wanted colleges to add to if it is one of the
		// states
		EnhancedRedBlackTreeAE<CollegeInterface> wanted = new EnhancedRedBlackTreeAE<CollegeInterface>();
		for (int i = 0; i < allColleges.length; i++) {
			// if the states match up, add it to the wanted list
			if (states.contains(allColleges[i].getState())) {
				wanted.insert((College) allColleges[i]);
			}
		}
		// another loop to get the name of all the colleges from the wanted rbt
		for (int i = 0; i < wanted.size(); i++) {
			returnList.add(wanted.getLast(numColleges)[i].getName());
		}

		return returnList;
	}

	@Override
	public List<String> findCollegesByLeastExpensive(int numColleges, List<String> states) {
		// make the returnList
		List<String> returnList = new ArrayList<>();
		// make a list of all the colleges you need
		CollegeInterface[] allColleges = rbt.getFirst(rbt.size());
		// make a placeholder rbt of the wanted colleges to add to if it is one of the
		// states
		EnhancedRedBlackTreeAE<CollegeInterface> wanted = new EnhancedRedBlackTreeAE<CollegeInterface>();
		for (int i = 0; i < allColleges.length; i++) {
			// if the states match up, add it to the wanted list
			if (states.contains(allColleges[i].getState())) {
				wanted.insert((College) allColleges[i]);
			}
		}
		// another loop to get the name of all the colleges from the wanted rbt
		for (int i = 0; i < wanted.size(); i++) {
			returnList.add(wanted.getFirst(numColleges)[i].getName());
		}

		return returnList;
	}

	@Override
	public void addCollegeToTree(CollegeInterface college) {
		rbt.insert((College) college);
		collegeCount++;
	}

	@Override
	public List<String> findCollegesByPriceRange(int min, int max, List<String> states) {
		// make 2 dummy colleges to compare to when adding the wanted colleges
		College minCollege = new College();
		College maxCollege = new College();

		// make a returnlist
		List<String> returnList = new ArrayList<>();

		// make a list of all the colleges
		CollegeInterface[] allColleges = rbt.getFirst(rbt.size());

		// make a list of the colleges you want so that you can go though them
		EnhancedRedBlackTreeAE<CollegeInterface> wanted = new EnhancedRedBlackTreeAE<CollegeInterface>();
		for (int i = 0; i < allColleges.length; i++) {
			// if the college is in the list of what is wanted, add it
			if (states.contains(allColleges[i].getState())) {
				wanted.insert((College) allColleges[i]);
			}
		}
		// another loop to get the name of all the colleges from the wanted rbt
		for (int i = 0; i < wanted.size(); i++) {
			returnList.add(wanted.getRange(minCollege, maxCollege)[i].getName());
		}

		return returnList;

	}

	@Override
	public String getStatisticsString() {
		try {
			return "Dataset contains:\n" +
					collegeCount + " colleges \n"
					+ rbt.getFirst(1)[0].getOutState() + "minimum tuition \n"
					+ rbt.getLast(1)[0].getOutState() + "maximum tuition";
		} catch (NullPointerException npe) {
			return "Dataset contains:\n" +
					collegeCount + " colleges \n"
					+ "0 minimum tuition \n"
					+ "0 maximum tuition";
		}
	}

}
