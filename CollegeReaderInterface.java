import java.io.FileNotFoundException;
import java.util.List;

public interface CollegeReaderInterface {
   // public CollegeReaderInterface();
   public List<CollegeInterface> readCollegesFromFile(String filename) throws FileNotFoundException;
}
