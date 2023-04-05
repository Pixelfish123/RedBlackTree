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
import java.util.Scanner;

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
                    currentDir + "/test.csv");
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
            collegeList = reader.readCollegesFromFile(currentDir + "/testBlank.csv");
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

    @Test
    /**
     * INTEGRATION TEST: Tests the functionaility of the AE's EnhancedRedBlackTree
     * of CollegeDW objects
     */
    void test6() {
        EnhancedRedBlackTreeAE<CollegeInterface> tree = new EnhancedRedBlackTreeAE<CollegeInterface>();

        CollegeDW college1 = new CollegeDW("University of Wisconsin-Madison", "Wisconsin", 10000, 20000);
        CollegeDW college2 = new CollegeDW("University of Wisconsin-Milwaukee", "Wisconsin", 5000, 10000);
        tree.insert(college1);
        tree.insert(college2);
        assertEquals(tree.size(), 2);
        assertEquals(tree.getLast(2)[0].getName(), "University of Wisconsin-Madison");
        assertEquals(tree.getLast(2)[1].getName(), "University of Wisconsin-Milwaukee");

    }

    @Test
    /**
     * INTEGRATION TEST: Tests FD's CollegeSearchFrontend commandLoop with DW's
     * readCollegesFromFile
     */
    void test7() {
        TextUITester uiTester = new TextUITester("L\ntest.csv\nB\n1\nQ\n"); // sequence of user input
        try (Scanner scnr = new Scanner(System.in)) {
            EnhancedRedBlackTreeAE<CollegeInterface> rbt = new EnhancedRedBlackTreeAE<CollegeInterface>();
            CollegeReaderInterface reader = new CollegeReaderDW();
            CollegeSearchBackendBD backend = new CollegeSearchBackendBD(rbt, reader);
            CollegeSearchFrontendFD frontend = new CollegeSearchFrontendFD(scnr, backend);
            frontend.runCommandLoop();
            String systemOutput = uiTester.checkOutput();
            assertEquals("//////////////////////////////////////////////////////////////////////\n" +
                    "Welcome to the College Finder App.\n" +
                    "//////////////////////////////////////////////////////////////////////\n" +
                    "Choose a command from the list below:\n" +
                    "	[L]oad data from file\n" +
                    "	Display [T]op most expensive\n" +
                    "	Display [B]ottom least expensive\n" +
                    "	Enter [P]rice Range\n" +
                    "	[Q]uit\n" +
                    "Choose command: \n" +
                    "Enter the name of the file to load: \n" +
                    "Choose a command from the list below:\n" +
                    "	[L]oad data from file\n" +
                    "	Display [T]op most expensive\n" +
                    "	Display [B]ottom least expensive\n" +
                    "	Enter [P]rice Range\n" +
                    "	[Q]uit\n" +
                    "Choose command: \n" +
                    "Enter how many options you want to see: \n" +
                    "Choose a command from the list below:\n" +
                    "	[L]oad data from file\n" +
                    "	Display [T]op most expensive\n" +
                    "	Display [B]ottom least expensive\n" +
                    "	Enter [P]rice Range\n" +
                    "	[Q]uit\n" +
                    "Choose command: \n" +
                    "[1] Trident University International\n" +
                    "//////////////////////////////////////////////////////////////////////\n" +
                    "Thank you for using the College Finder App.\n" +
                    "//////////////////////////////////////////////////////////////////////\n",
                    systemOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * INTEGRATION TEST
     */
    void test8() {

    }

    @Test
    /**
     * INTEGRATION TEST
     */
    void test9() {

    }

}
