import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CollegeReaderDW implements CollegeReaderInterface {
    private List<CollegeInterface> colleges;

    public CollegeReaderDW() {
        colleges = new ArrayList<CollegeInterface>();
    }

    @Override
    public List<CollegeInterface> readCollegesFromFile(String filename) throws FileNotFoundException {
        Scanner file = new Scanner(new File(filename));
        while (file.hasNextLine()) {
            String line = file.nextLine();
            String[] parts = line.split(",");
            CollegeInterface college = new CollegeDW(parts[0], parts[1], Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[3]));
            colleges.add(college);
        }
        return colleges;
    }
}