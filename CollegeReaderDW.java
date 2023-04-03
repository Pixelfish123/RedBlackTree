
// --== CS400 Spring 2023 File Header Information ==--
// Name: Alan Liang
// Email: aliang26@wisc.edu
// Team: CI
// TA: Karan Grover
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class reads in a csv file and creates a list of CollegeInterface
 * objects.
 */
public class CollegeReaderDW implements CollegeReaderInterface {

    @Override
    public List<CollegeInterface> readCollegesFromFile(String filename) throws FileNotFoundException {
        // will contain name, state, instate Total, outstate Total
        ArrayList<CollegeInterface> colleges = new ArrayList<CollegeInterface>();
        Scanner scan = new Scanner(new File(filename));
        scan.next(); // skip the header line
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 10) { // only make college if the line is valid
                CollegeInterface college = new CollegeDW(parts[0], parts[1], Integer.parseInt(parts[7]),
                        Integer.parseInt(parts[9]));
                colleges.add(college);
            }
        }
        scan.close();
        return colleges;
    }
}