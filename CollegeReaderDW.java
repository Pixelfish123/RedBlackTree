import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CollegeReaderDW implements CollegeReaderInterface {

    @Override
    public List<CollegeInterface> readCollegesFromFile(String filename) throws FileNotFoundException {
        // will contain name, state, instate Total, outstate Total
        ArrayList<CollegeInterface> colleges = new ArrayList<CollegeInterface>();
        Scanner scan = new Scanner(new File(filename));
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split(",");
            CollegeInterface college = new CollegeDW(parts[0], parts[1], Integer.parseInt(parts[8]),
                    Integer.parseInt(parts[10]));
            colleges.add(college);
        }
        scan.close();
        return colleges;
    }
}