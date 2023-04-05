import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CollegeReader implements CollegeReaderInterface {

	@Override
	public List<CollegeInterface> readCollegesFromFile(String filename) throws FileNotFoundException {
		// TODO Auto-generated method stub
		List<CollegeInterface> returnList = new ArrayList<>();
		//add a bunch of colleges to the list
		returnList.add(null);
		returnList.add(null);
		returnList.add(null);
		return returnList;
	}

}
