// --== CS400 Spring 2023 File Header Information ==--
// Name: Alan Liang
// Email: aliang26@wisc.edu
// Team: CI
// TA: Karan Grover
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * This class contains the tests for the DataWrangler class.
 */
public class DataWranglerTests {
    String currentDir = System.getProperty("user.dir");

    /**
     * This tests the getter methods and the constructor of the College method.
     */
    @Test
    void test1() {
        CollegeInterface college = new CollegeDW("University of Wisconsin-Madison", "Wisconsin", 10000, 20000);
        assertEquals(college.getName(), "University of Wisconsin-Madison");
        assertEquals(college.getState(), "Wisconsin");
        assertEquals(college.getInState(), 10000);
        assertEquals(college.getOutState(), 20000);
    }

    /**
     * Tests the functionallity of the readCollegeFromFile method with a valid file,
     * ensuring it is properly stored in an ArrayList using a smaller sample of the
     * real csv
     */
    @Test
    void test2() {
        List<CollegeInterface> collegeList = new ArrayList<CollegeInterface>();
        CollegeReaderInterface reader = new CollegeReaderDW();
        List<CollegeInterface> expectedColleges = new ArrayList<CollegeInterface>();
        expectedColleges.add(new CollegeDW("University of Wisconsin-Madison", "Wisconsin", 21669, 47919));
        expectedColleges.add(new CollegeDW("The King's College", "New York", 50450, 50450));
        expectedColleges.add(new CollegeDW("Trident University International", "California", 11550, 11550));
        expectedColleges.add(new CollegeDW("University of Charleston", "West Virginia", 40080, 40080));
        expectedColleges.add(new CollegeDW("University of Hawaii at Hilo", "Hawaii", 19126, 32086));

        try {
            collegeList = reader.readCollegesFromFile(
                    currentDir + "/test.csv"); // fails on my computer but works in PuTTy so...
        } catch (FileNotFoundException fne) {
            fail("this exists but exception was still thrown");
        }
        for (int i = 0; i < expectedColleges.size(); i++) {
            assertEquals(expectedColleges.get(i).getName(), collegeList.get(i).getName());
            assertEquals(expectedColleges.get(i).getInState(), collegeList.get(i).getInState());
            assertEquals(expectedColleges.get(i).getOutState(), collegeList.get(i).getOutState());
            assertEquals(expectedColleges.get(i).getState(), collegeList.get(i).getState());
        }
    }

    /**
     * Tests the functionallity of the readCollegeFromFile method with an invalid
     * file name
     * Expects a FileNotFoundExcpetion to be thrown
     */
    @Test
    void test3() {
        List<CollegeInterface> collegeList = new ArrayList<CollegeInterface>();
        CollegeReaderInterface reader = new CollegeReaderDW();
        try {
            collegeList = reader.readCollegesFromFile("fakefile.csv"); // expect exception
        } catch (FileNotFoundException fne) {

        }
    }

    /**
     * Tests to ensure that the reader skips blank lines
     */
    @Test
    void test4() {
        List<CollegeInterface> collegeList = new ArrayList<CollegeInterface>();
        CollegeReaderInterface reader = new CollegeReaderDW();
        List<CollegeInterface> expectedColleges = new ArrayList<CollegeInterface>();
        expectedColleges.add(new CollegeDW("University of Wisconsin-Madison", "Wisconsin", 21669, 47919));
        expectedColleges.add(new CollegeDW("University of Hawaii at Hilo", "Hawaii", 19126, 32086));

        try {
            collegeList = reader.readCollegesFromFile(currentDir + "/testBlank.csv"); // fails on my computer but works
                                                                                      // in PuTTy so...
        } catch (FileNotFoundException fne) {
            fail("this exists but exception was still thrown");
        }
        for (int i = 0; i < expectedColleges.size(); i++) {
            assertEquals(expectedColleges.get(i).getName(), collegeList.get(i).getName());
            assertEquals(expectedColleges.get(i).getInState(), collegeList.get(i).getInState());
            assertEquals(expectedColleges.get(i).getOutState(), collegeList.get(i).getOutState());
            assertEquals(expectedColleges.get(i).getState(), collegeList.get(i).getState());
        }
        assertEquals(2, collegeList.size()); // ensures theres only two entries
    }

    /**
     * This tests the toString method of the College class
     */
    @Test
    void test5() {
        CollegeInterface college = new CollegeDW("University of Wisconsin-Madison", "Wisconsin", 10000, 20000);
        assertEquals(college.toString(),
                "University of Wisconsin-Madison\nState: Wisconsin\nInstate cost: $10000\nOutstate cost: $20000");
    }
}
