import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

/**
 * This class tests the functionality of the methods within the
 * CollegeSearchFrontendFD class
 */
public class FrontendDeveloperTests {

	/**
	 * This method tests when user types an incorrect command
	 */
	@Test
	public void test1() {
		TextUITester uiTester = new TextUITester("A\nQ\n"); // sequence of user input
		try (Scanner scnr = new Scanner(System.in)) {
			RedBlackTreeFD<String> rbt = new RedBlackTreeFD<>();
			CollegeFD college = new CollegeFD("name", "state", 1, 0);
			CollegeSearchBackendFD backend = new CollegeSearchBackendFD(rbt, college);
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
					"Didn't recognize that command. Please type one of the letters presented within []s to identify the command you would like to choose.\n"
					+
					"Choose a command from the list below:\n" +
					"	[L]oad data from file\n" +
					"	Display [T]op most expensive\n" +
					"	Display [B]ottom least expensive\n" +
					"	Enter [P]rice Range\n" +
					"	[Q]uit\n" +
					"Choose command: \n" +
					"//////////////////////////////////////////////////////////////////////\n" +
					"Thank you for using the College Finder App.\n" +
					"//////////////////////////////////////////////////////////////////////\n",
					systemOutput);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method tests when user types the load file command
	 */
	@Test
	public void test2() {
		TextUITester uiTester = new TextUITester("L\tuition_cost.csv\nQ\n"); // sequence of user input
		try (Scanner scnr = new Scanner(System.in)) {
			RedBlackTreeFD<String> rbt = new RedBlackTreeFD<>();
			CollegeFD college = new CollegeFD("name", "state", 1, 0);
			CollegeSearchBackendFD backend = new CollegeSearchBackendFD(rbt, college);
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
					"Error: Could not find or load file: tuition_cost.csv" +
					"Choose a command from the list below:\n" +
					"	[L]oad data from file\n" +
					"	Display [T]op most expensive\n" +
					"	Display [B]ottom least expensive\n" +
					"	Enter [P]rice Range\n" +
					"	[Q]uit\n" +
					"Choose command: \n" +
					"//////////////////////////////////////////////////////////////////////\n" +
					"Thank you for using the College Finder App.\n" +
					"//////////////////////////////////////////////////////////////////////\n",
					systemOutput);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method tests when user types the print top, most expensive colleges
	 * command
	 */
	@Test
	public void test3() {
		TextUITester uiTester = new TextUITester("T\nWisconsin\n\nIn\n1\nQ\n"); // sequence of user input
		try (Scanner scnr = new Scanner(System.in)) {
			RedBlackTreeFD<String> rbt = new RedBlackTreeFD<>();
			CollegeFD college = new CollegeFD("name", "state", 1, 0);
			CollegeSearchBackendFD backend = new CollegeSearchBackendFD(rbt, college);
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
					"List of states to search for: \n" +
					"Enter full name of state, or press enter to search: \n" +
					"Enter [In]-state or [Out]-of-state: \n" +
					"Enter how many options you want to see: \n" +
					"Found results: \n" +
					"[1] University of Wisconsin-Madison\n" +
					"Choose a command from the list below:\n" +
					"	[L]oad data from file\n" +
					"	Display [T]op most expensive\n" +
					"	Display [B]ottom least expensive\n" +
					"	Enter [P]rice Range\n" +
					"	[Q]uit\n" +
					"Choose command: \n" +
					"//////////////////////////////////////////////////////////////////////\n" +
					"Thank you for using the College Finder App.\n" +
					"//////////////////////////////////////////////////////////////////////\n",
					systemOutput);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method tests when user types the print bottom, least expensive colleges
	 * command
	 */
	@Test
	public void test4() {
		TextUITester uiTester = new TextUITester("B\nWisconsin\n\nIn\n1\nQ\n"); // sequence of user input
		try (Scanner scnr = new Scanner(System.in)) {
			RedBlackTreeFD<String> rbt = new RedBlackTreeFD<>();
			CollegeFD college = new CollegeFD("name", "state", 1, 0);
			CollegeSearchBackendFD backend = new CollegeSearchBackendFD(rbt, college);
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
					"List of states to search for: \n" +
					"Enter full name of state, or press enter to search: \n" +
					"Enter [In]-state or [Out]-of-state: \n" +
					"Enter how many options you want to see: \n" +
					"Found results: \n" +
					"[1] University of Wisconsin-Madison\n" +
					"Choose a command from the list below:\n" +
					"	[L]oad data from file\n" +
					"	Display [T]op most expensive\n" +
					"	Display [B]ottom least expensive\n" +
					"	Enter [P]rice Range\n" +
					"	[Q]uit\n" +
					"Choose command: \n" +
					"//////////////////////////////////////////////////////////////////////\n" +
					"Thank you for using the College Finder App.\n" +
					"//////////////////////////////////////////////////////////////////////\n",
					systemOutput);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method tests when user types the print colleges w/in price range command
	 */
	@Test
	public void test5() {
		TextUITester uiTester = new TextUITester("P\nWisconsin\n\nIn\n100\n200\nQ\n"); // sequence of user input
		try (Scanner scnr = new Scanner(System.in)) {
			RedBlackTreeFD<String> rbt = new RedBlackTreeFD<>();
			CollegeFD college = new CollegeFD("name", "state", 1, 0);
			CollegeSearchBackendFD backend = new CollegeSearchBackendFD(rbt, college);
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
					"List of states to search for: \n" +
					"Enter full name of state, or press enter to search: \n" +
					"Enter [In]-state or [Out]-of-state: \n" +
					"Enter minimum price: \n" +
					"Enter maximum price: \n" +
					"Found results: \n" +
					"[1] University of Wisconsin-Madison\n" +
					"Choose a command from the list below:\n" +
					"	[L]oad data from file\n" +
					"	Display [T]op most expensive\n" +
					"	Display [B]ottom least expensive\n" +
					"	Enter [P]rice Range\n" +
					"	[Q]uit\n" +
					"Choose command: \n" +
					"//////////////////////////////////////////////////////////////////////\n" +
					"Thank you for using the College Finder App.\n" +
					"//////////////////////////////////////////////////////////////////////\n",
					systemOutput);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
