
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public interface CollegeSearchBackendInterface {

	// public CollegeSearchBackendInterface(EnhancedRedBlackTree enhancedRBT,
	// CollegeInterface collegeList);

	public void loadData(String filename) throws FileNotFoundException;

	public List<String> findCollegesByMostExpensive(int numColleges, List<String> states);

	public List<String> findCollegesByLeastExpensive(int numColleges, List<String> states);

	public List<String> findCollegesByPriceRange(int min, int max, List<String> states);

	public void addCollegeToTree(CollegeInterface college);

	public String getStatisticsString();

}
